/*
	Problem statement:
	You just made a new friend at an international puzzle conference, 
	and you asked for a way to keep in touch. You found the following note 
	slipped under your hotel room door the next day:

	"Salutations, new friend! I have replaced every digit of my phone 
	number with its spelled-out uppercase English representation 
	("ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", 
	"EIGHT", "NINE" for the digits 0 through 9, in that order), and then 
	reordered all of those letters in some way to produce a string S. It's 
	up to you to use S to figure out how many digits are in my phone number 
	and what those digits are, but I will tell you that my phone number consists 
	of those digits in nondecreasing order. Give me a call... if you can!"
 */

import java.util.Collections;
import java.util.ArrayList;

public class GetTheDigits {	
	private String[] digits = {
			"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE",
			"SIX", "SEVEN", "EIGHT", "NINE"};
	
	private ArrayList<Integer> result;
	int n;
	
	GetTheDigits() {
		result = new ArrayList<Integer>();
		n = 0;
	}
	
	private String getDigits(String s, int i) {
		if (s.equals("")) return "";
		if (i > 9) return s;
		
		for (int j = 0; j < digits[i].length(); j++) {
			if (s.charAt(0) == digits[i].charAt(j)) {
				String temp = remove(s, digits[i]);
				
				if (temp == s) return getDigits(s, i + 1);
				
				result.add(i);
				n++;
				
				//Base case, the string is empty after removing the digit from it
				if (temp.equals("")) return "";
				
				//Successful parsing
				if (getDigits(temp, 0).equals("")) return "";
			}
		}
		
		//The parsing was not successful, try with the next digit
		return getDigits(s, i + 1);
	}
	
	//Remove a string from another
	//If the full string is not found, return the original string
	private String remove(String s, String num) {
		StringBuilder temp = new StringBuilder();
		String temp2 = num;
		int counter = 0;
		boolean removed = false;
		
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < temp2.length(); j++) {
				if (s.charAt(i) == temp2.charAt(j)) {
					removed = true;
					counter++;
					temp2 = removeChar(temp2, temp2.charAt(j));
					break;
				}
			}
			
			if (!removed) temp.append(s.charAt(i));
			
			removed = false;
		}
		
		//Number not found, return original string
		if (counter < num.length()) return s;
		
		//Number found, return new string
		return temp.toString();
	}
	
	//Remove one character from a string
	private String removeChar(String s, char c) {
		StringBuilder temp = new StringBuilder();
		boolean found = false;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == c && !found) {
				found = true;
			} else {
				temp.append(s.charAt(i));
			}
		}
		
		return temp.toString();
	}
	
	public static void main(String args []) {
		GetTheDigits digits = new GetTheDigits();
		String temp = "WEIGHFOXTOURIST";
		
		digits.getDigits(temp, 0);
		Collections.sort(digits.result);
		
		System.out.println(digits.result);
	}
}