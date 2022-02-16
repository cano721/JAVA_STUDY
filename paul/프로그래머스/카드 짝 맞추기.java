import java.util.*;
class Solution {
    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};
    static int[][] board;
    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y = yy;
            x = xx;
        }
    }
    
    static class Node{
        int y, x, cnt;
        public Node(int yy, int xx ,int c){
            y =yy;
            x =xx;
            cnt = c;
        }
    }
    
    public int solution(int[][] board, int r, int c) {
        int answer = 0;
        while(true){
            Node node = bfs(r,c, board);
            if(node == null) break;
            answer += node.cnt;
            r = node.y;
            c = node.x;
        }
        return answer;
    }
    
    static boolean isValid(int y, int x){
        if(y < 0 || x< 0 || y> 3 || x > 3) return false;
        return true;
    }
    
    static Node bfs(int r, int c, int[][] board){
        int[][] dist = new int[4][4];
        Pair pair = new Pair(r,c);
        Queue<Pair> q = new LinkedList<>();
        q.add(pair);
        
        //가장 가까운 번호 아무거나 찾기.
        while(!q.isEmpty()){
            Pair now = q.poll();
            //move
            for(int i =0; i< 4; i++){
                // once move
                int y = dy[i] + now.y, x = dy[i] + now.x;
                if(isValid(y,x)){
                    dist[y][x] = dist[now.y][now.x] + 1;
                    
                    //번호 찾음.
                    if(board[y][x] != 0 ){
                        //enter 값 더해서
                        board[y][x] =0;
                        Node findNode = findSameImage(y,x, board);
                        findNode.cnt = findNode.cnt + dist[y][x] +1;
                        return findNode;
                    }
                    q.add(new Pair(y,x));
                } 
                // ctrl + move
                
                int cy =y, cx =x;
                for(int j =0; j<2; j++){
                    cy += dy[j]; cx += dy[j];
                    
                    if(!isValid(cy, cx)) {
                        int py = cy-dy[j], px = cx-dx[j];
                        // 한 칸이라도 움직임.
                        if(cy-dy[j] != y && cx-dx[j] != x ) {
                            dist[py][px] += 1;
                            q.add(new Pair(py,px));
                        }
                        break;
                    }
                    
                    //숫자 찾음
                    if(board[cy][cx] != 0){
                        board[cy][cx] =0;
                        Node findNode = findSameImage(y,x, board);
                        findNode.cnt = findNode.cnt + dist[y][x] +1;
                        return findNode;
                    }
                    
                }
                
            } //for 
            
        } // while
        
        return null;
    } // bfs
    
    static Node findSameImage(int r, int c, int[][] board){
        int[][] dist = new int[4][4];
        
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(r,c));
        
        while(true){
            Pair now = q.poll();
            for(int i =0; i<4; i++){
                int y = dy[i] + now.y, x = dy[i] + now.x;
                if(isValid(y,x) &&  dist[y][x] == 0){
                    dist[y][x] = dist[now.y][now.x] + 1;
                    //번호 찾음.
                    if(board[y][x] == board[r][c] ){
                        board[y][x] =0;
                        //enter 값 더해서
                        Node node = new Node(y,x, dist[y][x] + 1);
                        return node;   
                    }
                    // 못찾음 
                    q.add(new Pair(y,x));
                } // if
                
                // Ctrl+ move
                int cy =y, cx =x;
                for(int j =0; j<2; j++){
                    cy += dy[j]; cx += dy[j];
                    
                    if(!isValid(cy, cx)) {
                        // 한 칸이라도 움직임.
                        int py = cy-dy[j], px = cx-dx[j];
                        if(cy-dy[j] != y && cx-dx[j] != x ) {
                            dist[py][px] += 1;
                            q.add(new Pair(py, px));
                        }
                        break;
                    }
                    
                    //숫자 찾음
                    if(board[cy][cx] != 0){
                        // 같은 숫자 찾음
                        if(board[cy][cx] == board[r][c]){
                            board[cy][cx] =0;
                            Node node = new Node(cy, cx, dist[cy][cx] + 1);
                            return node;
                        }
                        
                        //거리 증가.
                        dist[cy][cx] += dist[y][x] + 1;
                        q.add(new Pair(cy, cx));
                        break;
                    }
                }
                
            }
            
        }
    }

}