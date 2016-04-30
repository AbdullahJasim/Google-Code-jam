public class Pancakes {
	private void printOutput(int caseNum, String pancakes) {
		char[] newPancakes = pancakes.toCharArray();
		int steps = flipPancakes(newPancakes, 0);
		System.out.println("Case #" + caseNum + ": " + steps);
	}
	
	//Find the pancake with the blank page at the very bottom
	private int lastPancake(char[] pancakes) {
		for (int i = pancakes.length - 1; i > 0; i--) {
			if (pancakes[i] != '+') {
				return i;
			}
		}
		
		return 0;
	}
	
	//Recursive function, flip all necessary top pancakes then call again
	//Return the number of steps when all pancakes are on the happy side
	private int flipPancakes(char[] pancakes, int step) {
		if (checkIfReady(pancakes)) return step;
		
		char[] flippedPancakes = new char[pancakes.length];
		int lastPancake = lastPancake(pancakes) + 1;
	
		for (int i = lastPancake; i < pancakes.length; i++) {
			flippedPancakes[i] = '+';
		}
		
		int j = lastPancake;
		
		for (int i = 0; i < lastPancake; i++) {
			if (pancakes[i] == '-') {
				flippedPancakes[i] = '+';
			} else {
				flippedPancakes[i] = '-';
			}
		}

		return flipPancakes(flippedPancakes, step + 1);
	}
	
	private boolean checkIfReady(char[] pancakes) {
		boolean ready = true;
		
		for (int i = 0; i < pancakes.length; i++) {
			if (pancakes[i] != '+') return false;
		}
		
		return true;
	}
	
	
	public static void main(String args []) {
		int i = 1;
		Pancakes pancakes = new Pancakes();
		
		while (i < Integer.parseInt(args[0]) + 1) {
			pancakes.printOutput(i, args[i]);
			i++;
		}
		
		System.exit(0);
	}
}