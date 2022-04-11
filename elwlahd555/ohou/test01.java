package elwlahd555.ohou;

import java.util.LinkedList;

public class test01 {
	public static void main(String[] args) {
		String path ="EEESEEEEEENNNN";
		System.out.println(solution(path));
	}

	private static String[] solution(String path) {

		LinkedList <String> que= new LinkedList<String>();
		StringBuffer sb= new StringBuffer();
		char start=path.charAt(0);
		int nowtime=0;
		int cnt=1;
		for (int i = 1; i < path.length(); i++) {
			if(path.charAt(i)==start) {
				cnt++;
			}else {
				sb=new StringBuffer();
				if(cnt>5) {
				nowtime+=cnt-5;
				cnt=5;
				}
				sb.append("Time ").append(nowtime).append(": Go straight ");
				sb.append(cnt).append("00m and turn ");
				if(start=='E') {
					if(path.charAt(i)=='S') {
						sb.append("turn right");
					}else {
						sb.append("turn left");
					}
				}else if(start=='W') {
					if(path.charAt(i)=='S') {
						sb.append("turn left");
					}else {
						sb.append("turn right");
					}
				}else if(start=='N') {
					if(path.charAt(i)=='W') {
						sb.append("turn left");
					}else {
						sb.append("turn right");
					}
				}else{
					if(path.charAt(i)=='E') {
						sb.append("turn left");
					}else {
						sb.append("turn right");
					}
				}
				nowtime+=cnt;
				start=path.charAt(i);
				cnt=1;
				que.add(sb.toString());
			}
		}
		String []answer =new String[que.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i]=que.poll();
		}
		return answer;
	}
}
