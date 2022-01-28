package 전체유형문제풀이;

import java.util.*;

/*
[정렬]

Comparator 구현하기

1. 정규식 표현을 이용하여 0번 인덱스에 head 담기
2. compareTo 사용하여 문자 비교하기
2-1. 대소문자는 구별하지 않음으로, 소문자로 일괄 변경하기
     문자1 > 문자2 , 양수 반환
     문자1 < 문자2, 음수 반환
     문자1 = 문자2, 0 반환
     
3. 결과가 0인 경우, 숫자비교하기


https://subin-0320.tistory.com/103

*/
public class PG17686_파일명정렬 {

	public static void main(String[] args) {

		String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};

		String[] res = solution(files);

		for(String str : res) {
			System.out.print(str+" ");
		}

	}

	private static String[] solution(String[] files) {

		Arrays.sort(files, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				//정규식을 이용한 head부분저장
				String head1 = o1.split("[0-9]")[0];
				String head2 = o2.split("[0-9]")[0];

				//헤더 부분의 문자비교
				int res = head1.toLowerCase().compareTo(head2.toLowerCase());

				//숫자비하교하기
				if (res == 0) {
					res = changeNum(o1, head1) - changeNum(o2, head2);
				}
				return res;
			}

		});

		return files;
	}

	private static int changeNum(String s, String head) {
		 //숫자부분만 구하기
        s = s.substring(head.length());
        String res ="";
        
        for(char c : s.toCharArray()){
            /*
            isDigit
            	문자가 숫자인 경우 true
            	숫자가 아닌 경우 false
            
            1. 숫자인경우
            2. 길이가 5보다 작은경우
            */
            if(Character.isDigit(c) && res.length()<5) res +=c;
            else break;
        }
        return Integer.valueOf(res);
	}
}
