// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.core.ui.editor.update.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.eclipse.gef.commands.Command;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.PluginChecker;
import org.talend.core.model.metadata.IEbcdicConstant;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataTool;
import org.talend.core.model.metadata.QueryUtil;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.Query;
import org.talend.core.model.metadata.builder.connection.SAPFunctionUnit;
import org.talend.core.model.metadata.builder.connection.impl.XmlFileConnectionImpl;
import org.talend.core.model.metadata.designerproperties.RepositoryToComponentProperty;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INodeConnector;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.DatabaseConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.core.model.update.EUpdateResult;
import org.talend.core.model.update.UpdateResult;
import org.talend.core.model.update.UpdatesConstants;
import org.talend.core.model.utils.TalendTextUtils;
import org.talend.core.ui.ICDCProviderService;
import org.talend.core.ui.IEBCDICProviderService;
import org.talend.designer.core.model.components.EParameterName;
import org.talend.designer.core.model.components.EmfComponent;
import org.talend.designer.core.ui.editor.cmd.ChangeMetadataCommand;
import org.talend.designer.core.ui.editor.cmd.PropertyChangeCommand;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.designer.core.ui.editor.update.UpdateManagerUtils;
import org.talend.designer.core.utils.SAPParametersUtils;
import org.talend.repository.UpdateRepositoryUtils;

/**
 * ggu class global comment. Detailled comment
 * 
 */
public class UpdateNodeParameterCommand extends Command {

    private UpdateResult result;

    public UpdateNodeParameterCommand(UpdateResult result) {
        super();
        this.result = result;
    }

    @Override
    public void execute() {
        if (result == null) {
            return;
        }
        Object updateObject = result.getUpdateObject();
        if (updateObject == null) {
            return;
        }
        switch (result.getUpdateType()) {
        case NODE_PROPERTY:
            updateProperty();
            break;
        case NODE_SCHEMA:
            updateSchema();
            break;
        case NODE_QUERY:
            updateQuery();
            break;
        case NODE_SAP_FUNCTION:
            updateSAPParameters();
            break;
        default:
            return;
        }

        if (updateObject instanceof Node) {
            Node node = (Node) updateObject;
            if (node.getProcess() instanceof IProcess2) {
                PropertyChangeCommand pcc = new PropertyChangeCommand(node, EParameterName.UPDATE_COMPONENTS.getName(),
                        Boolean.TRUE);
                ((IProcess2) node.getProcess()).getCommandStack().execute(pcc);
            }
        }
    }

    /**
     * DOC YeXiaowei Comment method "updateSAPParameters".
     */
    private void updateSAPParameters() {
        Object updateObject = result.getUpdateObject();
        if (updateObject == null) {
            return;
        }
        boolean builtin = true;
        if (updateObject instanceof Node) {
            Node node = (Node) updateObject;
            if (result.getResultType() == EUpdateResult.UPDATE) {
                if (result.isChecked()) {
                    if (result.getParameter() instanceof SAPFunctionUnit) {
                        SAPFunctionUnit unit = (SAPFunctionUnit) result.getParameter();
                        for (IElementParameter param : node.getElementParameters()) {
                            SAPParametersUtils.retrieveSAPParams(node, unit.getConnection(), param, unit.getName());
                        }
                        builtin = false;
                    }
                }
            }
            if (builtin) { // built-in
                node.setPropertyValue(EParameterName.SCHEMA_TYPE.getName(), EmfComponent.BUILTIN);
                for (IElementParameter param : node.getElementParameters()) {
                    SAPParametersUtils.setNoRepositoryParams(param);
                }
            }
        }

    }

    @SuppressWarnings("unchecked")//$NON-NLS-1$
    private void updateProperty() {
        Object updateObject = result.getUpdateObject();
        if (updateObject == null) {
            return;
        }
        if (updateObject instanceof Node) { // opened job
            Node node = (Node) updateObject;

            boolean update = false;
            // added by wzhang for bug 9302
            boolean isXsdPath = false;
            Object parameter = result.getParameter();
            if (parameter instanceof XmlFileConnectionImpl) {
                String filePath = ((XmlFileConnectionImpl) parameter).getXmlFilePath();
                if (filePath != null) {
                    if (filePath.toLowerCase().endsWith(".xsd")) { //$NON-NLS-1$ 
                        isXsdPath = true;
                    }
                }
            }

            if (result.getResultType() == EUpdateResult.UPDATE) {
                // upgrade from repository
                if (result.isChecked()) {
                    for (IElementParameter param : node.getElementParameters()) {
                        String repositoryValue = param.getRepositoryValue();
                        if (param.isShow(node.getElementParameters()) && (repositoryValue != null)) {
                            if (param.getName().equals(EParameterName.PROPERTY_TYPE.getName())
                                    || param.getField() == EParameterFieldType.MEMO_SQL) {
                                continue;
                            }
                            if (param.getField().equals(EParameterFieldType.FILE) && isXsdPath) {
                                continue;
                            }
                            Object objectValue = RepositoryToComponentProperty.getValue(
                                    (org.talend.core.model.metadata.builder.connection.Connection) result.getParameter(),
                                    repositoryValue, node.getMetadataList().get(0));
                            if (param.getName().equals(EParameterName.CDC_TYPE_MODE.getName())) {
                                //
                                String propertyValue = (String) node.getPropertyValue(EParameterName.REPOSITORY_PROPERTY_TYPE
                                        .getName());
                                Item item = null;
                                IRepositoryObject lastVersion = UpdateRepositoryUtils.getRepositoryObjectById(propertyValue);
                                if (lastVersion != null) {
                                    item = lastVersion.getProperty().getItem();
                                }
                                if (item != null && PluginChecker.isCDCPluginLoaded()) {
                                    ICDCProviderService service = (ICDCProviderService) GlobalServiceRegister.getDefault()
                                            .getService(ICDCProviderService.class);
                                    if (service != null) {
                                        try {
                                            List<IRepositoryObject> all;
                                            all = CorePlugin.getDefault().getProxyRepositoryFactory().getAll(
                                                    ERepositoryObjectType.METADATA_CONNECTIONS);
                                            for (IRepositoryObject obj : all) {
                                                Item tempItem = obj.getProperty().getItem();
                                                if (tempItem instanceof DatabaseConnectionItem) {
                                                    String cdcLinkId = service
                                                            .getCDCConnectionLinkId((DatabaseConnectionItem) tempItem);
                                                    if (cdcLinkId != null && item.getProperty().getId().equals(cdcLinkId)) {
                                                        objectValue = RepositoryToComponentProperty.getValue(
                                                                ((DatabaseConnectionItem) tempItem).getConnection(),
                                                                repositoryValue, node.getMetadataList().get(0));
                                                    }
                                                }
                                            }
                                        } catch (PersistenceException e) {
                                            ExceptionHandler.process(e);
                                        }
                                    }
                                }
                            }
                            if (objectValue != null) {
                                if (param.getField().equals(EParameterFieldType.CLOSED_LIST)
                                        && repositoryValue.equals(UpdatesConstants.TYPE)) {
                                    boolean found = false;
                                    String[] items = param.getListRepositoryItems();
                                    for (int i = 0; (i < items.length) && (!found); i++) {
                                        if (objectValue.equals(items[i])) {
                                            found = true;
                                            node.setPropertyValue(param.getName(), param.getListItemsValue()[i]);
                                        }
                                    }
                                } else {
                                    // update tFileInputExcel job
                                    if (param.getField().equals(EParameterFieldType.TABLE)) {
                                        if (param.getName().equals("SHEETLIST") && objectValue instanceof List) {
                                            List<Map<String, Object>> paramMaps = (List<Map<String, Object>>) param.getValue();
                                            if (paramMaps == null) {
                                                paramMaps = new ArrayList<Map<String, Object>>();
                                                node.setPropertyValue(param.getName(), paramMaps);
                                            } else {
                                                // hywang add for 9537
                                                List<Map<String, Object>> objectValueList = (List<Map<String, Object>>) objectValue;

                                                if (paramMaps.size() < objectValueList.size()) {
                                                    paramMaps.clear();
                                                    for (int i = 0; i < objectValueList.size(); i++) {
                                                        Map<String, Object> map = objectValueList.get(i);
                                                        paramMaps.add(map);
                                                    }
                                                } else {
                                                    String value = null;
                                                    List<String> repNames = new ArrayList<String>();
                                                    for (int i = 0; i < objectValueList.size(); i++) {
                                                        repNames.add(objectValueList.get(i).get("SHEETNAME").toString());
                                                    }
                                                    for (int j = 0; j < paramMaps.size(); j++) {
                                                        Map<String, Object> map = paramMaps.get(j);
                                                        value = map.get("SHEETNAME").toString();
                                                        if (!repNames.contains(value)) {
                                                            paramMaps.remove(j);
                                                        }
                                                    }
                                                }
                                            }
                                            //
                                        }
                                    } else {
                                        node.setPropertyValue(param.getName(), objectValue);
                                    }
                                }
                            } else if (param.getField().equals(EParameterFieldType.TABLE)
                                    && UpdatesConstants.XML_MAPPING.equals(repositoryValue)) {
                                RepositoryToComponentProperty.getTableXMLMappingValue(
                                        (org.talend.core.model.metadata.builder.connection.Connection) result.getParameter(),
                                        (List<Map<String, Object>>) param.getValue(), node);
                            } else if (param.getField().equals(EParameterFieldType.TABLE) && param.getName().equals("PARAMS")) {
                                objectValue = RepositoryToComponentProperty.getValue(
                                        (org.talend.core.model.metadata.builder.connection.Connection) result.getParameter(),
                                        "PARAMS", node.getMetadataList().get(0));
                                List<Map<String, Object>> paramMaps = (List<Map<String, Object>>) param.getValue();
                                if (paramMaps == null) {
                                    paramMaps = new ArrayList<Map<String, Object>>();
                                } else {
                                    paramMaps.clear();
                                }
                                if (objectValue != null) {
                                    List<String> objectValueList = (List<String>) objectValue;
                                    for (int i = 0; i < objectValueList.size(); i++) {
                                        Map<String, Object> map = new HashedMap();
                                        map.put("VALUE", TalendTextUtils.addQuotes((String) objectValueList.get(i)));
                                        paramMaps.add(map);
                                    }
                                }
                            }
                            param.setRepositoryValueUsed(true);
                            param.setReadOnly(true);
                            update = true;
                        }
                    }
                }
            }
            if (!update) { // bult-in
                node.setPropertyValue(EParameterName.PROPERTY_TYPE.getName(), EmfComponent.BUILTIN);
                for (IElementParameter param : node.getElementParameters()) {
                    String repositoryValue = param.getRepositoryValue();
                    if (param.isShow(node.getElementParameters()) && (repositoryValue != null)) {
                        if (param.getName().equals(EParameterName.PROPERTY_TYPE.getName())
                                || param.getField() == EParameterFieldType.MEMO_SQL) {
                            continue;
                        }
                        param.setRepositoryValueUsed(false);
                        param.setReadOnly(false);
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")//$NON-NLS-1$
    private void updateSchema() {
        Object updateObject = result.getUpdateObject();
        if (updateObject == null) {
            return;
        }
        if (updateObject instanceof Node) { // opened job
            Node node = (Node) updateObject;

            boolean builtIn = true;

            if (result.getResultType() == EUpdateResult.UPDATE) {
                if (result.isChecked()) {
                    if (result.getParameter() instanceof List) {
                        // for ebcdic
                        if (PluginChecker.isEBCDICPluginLoaded()) {
                            IEBCDICProviderService service = (IEBCDICProviderService) GlobalServiceRegister.getDefault()
                                    .getService(IEBCDICProviderService.class);
                            if (service != null) {
                                if (service.isEbcdicNode(node)) {
                                    List<Object> parameter = (List<Object>) result.getParameter();
                                    if (parameter.size() >= 2) {
                                        IMetadataTable newTable = (IMetadataTable) parameter.get(0);
                                        String schemaName = (String) parameter.get(1);
                                        IMetadataTable metadataTable = MetadataTool.getMetadataTableFromNode(node, schemaName);
                                        if (metadataTable != null) {
                                            MetadataTool.copyTable(newTable, metadataTable);
                                        }
                                        syncSchemaForEBCDIC(node, metadataTable);
                                    }
                                    return;
                                }
                            }
                        }
                    } else if (result.getParameter() instanceof IMetadataTable) {
                        IMetadataTable newTable = (IMetadataTable) result.getParameter();
                        // node.getMetadataFromConnector(newTable.getAttachedConnector()).setListColumns(newTable.
                        // getListColumns());
                        if (newTable != null) {

                            for (INodeConnector nodeConnector : node.getListConnector()) {
                                if (nodeConnector.getBaseSchema().equals(newTable.getAttachedConnector())) {
                                    IElementParameter param = node.getElementParameterFromField(EParameterFieldType.SCHEMA_TYPE);
                                    if (param != null) {
                                        ChangeMetadataCommand cmd = new ChangeMetadataCommand(node, param, null, newTable);
                                        // wzhang added to fix 9251. get the current connection.
                                        String propertyValue = (String) node
                                                .getPropertyValue(EParameterName.REPOSITORY_PROPERTY_TYPE.getName());
                                        IRepositoryObject lastVersion = UpdateRepositoryUtils
                                                .getRepositoryObjectById(propertyValue);
                                        Connection repositoryConn = null;
                                        if (lastVersion != null) {
                                            final Item item = lastVersion.getProperty().getItem();
                                            if (item != null && item instanceof ConnectionItem) {
                                                repositoryConn = ((ConnectionItem) item).getConnection();
                                            }
                                        }
                                        cmd.setConnection(repositoryConn);

                                        cmd.setRepositoryMode(true);
                                        cmd.execute(true);
                                    } else {
                                        MetadataTool.copyTable(newTable, node.getMetadataFromConnector(nodeConnector.getName()));
                                    }
                                }
                            }
                            builtIn = false;
                        }
                    }
                }
            } else if (result.getResultType() == EUpdateResult.RENAME) {
                List<Object> parameter = (List<Object>) result.getParameter();
                if (parameter.size() >= 3) {
                    IMetadataTable newTable = (IMetadataTable) parameter.get(0);
                    String oldSourceId = (String) parameter.get(1);
                    String newSourceId = (String) parameter.get(2);
                    // for ebcdic
                    if (PluginChecker.isEBCDICPluginLoaded()) {
                        IEBCDICProviderService service = (IEBCDICProviderService) GlobalServiceRegister.getDefault().getService(
                                IEBCDICProviderService.class);
                        if (service != null) {
                            if (service.isEbcdicNode(node)) {
                                String[] sourceIdAndChildName = UpdateManagerUtils.getSourceIdAndChildName(oldSourceId);
                                final String oldSchemaName = sourceIdAndChildName[1];

                                sourceIdAndChildName = UpdateManagerUtils.getSourceIdAndChildName(newSourceId);
                                final String newSchemaName = sourceIdAndChildName[1];
                                Map<String, Object> lineValue = (Map<String, Object>) parameter.get(3);
                                if (lineValue != null) {
                                    IMetadataTable metadataTable = MetadataTool.getMetadataTableFromNode(node, oldSchemaName);
                                    Object schemaName = lineValue.get(IEbcdicConstant.FIELD_SCHEMA);
                                    if (metadataTable != null && schemaName != null) {
                                        lineValue.put(IEbcdicConstant.FIELD_SCHEMA, newSchemaName);

                                        MetadataTool.copyTable(newTable, metadataTable);
                                        syncSchemaForEBCDIC(node, metadataTable);
                                        metadataTable.setLabel(newSchemaName);

                                    }
                                }
                                return;
                            }
                        }
                    }
                    String schemaParamName = UpdatesConstants.SCHEMA + UpdatesConstants.COLON
                            + EParameterName.REPOSITORY_SCHEMA_TYPE.getName();
                    IElementParameter repositoryParam = node.getElementParameter(schemaParamName);
                    if (repositoryParam != null && oldSourceId.equals(repositoryParam.getValue())) {
                        node.setPropertyValue(schemaParamName, newSourceId);

                        if (newTable != null) {
                            for (INodeConnector nodeConnector : node.getListConnector()) {
                                if (nodeConnector.getBaseSchema().equals(newTable.getAttachedConnector())) {
                                    MetadataTool.copyTable(newTable, node.getMetadataFromConnector(nodeConnector.getName()));
                                }
                            }
                        }
                        builtIn = false;
                    }
                }
            }
            if (builtIn) { // built-in
                // for ebcdic
                if (PluginChecker.isEBCDICPluginLoaded()) {
                    IEBCDICProviderService service = (IEBCDICProviderService) GlobalServiceRegister.getDefault().getService(
                            IEBCDICProviderService.class);
                    if (service != null) {
                        if (service.isEbcdicNode(node)) {
                            Object parameter = result.getParameter();
                            if (parameter instanceof Map) {
                                Map<String, Object> lineValue = (Map<String, Object>) parameter;
                                lineValue.remove(IEbcdicConstant.FIELD_SCHEMA + IEbcdicConstant.REF_TYPE);
                            }
                            return;
                        }
                    }
                }
                node.setPropertyValue(EParameterName.SCHEMA_TYPE.getName(), EmfComponent.BUILTIN);
                for (IElementParameter param : node.getElementParameters()) {
                    SAPParametersUtils.setNoRepositoryParams(param);
                }
            }
        }
    }

    /**
     * nrousseau Comment method "synchSchemaForEBCDIC".
     */
    private void syncSchemaForEBCDIC(Node node, IMetadataTable metadataTable) {
        for (IConnection conn : node.getOutgoingConnections()) {
            if (conn.getLineStyle() == EConnectionType.FLOW_MAIN
                    && metadataTable.getTableName().equals(conn.getMetadataTable().getTableName())) {
                Node target = (Node) conn.getTarget();
                IElementParameter schemaTypeParam = target.getElementParameterFromField(EParameterFieldType.SCHEMA_TYPE);
                if (schemaTypeParam != null) {
                    ChangeMetadataCommand cmd = new ChangeMetadataCommand(target, schemaTypeParam, null, metadataTable);
                    cmd.setRepositoryMode(true);
                    cmd.execute(true);
                }
            }
        }
    }

    private void updateQuery() {
        Object updateObject = result.getUpdateObject();
        if (updateObject == null) {
            return;
        }
        if (updateObject instanceof Node) { // opened job
            Node node = (Node) updateObject;

            boolean update = false;
            if (result.getResultType() == EUpdateResult.UPDATE) {
                if (result.isChecked()) {
                    Query query = (Query) result.getParameter();
                    if (query != null) {
                        for (IElementParameter param : node.getElementParameters()) {
                            if (param.getField() == EParameterFieldType.MEMO_SQL
                                    && UpdatesConstants.QUERY.equals(param.getName())) {
                                // modefied by hyWang
                                String value = query.getValue();
                                if (!query.isContextMode()) {
                                    value = QueryUtil.checkAndAddQuotes(value);
                                }
                                param.setValue(value);
                                param.setRepositoryValueUsed(true);
                                param.setReadOnly(true);
                                update = true;
                            }
                        }
                    }
                }
            }
            if (!update) {
                node.setPropertyValue(EParameterName.QUERYSTORE_TYPE.getName(), EmfComponent.BUILTIN);
                IElementParameter sqlParam = node.getElementParameterFromField(EParameterFieldType.MEMO_SQL);
                if (sqlParam != null) {
                    sqlParam.setRepositoryValueUsed(false);
                    sqlParam.setReadOnly(false);
                }
            }
        }
    }

}
