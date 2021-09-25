package com.swinburne.data.preprocessing.treenode;

import lombok.Data;

import java.util.*;
import java.util.function.Consumer;

@Data
public class TreeNode implements Iterable<TreeNode>{

    private String ctxName;
    private String ctxValue;
    private TreeNode parent;
    private List<TreeNode> children;

    public TreeNode(String ctxName, String ctxValue) {
        this.ctxName = ctxName;
        this.ctxValue = ctxValue;
        this.children = new ArrayList<>();
    }

    public TreeNode addChild(TreeNode child) {
//        TreeNode<T> childNode = new TreeNode<T>(child);
        child.parent = this;
        this.children.add(child);
        return child;
    }

    public List<TreeNode> getChildren() {
        return this.children;
    }

    @Override
    public Iterator<TreeNode> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super TreeNode> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<TreeNode> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public String toString() {
        return this.getCtxName() + " " + this.getCtxValue();
    }


}
