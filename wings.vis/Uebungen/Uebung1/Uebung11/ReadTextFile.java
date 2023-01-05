import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadTextFile {
 
	public static void main(String[] args) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("textdatei.txt"));
			String s = "";
			String res;
			while ((res = in.readLine()) != null) {
				s = s + res + "\n";
			}
			System.out.println(s);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}