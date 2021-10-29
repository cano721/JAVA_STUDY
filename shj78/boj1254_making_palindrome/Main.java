import java.util.Scanner;

class Main
{
	public int solution(String str)
	{
		int answer=0;
		
		String str2=str.toLowerCase().replaceAll("[^A-Z]","");
		
		String temp = new StringBuilder(str2).reverse().toString();

		//주어진 문자열 자체로 팰린드롬이라면 
		if(str2.equals(temp)) answer=str2.length();
		else{
			//str 마지막 한 문자만 빼고 하나씩 넣어주며 포문돌려확인
			String str2 = temp.substring(0,str2.length()-1);
			System.out.println(str2);
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
