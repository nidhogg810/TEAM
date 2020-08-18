package com.example.demo.BPlusTree;

import java.util.HashMap;
import java.util.Map;

public class InnerNode extends Node{
    private int min_degree=-1;
    private Map<Integer,Object> InnerNode = new HashMap<>();
    public InnerNode(int t){
        super.isLeaf=false;
    }
}
