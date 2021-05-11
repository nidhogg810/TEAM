package com.example.demo.sentinel;

import lombok.Data;

@Data
public class DegradeRule {
    private String resource;
    private int grade;
    private int minRequestAmount;
    private int timeWindow;
    private double count;
}
