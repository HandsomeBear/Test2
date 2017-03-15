package com.sh.strategy;

public class SpecialStrategy implements IStrategy{

	@Override
	public String operate(String info) {
		return "SpecialStrategy:" + info;
	}

}
