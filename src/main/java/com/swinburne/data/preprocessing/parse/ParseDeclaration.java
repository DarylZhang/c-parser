package com.swinburne.data.preprocessing.parse;

import com.swinburne.data.preprocessing.Utils;
import com.swinburne.data.preprocessing.ctype.Record;
import com.swinburne.data.preprocessing.ctype.TVariable;
import com.swinburne.data.preprocessing.treenode.TreeNode;
import com.swinburne.data.preprocessing.treenode.TreeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseDeclaration {

    public ParseDeclaration() {
    }

    public void parseDeclaration(TreeNode root, Map<String, Record> variableMap) throws Exception {

        List<TreeNode> paramsNodes = new ArrayList<>();
        TreeUtils.bfsSearchByCtxName(root, "declaration", paramsNodes); //处理方法内部定义的变量

        for (TreeNode declarationNode : paramsNodes) {
            String declarationCtxValue = declarationNode.getCtxValue();
            declarationCtxValue = declarationCtxValue.substring(0, declarationCtxValue.length() - 1);

            if (declarationCtxValue.contains("=")) {
                declarationCtxValue = declarationCtxValue.substring(0, declarationCtxValue.indexOf("="));
            }

            boolean isPointer = false;
            boolean isArray = false;
            String variableRef = null;

            if (declarationCtxValue.contains("*")) {
                isPointer = true;
            }
            if (declarationCtxValue.contains("[") && declarationCtxValue.contains("]")) {
                isArray = true;
            }
            variableRef = declarationCtxValue;

            TVariable tVariable = new TVariable(variableRef);
            tVariable.setIsPointer(isPointer);
            tVariable.setIsArray(isArray);

            for (TreeNode childNode : declarationNode.getChildren()) {

                String childRuleName = childNode.getCtxName();
                String childCtxValue = childNode.getCtxValue();

                if ("typeSpecifier".equals(childRuleName)) {// typeSpecifier
                    this.parseTypeSpecifier(tVariable, childNode);
                } else if ("declarationSpecifiers".equals(childRuleName)) { // declarationSpecifiers
                    this.parseDeclarationSpecifiers(tVariable, childNode);
                }

                break;
            }

            if (tVariable != null) {
                if (tVariable.getRef().contains(",")) {
                    this.splitVariables(variableMap, tVariable);
                } else {
                    variableMap.put(tVariable.getRef(), tVariable);
                }
            }
        }
        System.out.println("");
    }

    private void splitVariables(Map variableMap, TVariable tVariable) {

        String[] refs = tVariable.getRef().split(",");
        for (String ref : refs) {
            TVariable newVariable = new TVariable(ref);
            newVariable.setTypename(tVariable.getTypename());
            if (ref.contains("*")) {
                newVariable.setRef(newVariable.getRef().substring(1));
                newVariable.setIsPointer(true);
            }

            variableMap.put(newVariable.getRef(), newVariable);
        }
    }

    private TVariable parseDeclarationSpecifiers(TVariable tVariable, TreeNode node) {

//        String variableRef = null;
//        String declarationCtxValue = declarationNode.getCtxValue();
//        String childCtxValue = childNode.getCtxValue();
//        boolean hasEqualsSign = false;
//
//        if (declarationCtxValue.contains("=")) {
//            hasEqualsSign = true;
//        }
//
//        if (hasEqualsSign) {
//            variableRef = declarationCtxValue.substring(0, declarationCtxValue.indexOf("="));
//        } else {
//            variableRef = declarationCtxValue;
//        }
//
//        TVariable tVariable = new TVariable(variableRef);
//        tVariable.setIsPointer(Utils.isPointer(declarationNode.getCtxValue()));
//        tVariable.setIsArray(Utils.isArray(declarationNode.getCtxValue()));
//
//        this.getParamtersRef(tVariable, childNode);
//
//        for (TreeNode node : childNode.getChildren()) {
//            String ruleName = node.getCtxName();
////            String childCtxValue = node.getCtxValue();
//
//            if ("typeSpecifier".equals(ruleName)) {
//                if (Utils.isBasicVariable(childCtxValue)) {
//                    tVariable.setTypename(Utils.getBasicType(childCtxValue));
//                } else if (Utils.isStruct(childCtxValue)) {
//                    tVariable.setTypename("struct");
//                    if (!tVariable.getIsPointer())
//                        tVariable.setName(childCtxValue.substring(childCtxValue.indexOf("struct") + 6));
//                    else
//                        tVariable.setName(childCtxValue.substring(childCtxValue.indexOf("struct") + 6,
//                                childCtxValue.length() - 1));
//                }
//            }
//        }
        String declarationSpecifiersCtxValue = node.getCtxValue();

        if (tVariable.getRef().equals(node.getCtxValue())) {
            for (TreeNode childNode : node.getChildren()) {

                if ("typedefName".equals(childNode.getCtxName())) {
                    tVariable.setRef(childNode.getCtxValue());
                    declarationSpecifiersCtxValue = declarationSpecifiersCtxValue.substring(0, declarationSpecifiersCtxValue.lastIndexOf(childNode.getCtxValue()));
                    break;
                }

            }
        } else {
            tVariable = this.getParamtersRef(tVariable, node);
        }

        for (TreeNode childNode : node.getChildren()) {

            String ruleName = childNode.getCtxName();

            if ("typeSpecifier".equals(ruleName)) {
                if ("unsigned".equals(childNode.getCtxValue()))
                    continue;

                if (Utils.isBasicVariable(declarationSpecifiersCtxValue)) {
                    tVariable.setTypename(Utils.getBasicType(declarationSpecifiersCtxValue));
                } else if (Utils.isStruct(declarationSpecifiersCtxValue)) {
                    tVariable.setTypename("struct");
                    if (!tVariable.getIsPointer())
                        tVariable.setName(declarationSpecifiersCtxValue.substring(declarationSpecifiersCtxValue.indexOf("struct") + 6));
                    else
                        tVariable.setName(declarationSpecifiersCtxValue.substring(declarationSpecifiersCtxValue.indexOf("struct") + 6,
                                declarationSpecifiersCtxValue.length() - 1));
                }
            } else if ("structOrUnionSpecifier".equals(ruleName)) {
                tVariable.setTypename("struct");
                if (!tVariable.getIsPointer())
                    tVariable.setName(declarationSpecifiersCtxValue.substring(declarationSpecifiersCtxValue.indexOf("struct") + 6));
                else
                    tVariable.setName(declarationSpecifiersCtxValue.substring(declarationSpecifiersCtxValue.indexOf("struct") + 6,
                            declarationSpecifiersCtxValue.length() - 1));
            }

        }

        return tVariable;
    }

    private TVariable parseTypeSpecifier(TVariable tVariable, TreeNode node) {

//        String variableRef = null;
//        String declarationCtxValue = declarationNode.getCtxValue();
//        String childCtxValue = childNode.getCtxValue();
//        boolean hasEqualsSign = false;
//
//        if (declarationCtxValue.contains("=")) {
//            hasEqualsSign = true;
//        }
//
//        if (hasEqualsSign) {
//            variableRef = declarationCtxValue.substring(0, declarationCtxValue.indexOf("="));
//        } else {
//            variableRef = declarationCtxValue;
//        }
//
//        TVariable tVariable = new TVariable(variableRef);
//        tVariable.setIsPointer(Utils.isPointer(declarationCtxValue));
//        tVariable.setIsArray(Utils.isArray(declarationCtxValue));
//
//        this.getParamtersRef(tVariable, childNode);
//
//        if (Utils.isBasicVariable(childCtxValue)) {
//            tVariable.setTypename(Utils.getBasicType(childCtxValue));
//        } else if (Utils.isStruct(childCtxValue)) {
//            tVariable.setTypename("struct");
//            if (!tVariable.getIsPointer())
//                tVariable.setName(childCtxValue.substring(childCtxValue.indexOf("struct") + 6));
//            else
//                tVariable.setName(childCtxValue.substring(childCtxValue.indexOf("struct") + 6,
//                        childCtxValue.length() - 1));
//        }
        String childCtxValue = node.getCtxValue();

        tVariable = this.getParamtersRef(tVariable, node);

        if (Utils.isBasicVariable(childCtxValue)) {
            tVariable.setTypename(Utils.getBasicType(childCtxValue));
        } else if (Utils.isStruct(childCtxValue)) {
            tVariable.setTypename("struct");
            if (!tVariable.getIsPointer())
                tVariable.setName(childCtxValue.substring(childCtxValue.indexOf("struct") + 6));
            else
                tVariable.setName(childCtxValue.substring(childCtxValue.indexOf("struct") + 6,
                        childCtxValue.length() - 1));
        } else {
            tVariable.setTypename("struct");
            if (tVariable.getIsPointer())
                tVariable.setName(childCtxValue.substring(0, childCtxValue.length() - 1));
        }

        return tVariable;
    }

    private TVariable getParamtersRef(TVariable tVariable, TreeNode node) {

        String childCtxValue = node.getCtxValue();

        String variableRef = tVariable.getRef();

        variableRef = variableRef.substring(variableRef.
                indexOf(childCtxValue) + childCtxValue.length());

        if (variableRef.contains("[")) {
            variableRef = Utils.removeArrayBrace(variableRef);
        }

        tVariable.setRef(variableRef);

        return tVariable;
    }
}
