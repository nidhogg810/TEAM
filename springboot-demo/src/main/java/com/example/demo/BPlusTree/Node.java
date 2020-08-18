package com.example.demo.BPlusTree;

public class Node {
    protected boolean isRoot;
    protected boolean isLeaf;
    protected Node parent;

    public boolean isRoot() {
        return isRoot;
    }


    public boolean isLeaf() {
        return isLeaf;
    }


    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
