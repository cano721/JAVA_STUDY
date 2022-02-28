package 전체유형문제풀이;

public class PG12951_JadenCase문자열만들기 {

	public static void main(String[] args) {

		String s = "for the laSt week";

		String result = solution(s);
		result = solutionOK(s);
		
		System.out.println(result);

	}

	//재풀이
	private static String solutionOK(String s) {
		String answer = "";
        String[] sp = s.toLowerCase().split("");
        boolean chk = true;
        
        //공백일때를 체크하기
        for(String ss : sp){
            answer += (chk)? ss.toUpperCase() : ss; //true일때 대문자
            
            
            chk = ss.equals(" ") ? true : false; //공백일때 true
        }
		
		return answer;
	}

	//런타임 에러
	private static String solution(String s) {
		String answer = "";

		String[] tempSplit = s.split(" ");

		for (int i = 0; i < tempSplit.length; i++) {
			if(i==0) {
				answer += String.valueOf(tempSplit[i].charAt(0)).toUpperCase();
			}else {
				answer += " "+String.valueOf(tempSplit[i].charAt(0)).toUpperCase();
			}

			for (int j = 1; j < tempSplit[i].length(); j++) {

				if (tempSplit[i].charAt(j) >= 65 && tempSplit[i].charAt(j) <= 90) { //대문자
					answer += String.valueOf(tempSplit[i].charAt(j)).toLowerCase();
				} else {
					answer += String.valueOf(tempSplit[i].charAt(j));
				}

			}
		}

		return answer;
	}
}
