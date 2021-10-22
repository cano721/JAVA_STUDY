import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1065_한수 {

	public static void main(String[] args) throws IOException {
		// 입력 값 셋팅 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int T, answer = 0;
		T = Integer.parseInt(st.nextToken());
		
		if(T < 100 ) {
			System.out.println(T);
		}
		else {
			answer += 99;
			String t;
			
			for (int i = 100; i <= T; i++) {
				answer++;					//일단 증가
				t = i + "";
				String[] arr = t.split(""); //String으로 바꿔서 한문자씩 넣기
				
				int temp = Integer.parseInt(arr[0]) - Integer.parseInt(arr[1]);	//공차
				
				for (int j = 1; j < arr.length-1; j++) {
					if(temp == (Integer.parseInt(arr[j]) - Integer.parseInt(arr[j+1]))) {
						continue;
					}else {
						answer--;			//등차수열 아니면 감소시키기
						break;
					}
				}
			}
			System.out.println(answer);
		}

	}

}
