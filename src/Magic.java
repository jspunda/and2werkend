import java.util.ArrayList;


public class Magic {
	
	private ArrayList<Exp> madeChanges;
	private String input;
	
	public Magic (String input) {
		madeChanges = new ArrayList<Exp>();
		this.input = input;
		StringReader reader = new StringReader(input);
		Exp output = reader.getExp();
		output = reader.getExp();
		Exp pruned = fixString(output);
		this.input = pruned.getString();
	}

	
	private int contained(Exp in) {
		for(int i=0; i < madeChanges.size();i++) {
			Exp a = madeChanges.get(i);
			if (a.getString().equals(in.getString())) {
				return i;
			}
		}
		return -1;
	}
	
	private Exp fixString(Exp in) {
		if(in == null) {
			return in;
		}
		int x = contained(in);
		if(x == -1) {
			madeChanges.add(in); 
		} else {
			in = new SExp(x+1+"");
		}
		Exp left = fixString(in.left);
		Exp right = fixString(in.right);
		return new FExp(in.value, left, right);
	}
	
	public String toString() {
		return input;
	}
		
}


