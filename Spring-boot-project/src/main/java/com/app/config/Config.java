package com.app.config;

import java.util.ArrayList;
import java.util.Arrays;


public class Config {
	
	public static ArrayList<currencyTypes> currencyList = new ArrayList<>(Arrays.asList(currencyTypes.USDINR));
	
	public static enum currencyTypes {
		
		USDINR
		
	}
	
}
