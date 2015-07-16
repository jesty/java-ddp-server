package com.github.jesty.ddpserver.server.msg.method;

class TestClass{
	public String testMethod(String text){
		return text + " yes!";
	}
	
	public void testMethodVoid(String text){
		System.out.println("Void invoked with " + text);
	}
	
	public String testMethodNoParams(){
		return "No params yes!";
	}
	
	public static String staticTestMethod(String text){
		return text + " yes static!";
	}
	
	public int add(int a, int b){
		return a + b;
	}
	
	public int sub(int a, long b){
		return (int) (a - b);
	}
}