import java.util.Scanner;

class Main
{
	public int solution(String str)
	{
		int answer=0;
		
		str=str.toLowerCase().replaceAll("[^A-Z]","");
		
		String temp = new StringBuilder(s).reverse().toString();
		//�־��� ���ڿ� ��ü�� �Ӹ�����̶�� 
		if(s.equals(temp)) answer=str.length();
		else{
		//str ������ �� ���ڸ� ���� �ϳ��� �־��ָ� ��������Ȯ��
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
