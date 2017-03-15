package com.sh.strategy;

public class NormalStrategy implements IStrategy{

	@Override
	public String operate(String info) {
		return "NormalStrategy:" + info;
	}

}
