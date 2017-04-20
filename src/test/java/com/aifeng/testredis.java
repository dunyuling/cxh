package com.aifeng;

import org.junit.Test;

public class testredis {

	@Test
	public void testObj(){
		int i = 3;
		String a = "3";
		test(a);
	}
	
	public void test(Object obj){
		System.out.println(obj.getClass());
	}
}
