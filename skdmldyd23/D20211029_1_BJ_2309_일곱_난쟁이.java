import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class D20211029_1_BJ_2309_일곱_난쟁이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] parson = new int[9];
		for(int i = 0; i < 9; i++) parson[i] = Integer.parseInt(br.readLine());
		int sum = Arrays.stream(parson).sum();
		calculation(parson, sum);
		Arrays.sort(parson);
		
		for(int i = 0; i < 9; i++) if(parson[i] > 0) sb.append(parson[i]).append("\n");
		System.out.println(sb);
	}
	
	public static void calculation(int[] parson, int sum) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(i == j) continue;
				int temp = sum - parson[i] - parson[j];
				if(temp == 100) {
					parson[i] = 0;
					parson[j] = 0;
					return ;
				}
			}
		}
	}
}
