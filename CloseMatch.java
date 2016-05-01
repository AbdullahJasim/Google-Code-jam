/*
	Problem statement:
	You are attending the most important game in sports history. 
	The Oceania Coders are playing the Eurasia Jammers in the 
	Centrifugal Bumble-Puppy world finals. Unfortunately, you were 
	sleep deprived from all the anticipation, so you fell asleep during 
	the game!

	The scoreboard is currently displaying both scores, perhaps 
	with one or more leading zeroes (because the scoreboard displays 
	a fixed number of digits). While you were asleep, some of the 
	lights on the scoreboard were damaged by strong ball hits, so one
	or more of the digits in one or both scores are not being displayed.
	
	You think close games are more exciting, and you would like to 
	imagine that the scores are as close as possible. Can you fill in 
	all of the missing digits in a way that minimizes the absolute 
	difference between the scores? If there is more than one way to 
	attain the minimum absolute difference, choose the way that minimizes 
	the Coders' score. If there is more than one way to attain the minimum 
	absolute difference while also minimizing the Coders' score, choose 
	the way that minimizes the Jammers' score.
*/

public class CloseMatch {
	private String scoreOne;
	private String scoreTwo;
	int bigger;
	
	CloseMatch(String one, String two) {
		this.scoreOne = one;
		this.scoreTwo = two;
		bigger = 0;
	}
	
	private void getMinDiff() {
		StringBuilder tempOne = new StringBuilder();
		StringBuilder tempTwo = new StringBuilder();
		
		for (int i = 0; i < scoreOne.length(); i++) {
			//We have five cases:
			//Case 1: the two numbers are the same
			//Do nothing, go for next digit
			if (scoreOne.charAt(i) == scoreTwo.charAt(i) && scoreOne.charAt(i) != '?') {
				tempOne.append(scoreOne.charAt(i));
				tempTwo.append(scoreTwo.charAt(i));
				continue;
			}
			
			//If they are not the same:
			
			//Case 2: two of them are unknown
			if (scoreOne.charAt(i) == '?' && scoreTwo.charAt(i) == '?') {
				//Case 2.a: none is bigger so far, put them both as 0
				if (bigger == 0) {
					tempOne.append('0');
					tempTwo.append('0');
				//Case 2.b: first score has been higher so far
				//Set first as 0, and second as 9
				} else if (bigger == 1) {
					tempOne.append('0');
					tempTwo.append('9');
				//Case 2.c: opposite of 2.b
				} else {
					tempOne.append('9');
					tempTwo.append('0');
				}
				
				continue;
			}
			
			//Case 3: first one is unknown, second is not
			if (scoreOne.charAt(i) == '?' && scoreTwo.charAt(i) != '?') {
				//Case 3.a: none is bigger so far, put 2nd to be equal
				if (bigger == 0) {
					tempOne.append(scoreTwo.charAt(i));
					tempTwo.append(scoreTwo.charAt(i));
				//Case 3.b: first one has been bigger so far
				//Put it as 0, keep 2nd
				} else if (bigger == 1) {
					tempOne.append('0');
					tempTwo.append(scoreTwo.charAt(i));
				//Case 3.c: second one has been bigger so far
				//Set the first to 9, keep 2nd
				} else {
					tempOne.append('9');
					tempTwo.append(scoreTwo.charAt(i));
				}
				
				continue;
			}
			
			//Case 4: opposite of case 3
			if (scoreOne.charAt(i) != '?' && scoreTwo.charAt(i) == '?') {
				//Case 3.a: none is bigger so far, put 1st to be equal
				if (bigger == 0) {
					tempOne.append(scoreOne.charAt(i));
					tempTwo.append(scoreOne.charAt(i));
				//Case 3.b: first one has been bigger so far
				//Put it as 9, keep 1st
				} else if (bigger == 1) {
					tempOne.append(scoreTwo.charAt(i));
					tempTwo.append('9');
				//Case 3.c: second one has been bigger so far
				//Set the second to 0, keep 1st
				} else {
					tempOne.append(scoreTwo.charAt(i));
					tempTwo.append('0');
				}
				
				continue;
			}
			
			//Case 5: both are known
			if (scoreOne.charAt(i) > scoreTwo.charAt(i)) {
				tempOne.append(scoreOne.charAt(i));
				tempTwo.append(scoreTwo.charAt(i));
				bigger = 1;
			} else {
				tempOne.append(scoreOne.charAt(i));
				tempTwo.append(scoreTwo.charAt(i));
				bigger = 2;
			}
		}
		
		scoreOne = tempOne.toString();
		scoreTwo = tempTwo.toString();
	}
	
	public static void main(String args []) {
		CloseMatch scores = new CloseMatch("?501?8", "??9760");
		scores.getMinDiff();
		
		System.out.println(scores.scoreOne);
		System.out.println(scores.scoreTwo);
	}
}