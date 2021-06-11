package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {
    private Item itemTest;

    @Before
    public void setup(){
        itemTest = new Item("A1", "Potato Crisps", "Chip", 3.05);
    }

    @Test
    public void Item_Price(){

    }


}
