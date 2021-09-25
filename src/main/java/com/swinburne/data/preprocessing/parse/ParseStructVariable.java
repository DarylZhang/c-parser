package com.swinburne.data.preprocessing.parse;

import com.swinburne.data.preprocessing.constant.CommonConstant;
import com.swinburne.data.preprocessing.ctype.Record;
import com.swinburne.data.preprocessing.ctype.Struct;
import com.swinburne.data.preprocessing.ctype.StructVariable;
import com.swinburne.data.preprocessing.ctype.TVariable;
import com.swinburne.data.preprocessing.treenode.TreeNode;
import com.swinburne.data.preprocessing.treenode.TreeUtils;

import java.util.*;

public class ParseStructVariable {

    private Map<String, Struct> structMap = null;

    public ParseStructVariable() {
    }

    public StringBuffer parseStructVariable(TreeNode root, Map<String, Record> variableMap) throws Exception {

        List<TreeNode> paramsNodes = new ArrayList<>();
        TreeUtils.bfsSearchByCtxNameWithParent(root, "primaryExpression", "postfixExpression", paramsNodes);

        if (paramsNodes.size() > 0) {
            this.structMap = new HashMap<>();
        } else {
            return new StringBuffer();
        }

        Iterator<Map.Entry<String, Record>> it = variableMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Record> pair = it.next();
            TVariable tVariable = (TVariable) pair.getValue();

            if ("struct".equals(tVariable.getTypename())) {
                if (this.structMap.containsKey(tVariable.getName())) {
                    Struct existingStruct = this.structMap.get(tVariable.getName());

                    boolean isRefExisting = false;
                    if (existingStruct.getRefs() == null || existingStruct.getRefs().isEmpty()) {
                        isRefExisting = false;
                        existingStruct.setRefs(new Vector<String>());
                    } else {
                        isRefExisting = existingStruct.getRefs().stream().anyMatch(v -> v.equals(tVariable.getRef()));
                    }

                    if (!isRefExisting) {
                        existingStruct.getRefs().add(tVariable.getRef());
                    }
                } else {
                    if ("void".equals(tVariable.getName()))
                        continue;

                    Struct struct = new Struct(tVariable.getName());
                    struct.setRef(tVariable.getRef());
                    struct.setIsPointer(tVariable.getIsPointer());
                    struct.setIsArray(tVariable.getIsArray());
                    struct.setTypedef(tVariable.isTypedef());
                    Vector<String> structRefs = new Vector<>();
                    structRefs.add(tVariable.getRef());
                    struct.setRefs(structRefs);

                    this.structMap.put(struct.getName(), struct);
                }
            }
        }

//        Map<String, Struct> newStructMap = new HashMap<>();
        for (TreeNode treeNode : paramsNodes) {

            if ((treeNode.getParent().getCtxValue().contains("->") && treeNode.getCtxValue().equals(treeNode.getParent().getCtxValue().substring(0, treeNode.getParent().getCtxValue().indexOf("->"))) )
                    || ( treeNode.getParent().getCtxValue().contains(".") && treeNode.getCtxValue().equals(treeNode.getParent().getCtxValue().substring(0, treeNode.getParent().getCtxValue().indexOf("."))) )
                    ) {//struct，need to be handled

                if (treeNode.getParent().getCtxValue().contains(treeNode.getCtxValue()  + "(")) {
                    continue;
                }

                //search for existing struct
                Iterator<Map.Entry<String, Struct>> structIter = this.structMap.entrySet().iterator();
                Struct newStruct = null;

                boolean isExisting = false;
                while (structIter.hasNext()) {
                    Map.Entry<String, Struct> pair = structIter.next();
                    Struct struct = pair.getValue();

//                    if (struct.getRef().equals(treeNode.getCtxValue()) && !struct.getRef().equals(treeNode.getParent().getCtxValue())) {
                    if (struct.getRefs().stream().anyMatch(v -> v.equals(treeNode.getCtxValue()))) {
                        this.buildStructVariable(struct, treeNode.getParent().getCtxValue());
                        isExisting = true;
                        break;
                    }
                }

                if (!isExisting) {
                    newStruct = new Struct(treeNode.getCtxValue());
                    newStruct.setRef(treeNode.getCtxValue());
                    newStruct.setRefs(new Vector<String>(Arrays.asList(treeNode.getCtxValue())));

                    TVariable tVariable = new TVariable(newStruct.getName());
                    tVariable.setTypename("struct");

                    if (treeNode.getParent().getCtxValue().contains("->")) {
                        newStruct.setIsPointer(true);
                        tVariable.setIsPointer(true);
                    }

                    variableMap.put(newStruct.getName(), tVariable);

                    this.buildStructVariable(newStruct, treeNode.getParent().getCtxValue());
                }

                if (newStruct != null)
                    this.structMap.put(newStruct.getName(), newStruct);
            }
        }

        return buildStructStatement(this.structMap);
    }

    private void buildStructVariable(Struct targetStruct, String targetCtxText) throws Exception {

        String structRef = targetStruct.getRef();

        Vector<Record> targetStructChildren = null;
        if (targetStruct.getStructChildren() != null) {
            targetStructChildren = targetStruct.getStructChildren();
        } else {
            targetStructChildren = new Vector<>();
        }

        String ctxText = targetCtxText.substring(targetCtxText.indexOf(structRef) + structRef.length());

        List<Integer> symoblLocations = new ArrayList<>();

        for(int i = 0; i < ctxText.length(); i++) {
            char c = ctxText.charAt(i);

            if (c == '>' || c == '.') {
                symoblLocations.add(i);
            }
        }

        Struct parentStruct = null;

        for (int i = 0; i < symoblLocations.size(); i++) {
            Integer symoblLocation = symoblLocations.get(i);
            Integer nextSymoblLocation = i + 1 == symoblLocations.size() ? symoblLocations.get(i) : symoblLocations.get(i + 1);

            char nextSymobl = ctxText.charAt(nextSymoblLocation);

            boolean isArray = false;
            String variable = null;
            if (symoblLocation.intValue() == nextSymoblLocation.intValue()) { //只有一个
                variable = ctxText.substring(symoblLocation + 1);
                String typeName = "int";

                if (variable.contains("name") || variable.contains("message")
                        || variable.contains("address") || variable.contains("header")) {
                    typeName = "char";
                }

                if (variable.indexOf("[") > 0) {
                    variable = variable.substring(0, variable.indexOf("["));
                    isArray = true;
                }
                String finalVariable = variable;
                StructVariable structVariable = new StructVariable(variable, typeName);
                structVariable.setIsArray(isArray);

                if (parentStruct != null) {
                    Vector<Record> structChildren = new Vector<>();
                    structChildren.add(structVariable);

                    if (parentStruct.getStructChildren() == null) {
                        parentStruct.setStructChildren(structChildren);
                    } else {
                        boolean found = parentStruct.getStructChildren().stream().anyMatch(v -> v.getName().equals(finalVariable));
                        if (!found)
                            parentStruct.setStructChildren(structChildren);
                    }
                } else {
                    boolean found = targetStructChildren.stream().anyMatch(v -> v.getName().equals(finalVariable));
                    if (!found)
                        targetStructChildren.add(structVariable);
                }
            } else {
                if (ctxText.charAt(nextSymoblLocation) == '>') {
                    variable = ctxText.substring(symoblLocation + 1, nextSymoblLocation - 1);
                } else {
                    variable = ctxText.substring(symoblLocation + 1, nextSymoblLocation);
                }

                if (variable.indexOf("[") > 0) {
                    variable = variable.substring(0, variable.indexOf("["));
                    isArray = true;
                }
                String finalVariable = variable;

                Struct childStruct = new Struct(variable);
                childStruct.setIsArray(isArray);
                childStruct.setRef(variable);

                if (nextSymobl == '>') {
                    childStruct.setIsPointer(true);
                }

                if (parentStruct != null) {
                    Vector<Record> structChildren = new Vector<>();
                    structChildren.add(childStruct);

                    if (parentStruct.getStructChildren() == null) {
                        parentStruct.setStructChildren(structChildren);
                    } else {
                        boolean found = parentStruct.getStructChildren().stream().anyMatch(v -> v.getName().equals(finalVariable));
                        if (!found)
                            parentStruct.setStructChildren(structChildren);
                    }
                } else {
                    boolean found = targetStructChildren.stream().anyMatch(v -> v.getName().equals(finalVariable));
                    if (!found) {
                        targetStructChildren.add(childStruct);
                    } else {
                        Record existingVariable = targetStructChildren.stream().filter(v -> v.getName().equals(finalVariable)).findFirst().get();

                        if (!existingVariable.getTypename().equals(childStruct.getTypename())) {
                            for (int j = 0; j < targetStructChildren.size(); j++) {
                                if (targetStructChildren.get(j).getName().equals(existingVariable.getName())) {
                                    targetStructChildren.remove(j);
                                    break;
                                }
                            }
                            targetStructChildren.add(childStruct);
                        }
                    }
                }

                parentStruct = childStruct;

            }

            targetStruct.setStructChildren(targetStructChildren);
        }
    }

    public StringBuffer buildStructStatement(Map<String, Struct> structMap) {

        StringBuffer sb = new StringBuffer();

        if (this.structMap == null || this.structMap.isEmpty())
            return sb;

        Iterator<Map.Entry<String, Struct>> it = this.structMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Struct> pair = it.next();
            String key = pair.getKey();
            Struct struct = pair.getValue();

            sb = this.buildStruct(struct, sb, 1);
        }

        return sb;
    }

    private StringBuffer buildStruct(Struct struct, StringBuffer sb, int nextLevel) {

        for (int i = 1; i < nextLevel; i++) {
            sb.append("\t");
        }

        if (struct.isTypedef()) {
            sb.append("typedef").append(CommonConstant.BLANK);
        }

        sb.append("struct");

        if (nextLevel == 1) {
            if (!struct.isTypedef()) {
                sb.append(CommonConstant.BLANK);
                sb.append(struct.getName());
            }
        }
        sb.append(" {").append(CommonConstant.NEW_LINE);

        if (struct.getStructChildren() != null) {
            for (Record record : struct.getStructChildren()) {
                if (!record.getTypename().equals("struct")) {
                    for (int i = 1; i < nextLevel + 1; i++) {
                        sb.append("\t");
                    }
                    sb.append(record.getTypename()).append(CommonConstant.BLANK).append(record.getName());
                    if (record.getIsArray()) {
                        sb.append("[10]");
                    }
                    sb.append(";").append(CommonConstant.NEW_LINE);
                } else {
                    Struct childStruct = (Struct) record;
                    this.buildStruct(childStruct, sb, nextLevel + 1);
                }
            }
        }

        for (int i = 1; i < nextLevel; i++) {
            sb.append("\t");
        }
        sb.append("}");
        if (nextLevel > 1) {
            sb.append(CommonConstant.BLANK);

            if (struct.getIsPointer()) {
                sb.append("*");
            }
            sb.append(struct.getRef());

            if (struct.getIsArray()) {
                sb.append("[10]");
            }
        }

        if (nextLevel == 1) {
            if (struct.isTypedef()) {
                sb.append(CommonConstant.BLANK);
                sb.append(struct.getName());
            }
        }

        sb.append(";");
        sb.append(CommonConstant.NEW_LINE);

        return sb;
    }
}
