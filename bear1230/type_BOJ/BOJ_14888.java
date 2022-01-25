import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static int n;
	public static int[] num;
	public static int[] oper = new int[4];
	public static int max = -1000000000;
	public static int min = 1000000000;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		num = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
        
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
        
		for (int j = 0; j < 4; j++) {
			oper[j] = Integer.parseInt(st.nextToken());
		}

		back(num[0], 1);
        
		bw.write(max + "\n");
		bw.write(min + " ");
        
		bw.flush();
		bw.close();
	}

	public static void back(int a, int index) {
		if (index == n) {
			if (max < a)
				max = a;
			if (min > a)
				min = a;
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (oper[i] > 0) {
				--oper[i];

				switch (i) {
				case 0:
					back(a + num[index], index + 1);
					break;
				case 1:
					back(a - num[index], index + 1);
					break;
				case 2:
					back(a * num[index], index + 1);
					break;
				case 3:
					back(a / num[index], index + 1);
					break;

				}
				oper[i]++;
			}
		}

	}
}