package semiproject2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class problemShuffle {

	
	public static void main(String[] args) {
		problemShuffle ps = new problemShuffle();
		List<Integer> arr = ps.problemShuffle(5);
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
		
	}
	
	
	public static List<Integer> problemShuffle (int a) {
		List<Integer> array = new ArrayList();
		for (int i = 0; i < a; i++) {
			array.add(i);
		}
		Collections.shuffle(array);
		
		for (int i = 0; i < array.size(); i++) {
		}
		return array;
	}
}
