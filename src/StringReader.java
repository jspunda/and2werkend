
public class StringReader {
	
	private Exp exp;
	private String input;
	private StringBuilder builder;
	
	public StringReader(String input) {
		this.input = input;
		builder = new StringBuilder();
		exp = buildExp(this.input);
	}
	
	private Exp buildExp(String input) {
		char head = input.charAt(0);
			if (head == '(') {
				String temp = builder.toString();
				builder = new StringBuilder();
				return new FExp(temp, buildExp(splitComma(input)[0]), buildExp(splitComma(input)[1]));
			} 
			else {
				builder.append(head);
				if(input.length() == 1) {
					String temp2 = builder.toString();
					builder = new StringBuilder();
					return new SExp(temp2);
				}
				else {
					return buildExp(input.substring(1));
				}
			}
	}
	
	private String[] splitComma(String input) {
		String[] returnString = new String[2];
		int countBrackets = 0;
		for (int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '(')
				countBrackets++;
			else if (input.charAt(i) == ')')
				countBrackets--;
			else if(countBrackets == 1 && input.charAt(i) == ',') { 
				returnString[0] = input.substring(1, i);
				returnString[1] = input.substring(i+1, input.length()-1);
				break;
			}
		}
		return returnString;
	}
	
	public Exp getExp() {
		return exp;
	}
}
