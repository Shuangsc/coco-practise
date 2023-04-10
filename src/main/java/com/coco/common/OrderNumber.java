package com.coco.common;

import java.util.*;
public class OrderNumber {
    public static int getOrderNumber (int n) {
        int min = 1, max = 9;
        for (int i = 0; i < n; i++) {
            min *= 10;
            max *= 10;
        }
        int orderNumber = (((int) (new Random().nextDouble() * (max - min)))) + min;
        return orderNumber;
    }
}
