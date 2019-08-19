import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockSpawner {
	ArrayList<Integer> pathTaken;
	
	public BlockSpawner() {
		pathTaken = new ArrayList<Integer>();
	}
		
	public void runProgram() {
		int[] allArray = {-1, 6, 1};
		int[] sixOneArray = {6, 1};
		int[] negOneSixArray = {-1, 6};
		int[] rowOne =   {1,  7, 13, 19, 25, 31, 37, 43, 49, 55};
		int[] rowTwo =   {2,  8, 14, 20, 26, 32, 38, 44, 50, 56};
		int[] rowThree = {3,  9, 15, 21, 27, 33, 39, 45, 51, 57};
		int[] rowFour =  {4, 10, 16, 22, 28, 34, 40, 46, 52, 58};
		int[] rowFive =  {5, 11, 17, 23, 29, 35, 41, 47, 53, 59};
		int[] rowSix =   {6, 12, 18, 24, 30, 36, 42, 48, 54, 60};
		
		int randomStart = getRandomNumberInRange(1, 6);
		System.out.println(randomStart);

		pathTaken.add(randomStart);
		int number = randomStart;
		System.out.println("1: " + randomStart);
		
		// start at 2 since 1 has already printed and we don't start at 0 here
		int iteration = 2;
		
		int lastNumber = 0;
		int secondLastNumber = 0;
		
		while(number < 60) {
			
			int next = 0;
			boolean nextInRowOne = checkNumber(number, rowOne);
			boolean nextInRowSix = checkNumber(number, rowSix);
				
			int difference = number - secondLastNumber;
			
			if (!nextInRowOne && !nextInRowSix 
					&& (difference == 7)){
				next = sixOneArray[getRandomNumberInRange(0, 1)];
			}else if (!nextInRowOne && !nextInRowSix 
					&& (difference == 5)){
				next = negOneSixArray[getRandomNumberInRange(0, 1)];
			}else if (!nextInRowOne && !nextInRowSix 
					&& ((difference == 2) || (difference == -2))) {
				next = 6;
			}else if (!nextInRowOne && !nextInRowSix){
				next = allArray[getRandomNumberInRange(0, 2)];
			}else if ((nextInRowOne || nextInRowSix) && ((difference == 5) || (difference == -2)
					|| (difference == 7) || (difference == 2))) {
				next = 6;
			}else if (nextInRowOne) {
				next = sixOneArray[getRandomNumberInRange(0, 1)];
			}else if (nextInRowSix) {
				next = negOneSixArray[getRandomNumberInRange(0, 1)];
			}
			
			secondLastNumber = lastNumber;
			lastNumber = number;
			number += next;
			
			pathTaken.add(number);
			System.out.println((iteration) + ": " + number + " - last: " + lastNumber + " - 2ndLast: " + secondLastNumber + " - difference: " + difference);
			iteration++;
		}
		printGrid(pathTaken);
	}
	
	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	// Used for an Integer Array
	public boolean checkNumber(int num, int[] numArray) {
		boolean isInArray = false;
		
		for (int i = 0; i < numArray.length; i++) {
			if (num == numArray[i]) {
				isInArray = true;
			}
		}
		return isInArray;
	}
	
	// Used for an Integer ArrayList
	public boolean checkNumber(int num, ArrayList<Integer> numArray) {
		boolean isInArray = false;
		
		for (int i = 0; i < numArray.size(); i++) {
			if (num == numArray.get(i)) {
				isInArray = true;
			}
		}
		return isInArray;
	}
	
	public void testStuff() {

	}
	
	public void printGrid(ArrayList<Integer> arrayList) {
		System.out.println();
		int[] row10 = {55, 56, 57, 58, 59, 60};
		int[] row9 =  {49, 50, 51, 52, 53, 54};
		int[] row8 =  {43, 44, 45, 46, 47, 48};
		int[] row7 =  {37, 38, 39, 40, 41, 42};
		int[] row6 =  {31, 32, 33, 34, 35, 36};
		int[] row5 =  {25, 26, 27, 28, 29, 30};
		int[] row4 =  {19, 20, 21, 22, 23, 24};
		int[] row3 =  {13, 14, 15, 16, 17, 18};
		int[] row2 =  { 7,  8,  9, 10, 11, 12};
		int[] row1 =  { 1,  2,  3,  4,  5,  6};
		
		printAllDashesAndX(row10, 10);
		printAllDashesAndX(row9, 9);
		printAllDashesAndX(row8, 8);
		printAllDashesAndX(row7, 7);
		printAllDashesAndX(row6, 6);
		printAllDashesAndX(row5, 5);
		printAllDashesAndX(row4, 4);
		printAllDashesAndX(row3, 3);
		printAllDashesAndX(row2, 2);
		printAllDashesAndX(row1, 1);
		
	}
	
	public void printAllNumbersAndX(int[] array, int arrayNumber) {
		if (arrayNumber > 2) {
			for (int num : array) {
				if (checkNumber(num, pathTaken)) {
					System.out.print(" X ");
				}else {
					System.out.print(num + " ");
				}	
			}
		}else if (arrayNumber == 2) {
			for (int num : array) {
				if (checkNumber(num, pathTaken)) {
					System.out.print(" X ");
				}else {
					if (num < 10) {
						System.out.print(" " + num + " ");
					}else {
						System.out.print(num + " ");
					}
					
				}	
			}
		}else {
			for (int num : array) {
				if (checkNumber(num, pathTaken)) {
					System.out.print(" X ");
				}else {
					System.out.print(" " + num + " ");
				}	
			}
		}
		System.out.println();
	}
	
	public void printAllDashesAndX(int[] array, int arrayNumber) {
		for (int num : array) {
			
			if (checkNumber(num, pathTaken)) {
				System.out.print(" X ");
			}else {
				System.out.print(" - ");
			}
		}
		System.out.println();
	}
	
}
