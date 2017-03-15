package com.sh.strategy;

public class Demo {

	public static void main(String[] args) {
		Context context = new Context(new NormalStrategy());
		context.operateWapper("This is just a demo...");
		
		Context contexts = new Context(new SpecialStrategy());
		contexts.operateWapper("This is just a demo...");
	}
}
