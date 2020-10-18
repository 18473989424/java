package dome.text1;

class Base {
	private void amethod(int iBase) {
		System.out.println("Base.amethod");
	}
}

public class Super extends Base {
	public static void main(String args[]) {
		Super o = new Super();
		int iBase = 0;
		o.amethod(iBase);
	}

	public void amethod(int iOver) {
		System.out.println("Over.amethod");
	}
}
