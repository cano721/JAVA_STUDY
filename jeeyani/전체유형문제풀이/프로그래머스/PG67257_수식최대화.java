package 전체유형문제풀이.프로그래머스;

import java.util.ArrayList;

public class PG67257_수식최대화 {

	public static void main(String[] args) {
		
		String expression = "100-200*300-500+20";
	
	
		long result = new Solution2().solution(expression);	
		
		System.out.print(result);
	
	}

}

class Solution2 {
	
	public static long answer = Long.MIN_VALUE;
	
	public static ArrayList<Long> numList = new ArrayList<>(); // 숫자 담을 리스트 
	public static ArrayList<String> operList = new ArrayList<>(); // 연산자 담을 리스트 
	
	public static String[] oper = {"+", "-", "*"};
	public static String[] output = new String[3]; // 순열 결과 담을 배열 
	public static boolean[] visited = new boolean[3]; 
    
    public long solution(String expression) {
		String n = "";
		
		
		//1. 연산자와 숫자 따로 구분하여 저장하기
		for(int i = 0; i < expression.length(); i++) {
			char exp = expression.charAt(i);
			if(exp == '+' || exp == '-' || exp == '*') {
				operList.add(exp + "");
				numList.add(Long.parseLong(n));
				n = "";
			} else { //그냥 숫자인 경우
				n += exp;
			}
		}  
		// 마지막 숫자 삽입 
		numList.add(Long.parseLong(n));
	
		//순열 만들기(우선순위 경우의 수 찾기)
		per(0, oper.length);
		
		return answer;
	}

	//순열 찾기
	static void per(int depth, int r) {
		if(depth == r) {
			solve(); // 연산 
		
			return;
		}
		//최대 3! 3P3
		for(int i = 0; i < oper.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = oper[i]; //oper = {"+", "-", "*"}; + , - , * 순으로 연산
				per(depth + 1, r);  
				visited[i] = false;
			}
		}
	}
    
	// 연산 
	static void solve() {
		// 연산자 우선 순위에 따른 값을 얻기 위해 List 복사 
		ArrayList<String> oper = new ArrayList<String>();
		oper.addAll(operList);
		
		ArrayList<Long> num = new ArrayList<Long>();
		num.addAll(numList);
		
		for(int i = 0; i < output.length; i++) {
			String curOper = output[i]; // 현재 우선순위 연산자 
			
			for(int j = 0; j < oper.size(); j++) {
				
				if(oper.get(j).equals(curOper)) { // 현재 우선순위에 맞는 연산자일 경우 
					long n1 = num.get(j);
					long n2 = num.get(j+1);
					long res = cal(n1, n2, curOper);
					
				
					num.remove(j+1); // 숫자 삭제 
					num.remove(j);
					oper.remove(j); // 연산자 삭제 
					
	
					num.add(j, res); // 연산 결과 넣기 
			
					j--; // 삭제했으니 인덱스 하나 줄여주기 
				}
			}
		}
		
		answer = Math.max(answer, Math.abs(num.get(0)));
	}
	
	// 수식 계산 
	static long cal(long n1, long n2, String o) {
		long res = 0;
		switch(o) {
		case "+" :
			res = n1 + n2;
			break;
		case "-" :
			res = n1 -n2;
			break;
		case "*" :
			res = n1 * n2;
			break;
		}
		
		return res;
	}

}
