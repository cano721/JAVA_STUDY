import java.util.*;

class Solution {
    public static int[] card, choice;
    public static int w,h;
    public static int[] dirX = {0,0,1,-1};
    public static int[] dirY = {1,-1,0,0};
    public static int answer = Integer.MAX_VALUE;
    
    public static int solution(int[][] board, int r, int c) {
        
        int cardNum = cardNum(board);
        card = new int[cardNum+1];
        choice = new int[cardNum+1];
        
        w = board[0].length;
        h = board.length;
        
        cardSequence(board,1,r,c);
        return answer;
    }
    
    // 정해진 순서에 따른 카드 맞추기 함수
    public static void solve(int r, int c,int[][] board){
        int cnt = 0;
        boolean[][] totalVisited = new boolean[h][w];
        int[][] newBoard = new int[h][w];

        for(int i = 0; i < h; i++){
            newBoard[i] = board[i].clone();
        }
        ArrayList<int[]> arr = new ArrayList<>();
        for(int i = 1; i < choice.length; i++){
            int num = choice[i];
            
            for(int j = 0; j < 2; j++){
                int[] pos = bfs(r,c,newBoard,num,totalVisited);
                r = pos[0];
                c = pos[1];
                cnt += pos[2];
                arr.add(new int[] {r,c});
                totalVisited[r][c] = true;
            }
            
            // 보드 카드 0으로 만들기
            for(int[] a : arr){
                newBoard[a[0]][a[1]] = 0;
            }
        }
        answer = Math.min(answer,cnt);
    }
    
    // 카드 맞추기 위한 조작 카운트 함수
    public static int[] bfs(int r, int c, int[][] board, int num,boolean[][] totalVisited){
        
        boolean[][] visited = new boolean[h][w];
        
        if(board[r][c] == num && totalVisited[r][c] == false){
            return new int[] {r,c,1};
        }
        
        Queue<int[]> q = new LinkedList<>();
        
        visited[r][c] = true;
        q.offer(new int[]{r,c,0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            int x = cur[0];
            int y = cur[1];
            int cost = cur[2];
            
            // 원하는 숫자면 탈출
            if(board[x][y] == num && totalVisited[x][y] == false){
                return new int[] {x,y,cost+1};
            }
            
            for(int idx = 0; idx < 4; idx++){
                for(int k = 1; k < 4; k++){
                    
                    int nx = dirX[idx]*k + x;
                    int ny = dirY[idx]*k + y;
                    
                    if(nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
                    
                    if(visited[nx][ny] == true) continue;
                    
                    // 1칸 이동
                    if(k == 1){
                        visited[nx][ny] = true;
                        q.offer(new int[] {nx,ny,cost+1});
                    // ctrl 이동(카드를 만나거나 끝자락까지 이동)
                    }else{
                        if(board[nx][ny] != 0){
                            visited[nx][ny] = true;
                            q.offer(new int[] {nx,ny,cost+1});
                            break;
                        // 
                        }else if(idx == 0){
                            if(ny == w-1){
                                visited[nx][ny] = true;
                                q.offer(new int[] {nx,ny,cost+1});
                                break;
                            }
                        }else if(idx == 1){
                            if(ny == 0){
                                visited[nx][ny] = true;
                                q.offer(new int[] {nx,ny,cost+1});
                                break;
                            }
                        }else if(idx == 2){
                            if(nx == 0){
                                visited[nx][ny] = true;
                                q.offer(new int[] {nx,ny,cost+1});
                                break;
                            }
                        }else{
                            if(nx == h-1){
                                visited[nx][ny] = true;
                                q.offer(new int[] {nx,ny,cost+1});
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        return new int[] {0,0,0};
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
        Set<Integer> set = new HashSet<>();
        
        for(int[] b : board){
            for(int card : b){
                if(card != 0){
                    set.add(card);
                }
            }
        }
        
        return set.size();
    }
}