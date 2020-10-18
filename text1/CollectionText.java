package dome.text1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionText {
	
	public static void main(String[] args) {
		
		Integer[] ints1 = {1,2,3,4,5,6,7}; 
		Integer[] ints2 = {1,3,5,7,9};
		
		List<Integer> list1 = new ArrayList<>(ints1.length);
		List<Integer> list2 = new ArrayList<>(ints2.length);
		
		Collections.addAll(list1, ints1);
		Collections.addAll(list2, ints2);
		
		list1.retainAll(list2);
		
		Collections.sort(list1);
		
		for (Integer integer : list2) {
			System.out.println(integer);
		}
	}
	
}
