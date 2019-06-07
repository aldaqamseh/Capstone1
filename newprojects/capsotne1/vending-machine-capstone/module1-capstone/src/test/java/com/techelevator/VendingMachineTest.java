package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {
	
	VendingMachine sut;

	@Before
	public void setUp() throws Exception {
		sut = new VendingMachine();
	}

	@After
	public void tearDown() throws Exception {
	}

	
	//vend() is inherently tested within each of the other methods' tests
	//feedMoney() is inherently tested within each of the other methods' tests
	@Test
	public void testIsInStock() {
		sut.refill();
		assertTrue(sut.isInStock("A1"));
		sut.feedMoney(new BigDecimal("20.00"));
		for(int i = 0; i < 5; i++) {
			sut.vend("A1");
		}
		assertFalse(sut.isInStock("A1"));
	}
	
	@Test
	public void testCanPurchase() {
		sut.refill();
		sut.feedMoney(new BigDecimal("6.00"));
		assertTrue(sut.canPurchase("A1"));
		sut.vend("A1");
		assertFalse(sut.canPurchase("A1"));
		sut.vend("D2");
		sut.vend("C1");
		sut.vend("D4"); //Should take us to $0.00
		assertFalse(sut.canPurchase("D4"));	
	}
	
	@Test
	public void testDisplayItemsAfterRefill() {
		String expectedOutput =
				"A1  Potato Crisps        $3.05  (5)\n" +
				"A2  Stackers             $1.75  (5)\n" +
				"A3  Grain Waves          $2.75  (5)\n" +
				"A4  Cloud Popcorn        $3.65  (5)\n" +
				"B1  Moonpie              $1.80  (5)\n" +
				"B2  Cowtales             $1.50  (5)\n" +
				"B3  Wonka Bar            $1.50  (5)\n" +
				"B4  Crunchie             $1.75  (5)\n" +
				"C1  Cola                 $1.25  (5)\n" +
				"C2  Dr. Salt             $1.50  (5)\n" +
				"C3  Mountain Melter      $1.50  (5)\n" +
				"C4  Heavy                $1.50  (5)\n" +
				"D1  U-Chews              $0.85  (5)\n" +
				"D2  Little League Chew   $0.95  (5)\n" +
				"D3  Chiclets             $0.75  (5)\n" +
				"D4  Triplemint           $0.75  (5)\n";
		sut.refill();
		assertEquals(expectedOutput,sut.displayItems());
		
	}
	
	
	@Test
	public void testDisplayItemsAfterDepletingOneItem() {
		String expectedOutput =
				"A1  Potato Crisps        $3.05  (5)\n" +
				"A3  Grain Waves          $2.75  (5)\n" +
				"A4  Cloud Popcorn        $3.65  (5)\n" +
				"B1  Moonpie              $1.80  (5)\n" +
				"B2  Cowtales             $1.50  (5)\n" +
				"B3  Wonka Bar            $1.50  (5)\n" +
				"B4  Crunchie             $1.75  (5)\n" +
				"C1  Cola                 $1.25  (5)\n" +
				"C2  Dr. Salt             $1.50  (5)\n" +
				"C3  Mountain Melter      $1.50  (5)\n" +
				"C4  Heavy                $1.50  (5)\n" +
				"D1  U-Chews              $0.85  (5)\n" +
				"D2  Little League Chew   $0.95  (5)\n" +
				"D3  Chiclets             $0.75  (5)\n" +
				"D4  Triplemint           $0.75  (5)\n";
		sut.refill();
		sut.feedMoney(new BigDecimal("10.00"));
		for (int i = 5; i > 0; i--) {
			sut.vend("A2");
		}
		assertEquals(expectedOutput, sut.displayItems());
	}
	
	
	@Test
	public void testDisplayItemsWithoutRefillingMachineAtStart() {
		assertEquals("The vending machine is empty.",sut.displayItems());
		
	}
	
	@Test
	public void testDisplayItemsAfterDepletingAllItems() {
		sut.refill();
		String[] allSlots= {"A1","A2","A3","A4","B1","B2","B3","B4","C1","C2","C3","C4","D1","D2","D3","D4"};
		for(String slot : allSlots) {
			for(int i = 1; i <=5; i++) {
				sut.vend(slot);
			}
		}
		assertEquals("The vending machine is empty.",sut.displayItems());
	}
	

}
