package 전체유형문제풀이;

public class PG17681_비밀지도1차 {

	public static void main(String[] args) {

		int n = 5;
		int[] arr1 = { 9, 20, 28, 18, 11 };
		int[] arr2 = { 30, 1, 21, 17, 28 };

		String[] res = solution(n, arr1, arr2);

		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i]);
			System.out.println();
		}
	

	}

	private static String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];

		int map[] = new int[n];
		
		for (int i = 0; i < n; i++) {
			map[i] = arr1[i] | arr2[i];
		}
		
		for (int i = 0; i < map.length; i++) {
			//이진법으로 변경
			String temp = Integer.toBinaryString(map[i]);
			
			if(temp.length() < n) {
				int other = n - temp.length();                
                for(int j =0 ; j < other; j++) {               
                    temp = "0"+temp;
                }
			}
			
			temp = temp.replaceAll("1", "#");
			temp = temp.replaceAll("0", " ");
			answer[i] = temp;
		}
		

		return answer;
	}

}
