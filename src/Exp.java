
public abstract class Exp {
	
	protected Exp left;
	protected Exp right;
	protected String value;
	
	public Exp(String value) {
		this.value = value;
	}
	
	public Exp(String value, Exp left, Exp right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public String toString() {
	    return toString(this);
	}

	private String toString(Exp exp) {
	    if (exp == null) {
	        return null;
	    }
	    return "[" + toString(exp.left) + "," + exp.value + "," + toString(exp.right) + "]";
	}
	
	public String getString() {
		if(left == null) {
			return this.value;
		}
		return this.value + "(" + left.getString() + "," + right.getString() + ")";
	}
}
