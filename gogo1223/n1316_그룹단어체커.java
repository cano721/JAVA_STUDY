import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class n1316_그룹단어체커 {
	public static void main(String[] args) throws IOException {
		// 입력 값 셋팅 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int T;
		
		//테스트 개수
		T = Integer.parseInt(st.nextToken());
		
		String[] arr = new String[T];
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine()); 
			arr[i] = st.nextToken();
		}
		
		System.out.println(solution(arr));
	}

	private static int solution(String[] arr) {
		int answer = arr.length;
		String[] inArr = {}; 
		ArrayList<String> list = new ArrayList<>();
		
		for (int i = 0; i < arr.length; i++) {
			inArr = arr[i].split("");
			list.add(inArr[0]);
			
			for (int j = 1; j < inArr.length; j++) {
				if(inArr[j].equals(inArr[j-1])) {
					continue;
				}else {
					if(list.contains(inArr[j])) {
						answer--;
						break;
					}
					list.add(inArr[j]);
				}
			}
			list.clear();
		}
		return answer;
	}
}
