import java.util.Scanner;

/**
 * Main class
 * @author L. van den Bercken, s4057384 & J. Spunda, s4174615
 * Reads expressions from stdin and writes modified expressions to stdout
 */
public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();

		for(int i = 0; i < n; ++i){
			String line = s.next();

			Magic magic = new Magic(line);
			line = magic.toString();

			System.out.println(line);
		}
	}
}