package com.fitness.util;

import org.nutz.mvc.annotation.At;

@At("/testInterface")
public class TestInterface {
	
	@At("/hello")
	public String hello(){
		return "1233";
		
	}
	
	
}
