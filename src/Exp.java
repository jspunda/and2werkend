/**
 * 
 * @author L. van den Bercken 4057384 & J. Spunda 4174615
 * This is the abstract Exp class for creating expressions
 */
public abstract class Exp {
	
	/**
	 * left is left child of expression
	 * right is right child of expression
	 * value is the string value of expression
	 */
	
	protected Exp left;
	protected Exp right;
	protected String value;
	
	/**
	 * Constructor
	 * @param value instantiate expression with this value
	 */
	public Exp(String value) {
		this.value = value;
	}
	
	/**
	 * Second constructor
	 * @param value instantiate expression with this value
	 * @param left build expression with left child
	 * @param right build expression with right child
	 */
	public Exp(String value, Exp left, Exp right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * For printing expressions, uses help function
	 */
	public String toString() {
	    return toString(this);
	}
	
	/**
	 * Help function for printing expression
	 * @param exp The expression that is going to print
	 * @return The string representation of the expression
	 */
	private String toString(Exp exp) {
	    if (exp == null) {
	        return null;
	    }
	    return "[" + toString(exp.left) + "," + exp.value + "," + toString(exp.right) + "]";
	}
	/**
	 * Write expression as a string
	 * @return the string
	 */
	public String getString() {
		if(left == null) {
			return this.value;
		}
		return this.value + "(" + left.getString() + "," + right.getString() + ")";
	}
}