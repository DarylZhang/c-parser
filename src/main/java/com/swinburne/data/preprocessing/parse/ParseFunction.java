package com.swinburne.data.preprocessing.parse;

import com.swinburne.data.preprocessing.constant.CommonConstant;
import com.swinburne.data.preprocessing.ctype.Record;
import com.swinburne.data.preprocessing.ctype.Struct;
import com.swinburne.data.preprocessing.ctype.TVariable;
import com.swinburne.data.preprocessing.treenode.TreeNode;
import com.swinburne.data.preprocessing.treenode.TreeUtils;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
public class ParseFunction {

    public StringBuffer parseFunction(TreeNode root) {

        Map<String, String> functionMap = null;

        List<TreeNode> functionNodes = new ArrayList<>();
        TreeUtils.bfsSearchFunctionByCtxName(root, "postfixExpression", functionNodes);

        if (functionNodes.size() > 0) {
            functionMap = new HashMap<>();
        } else {
            return new StringBuffer();
        }

        for (TreeNode postfixNode : functionNodes) {
            String postfixExpressionCtxValue = postfixNode.getCtxValue();
            String functionName = postfixExpressionCtxValue.substring(0, postfixExpressionCtxValue.indexOf("("));

            functionMap.put(functionName, functionName);
        }

        return buildIntStatement(functionMap);
    }

    private StringBuffer buildIntStatement(Map<String, String> functionMap) {

        StringBuffer sb = new StringBuffer();

        if (functionMap == null || functionMap.isEmpty())
            return sb;

        sb.append(CommonConstant.NEW_LINE);

        Iterator<Map.Entry<String, String>> it = functionMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pair = it.next();
            String key = pair.getKey();
            String functionName = pair.getValue();

            sb.append("int").append(CommonConstant.BLANK).append(functionName).append("() {}");

            sb.append(CommonConstant.NEW_LINE);
        }

        return sb;
    }
}
