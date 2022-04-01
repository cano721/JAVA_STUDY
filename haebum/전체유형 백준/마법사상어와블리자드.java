/**
 * 1. 맵 생성
 * 
 * 2. 각 모듈화를 통해 구현할 예정
 */

import java.util.*;
import java.io.*;

public class 마법사상어와블리자드 {
    public static int n,m, result,sx,sy;
    public static ArrayList<int[]> numberMap = new ArrayList<>();
    public static int[][] beadMap;
    public static int[][] dirXY = {{-1,0},{1,0},{0,-1},{0,1}};
    public static int[] boomBead = new int[4];
    public static Queue<int[]> q;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        beadMap = new int[n][n];

        // 초기 구슬맵 채우기
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                beadMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        initNumberMap(); // 번호맵 채우기

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) -1;
            int s = Integer.parseInt(st.nextToken());
            blizard(d,s); // 마법 사용
            moveBead(); // 이동
            while(true){
                if(groupBead() == false){ // 폭파 그룹 확인 및 폭파
                    break;
                }
                moveBead(); // 이동
            }
            changeBead(); // 구슬 변화
        }

        System.out.println(boomBead[1]*1 + boomBead[2]*2 + boomBead[3]*3); // 폭파 구슬 더해서 출력
    }

    // public static void printMap(){
    //     System.out.println("--------------------");
    //     for(int i = 0; i < n; i++){
    //         for(int j = 0; j < n; j++){
    //             System.out.print(beadMap[i][j] +" ");
    //         }
    //         System.out.println();
    //     }
    //     System.out.println("--------------------");
    // }


    //구슬 변화 함수
    public static void changeBead(){

        int num = 0;
        int cnt = 0;
        int[][] newMap = new int[n][n]; // 변화된 구슬들 담을 맵

        ArrayList<Integer> beadCntNum = new ArrayList<>(); // 동일 번호 구슬 개수와 번호 담기
        for(int i = 0; i < numberMap.size(); i++){ // 맵 전체 순서대로 돌기
            int x = numberMap.get(i)[0];
            int y = numberMap.get(i)[1];

            if(beadMap[x][y] == 0){ //더 이상 구슬 없으면 종료
                break;
            }

            if(num != 0 && num != beadMap[x][y]){ // 기존 구슬과 번호가 달라지면 담기
                beadCntNum.add(cnt);
                beadCntNum.add(num);
                cnt = 0;
            }
            cnt++;
            num = beadMap[x][y];
        }

        if(num != 0){ // 마지막꺼 담기
            beadCntNum.add(cnt);
            beadCntNum.add(num);
        }

        int idx = 0;
        for(int i = 0; i < beadCntNum.size(); i++){ // 담았던 구슬 개수와 번호들을 입력
            int x = numberMap.get(idx)[0];
            int y = numberMap.get(idx)[1];

            newMap[x][y] = beadCntNum.get(i);
            idx++;
            if(idx >= numberMap.size()){ // 맵 범위 넘어가면 종료
                break;
            }
        }
        beadMap = newMap; // 변화된 구슬로 맵 변경
    }

    // 폭파 구슬그룹 체크 및 폭파 함수
    public static boolean groupBead(){
        boolean check = false;
        ArrayList<int[]> curGroup = new ArrayList<>(); // 현재 그룹
        ArrayList<int[]> boomGroup = new ArrayList<>(); // 폭파할 그룹

        int beadNum = 0; // 구슬번호
        int cnt = 0; // 개수
        for(int i = 0; i < numberMap.size(); i++){
            int x = numberMap.get(i)[0];
            int y = numberMap.get(i)[1];
            
            if(beadMap[x][y] == beadNum && beadMap[x][y] != 0){
                cnt++;
            }else{
                if(cnt >= 4){ // 현재 그룹이 4개이상이면 폭파 그룹에 담기
                    boomGroup.addAll(curGroup); // 좌표들 폭파 그룹에 담기
                    check = true; // 한번 이상 폭파됨
                }
                if(beadMap[x][y] == 0){ // 더이상 구슬이 없으면 종료
                    break;
                }
                curGroup.clear();
                cnt = 1;
            }
            curGroup.add(new int[] {x,y}); // 현재 좌표를 담기
            beadNum = beadMap[x][y];
        }

        if(cnt >= 4){ // 마지막꺼 체크
            boomGroup.addAll(curGroup);
            check = true;
        }

        if(check){ // 폭파할 그룹이 있으면 폭파
            for(int i = 0; i < boomGroup.size(); i++){
                int x = boomGroup.get(i)[0];
                int y = boomGroup.get(i)[1];

                int num = beadMap[x][y];
                boomBead[num]++; // 폭파 개수 늘리기
                beadMap[x][y] = 0; // 폭파해서 빈칸으로 바꾸기
            }
        }

        return check;
    }

    // 구슬 이동 함수
    public static void moveBead(){
        int pidx = 0; // 빈칸 좌표
        int idx = 1; // 구슬 가져올 좌표

        while(idx < numberMap.size()){ // 맵 벗어나기전까지만 체크

            // 빈칸 좌표
            int x = numberMap.get(pidx)[0];
            int y = numberMap.get(pidx)[1];

            // 구슬 가져올 좌표
            int nx = numberMap.get(idx)[0];
            int ny = numberMap.get(idx)[1];

            //해당 좌표가 빈칸일때
            if(beadMap[x][y] == 0){
                if(beadMap[nx][ny] == 0){ // 구슬 가져올 좌표가 빈칸이면 다음좌표 확인.
                    idx++;
                    continue;
                }
                beadMap[x][y] = beadMap[nx][ny]; // 구슬 이동
                beadMap[nx][ny] = 0;
            }

            pidx++; // 빈칸이 아니면 다음 좌표 확인

            if(pidx == idx){ // 숫자가 더 큰 좌표에서만 가져올 수 있으므로 같아지면 증가.
                idx++;
            }
        }
    }

    // 마법 사용 함수
    public static void blizard(int d, int s){
        for(int i = 1; i <= s; i++){ // 거리만큼 돌면서 구슬 지우기
            int x = sx + dirXY[d][0]*i; // 해당 방향, 해당 거리 구슬 좌표
            int y = sy + dirXY[d][1]*i;

            if(x < 0 || x >= n || y < 0 || y >= n){ // 맵 벗어나면 종료
                break;
            }
            beadMap[x][y] = 0;
        }
    }

    // 초기 번호 맵 생성 함수
    public static void initNumberMap(){
        sx = (n+1)/2 -1;
        sy = sx;

        beadMap[sx][sy] = 4; // 상어 표시

        int s = 1; // 방향 별 움직일 거리
        int[][] initDir = {{0,-1},{1,0},{0,1},{-1,0}};

        q = new LinkedList<>();

        q.offer(new int[]{sx,sy,0,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];
            int d = cur[2]; // 현재 번호를 채울 방향
            int cnt = cur[3]; // 현재방향으로 몇번 움직였는지

            if(cnt == s){
                if(d == 1 || d == 3){
                    s+=1;
                }
                d = (d + 1) % 4;
                cnt = 0;
            }
            
            int nx = x + initDir[d][0];
            int ny = y + initDir[d][1];

            if(nx < 0 || nx >= n || ny < 0 || ny >= n){
                continue;
            }

            numberMap.add(new int[] {nx,ny});
            q.offer(new int[] {nx,ny,d,cnt+1});
        }
    }
}