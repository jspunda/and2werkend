/**
 * 
 * @author L. van den Bercken, s4057384 & J. Spunda, s4174615
 *
 */
public class FExp extends Exp {
	
	/**
	 * Constructor
	 * @param value instantiate expression with this value
	 */
	public FExp(String value) {
		super(value);
	}
	
	/**
	 * Second constructor
	 * @param value instantiate expression with this value
	 * @param left build expression with left child
	 * @param right build expression with right child
	 */
	public FExp(String value, Exp left, Exp right) {
		super(value,left,right);
	}
	
	/**
	 * Setter for setting left child
	 * @param e left child is now expression e
	 */
	public void setLeft(Exp e) {
		left = e;
	}
	
	/**
	 * Setter for setting right child
	 * @param e right child is now expression e
	 */
	public void setRight(Exp e) {
		right = e;
	}
}
