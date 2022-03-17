import java.util.*;
import java.io.*;

public class Main {
	static int n, m, ans;
	static int[] list;
	static int[] countList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		list = new int[n];
		countList = new int[m + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}

		int left = 1;
		int right = n * 100;

		while (left < right) {
			int mid = (left + right) >> 1;

			if (isValid(mid)) {
				ans = mid;
				right = mid;
			} else {
				left = mid + 1;
			}
		}
        
		System.out.println(ans);
		remove();
		
		for (int i = 0; i < m; i++) {
			System.out.print(countList[i] + " ");
		}
	}
	


	private static boolean isValid(int mid) {
		int count = 0;
		int tmpSum = 0;
		int[] tmpCount = new int[m + 1];
		for (int i = 0; i < n; i++) {
			if (list[i] > mid) {
				return false;
			}
			if (tmpSum + list[i] > mid) {
				tmpSum = list[i];
				count++;
			} else {
				tmpSum += list[i];
			}
			if (count >= m) {
				return false;
			}
			tmpCount[count]++;
		}
		countList = tmpCount;
		return true;

	}
	
	private static void remove() {
		int idx;
		for (int i = 0; i < m; i++) {
			if (countList[i] == 0) {
				idx = i - 1;
				countList[i]++;
				while (true) {
					if (countList[idx] == 1) {
						idx--;
						continue;
					}
					break;
				}
				countList[idx]--;
			}
		}
	}

}
