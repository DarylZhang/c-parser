package com.swinburne.data.preprocessing.treenode;

import java.util.List;

public class TreeUtils {

    public static List<TreeNode> bfsSearchByCtxName(TreeNode root, String ctxName, List<TreeNode> lists) {

        if (root == null || root.getChildren().size() == 0)
            return lists;

        for (TreeNode node : root.getChildren()) {
            if (ctxName.equals(node.getCtxName())) {
                lists.add(node);
            }

            if (node.getChildren().size() > 0) {
                bfsSearchByCtxName(node, ctxName, lists);
            }
        }

        return lists;
    }

    public static List<TreeNode> bfsSearchByCtxNameWithParent(TreeNode root, String ctxName, String parentCtxName, List<TreeNode> lists) {

        if (root == null || root.getChildren().size() == 0)
            return lists;

        for (TreeNode node : root.getChildren()) {
            if (ctxName.equals(node.getCtxName()) && node.getParent().getCtxName().equals(parentCtxName)) {
                lists.add(node);
            }

            if (node.getChildren().size() > 0) {
                bfsSearchByCtxNameWithParent(node, ctxName, parentCtxName, lists);
            }
        }

        return lists;
    }

    public static List<TreeNode> bfsSearchFunctionByCtxName(TreeNode root, String ctxName, List<TreeNode> lists) {

        if (root == null || root.getChildren().size() == 0)
            return lists;

        for (TreeNode node : root.getChildren()) {
            if (ctxName.equals(node.getCtxName())
                    && node.getCtxValue().contains("(")  && node.getCtxValue().contains(")")) {
                lists.add(node);
            }

            if (node.getChildren().size() > 0) {
                bfsSearchFunctionByCtxName(node, ctxName, lists);
            }
        }

        return lists;
    }
}
