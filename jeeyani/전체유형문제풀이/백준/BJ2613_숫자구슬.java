package 전체유형문제풀이.백준;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
 * [이분법]
 * 
 * 1. 그룹의 합 중에서 최대값이 최소가 될 수 있는 최적의 해(mid) --> 최대값이 mid이하가 되도록 구슬을 나눌 수 있는 가
 * 
 * 2. left = 원소 중 최대값, rigth = 모든 구슬의 합  으로 초기화
 * 
 * 3-1. 그룹의 갯수를 만족하면 좌측으로 이동(더 작은 기준을 찾기 위해)
 * 3-2. 그룹의 갯수를 만족하지 않으면 우측으로 이동(mid값을 더 크게 잡기 위해)
 * 
 * ex)  mid = 26 
 * {5,4,2,6,9}  <= 26   || {3,8,7} < 26
 * 
 * 만약 그룹수가 3이라면, {5 4 2 6 9},{3},{8 7}  || {5 4 2 6 9},{3,8},{7}
 * 따라서 왼쪽부터 최대한 채워넣기!!
 * 
 * 
 * 
 * */


public class BJ2613_숫자구슬 {
	
	static int n,m;
	static int[] bead;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		bead = new int[n];
		int left = 0;
		int right = 0;
		int mid = 0;
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			bead[i] = Integer.parseInt(st.nextToken());
			
			//원소 중에 최대값을 left로 설정해 둔다.
			left = left < bead[i]? bead[i]:left;
			
			//모든 구슬의 합으로 초기화
			right += bead[i];
		}
		
		while(left <= right) {
			
			mid = (left+right)/2;
			
			//mid가 그룹 개수를 만족하지 확인하기
			if(chkGroup(mid)) {
				right = mid-1;
			}
			else left = mid +1;
			
		}
		
		//최종적으로 결정된 구슬그룹의 최대값
		sb.append(left+"\n");		
		
		int sum = 0;
		int beadNum = 0; //각 그룹별 구슬 개수
		
		for (int i = 0; i < n; i++) {
			sum += bead[i];
			
			if(sum > left) {
				sum = bead[i];
				m--; 
				
				sb.append(beadNum+" ");
				beadNum = 0;
			}
			
			beadNum++;
			
			//나머지 그룹에 적어도 구슬 1개를 배치해야함으로, m개의 그룹을 만들기 위해 최소한의 구슬을 남겨두기
			if(n-i == m) break;
		}
		
		//나머지 남은 그룹에는 구슬을 1개씩 배치한다
		for(int i = m; i>0; i--) {
			sb.append(beadNum+" ");
			beadNum = 1;
		}
		
		
		bw.write(sb.toString()+"\n");
		bw.flush();
		bw.close();
	}
	
	public static boolean chkGroup(int mid) {
		int sum = 0;
		int cnt = 1;
		
		for (int i = 0; i < n; i++) {
			sum += bead[i];
			
			if(sum > mid) {
				sum = bead[i];
				cnt++;
			}
		}
		
		if(cnt <= m) return true;
		else return false;
	}

}
