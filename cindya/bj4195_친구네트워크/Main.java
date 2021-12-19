package cindya.bj4195_친구네트워크;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<String, String> friends = new HashMap<>();
    private static Map<String, Integer> memberNumber = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0){
            int f = Integer.parseInt(br.readLine());
            friends.clear();
            memberNumber.clear();
            while (f-- > 0){
                String[] newFriend = br.readLine().split(" ");
                for(int i = 0; i < 2; i++){
                    if(!friends.containsKey(newFriend[i])) { // 새로운 친구면
                        friends.put(newFriend[i], newFriend[i]); // friends에 저장
                        memberNumber.put(newFriend[i], 1); // 친구 수를 1로 저장
                    }
                }
                String g1 = findGroup(newFriend[0]), g2 = findGroup(newFriend[1]); // 각각 그룹장 찾기
                int res = memberNumber.get(g1); // 그룸 인원은 g1의 인원으로 초기화
                if(!g1.equals(g2)){ // 두 그룹장이 같지 않으면
                    // 인원이 더 많은 쪽으로 합치고, 인원수 합 저장
                    if(memberNumber.get(g1) < memberNumber.get(g2)){
                        friends.replace(g1, g2);
                        memberNumber.replace(g2, memberNumber.get(g1) + memberNumber.get(g2));
                        res = memberNumber.get(g2);
                    }
                    else{
                        friends.replace(g2, g1);
                        memberNumber.replace(g1, memberNumber.get(g1) + memberNumber.get(g2));
                        res = memberNumber.get(g1);
                    }
                }
                bw.write(res + "\n"); // 출력
            }
        }
        br.close();
        bw.close();
    }

    // 그룹장 이름을 반환하는 함수
    private static String findGroup(String name){
        if(!friends.get(name).equals(name))
            friends.replace(name, findGroup(friends.get(name)));
        return friends.get(name);
    }
}
