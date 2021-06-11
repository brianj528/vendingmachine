package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.*;
import com.techelevator.VendingMachine;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    private VendingMachine vendingMachineTest;
    private VendingMachineCLI machineTest;
    private Item item;
    private FileReader fileReaderTest;
    //Every method works the way we want
    //isn't any way for user to break out code


    @Test
    public void itemMapIsCorrectSize(){
        VendingMachine vendingMachine = new VendingMachine();
        SortedMap<String, List<Item>> itemMap = new TreeMap<>(); //returns key value pairs
        List<Item> itemDetails = new ArrayList<Item>();
        Integer expected = 16;
        Integer result = vendingMachine.getItemMap().containsKey();
        Assert.assertEquals(expected, result);

    }









    }

