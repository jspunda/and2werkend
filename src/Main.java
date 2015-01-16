import java.util.ArrayList;


public class Main {

	public static IsSubTree sub = new IsSubTree();
	public static Exp target = null;
	public static int changed = 0;
	public static ArrayList<Exp> madeChanges;
	public static ArrayList<String> toReplace;
	public static ArrayList<String> replacement;
	
	public static void main(String[] args) {
		madeChanges = new ArrayList<Exp>();
		toReplace = new ArrayList<String>();
		replacement = new ArrayList<String>();
		String input = "g(f(a,a),h(f(a,a),g))";
		StringReader reader = new StringReader(input);
		Exp output = reader.getExp();
		System.out.println(output.getString());
		output = reader.getExp();
		buildArrayList2(output);
		System.out.println(rewrite(output.getString()));
	}

	public static String rewrite(String input) {
		for(int i = toReplace.size()-1; i >= 0 ; i--) {
			input = replaceLast(input, toReplace.get(i), replacement.get(i));
		}
		return input;
	}
		
	public static String replaceLast(String string, String toReplace, String replacement) {
	    int pos = string.lastIndexOf(toReplace);
	    if (pos > -1) {
	        return string.substring(0, pos)
	             + replacement
	             + string.substring(pos + toReplace.length(), string.length());
	    } else {
	        return string;
	    }
	}
	
	public static void buildArrayList(Exp in) {
		if(in == null) {
			return;
		}
		madeChanges.add(in);
		buildArrayList(in.left);
		buildArrayList(in.right);
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
	
	public static void buildArrayList2(Exp in) {
		if(in == null) {
			return;
		}
		int x = contained(in);
		if(x == -1) {
			madeChanges.add(in); 
		} else {
			toReplace.add(in.getString());
			//System.out.println(in.getString());
			Exp t = new SExp(x+1+"",x);
			in = t;
			replacement.add(t.getString());
			//System.out.println(t.getString());
		}
		buildArrayList2(in.left);
		buildArrayList2(in.right);
	}
	
	public static void pruneTree(Exp left, Exp right, Exp parent) {
		if(left == null || right == null) {
			return;
		}
		System.out.println(left.getString() + " en " + right.getString() + " met parent " + parent.getString());
		Exp copy;
		if (sub.areIdentical(left, right)) {
			target = parent;
			if(left.left != null) {
				copy = new FExp(left);
			}
			else {
				copy = new SExp(left);
			}
			copy.counter = copy.counter-changed;
			copy.changed = true;
			target.right = copy;
			changed++;
			target = null;
		} else {
			dfsMatch(left, right);
			if(target != null){
				if(left.left != null) {
					copy = new FExp(left);
				}
				else {
					copy = new SExp(left);
				}
				if(sub.areIdentical(target.left,copy)) {
					target.left = copy;
					if(sub.areIdentical(target.right, copy)) {
						target.right = copy;
					}
				}
				if (sub.areIdentical(target.right,copy)) {
					target.right = copy;
				}
				copy.counter = copy.counter-changed;
				copy.changed = true;
				changed++;
				target = null;
			}
		}
		pruneTree(left.left, left.right, left);
		pruneTree(right.left, right.right, right);
		
	}

	
	public static void dfsMatch(Exp match, Exp targetNode) {
		if (targetNode == null) {
			return;
		}
		if (sub.areIdentical(match, targetNode.right) || sub.areIdentical(match, targetNode.left)) {
			target = targetNode;
		}
		dfsMatch(match,targetNode.left);
		dfsMatch(match,targetNode.right);
	}
}
