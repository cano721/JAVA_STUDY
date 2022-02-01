package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class d220201_튜플 {

	public static void main(String[] args) {
		String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
		int[] answer = solution(s);
		for (int i = 0; i < answer.length; i++) {
			System.out.print(answer[i]+" ");
		}
	}

	private static int[] solution(String s) {
		s = s.substring(2, s.length()-2).replace("},{", "-");
		String[] arr = s.split("-");
		
		Arrays.sort(arr, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});

		ArrayList<Integer> answer = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			String[] arr2 = arr[i].split(",");
			for (int j = 0; j < arr2.length; j++) {
				if(!answer.contains(Integer.parseInt(arr2[j]))) {
					answer.add(Integer.parseInt(arr2[j]));
				}
			}
		}
		int[] ans = new int[arr.length];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = answer.get(i);
		}
		return ans;
	}

}
