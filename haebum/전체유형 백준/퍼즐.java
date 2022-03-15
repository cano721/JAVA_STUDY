/**
 * 1. bfs로 전체 확인
 * 
 * 2. 맵을 set으로 저장하여 왔던 곳은 다시 안가기
 * 
 * 3. 맵은 칸을 밀어가며 숫자로 표현하기
 */

import java.util.*;
import java.io.*;

public class 퍼즐 {

    public static int start = 0;

    public static Map<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0; i < 3; i++){
            String[] str = br.readLine().split(" ");
            for(int j = 0; j < 3; j++){
                int num = Integer.parseInt(str[j]);
                if(num == 0){
                    num = 9;
                }
                start *= 10;
                start += num;
            }
        }

        int cnt = bfs();
        bw.write(cnt +"\n");
        bw.flush();
        bw.close();
    }

    public static int bfs(){
        Queue<Integer> q = new LinkedList<>();
        int[][] dirXY = {{0,1},{0,-1},{1,0},{-1,0}};
        map.put(start,0);
        q.offer(start);

        while(!q.isEmpty()){
            int curNum = q.poll();
            String cur = String.valueOf(curNum);
            int ninePos = cur.indexOf("9");
            int x = ninePos/3; // 행
            int y = ninePos%3; // 열

            for(int i = 0; i < 4; i++){
                int nx = x + dirXY[i][0];
                int ny = y + dirXY[i][1];
                int move = nx*3 + ny; // 숫자표현

                if(nx < 0 || nx >= 3 || ny < 0 || ny >= 3){
                    continue;
                }
                StringBuilder next = new StringBuilder(cur);
                //스왑
                char temp = next.charAt(move); // 이동할 곳에 담긴 숫자
                next.setCharAt(move, '9');
                next.setCharAt(ninePos, temp);
                
                int nextNum = Integer.parseInt(next.toString());

                if(!map.containsKey(nextNum)){
                    map.put(nextNum,map.get(curNum)+1);
                    q.offer(nextNum);
                }
            }
        }
        if(map.containsKey(123456789)){
            return map.get(123456789);
        }else{
            return -1;
        }
    }
}
