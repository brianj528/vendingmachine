package com.techelevator;

import java.io.*;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.*;

public class Item{
    private String itemCode;
    private String itemName;
    private String itemType;
    private double itemPrice;


    public Item(String itemCode, String itemName, String itemType, double itemPrice) {
        this.itemCode=itemCode;
        this.itemName=itemName;
        this.itemType=itemType;
        this.itemPrice=itemPrice;
    }

    public String getItemName() { return itemName; }
    public String getItemCode() { return itemCode; }
    public double getItemPrice() { return itemPrice; }
    public String getItemType() { return itemType; }


}






