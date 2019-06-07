package com.techelevator;

import java.math.BigDecimal;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "EXIT";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													   MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_BUY_ITEM = "Buy Item";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[]PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_BUY_ITEM, 
														PURCHASE_MENU_OPTION_FINISH_TRANSACTION };
	private static final String FEED_MENU_OPTION_ONE = "$1.00";
	private static final String FEED_MENU_OPTION_TWO = "$2.00";
	private static final String FEED_MENU_OPTION_FIVE = "$5.00";
	private static final String FEED_MENU_OPTION_TEN = "$10.00";
	private static final String FEED_MENU_OPTION_EXIT = "Go Back";
	private static final String[] FEED_MENU_OPTIONS = { FEED_MENU_OPTION_ONE, FEED_MENU_OPTION_TWO, 
														FEED_MENU_OPTION_FIVE, FEED_MENU_OPTION_TEN, FEED_MENU_OPTION_EXIT };
	
	private Menu menu;
	private VendingMachine vm = new VendingMachine();
	
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	
	public void run() {
		Logger logger = new Logger();
		vm.refill();
		//Original menu.
		while(true) {
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if(choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println(vm.displayItems());
			} else if(choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//Purchase Menu
				while(true) {
					System.out.println("\nCurrent Balance is $" + vm.getAvailableFunds());
					String purchaseChoice = (String)menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					
					if(purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						//Feed Money Menu
						while(true) {
							System.out.println("\nCurrent Balance is $" + vm.getAvailableFunds());
							String feedChoice = (String)menu.getChoiceFromOptions(FEED_MENU_OPTIONS);
							
							if(feedChoice.equals(FEED_MENU_OPTION_EXIT)) {
								break;
							}
							vm.feedMoney(new BigDecimal(feedChoice.replace("$", "")));
						}
					} else if(purchaseChoice.equals(PURCHASE_MENU_OPTION_BUY_ITEM)) {
						System.out.println("\nCurrent Balance is $" + vm.getAvailableFunds());
						System.out.println(vm.displayItems());
						System.out.println("\nPlease enter your selection or enter 0 to exit: ");
						menu.getChoiceForSpecificItem(vm);
					} else if(purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						Change change = new Change();
						logger.logChange(vm.getAvailableFunds());
						System.out.println(change.calculateChange(vm.getAvailableFunds()));
						vm.resetAvailableFunds();
						break;
					}
				}
			} else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
