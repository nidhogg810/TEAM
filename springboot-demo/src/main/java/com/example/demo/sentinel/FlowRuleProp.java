package com.example.demo.sentinel;

import lombok.Data;

/**
 * @author yuhan
 */
@Data
public class FlowRuleProp {
    private String resource;
    private int grade;
    private int minRequestAmount;
    private int timeWindow;
    private double count;
}
