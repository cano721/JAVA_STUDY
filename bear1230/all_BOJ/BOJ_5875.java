import java.io.*;

public class Main {
	static int left, right, sum, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int n = str.length();

		left = 0;
		right = 0;
		sum = 0;
		ans = 0;

		for (int i = 0; i < n; i++) {
			if (str.charAt(i) == '(') {
				left++;
				sum++;
			} else {
				right++;
				sum--;
			}

			if (sum == 1) {
				left = 0;
			}

			if (sum == -1) {
				ans = right;
				break;
			}
		}

		if (sum == 2)
			ans = left;

		System.out.println(ans);

	}

}