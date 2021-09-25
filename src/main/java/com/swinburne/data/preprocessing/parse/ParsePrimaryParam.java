package com.swinburne.data.preprocessing.parse;

import com.swinburne.data.preprocessing.Utils;
import com.swinburne.data.preprocessing.constant.CommonConstant;
import com.swinburne.data.preprocessing.ctype.Record;
import com.swinburne.data.preprocessing.ctype.Struct;
import com.swinburne.data.preprocessing.ctype.TVariable;
import com.swinburne.data.preprocessing.treenode.TreeNode;
import com.swinburne.data.preprocessing.treenode.TreeUtils;

import java.util.*;

public class ParsePrimaryParam {

    private Map<String, Record> variableMap = null;
//    private Map<String, Record> structMap = null;

    public ParsePrimaryParam(Map<String, Record> variableMap) {
        this.variableMap = variableMap;
//        this.structMap = structMap;
    }

    public StringBuffer findUndefinedNonStructVar(TreeNode root) {
        Map<String, Record> underfinedNonStructVarMap = new HashMap<>();

        List<TreeNode> paramsNodes = new ArrayList<>();
        TreeUtils.bfsSearchByCtxName(root, "primaryExpression", paramsNodes);

        for (TreeNode treeNode : paramsNodes) {
            boolean found = this.variableMap.containsKey(treeNode.getCtxValue());

            if (!found) {

                if (Utils.isNumber(treeNode.getCtxValue()) || Utils.isString(treeNode.getCtxValue())
                        || Utils.hasParenthesis(treeNode.getCtxValue()) || Utils.hasParenthesis(treeNode.getParent().getCtxValue())
                        || Utils.checkHex(treeNode.getCtxValue())) {
                    continue;
                }

                TVariable tVariable = new TVariable(treeNode.getCtxValue());
                tVariable.setDefined(false);

                underfinedNonStructVarMap.put(tVariable.getRef(), tVariable);
            }
        }

        return this.buildIntStatement(underfinedNonStructVarMap);
    }

    private StringBuffer buildIntStatement(Map<String, Record> underfinedNonStructVarMap) {

        StringBuffer sb = new StringBuffer();

        if (underfinedNonStructVarMap == null || underfinedNonStructVarMap.isEmpty())
            return sb;

        sb.append(CommonConstant.NEW_LINE);

        Iterator<Map.Entry<String, Record>> it = underfinedNonStructVarMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Record> pair = it.next();
            String key = pair.getKey();
            TVariable tVariable = (TVariable) pair.getValue();

            if (tVariable.getRef().equals("NULL"))
                continue;

            sb.append(tVariable.getTypename()).append(CommonConstant.BLANK)
                    .append(tVariable.getRef()).append(CommonConstant.SEMICOLON);
            sb.append(CommonConstant.NEW_LINE);
        }

        return sb;
    }

}
