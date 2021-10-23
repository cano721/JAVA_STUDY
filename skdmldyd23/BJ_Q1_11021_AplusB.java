import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.StringTokenizer;

 

public class BJ_Q1_11021_AplusB {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for (int i = 1; i <= t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			sb.append("Case #").append(i).append(": ")
			.append(calculation(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())))
			.append("\n");
		}
		System.out.println(sb);
	}

	private static int calculation(int... nums) {
		int value = 0;
		for(int num : nums) value += num; 
		return value;

	}

}