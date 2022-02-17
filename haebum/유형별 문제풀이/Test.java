
/**
    1. 카드 순서 정하기
    1-1) 순열
    
    2. 순서에 따른 카드 맞추기
    2-1) 순서마다 별도로 해야함(맵 또는 카드 위치)
    
    3. 카드 이동 순서 계산 함수
    3-1) bfs
    
    4. 순서에따른 카드 다 맞췄을 때 최소값 반환
**/

import java.util.*;

class Test{
    public static void main(String[] args) {
        int[][] board = {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}};
        int r = 1;
        int c = 0;
        Solution.solution(board, r, c);
    }
}

class Solution {
    public static int[] card, choice;
    public static int w,h;
    public static int[] dirX = {0,0,1,-1};
    public static int[] dirY = {1,-1,0,0};
    
    public static Map<Integer,ArrayList<int[]>> map = new HashMap<>();
    
    public static int answer = Integer.MAX_VALUE;
    
    public static int solution(int[][] board, int r, int c) {
        
        w = board[0].length;
        h = board.length;
        
        int cardNum = cardNum(board);
        card = new int[cardNum+1];
        choice = new int[cardNum+1];
        
        cardSequence(board,1,r,c);
        System.out.println(answer);
        return answer;
    }
    
    // 정해진 순서에 따른 카드 맞추기 함수
    public static void solve(int r, int c,int[][] board){
        int cnt = 0;

        for(int i = 1; i < choice.length; i++){
            int num = choice[i];
            
            // 해당 두개의 카드 위치
            int[] end1 = map.get(num).get(0);
            int[] end2 = map.get(num).get(1);
            
            // 두카드까지 가는 거리 조작 횟수
            int dis1 = bfs(r,c,end1,board);
            int dis2 = bfs(r,c,end2,board);
            
            // 현재위치로부터 가까운 곳 먼저 갔다가 다음 꺼 가서 카드 맞추기
            if(dis1 > dis2){
                cnt += dis2+2; // 엔터키 2번 더하기
                r = end2[0];
                c = end2[1];
                cnt += bfs(r,c,end1,board);
                r = end1[0];
                c = end1[0];
            }else{
                cnt += dis1+2;
                r = end1[0];
                c = end1[1];
                cnt += bfs(r,c,end2,board);
                r = end2[0];
                c = end2[1];
            }
            
            // 짝 맞춘 카드 0으로 만들기
            board[end1[0]][end1[1]] = 0;
            board[end2[0]][end2[1]] = 0;
        }
        
        // 0으로 만든 카드 되돌리기
        for(int key : map.keySet()){
            int[] pos1 = map.get(key).get(0);
            int[] pos2 = map.get(key).get(1);
            
            board[pos1[0]][pos1[1]] = key;
            board[pos2[0]][pos2[1]] = key;
        }
        
        // 현재 순서 조작값 비교
        answer = Math.min(answer,cnt);
    }
    
    // 카드 맞추기 위한 이동 조작 카운트 함수
    public static int bfs(int r, int c, int[] end, int[][] board){
        
        boolean[][] visited = new boolean[h][w];
        
        Queue<int[]> q = new LinkedList<>();
        
        visited[r][c] = true;
        q.offer(new int[]{r,c,0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            int x = cur[0];
            int y = cur[1];
            int cost = cur[2];
            
            // 원하는 숫자면 탈출
            if(x == end[0] && y == end[1]){
                return cost;
            }
            
            // 사방 탐색
            for (int d = 0; d < 4; d++) {
                int nr = x + dirX[d];
                int nc = y + dirY[d];
                if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[] { nr, nc, cost + 1 });
                }
            }

            // ctrl + 사방 탐색
            for (int d = 0; d < 4; d++) {
                int[] result = findCard(board, x, y, d);
                if ((result[0] != x || result[1] != y) && !visited[result[0]][result[1]]) {
                    visited[result[0]][result[1]] = true;
                    q.offer(new int[] { result[0], result[1], cost + 1 });
                }
            }

           
        }
        return 0;
    }

    static int[] findCard(int[][] board, int r, int c, int d) {
        r += dirX[d];
        c += dirY[d];
        while (r >= 0 && r < 4 && c >= 0 && c < 4) {
            if (board[r][c] != 0) {
                return new int[]{r, c};
            }
            r += dirX[d];
            c += dirY[d];
        }
        return new int[]{r - dirX[d], c - dirY[d]};
    }
    
    // 순서 정하기 함수
    public static void cardSequence(int[][] board,int stage,int r, int c){
        
        if(stage == card.length){
            solve(r,c,board);
            return;
        }
        
        for(int i = 1; i < card.length; i++){
            if(card[i] == 0){
                card[i] = 1;
                choice[stage] = i;
                cardSequence(board, stage+1, r, c);
                card[i] = 0;
            }
        }
    }
    
    
    // 카드 개수 파악 함수
    public static int cardNum(int[][] board){
        
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(board[i][j] != 0){
                    if(!map.containsKey(board[i][j])){
                        map.put(board[i][j],new ArrayList<>());
                    }
                    map.get(board[i][j]).add(new int[]{i,j});
                }
            }
        }
        
        return map.size();
    }
}