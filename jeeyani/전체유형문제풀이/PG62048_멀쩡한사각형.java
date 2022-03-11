package 전체유형문제풀이;

import java.util.*;

/*
 * https://taesan94.tistory.com/m/55
 * 
 * 1. 사용할 수 없는 직사각형은 동일한 패턴이 반복됨
 * 		- 대각선의 영향을 받는 직사각형의 갯수는 n*m일때 (n+m-1)개이다.
 * 		- 사각형을 대각선으로 자르면, 왼쪽맨위끝에서부터 오른쪽맨끝으로 이동하는데 이때 최대가로는 n, 최대세로는 m
 * 		- 가로로 이동할때와 세로로 이동할때 시작점은 똑같기 때문에 중복됨으로 -1
 * 
 * 
 * 2. 일정한 간격으로 좌표가 이동
 *  	해당 간격은 w,h 의 최소공배수로 w와 h를 나눈값 (w/comm   , h/comm)
 * 
 * 
 * 따라서 {(w/최대공약수) + (h/최대공약수) - 1} * 최대공약수
 * == (w + h - 최대공약수)
 * 
 * */

public class PG62048_멀쩡한사각형 {

	public static void main(String[] args) {

		long result = solution(8,12);

		System.out.println(result);

	}

	private static long solution(int w, int h) {
		long answer = 0;
        
        long wLong = Long.parseLong(String.valueOf(w));
        long hLong = Long.parseLong(String.valueOf(h));
        
        //최대공약수 구하기
        long comm = GreatCom(wLong,hLong);
       
        //전체직사각형 갯수에서 영향받는 직사각형 갯수 빼주기
        answer = (wLong * hLong) - (wLong + hLong - comm);
        
        return answer;
	}

	//w와 h의 최대공약수 구하기
	private static long GreatCom(long w, long h) {
		long s = 0;
		long b = 0;

		b = (w >= h) ? w : h;
		s = (w < h) ? w : h;

		while (s != 0) {
			long num = b % s;
			b = s;
			s = num;
		}

		return b;
	}
	


}