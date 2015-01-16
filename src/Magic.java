import java.util.Hashtable;

/**
 * Magic class
 * @author L. van den Bercken, s4057384 & J. Spunda, s4174615
 * This is where (most of the) magic happens
 */
public class Magic {

	/**
	 * table: Hashtable that saves intermediate results 
	 * (key: the string, value: its number)
	 * output: Output string that will be returned at the end
	 * counter: Counter to keep track of what number to give to an expression 
	 * (will be the value in the hashtable)
	 */
	private Hashtable<String, Integer> table;
	private String output;
	private int counter;
	
	/**
	 * Constructor for this class
	 * Instantiates the attributes and starts the magic
	 * @param input: String to be fixed
	 */
	public Magic (String input) {
		StringReader reader = new StringReader(input);
		Exp output = reader.getRoot();
		table = new Hashtable<String, Integer>();
		counter = 0;
		Exp pruned = fixString(output);
		this.output = pruned.getString();
	}
	
	/**
	 * Checks whether Exp in is contained in hashtable
	 * @param in: The expression to be found
	 * @return -1 if was not present in hashtable
	 * 	and the number in the hashtable if it was present
	 */
	private int contained(Exp in) {
		Object n = table.get(in.getString());
		if (n == null) {
			return -1;
		}
		return (Integer) n;
	}
	
	/**
	 * Recursively looks at all expressions, starting at 'in'
	 * and adds it to the hashtable if it was a new expression.
	 * If not, we may replace this expression (and its children) by
	 * the value found in the hashtable.
	 * @param in: The expression to be checked
	 * @return the root of the new, fixed, expression
	 */
	private Exp fixString(Exp in) {
		if(in == null) {
			return in;
		}
		int x = contained(in);
		if(x == -1) {
			table.put(in.getString(), counter);
			counter++;
		} else {
			in = new SExp(x+1+"");
		}
		Exp left = fixString(in.left);
		Exp right = fixString(in.right);
		return new FExp(in.value, left, right);
	}
	
	/**
	 * Getter for output;
	 * @return output
	 */
	public String toString() {
		return output;
	}
		
}