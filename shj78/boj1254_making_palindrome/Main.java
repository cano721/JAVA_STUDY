import java.util.Scanner;

class Main
{
	public int solution(String str)
	{
		int answer=0;
		
		str=str.toLowerCase().replaceAll("[^A-Z]","");
		
		String temp = new StringBuilder(s).reverse().toString();
		//주어진 문자열 자체로 팰린드롬이라면 
		if(s.equals(temp)) answer=str.length();
		else{
		//str 마지막 한 문자만 빼고 하나씩 넣어주며 포문돌려확인
		}

		return answer;
	}

	public static void main(String[] args) 
	{
		Scanner kb = new Scanner(System.in);

		String str = kb.next();

		Main T = new Main();

		System.out.println(T.solution(str));
	}
}
