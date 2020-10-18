
public class RunTimeDemo {

	static class MyMath {
		public static double round(double num, int scale) {
			return Math.round(num * Math.pow(10, scale)) / Math.pow(10, scale);
		}
	}

	public static void main(String[] args) {
		System.err.println(MyMath.round(19.5687, 2));
		;
	}
}
