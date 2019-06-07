package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Items {

	public Gum(String name, BigDecimal price) {
		super(name, price);
	}

	String sound = "Chew Chew, Yum!";

	@Override
	public String getSound() {
		return sound;
	}
}
