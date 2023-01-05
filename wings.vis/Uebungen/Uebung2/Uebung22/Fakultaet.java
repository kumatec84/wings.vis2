import java.math.BigInteger;

public class Fakultaet {

	public static void main(String[] args) {
		Fakultaet f = new Fakultaet();
		int value = Integer.parseInt(args[0]);
		System.out.println("Starte mit der Berechnung der Fakultaet von: " + value);
		while (true) {
			BigInteger result = getFactorial(BigInteger.valueOf(value));
			System.out.print("!" + value + " = " + result);
		}
	}

	public static BigInteger getFactorial(BigInteger f) { // Rekursive Berechnung der Fakultaet
		if (f.compareTo(BigInteger.valueOf(1)) == -1 || f.compareTo(BigInteger.valueOf(1)) == 0) {
			return BigInteger.valueOf(1);
		} else {
			return f.multiply(getFactorial(f.subtract(BigInteger.valueOf(1))));
		}
	}
}