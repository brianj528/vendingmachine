package com.techelevator;

import com.techelevator.view.Menu;

import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class VendingMachineCLI {
	NumberFormat formatter = NumberFormat.getCurrencyInstance();{
	}


	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "End Simulation";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT};


	private static final String PURCHASE_MENU_OPTION_ADD_MONEY = "Add Money";
	private static final String PURCHASE_MENU_OPTION_BUY_ITEM = "Buy Item";
	private static final String PURCHASE_MENU_OPTION_CASH_OUT = "Cash out";
	private static final String PURCHASE_MENU_OPTION_PREVIOUS_MENU = "Previous Menu";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_ADD_MONEY, PURCHASE_MENU_OPTION_BUY_ITEM,
			PURCHASE_MENU_OPTION_CASH_OUT, PURCHASE_MENU_OPTION_PREVIOUS_MENU};

	//Loop
	private static final String MAIN_LOOP = "Main";
	private static final String PURCHASE_LOOP = "Purchase";

	private Menu menu;
	private VendingMachine vm = new VendingMachine("WELCOME! ARE YOU HUNGRY?!");

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws IOException {
		vm.announceStart();

		String menuLoop = MAIN_LOOP;

		while (true) {

			String choice = (String)
					menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayStock();
				System.out.println("\nCurrent balance is: " + (formatter.format(vm.balance / 100)));
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				menuLoop = PURCHASE_LOOP;
				System.out.println("\nCurrent balance is: " + (formatter.format(vm.balance / 100)));
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				cashOut();
				System.out.println("Goodbye, thank you!");
				break;

			}


			// PURCHASE SUB MENU LOOP - LOOP LEVEL 0-1-0
			while (menuLoop.equals(PURCHASE_LOOP)) {
				 choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				if (choice.equals(PURCHASE_MENU_OPTION_ADD_MONEY)) {
					System.out.println("**************");
					System.out.println("Money, please!");
					System.out.println("**************");
					feedMoney();
					System.out.println("\nCurrent balance is: " + (formatter.format(vm.balance / 100)));

				}
				if (choice.equals(PURCHASE_MENU_OPTION_BUY_ITEM)) {
					System.out.println("***************************");
					System.out.println("Great! What can we get you?");
					System.out.println("***************************");
					displayStock();
					System.out.println("***************************");
					buyItem();
					readFile();
					System.out.println("\nCurrent balance is: " + (formatter.format(vm.balance / 100)));
				}
				if (choice.equals(PURCHASE_MENU_OPTION_CASH_OUT)) {
					System.out.println("Are you sure you don't want any more snacks?");
					cashOut();
					System.out.println("\nCurrent balance is: " + (formatter.format(vm.balance / 100)));
				}
				if (choice.equals(PURCHASE_MENU_OPTION_PREVIOUS_MENU)) {
					menuLoop = MAIN_LOOP;
				}


			}


		}
	}

	private void displayStock() {
		vm.displayStock();
	}
	private void buyItem(){
		vm.buyItem();
	}

	public void feedMoney(){
		vm.feedMoney();
	}

	public void cashOut(){
		vm.cashOut();
	}

	public void readFile () throws IOException {

	LocalDateTime myDateObj = LocalDateTime.now();
		FileWriter f = new FileWriter("log.txt", true);
		PrintWriter out = new PrintWriter(f);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
		String formattedDate = myDateObj.format(myFormatObj);
		for(Item str : vm.purchaseLog){
			out.write(formattedDate + " Machine Balance: " + formatter.format(vm.balance/100) + " " + str.getItemName());
			out.close();
		}


	}


		public static void main (String[]args) throws IOException {
			Menu menu = new Menu(System.in, System.out);
			VendingMachineCLI cli = new VendingMachineCLI(menu);
			cli.run();
		}


	}