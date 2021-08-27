package com.smbcgroup.training.roman;

public class RomanNumeralsConverter {
	
	public String toRomanNumeral(Integer integerValue) {
		String[] symbols = {"I", "V", "X", "L", "C", "D", "M", ""};
		String result = "";
		String str = integerValue.toString();
		int counter = 0;
		
		for (int i = str.length() - 1; i >= 0; i-- ) {
			int current = Character.getNumericValue(str.charAt(i));
			if (counter == 6 && current > 3) {
				throw new IllegalArgumentException("Number exceeds roman numeral limit!");
			}
			if (current % 5 == 0) {
				counter += 2;
				continue;
			} else if (current % 5 < 4) {
				result = symbols[counter+1].repeat(Math.floorDiv(current, 5)) + symbols[counter].repeat(current % 5) + result;
			} else if (current % 5 == 4) {
				result = symbols[counter] + symbols[counter+Math.floorDiv(current, 5)+1] + result;
			}
			counter += 2;
 		}
		System.out.println(str + " " + result);
		return result;
	}
	
	public Integer toInteger(String romanNumeral) {
		char prev = 'Z';
		int digit = 0;
		
		for (int i = 0; i < romanNumeral.length(); i++) {
			char current = romanNumeral.charAt(i);
			switch (current) {
			case 'I':
				digit += 1;
				break;
			case 'V':
				if (prev == 'I') {
					digit += 3;
				} else {
					digit += 5;
				}
				break;
			case 'X':
				if (prev == 'I') {
					digit += 8;
				} else {					
					digit += 10;
				}
				break;
			case 'L':
				if (prev == 'X') {
					digit += 30;
				} else {
					digit += 50;
				}
				break;
			case 'C':
				if (prev == 'X') {
					digit += 80;
				} else {					
					digit += 100;
				}
				break;
			case 'D':
				if (prev == 'C') {
					digit += 300;
				} else {
					digit += 500;
				}
				break;
			case 'M':
				if (prev == 'C') {
					digit += 800;
				} else {					
					digit += 1000;
				}
				break;
			default:
				digit += 0;
			}
			prev = current;
		}
		return digit;
	}

}
