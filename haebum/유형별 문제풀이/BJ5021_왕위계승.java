import java.util.*;
import java.io.*;

/**
 * 자식의 부모정보를 저장하기
 * 
 * 첫 시작인 왕을 1로 저장하두고
 * 
 * 나온 후보들의 혈통을 재귀형태로 구하기
 * 
 * 구해질때마다 해쉬에 저장
 */
public class BJ5021_왕위계승 {

    public static int n,m;
    public static double answerNum = 0d;
    public static Map<String,Double> blood = new HashMap<>();
    public static Map<String,String[]> parent = new HashMap<>();
    public static String answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        String king = br.readLine();
        blood.put(king, 1.0);

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            String child = st.nextToken();
            String parent1 = st.nextToken();
            String parent2 = st.nextToken();
            
            //부모 정보 저장
            parent.put(child,new String[] {parent1,parent2});
        }

        // 후보들 체크
        for(int i = 0; i < m; i++){
            String checkKing = br.readLine();

            // 저장되어있지 않으면 구하기
            if(!blood.containsKey(checkKing)){
                findBlood(checkKing);
             }
             // 현재 저장되어있는 정답보다 높은 혈통이면 변경
             if(blood.get(checkKing) >= answerNum){
                 answerNum = blood.get(checkKing);
                 answer = checkKing;
             }
        }

        System.out.println(answer);
    }

    // 혈통 구하기(재귀 구조)
    public static double findBlood(String p){
        // 이미 혈통이 저장되어있으면 반환
        if(blood.containsKey(p)){
            return blood.get(p);
        }
        // 혈통 구할 사람의 부모가 있는가
        if(parent.containsKey(p)){
            String[] parents = parent.get(p);
            double p1_blood = findBlood(parents[0]);
            double p2_blood = findBlood(parents[1]);
            // 혈통 계산후 저장
            saveBlood(p,p1_blood,p2_blood);
        // 없으면 혈통을 없는걸로 저장
        }else{
            blood.put(p,0d);
        }
        // 혈통 반환
        return blood.get(p);
    }

    public static void saveBlood(String c, double p1, double p2){
        // 두 부모가 왕족의 혈통이 아니면
        if(p1 == 0d && p2 == 0d){
            // 자식 혈통은 0d
            blood.put(c,0d);
        // 한쪽만 혈통이 아니면
        }else if(p1 == 0d){
            blood.put(c,p2/2);
        }else if(p2 == 0d){
            blood.put(c,p1/2);
        // 둘다 혈통이면 더하기
        }else{
            blood.put(c,(p1 + p2)/2);
        }
    }
}

