/**
 * StringReader class
 * @author L. van den Bercken, s4057384 & J. Spunda, s4174615
 * Reads a string and creates an expression tree from it
 */
public class StringReader {
	
	/**
	 * root: The root of the expression tree
	 */
	private Exp root;
	
	/**
	 * Constructor, starts building
	 * @param input
	 */
	public StringReader(String input)  {
		root = buildExpr(input);
	}
	
	/**
	 * This function splits a given String,
	 * in the form of an expression, and returns
	 * the left and right part of that expression,
	 * as an array of 2 strings
	 * @param input A string in the form of an expression
	 * @return returnString[0] is the left part and
	 * returnString[1] is the right part
	 */
	private String[] splitComma(String input) {
		String[] returnString = new String[2];
		int countBrackets = 0;
		for (int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '(')
				countBrackets++;
			else if (input.charAt(i) == ')')
				countBrackets--;
			else if(countBrackets == 1 && input.charAt(i) == ',') {
				returnString[0] = input.substring(2, i);
				returnString[1] = input.substring(i+1, input.length()-1);
				break;
			}
		}
		return returnString;
	}
	
	/**
	 * Recursively goes through input string
	 * and builds up a tree of expressions
	 * @param in
	 * @return root of the expression tree
	 */
	private Exp buildExpr(String in) {
		boolean isFExp = false;
		StringBuilder s = new StringBuilder();
		//Check for FExp or not
		if (in.contains("(")) {
			isFExp = true;
		}
		int i;
		//Gather chars before opening bracket
		for(i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			if (c !='(') {
				s.append(in.charAt(i));
			} else {
				break;
			}
		}
		//Recursively check left and right expressions
		if(isFExp) {
			String n = in.substring(i-1);
			String left = splitComma(n)[0];
			String right = splitComma(n)[1];
			return new FExp(s.toString(),  buildExpr(left), buildExpr(right));
		}
		return new SExp(s.toString());
	}
	
	/**
	 * Getter for root
	 * @return root of the expression tree
	 */
	public Exp getRoot(){
		return root;
	}
}