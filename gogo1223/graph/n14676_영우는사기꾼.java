package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class n14676_영우는사기꾼 {
	static int n, m, k;
	static StringTokenizer st;
	static BufferedReader br;
	static ArrayList<ArrayList<Integer>> list;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine()," ");
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        list = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<Integer>());
		}
        //관계 info
        for (int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine()," ");
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	list.get(y).add(x);		//y를 건설하는데 필요한 x를 넣어준다.
		}
        
        System.out.println(solution());
	}

	private static String solution() throws IOException {
		//영우 info
        int[] info = new int[n+1];
        String answer = "King-God-Emperor";
        for (int i = 0; i < k; i++) {
        	st = new StringTokenizer(br.readLine()," ");
        	int flag = Integer.parseInt(st.nextToken());//건물 건설 : 1, 파괴 : 2
        	int num = Integer.parseInt(st.nextToken());	//건물 number
        	
        	if(flag == 1) {
        		for (int j = 0; j < list.get(num).size(); j++) { //건물 지을 때 필요 조건이 있으면
    				if(info[list.get(num).get(j)] <= 0) {
    					answer = "Lier!";				//영우거짓말쟁이
    					return answer;
    				}
    			}
        		info[num]++;
        	}else if(flag == 2) {
        		if(info[num] == 0) {
        			answer = "Lier!";
        			return answer;
        		}
        		info[num]--;
        	}
        	
		}
        return answer;
	}

}
