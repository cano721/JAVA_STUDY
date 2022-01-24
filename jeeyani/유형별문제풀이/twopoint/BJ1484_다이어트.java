package 유형별문제풀이.twopoint;

import java.io.*;
import java.util.*;

public class BJ1484_다이어트 {

	static int G;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		G = Integer.parseInt(br.readLine());
		List <Integer> preWight = new ArrayList<>();
		
		int preG = G;
		int lastG = G-1;
		/*
		 * G를 현재의 몸무게, 성원이가 기억하는 몸무게(과거몸무게)는 현재 몸무게보다 작다
		 * 
		 * G킬로그램보다 크면 현재의 무게와 과거몸무게를 같이 범위를 줄여주기
		 * G킬로그램보다 작으면 과거 몸무게만 줄여주기
		 * 
		 */
		while(lastG > 0) {
			int wightG = (int) (Math.pow(preG, 2)-Math.pow(lastG, 2));
			
			if(wightG==G) {
				preWight.add(preG);
				lastG--;
			}
			else if(wightG > G) {
				preG--;
				lastG--;
			}
			else {
				lastG--;
			}
			
		}
		
		if(preWight.isEmpty()) {
			System.out.println(-1);
		}else {
			Collections.sort(preWight);
			for(int temp : preWight) {
				System.out.println(temp);
			}
		}

	}

}
