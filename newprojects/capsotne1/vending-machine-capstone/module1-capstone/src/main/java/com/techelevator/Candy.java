package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Items {

	public Candy(String name, BigDecimal price) {
		super(name, price);
	}

	String sound = "Munch Munch, Yum!";

	@Override
	public String getSound() {
		return sound;
	}

}
