package com.smbcgroup.training.roman;

import java.util.HashMap;

public class RomanNumeralsConverter {
	
	
	public String toRomanNumeral(Integer integerValue) {
		String result = "";
		String[] romanNums = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		int[] arabicNums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		for (int i = 0; i < romanNums.length; i++) {
			while (integerValue >= arabicNums[i]) {
				result += romanNums[i];
				integerValue -= arabicNums[i];
			}
		}
		
		return result;
	}
	
	public Integer toInteger(String romanNumeral) {
		Integer result = 0;
		
		HashMap<String, Integer> valuesMap = new HashMap<String, Integer>();
		valuesMap.put("I", 1);
		valuesMap.put("V", 5);
		valuesMap.put("X", 10);
		valuesMap.put("L", 50);
		valuesMap.put("C", 100);
		valuesMap.put("D", 500);
		valuesMap.put("M", 1000);
		
		for(int i = 0; i < romanNumeral.length(); i++) {
			char ch = romanNumeral.charAt(i);
			
			if (i > 0 && (valuesMap.get(ch) > valuesMap.get(romanNumeral.charAt(i-1)))) {
				
			}
		}
		return 0;
	}
	
	
	
	
	
	

}
