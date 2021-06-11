package com.techelevator;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class VendingMachine {
    private String startMessage = "WELCOME! ARE YOU HUNGRY?!";
    NumberFormat formatter = NumberFormat.getCurrencyInstance();{
    }
    List<Item> purchaseLog = new ArrayList<>();

    public VendingMachine(){

    }



    public VendingMachine(String startMessage){
        this.startMessage = startMessage;
    }
    public void announceStart(){
        System.out.println("*********************");
        System.out.println(startMessage);
        System.out.println("*********************");
    }
    double balance = 0.00;
    double getBalance(){ return balance;}


    public void feedMoney() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("\n Enter money please: 1, 2, 5, 10");
        String amountFed = myScanner.nextLine();
        if (amountFed.equals("1") || amountFed.equals("2") || amountFed.equals("5") || amountFed.equals("10")){
            balance += (Integer.parseInt(amountFed)*100);
            System.out.println("\nCurrent balance is: " + formatter.format(balance/100));


        } else System.out.println("\nNot accepted amount, sorry!");

    }


        //when select finish transaction return change
        //return balance as change
        public void cashOut(){
            int change = (int)(balance);
            int quarters = Math.round((int)change/25);
            change=change%25;
            int dimes = Math.round((int)change/10);
            change=change%10;
            int nickels = Math.round((int)change/5);
            change=change%5;
            System.out.println("Your change is " + formatter.format(balance/100));
            System.out.println("Quarters: " + quarters);
            System.out.println("Dimes: " + dimes);
            System.out.println("Nickels: " + nickels);
            balance = 0;


    }

    private SortedMap<String, List<Item>> itemMap = new TreeMap<>();
    private String inventoryFilePath = "vendingmachine.csv";
    private static final int DEFAULT_ITEM_COUNT = 5;
    public String itemName;
    public String itemCode;
    public double itemPrice;
    public String itemType;

    public SortedMap<String, List<Item>> getItemMap() {
        return itemMap;
    }

    public void setItemMap(SortedMap<String, List<Item>> itemMap) {
        this.itemMap = itemMap;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getItemType() {
        return itemType;
    }

    public double getItemPrice() {
        return itemPrice;
    }


    public SortedMap<String, List<Item>> loadStock() throws FileNotFoundException {

        File inventoryFile = new File(inventoryFilePath);
        try (Scanner scan = new Scanner(inventoryFile)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                line = line.replace("|", "@");
                String[] parts = line.split("@", 5);
                itemCode = parts[0].trim();
                itemName = parts[1].trim();
                itemPrice = Double.parseDouble((parts[2]));
                itemType = parts[3].trim();


                Item stockItem = new Item(itemCode, itemName, itemType, itemPrice);
                List<Item> itemDetails = new ArrayList<Item>();
                for (int i = 0; i < DEFAULT_ITEM_COUNT; i++) {
                    itemDetails.add(stockItem);

                }

                itemMap.put(itemCode, itemDetails);


            }

        } finally {
            return itemMap;
        }
    }

    public void displayStock() {
        if (itemMap.isEmpty()) {
            try {
                loadStock();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!itemMap.isEmpty()) {
            for (Map.Entry<String, List<Item>> inventorySlot : itemMap.entrySet()) {
                if (!inventorySlot.getValue().isEmpty()) {
                    int inventory = inventorySlot.getValue().size();
                    System.out.println(inventorySlot.getKey() + " " + ((Item) inventorySlot.getValue().get(0)).getItemName() + " $" + ((Item)inventorySlot.getValue().get(0)).getItemPrice()  + " Units in stock: " + inventory);
                } else {
                    if (inventorySlot.getValue().size() == 0) {
                        System.out.println(inventorySlot.getKey() + " Out of stock!");
                    }
                }
            }
        }
    }

    public void buyItem() {
        System.out.println("Enter item Code: ");
        Scanner itemSelect = new Scanner(System.in);
        String userInput = itemSelect.nextLine().toUpperCase();
        if (!itemMap.containsKey(userInput)) {
            System.out.println("Whoops! We don't have that. Try again?");
        } else {
            if (itemMap.get(userInput).isEmpty()) {
                System.out.println("Whoops! That's out of stock. Try again?");
            } else {
                if (balance < itemMap.get(userInput).get(0).getItemPrice() * 100 || (balance - itemMap.get(userInput).get(0).getItemPrice() * 100) < 0) {
                    System.out.println("You can't afford this right now! Try adding some money.");
                }
                if (itemMap.get(userInput).size() > 0 && balance > (itemMap.get(userInput).get(0).getItemPrice() * 100) && (balance - itemMap.get(userInput).get(0).getItemPrice() * 100) > 0) {
                    try {
                        Item itemBought = itemMap.get(userInput).remove(0);
                        System.out.println("Enjoy your " + itemBought.getItemType() + "! ");
                        balance -= (int) (itemBought.getItemPrice() * 100);
                        System.out.println("Current balance remaining is " + formatter.format(balance / 100));
                        purchaseLog.add(itemBought);
                        if (itemBought.getItemType().equals("Gum")) {
                            System.out.println("Chew Chew, Yum!");
                        }
                        if (itemBought.getItemType().equals("Drink")) {
                            System.out.println("Glug Glug, Yum!");
                        }
                        if (itemBought.getItemType().equals("Chip")) {
                            System.out.println("Crunch Crunch, Yum!");
                        }
                        if (itemBought.getItemType().equals("Candy")) {
                            System.out.println("Munch Munch, Yum!");
                        }
                    } catch (IndexOutOfBoundsException indOut) {
                        System.out.println("Congratulations! You got the last one. Enjoy it!");

                    }
                }


            }
        }
    }



}








