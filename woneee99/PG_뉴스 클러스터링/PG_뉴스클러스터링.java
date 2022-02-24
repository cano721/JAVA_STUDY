import java.util.*;

class PG_뉴스클러스터링 {
	public int solution(String str1, String str2) {
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();

		ArrayList<String> arr = new ArrayList<String>();
		ArrayList<String> brr = new ArrayList<String>();

		ArrayList<String> inter = new ArrayList<>();
		ArrayList<String> union = new ArrayList<>();

		for (int i = 0; i < str1.length() - 1; i++) {
			char a = str1.charAt(i);
			char b = str1.charAt(i + 1);
			if (Character.isLetter(a) && Character.isLetter(b)) {
				arr.add(a + "" + b);
			}
		}

		for (int i = 0; i < str2.length() - 1; i++) {
			char a = str2.charAt(i);
			char b = str2.charAt(i + 1);
			if (Character.isLetter(a) && Character.isLetter(b)) {
				brr.add(Character.toString(a) + Character.toString(b));
			}
		}

		// 교집합
		for (String s : arr) {
			if (brr.remove(s))
				inter.add(s);
			union.add(s);
		}
		// 햡집합
		for (String s : brr) union.add(s);

		double jakard = 0;

		if (union.size() == 0) jakard = 1; 
		else jakard = (double) inter.size() / (double) union.size();

		return (int) (jakard * 65536);
	}
}