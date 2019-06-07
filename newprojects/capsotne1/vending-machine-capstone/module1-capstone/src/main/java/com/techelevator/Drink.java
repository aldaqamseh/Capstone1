package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Items {

	public Drink(String name, BigDecimal price) {
		super(name, price);
	}

	String sound = "Glug Glug, Yum!";

	@Override
	public String getSound() {
		return sound;
	}

}
