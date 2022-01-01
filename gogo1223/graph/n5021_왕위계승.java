package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class n5021_왕위계승 {
	static int n, m;
	static String king;
    static Map<String,Double> blood = new HashMap<>();
    static Map<String,String[]> parent = new HashMap<>();
    static double answerNum = 0d;
    static String answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        king = br.readLine();
        blood.put(king, 1.0);
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine()," ");
        	String child = st.nextToken();
        	String parent1 = st.nextToken();
        	String parent2 = st.nextToken();
        	
            parent.put(child,new String[] {parent1,parent2});
		}
        
        for(int i = 0; i < m; i++){
            String checkKing = br.readLine();

            if(!blood.containsKey(checkKing)){		//저장되지 않음
                findBlood(checkKing);
            }
            if(blood.get(checkKing) >= answerNum){	//높은 혈통일 경우
                 answerNum = blood.get(checkKing);
                 answer = checkKing;
            }
        }
        System.out.println(answer);
	}

    public static double findBlood(String p){
        if(blood.containsKey(p)){
            return blood.get(p);
        }
        if(parent.containsKey(p)){		//부모 계산
            String[] parents = parent.get(p);
            double p1_blood = findBlood(parents[0]);
            double p2_blood = findBlood(parents[1]);
            blood.put(p, (p1_blood + p2_blood)/2);	//혈통 계산
        }else{
            blood.put(p,0d);
        }
        return blood.get(p);
    }
}
