package 유형별문제풀이.bitMask;

import java.io.BufferedReader;	
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.BitSet;
import java.util.StringTokenizer;

/*
 * 
 * 참고 ) https://txegg.tistory.com/112
 * 
 @author Jeeyani
 */

public class BJ2064_IP주소 {
	
	static int n;
	static int[][] ipAddress;
	static int[] netAddress= new int[4];
	static int[] netMask= new int[4] ;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n= Integer.parseInt(br.readLine());
		ipAddress = new int[n][4];
		
		//1. ip주소 입력받고 
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(),".");
			for (int j = 0; j < 4; j++) {
				ipAddress[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		f:for (int i = 0; i < 4; i++) {
			int minIP = ipAddress[0][i];
			int maxIP = ipAddress[0][i];
			
			for (int j = 0; j < n; j++) {
				minIP = Math.min(minIP, ipAddress[j][i]);
				maxIP = Math.max(maxIP, ipAddress[j][i]);
			}
			
			//2. 네트워크 마스크 찾기
			//달라지는 시점찾고, 같은 부분은 255 다른부분은 해당 차이만큼 계산해서 구하기 
			if(255 == 256+(~maxIP^minIP)) {
				netMask[i] = 255;
			}
			
			else {
				
				for (int j = 0; j <9; j++) {
					
					if(-(~maxIP^minIP) <= 1<<j) {
						netMask[i] = 256 - (1<<j);
						break f;
					}
					
				}
				
			}
			
		}
		
		for (int i = 0; i < 4; i++) {
			netAddress[i] = ipAddress[0][i] & netMask[i];
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(netAddress[0]+"."+netAddress[1]+"."+netAddress[2]+"."+netAddress[3]+"\n");	
		sb.append(netMask[0]+"."+netMask[1]+"."+netMask[2]+"."+netMask[3]);	
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	
}
