package semiproject2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class problemShuffle {

	
//	public static void main(String[] args) {
//		List<Integer> arr = problemShuffle(20);
//		for (int i = 0; i < arr.size(); i++) {
//			System.out.println(arr.get(i));
//		}
//		
//	}
	
	
	public static List<Integer> problemShuffleMethod (int a) {
		List<Integer> array = new ArrayList();
		for (int i = 1; i <= a; i++) {
			array.add(i);
		}
		Collections.shuffle(array);
		
		for (int i = 0; i < array.size(); i++) {
		}
		return array;
	}
}
