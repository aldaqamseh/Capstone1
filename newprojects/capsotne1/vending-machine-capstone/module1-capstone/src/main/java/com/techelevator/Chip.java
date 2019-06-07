package com.techelevator;

import java.math.BigDecimal;

public class Chip extends Items {

	public Chip(String name, BigDecimal price) {
		super(name, price);
	}

	String sound = "Crunch Crunch, Yum!";

	@Override
	public String getSound() {
		return sound;
	}

}
