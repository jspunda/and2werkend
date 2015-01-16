import java.util.Scanner;

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