import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteTextFile {

	public static void main(String[] args) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter("Outputfile.txt")));
			String pi = "3.141592654";
			out.println(pi);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}