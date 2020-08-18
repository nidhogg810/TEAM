package com.example.demo.BPlusTree;

import java.util.ArrayList;
import java.util.List;

public class LeafNode extends Node{
    static int min_degree=-1;
    private LeafNode nextNode;
    private List<Integer> dataFeilds;

    public static LeafNode getInstance(int t){
        min_degree = t;
        return new LeafNode();
    }

    private LeafNode(){
        super.isLeaf = true;
        super.isRoot = false;
//        super.parent = parent;
//        this.nextNode = next;
        dataFeilds = new ArrayList<Integer>(2*min_degree);
    }

    public void addData(Integer data){
        dataFeilds.add(data);
    }

    public LeafNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(LeafNode nextNode) {
        this.nextNode = nextNode;
    }

    public List<Integer> getDataFeilds() {
        return dataFeilds;
    }


}
