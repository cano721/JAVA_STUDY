import java.util.Arrays;

public class PG_HIndex {

	public static void main(String[] args) {
		int[] citations = { 3, 0, 6, 1, 5 };
		Arrays.sort(citations);
		
		int answer = 0;
		for (int i = 0; i < citations.length; i++) { 
			int h = citations.length - i;
		 
			if (citations[i] >= h) { 
				answer = h;
				break;
			}
		}
		
		System.out.println(answer);
	}
}


 
