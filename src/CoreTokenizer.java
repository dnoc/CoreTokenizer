import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public final class CoreTokenizer {

	//methods for each kind of token
	
	/**
	 * gatherLowercase increments the index until it finds a non-lowercase char or EOF.
	 * Then, calls reservedWord to check against reserved word list.
	 * Returns -1 at error.
	 * 
	 * @param s
	 * 		string input
	 * @param x
	 * 		index of char, originally char at x is a lowercase
	 * @return
	 */
	public static int gatherLowercase(String s, int x) {
		//startIndex is the index of the first letter of the word.
		int startIndex = x;
		/*
		 * while loop increments index x if the next char is lowercase and not EOF
		 */
		while ((x + 1 < s.length()) && Character.isLowerCase(s.charAt(x+1))) {
			x++;
		}
		/*
		 * if the next char is EOF, this returns the value of x 
		 */
		if (x + 1 >= s.length()) {
			x = reservedWord(startIndex, x, s);
			return x;
		}		
		/*
		 * switch statement does nothing if the next char is whitespace or symbol,
		 * assigns error value to x if next char is anything else (A-Z, 0-9)
		 */
		switch (s.charAt(x+1)) {
		case ';':
		case ',':
		case '=':
		case '!':
		case '[':
		case ']':
		case '&':
		case '|':
		case '(':
		case ')':
		case '+':
		case '-':
		case '*':
		case '<':
		case '>':
		case ' ':
		case '\t':
		case '\r':
		case '\n':
			x = reservedWord(startIndex, x, s);
			break;
		default:
			x = -1;
			break;
		}
		return x;		
	}
	
	/**
	 * gatherIdentifier increments when the next char is a legal ID char (A-Z then 0-9),
	 * returns x when the next char is a legal token end char,
	 * returns x = -1 when there is an error.
	 * 
	 * @param s
	 * 		Input String.
	 * @param x
	 * 		Character index.
	 * @return
	 */
	public static int gatherIdentifier(String s, int x) {
		/*
		 * while loop increments index x if the next char is uppercase and not EOF
		 */
		while ((x + 1 < s.length()) && Character.isUpperCase(s.charAt(x+1))) {
			x++;
		}
		//loop runs until a return happens or EOF
		while (x + 1 < s.length()) {
			//switch checks next char
			switch (s.charAt(x + 1)) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				//if the next char is a digit, increments x
				x++;
				break;
			case ';':
			case ',':
			case '=':
			case '!':
			case '[':
			case ']':
			case '&':
			case '|':
			case '(':
			case ')':
			case '+':
			case '-':
			case '*':
			case '<':
			case '>':
			case ' ':
			case '\t':
			case '\r':
			case '\n':
				//these cases are legal token endings, returns x
				return x;
			default:
				//default happens when char at x + 1 is illegal
				x = -1;
				return x;
			}			
		}
		//this return happens at EOF
		return x;
	}

	/**
	 * gatherInt increments x when the next char is a digit,
	 * returns when it is a legal token ending char,
	 * or returns x = -1 when there is an error (A-Z, a-z).
	 * 
	 * @param s
	 * 		Input string.
	 * @param x
	 * 		Character index.
	 * @return
	 */
	public static int gatherInt(String s, int x) { 
		//loop runs until a return happens or EOF
		while (x + 1 < s.length()) {
			//switch checks next char
			switch (s.charAt(x + 1)) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				//if the next char is a digit, increments x
				x++;
				break;
			case ';':
			case ',':
			case '=':
			case '!':
			case '[':
			case ']':
			case '&':
			case '|':
			case '(':
			case ')':
			case '+':
			case '-':
			case '*':
			case '<':
			case '>':
			case ' ':
			case '\t':
			case '\r':
			case '\n':
				//these cases are legal token endings, returns x
				return x;
			default:
				//default happens when char at x + 1 is illegal
				x = -1;
				return x;
			}			
		
		}
		//this return happens if char at x + 1 is EOF
		return x;
	}
		
	
	/**
	 * reservedWord checks a word in the input string to see if it is a reserved word.
	 * If it is, reservedWord prints appropriate token and returns endIndex.
	 * If it is not a reserved word, method does not print, then returns -1.
	 *   
	 * @param startIndex
	 * 		integer position of first letter in word
	 * @param endIndex
	 * 		integer position of last letter in word
	 * @param s
	 * 		input string
	 */
	public static int reservedWord(int startIndex, int endIndex, String s) {
		/*
		 * switch-case checks substring (word) against reserved word list
		 * returns endIndex, or -1 if error.
		 */
		switch (s.substring(startIndex, endIndex+1)) {
		case "program": // "+ - program;"
			System.out.println("1");
			return endIndex;
		case "begin":
			System.out.println("2");
			return endIndex;
		case "end":
			System.out.println("3");
			return endIndex;
		case "int":
			System.out.println("4");
			return endIndex;
		case "if":
			System.out.println("5");
			return endIndex;
		case "then":
			System.out.println("6");
			return endIndex;
		case "else":
			System.out.println("7");
			return endIndex;
		case "while":
			System.out.println("8");
			return endIndex;
		case "loop":
			System.out.println("9");
			return endIndex;
		case "read":
			System.out.println("10");
			return endIndex;
		case "write":
			System.out.println("11");
			return endIndex;
		default:
			return -1;
		}

	}
	
	
	/**
	 * Main method, opens file from command line, collects tokens and outputs their numbers.
	 * Number 34 is the error token, then the program terminates.
	 * If it gets through the whole file without error, prints EOF token "33".
	 * 
	 * @param args
	 * 		Command line arguments.
	 */
	public static void main(String[] args){
		
		//open file
		Scanner sc;
		try {
			sc = new Scanner(new File (args[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error opening file.");
			return;
		}		
		/*
		 * read line, while loop iterates over each line in the file
		 */
		while (sc.hasNext()) {
			String nextLine = sc.nextLine();
			int i = 0;
			/*
			 * while loop iterates over length of string
			 */
			while (i < nextLine.length())	{
				switch (nextLine.charAt(i)) {
					
					/* case for each lowercase letter falls through switch statement.
					* when char = lowercase it calls gatherLowercase.
					* gatherLowercase increments i if the next char is lowercase,
					* returns i if the next char is ws/special symbol, returns -1 at error token.
					* gatherLowercase handles non-error printing.
					* if -1 is returned, program terminates.
					*/
					case 'a': 
					case 'b': 
					case 'c': 
					case 'd':
					case 'e':
					case 'f':
					case 'g':
					case 'h':
					case 'i':
					case 'j':
					case 'k':
					case 'l':
					case 'm':
					case 'n':
					case 'o':
					case 'p':
					case 'q':
					case 'r':
					case 's':
					case 't':
					case 'u':
					case 'v':
					case 'w':
					case 'x':
					case 'y':
					case 'z':
						i = gatherLowercase(nextLine, i);
						if (i == -1) {
							System.out.println("34: Error");
							sc.close();
							return;
						}
						i++;
						break;
						
					/* cases for each digit falls through switch statement.
					 * case calls gatherInt, which increments i if the next char is a digit,
					 * returns i at WS/special symbol, or returns -1 at error (anything else).
					 * case terminates when i = -1.
					 */
					case '0':
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
						i = gatherInt(nextLine, i);
						if (i == -1) {
							System.out.println("34: Error");
							sc.close();
							return;
						}
						System.out.println("31");
						i++;
						break;
					
					/* case for each uppercase letter falls through switch statement.
					* when char = uppercase it calls gatherIdentifier.
					* gatherIdentifier increments i if the next char is uppercase,
					* calls gatherInt if next char is a digit
					* returns i if the next char is ws/special symbol, returns -1 at error token.
					* case statement when i = -1 terminates.
					*/
					case 'A':
					case 'B':
					case 'C':
					case 'D':
					case 'E':
					case 'F':
					case 'G':
					case 'H':
					case 'I':
					case 'J':
					case 'K':
					case 'L':
					case 'M':
					case 'N':
					case 'O':
					case 'P':
					case 'Q':
					case 'R':
					case 'S':
					case 'T':
					case 'U':
					case 'V':
					case 'W':
					case 'X':
					case 'Y':
					case 'Z':
						i = gatherIdentifier(nextLine, i);
						if (i == -1) {
							System.out.println("34: Error");
							sc.close();
							return;
						}
						System.out.println("32");
						i++;
						break;
						
					/* cases for ;,[]()+-* are simple as they end any token.
					 * Prints the appropriate token and increments.
					 */
					case ';':
						System.out.println("12");
						i++;
						break;
					case ',':
						System.out.println("13");
						i++;
						break;
					case '[':
						System.out.println("16");
						i++;
						break;
					case ']':
						System.out.println("17");
						i++;
						break;
					case '(':
						System.out.println("20");
						i++;
						break;
					case ')':
						System.out.println("21");
						i++;
						break;
					case '+':
						System.out.println("22");
						i++;
						break;
					case '-':
						System.out.println("23");
						i++;
						break;
					case '*':
						System.out.println("24");
						i++;
						break;
						
					/* if the char is '=', this case checks to see if the next char is the same.
					 * if it is, prints equality token number.
					 * else it prints assignment token number.
					 * "if" statement also ensures it isn't EOF. 
					 */
					case '=':
						if ((i + 1 < nextLine.length()) && nextLine.charAt(i+1) == '=') {
							System.out.println("26");
							i++;
						} else {
							System.out.println("14");
						}
						i++;
						break;
						
					/* if the char is '!', this case checks to see if the next char is '='.
					 * if it is, prints '!=' token number.
					 * else it prints '!' token number.
					 * "if" statement also ensures it isn't EOF. 
					 */
					case '!':
						if ((i + 1 < nextLine.length()) && nextLine.charAt(i+1) == '=') {
							System.out.println("25");
							i++;
						} else {
							System.out.println("15");
						}
						i++;
						break;
						
					/* if the char is '<', this case checks to see if the next char is '='.
					 * if it is, prints '<=' token number.
					 * else it prints '<' token number.
					 * "if" statement also ensures it isn't EOF. 
					 */
					case '<':
						if ((i + 1 < nextLine.length()) && nextLine.charAt(i+1) == '=') {
							System.out.println("29");
							i++;
						} else {
							System.out.println("27");
						}
						i++;
						break;
						
					/* if the char is '>', this case checks to see if the next char is '='.
					 * if it is, prints '>=' token number.
					 * else it prints '>' token number.
					 * "if" statement also ensures it isn't EOF. 
					 */
					case '>':
						if ((i + 1 < nextLine.length()) && nextLine.charAt(i+1) == '=') {
							System.out.println("30");
							i++;
						} else {
							System.out.println("28");
						}
						i++;
						break;
						
					
					/* if the char is '|', this case checks to see if the next char is the same.
					 * if it is, prints "OR" token number.
					 * else it prints error token number and terminates. 
					 * "if statement also ensures it isn't EOF.
					 */
					case '|':
						if ((i + 1 < nextLine.length()) && (nextLine.charAt(i+1) == '|')) {
							System.out.println("19");
							i++;
						} else {
							System.out.println("34: Error");
							sc.close();
							return;
						}
						i++;
						break;
						
					/* if the char is '&', this case checks to see if the next char is the same.
					 * if it is, prints "&&" token number.
					 * else it prints error token number and terminates. 
					 * "if" statement also ensures it isn't EOF.
					 */
					case '&':
						if ((i + 1 < nextLine.length()) && (nextLine.charAt(i+1) == '&')) {
							System.out.println("18");
							i++;
						} else {
							System.out.println("34: Error");
							sc.close();
							return;
						}
						i++;
						break;
					
					/* 
					 * default case is any whitespace, it's just skipped then
					 */
					default:
						i++;
						break;
					}
			}
		
		}
		//print EOF Token if it got through the whole file without errors
		System.out.println("33: EOF");
		//close input
		sc.close();
		return;		
		
	}
		
}
