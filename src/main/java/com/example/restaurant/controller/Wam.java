package com.example.restaurant.controller;

import java.sql.Array;
import java.util.ArrayList;

public class Wam {
    public static void main(String[] args) {
    double sum = 0;
        int[] score ={90,90,60,60,60,60};
        for (int i: score
             ) {
             sum+= i*6;
        }
        System.out.println(sum/(6*6));
    }
}
