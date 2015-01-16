import java.util.ArrayList;


public class Main {

	public static IsSubTree sub = new IsSubTree();
	public static Exp target = null;
	public static int changed = 0;
	public static ArrayList<Exp> madeChanges;
	
	public static void main(String[] args) {
		madeChanges = new ArrayList<Exp>();
		String input = "foo(bar(hark,hark),baz(zeebaars,zeebaars))";
		StringReader reader = new StringReader(input);
		Exp output = reader.getExp();
		System.out.println(output.getString());
		output = reader.getExp();
		Exp pruned = fixString(output);
		System.out.println(pruned.getString());
	}

	
	public static int contained(Exp in) {
		for(int i=0; i < madeChanges.size();i++) {
			Exp a = madeChanges.get(i);
			if (a.getString().equals(in.getString())) {
				return i;
			}
		}
		return -1;
	}
	
	public static Exp fixString(Exp in) {
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
}
