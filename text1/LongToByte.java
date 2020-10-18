package dome.text1;

import java.io.*;
public class LongToByte {
	
	public static void main(String[] args) {
		SetCheck setCheck = new SetCheck();
		setCheck.set();
		boolean check = setCheck.check();
		System.out.println(check);
	}
	
}
final class SetCheck {
	  private int  a = 0;
	  private long b = 0;
	 
	  void set() {
	    a =  1;
	    b = -1;
	  }
	 
	  boolean check() {
	    return ((b ==  0) ||
	            (b == -1 && a == 1));
	  }
}
