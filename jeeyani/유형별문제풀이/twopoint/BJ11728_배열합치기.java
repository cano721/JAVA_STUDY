package 유형별문제풀이.twopoint;

import java.io.*;
import java.util.*;

public class BJ11728_배열합치기 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] arrA = new int[n];
		int[] arrB = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arrA);
		Arrays.sort(arrB);
		
		//List 사용시 시간초과 ㅠㅠ
		//List<Integer> newArr = new ArrayList<>();
		
		int startA = 0;
		int startB = 0;
		
		//각 배열의 시작점을 시작으로 값을 비교하여 list에 담기
		while(startA < n && startB < m) {
			
			if(arrA[startA] > arrB[startB]) {
				sb.append(arrB[startB++] + " ");
				//newArr.add(arrB[startB++]);
				
			}
			else {
				//newArr.add(arrA[startA++]);
				sb.append(arrA[startA++] + " ");
			}
			
		}
		
		//남은 갯수 만큼 담기
		while(startA < n) {
			//newArr.add(arrA[startA++]);
			sb.append(arrA[startA++] + " ");
		}
		while(startB < m) {
			//newArr.add(arrB[startB++]);
			sb.append(arrB[startB++] + " ");
		}
		
		System.out.println(sb);
		
		/*for (int i = 0; i < newArr.size(); i++) {
			System.out.print(newArr.get(i)+" ");
		}*/
	}
}
