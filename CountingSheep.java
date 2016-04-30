/*
	Problem statement:
	Bleatrix Trotter the sheep has devised a strategy that helps her fall asleep faster.
	First, she picks a number N. Then she starts naming N, 2 × N, 3 × N, and so on.
	Whenever she names a number, she thinks about all of the digits in that number.
	She keeps track of which digits (0, 1, 2, 3, 4, 5, 6, 7, 8, and 9) she has seen at least 
	once so far as part of any number she has named. Once she has seen each of the ten 
	digits at least once, she will fall asleep.

	Bleatrix must start with N and must always name (i + 1) × N directly after i × N.
	For example, suppose that Bleatrix picks N = 1692. She would count as follows:

	N = 1692. Now she has seen the digits 1, 2, 6, and 9.
	2N = 3384. Now she has seen the digits 1, 2, 3, 4, 6, 8, and 9.
	3N = 5076. Now she has seen all ten digits, and falls asleep.
	What is the last number that she will name before falling asleep? If she will count forever, print INSOMNIA instead.
*/

public class CountingSheep {
	private void printOutput(int caseNum, int chosenNum) {
		if (chosenNum < 1) {
			System.out.println("Case #" + caseNum + ": INSOMNIA");
			return;
		}
		
		int digits = 0;
		int j = 1;
		int currentNum = chosenNum;
		String parsedNum = Integer.toString(currentNum);
		//Need to make sure that the initial value of all elements is not 0
		int[] digitsFound = {123, 123, 123, 123, 123, 123, 123, 123, 123, 123};
		int currentDigit;
		
		while (digits < 10) {
			currentNum = chosenNum * j;
			parsedNum = Integer.toString(currentNum);
			j++;
			
			for (int i = 0; i < parsedNum.length(); i++) {
				currentDigit = Character.getNumericValue(parsedNum.charAt(i));
				if (!checkIfExists(currentDigit, digitsFound)) {
					digitsFound[digits] = currentDigit;
					digits++;
				}
			}
		}
		
		System.out.println("Case #" + caseNum + ": " + Integer.toString(currentNum));
	}
	
	private boolean checkIfExists(int digit, int[] digitsFound) {
		for (int i = 0; i < digitsFound.length; i++) {
			if (digit == digitsFound[i]) return true;
		}
		
		return false;
	}
	
	public static void main(String args []) {
		CountingSheep sheep = new CountingSheep();
		int i = 1;
		
		while (i < Integer.parseInt(args[0]) + 1) {
			sheep.printOutput(i, Integer.parseInt(args[i]));
			i++;
		}
		
		System.exit(0);
	}
}