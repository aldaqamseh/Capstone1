package com.techelevator;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class Logger {

	
	public void logPurchase(String slot, Items item, BigDecimal initialBalance) {
		BigDecimal endingBalance = initialBalance.subtract(item.getPrice());
		String event = item.getItemName() + "  " + slot;
		printToLogFile(event,initialBalance,endingBalance);
	}
	
	
	public void logFeed(BigDecimal amountAdded, BigDecimal initialBalance)  {
		BigDecimal endingBalance = initialBalance.add(amountAdded);
		printToLogFile("FEED $"+amountAdded,initialBalance,endingBalance);
	}
	
	
	public void logOutOfStock(BigDecimal balance)  {
		printToLogFile("Item unavailable.",balance,balance);
	}
	
	public void notEnoughMoney(BigDecimal balance)  {
		printToLogFile("Insufficent funds.",balance,balance);
	}
	
	
	
	
	public void logChange(BigDecimal availableFunds)  {
		printToLogFile("DISPENSED CHANGE $"+availableFunds.toString(),availableFunds, new BigDecimal("0.00"));
	}
	
	
	private void printToLogFile(String event, BigDecimal start, BigDecimal finish) { //throws IOException {

		// Prepare line of output

		StringBuilder logEntry = new StringBuilder();			
		logEntry.append(String.format("%-24s", new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a").format(new java.util.Date())));
		logEntry.append(String.format("%-30s", event));
		logEntry.append(String.format("%1$8s","$"+start.toString()));
		logEntry.append(String.format("%1$8s","$"+finish.toString()));

		// Define log file

		File logFile = new File("Log.txt");

		// If log file does not exist, create it

		if (!logFile.exists()) {
			
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				System.out.println("\n***WARNING: UNABLE TO CREATE LOG FILE***\n");
			}
			
			
		} else if (logFile.exists() && logFile.isDirectory()) {
			String x = "\n***WARNING: DIRECTORY WITH NAME \"Log.txt\" exists.***\n";
			String x2 = x;
			System.out.println(x2);
		}


		try (FileOutputStream f = new FileOutputStream(logFile,true); 
			PrintWriter pw = new PrintWriter(f)) {

			// make the log entry

			pw.println(logEntry);
			pw.flush();

		} catch (IOException e) {
			extracted();
		}

	}

	private void extracted() {
		System.out.println("\n***WARNING: LOG FILE HAS BEEN DELETED.  COULD NOT RECORD TRANSACTION.***\n");
	}
}
