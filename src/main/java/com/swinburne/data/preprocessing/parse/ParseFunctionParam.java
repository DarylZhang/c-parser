package com.swinburne.data.preprocessing.parse;

import com.swinburne.data.preprocessing.Utils;
import com.swinburne.data.preprocessing.ctype.Record;
import com.swinburne.data.preprocessing.ctype.TVariable;
import com.swinburne.data.preprocessing.treenode.TreeNode;
import com.swinburne.data.preprocessing.treenode.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParseFunctionParam {

//    private Map<String, Record> variableMap = null;
//    private Map<String, Record> structMap = null;

    public ParseFunctionParam() {
//        this.variableMap = variableMap;
//        this.structMap = structMap;
    }

    public void parseFunctionParams(TreeNode root, Map<String, Record> variableMap) {

        List<TreeNode> paramsNodes = new ArrayList<>();
        TreeUtils.bfsSearchByCtxName(root, "parameterDeclaration", paramsNodes);

        if (!paramsNodes.isEmpty()) {
            for (TreeNode paramNode : paramsNodes) {
                String parameterDeclarationCtxValue = paramNode.getCtxValue();

                boolean isPointer = false;
                boolean isArray = false;
                String variableRef = null;

                if (parameterDeclarationCtxValue.contains("*")) {
                    isPointer = true;
                }
                if (parameterDeclarationCtxValue.contains("[") && parameterDeclarationCtxValue.contains("]")) {
                    isArray = true;
                }
                variableRef = parameterDeclarationCtxValue;

                TVariable tVariable = new TVariable(variableRef);
                tVariable.setIsPointer(isPointer);
                tVariable.setIsArray(isArray);

                for (TreeNode childNode : paramNode.getChildren()) {

                    String childRuleName = childNode.getCtxName();
                    String childCtxValue = childNode.getCtxValue();

                    if ("typeSpecifier".equals(childRuleName)) {
                        this.parseTypeSpecifier(tVariable, childNode);
                    } else if ("declarationSpecifiers".equals(childRuleName)) {
                        this.parseDeclarationSpecifiers(tVariable, childNode);
                    } else if ("typedefName".equals(childRuleName)) {
                        this.parseTypedefName(tVariable, childNode);
                    }

                    break;
                }

                variableMap.put(tVariable.getRef(), tVariable);
            }
        } else {
            TreeUtils.bfsSearchByCtxName(root, "directDeclarator", paramsNodes);

            for (TreeNode paramNode : paramsNodes) {
                if ("directDeclarator".equals(paramNode.getParent().getCtxName()))
                    break;

                String directDeclaratorCtxValue = paramNode.getCtxValue();

                boolean isPointer = false;
                boolean isArray = false;
                String variableRef = null;

                if (directDeclaratorCtxValue.contains("(") && directDeclaratorCtxValue.contains(")")) {
                    directDeclaratorCtxValue = directDeclaratorCtxValue.substring(directDeclaratorCtxValue.indexOf("(") + 1, directDeclaratorCtxValue.indexOf(")"));
                }

                if (directDeclaratorCtxValue.contains("*")) {
                    isPointer = true;
                }
                if (directDeclaratorCtxValue.contains("[") && directDeclaratorCtxValue.contains("]")) {
                    isArray = true;
                }
                variableRef = directDeclaratorCtxValue;

                TVariable tVariable = new TVariable(variableRef);
                tVariable.setIsPointer(isPointer);
                tVariable.setIsArray(isArray);

                for (TreeNode childNode : paramNode.getChildren()) {

                    String childRuleName = childNode.getCtxName();
                    String childCtxValue = childNode.getCtxValue();

                    if ("typeSpecifier".equals(childRuleName)) {
                        this.parseTypeSpecifier(tVariable, childNode);
                    } else if ("declarationSpecifiers".equals(childRuleName)) {
                        this.parseDeclarationSpecifiers(tVariable, childNode);
                    } else if ("typedefName".equals(childRuleName)) {
                        this.parseTypedefName(tVariable, childNode);
                    } else if ("structOrUnionSpecifier".equals(childRuleName)) {
                        this.parseStructOrUnionSpecifier(tVariable, childNode);
                    }

                    break;
                }

                variableMap.put(tVariable.getRef(), tVariable);
            }
        }

//        System.out.println("");
    }

    private TVariable parseDeclarationSpecifiers(TVariable tVariable, TreeNode node) {

        String childCtxValue = node.getCtxValue();

        tVariable = this.getParamtersRef(tVariable, node);

        for (TreeNode childNode : node.getChildren()) {

            String ruleName = childNode.getCtxName();

            if ("typeSpecifier".equals(ruleName)) {
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
                    tVariable.setTypedef(true);
                    if (!tVariable.getIsPointer())
                        tVariable.setName(childCtxValue.substring(childCtxValue.indexOf("struct") + 6));
                    else
                        tVariable.setName(childCtxValue.substring(childCtxValue.indexOf("struct") + 6,
                                childCtxValue.length() - 1));
                }
            }

        }

        return tVariable;
    }

    private TVariable parseTypeSpecifier(TVariable tVariable, TreeNode node) {

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
            tVariable.setTypedef(true);
            if (tVariable.getIsPointer())
                tVariable.setName(childCtxValue.substring(0, childCtxValue.length() - 1));
        }

        return tVariable;
    }

    private TVariable parseStructOrUnionSpecifier(TVariable tVariable, TreeNode node) {

        String childCtxValue = node.getCtxValue();

        String variableRef = tVariable.getRef();

        variableRef = variableRef.substring(variableRef.
                lastIndexOf(childCtxValue) + childCtxValue.length());

        if (variableRef.contains("[")) {
            variableRef = Utils.removeArrayBrace(variableRef);
        }

        if (variableRef.contains("*")) {
            variableRef = variableRef.substring(1, variableRef.length());
        }

        tVariable.setRef(variableRef);

        if (Utils.isStruct(childCtxValue)) {
            tVariable.setTypename("struct");
            tVariable.setName(childCtxValue.substring(childCtxValue.indexOf("struct") + 6));
        }

        return tVariable;
    }

    private TVariable parseTypedefName(TVariable tVariable, TreeNode node) {

        String childCtxValue = node.getCtxValue();

        tVariable = this.getParamtersRef(tVariable, node);

        tVariable.setTypename("struct");
        tVariable.setName(childCtxValue);

        return tVariable;
    }

    private TVariable getParamtersRef(TVariable tVariable, TreeNode node) {

        String childCtxValue = node.getCtxValue();

        String variableRef = tVariable.getRef();

        variableRef = variableRef.substring(variableRef.
                lastIndexOf(childCtxValue) + childCtxValue.length());

        if (variableRef.contains("[")) {
            variableRef = Utils.removeArrayBrace(variableRef);
        }

        if (variableRef.contains("*")) {
            variableRef = variableRef.substring(1, variableRef.length());
        }

        tVariable.setRef(variableRef);

        return tVariable;
    }
}
