import java.util.*;
//.참고 해서 했습니다..
class Solution {
    int[][] board;
    int[][] cases;
    int[] start;
    int answer;
    ArrayList<Integer> keys;
    HashMap<Integer, ArrayList<int[]>> map;
    public int solution(int[][] board, int r, int c) {
        this.board = board;
        this.map = new HashMap<>();
        this.keys = new ArrayList<>();
        this.start = new int[]{r,c};
        this.answer = 100;

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(board[i][j] != 0){
                    if(!map.containsKey(board[i][j])){
                        map.put(board[i][j], new ArrayList<>());
                        keys.add(board[i][j]);
                    }
                    map.get(board[i][j]).add(new int[]{i,j});
                }
            }
        }

        permutation(new int[this.keys.size()], 0, new boolean[this.keys.size()]);

        return answer;
    }

    int check(int[] aCase){
        int[] cur = this.start;

        int dist = 0;
        for(int i = 0; i < aCase.length; i++){
            int dist1 = getDistance(cur, this.map.get(aCase[i]).get(0));
            int dist2 = getDistance(cur, this.map.get(aCase[i]).get(1));
            if(dist1 < dist2){
                dist += dist1+2;
                dist += getDistance(this.map.get(aCase[i]).get(0), this.map.get(aCase[i]).get(1));
                cur = this.map.get(aCase[i]).get(1);
            }else{
                dist += dist2+2;
                dist += getDistance(this.map.get(aCase[i]).get(1), this.map.get(aCase[i]).get(0));
                cur = this.map.get(aCase[i]).get(0);
            }

            this.board[this.map.get(aCase[i]).get(0)[0]][this.map.get(aCase[i]).get(0)[1]] = 0;
            this.board[this.map.get(aCase[i]).get(1)[0]][this.map.get(aCase[i]).get(1)[1]] = 0;
        }

        for(int key: this.map.keySet()){
            board[this.map.get(key).get(0)[0]][this.map.get(key).get(0)[1]] = key;
            board[this.map.get(key).get(1)[0]][this.map.get(key).get(1)[1]] = key;
        }

        return dist;
    }

    void permutation(int[] aCase, int idx, boolean[] visited){
        if(idx == aCase.length){
            int dist = check(aCase);
            this.answer = dist < answer ? dist : answer;
        }

        for(int i = 0; i < this.keys.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                aCase[idx] = this.keys.get(i);
                permutation(aCase, idx+1, visited);
                visited[i] = false;
                aCase[idx] = 0;
            }
        }
    }

    int getDistance(int[] start, int[] end){
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];

        visited[start[0]][start[1]] = true;
        queue.add(new Node(start[0], start[1], 0));
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            int w_ctrl;
            if(cur.x == end[0] && cur.y == end[1])
                return cur.dist;

            if(cur.x-1 >= 0) {
                if(!visited[cur.x-1][cur.y]) {
                    visited[cur.x - 1][cur.y] = true;
                    queue.add(new Node(cur.x - 1, cur.y, cur.dist + 1));
                }

                w_ctrl = cur.x-1;
                while(w_ctrl > 0 && board[w_ctrl][cur.y] == 0)
                    w_ctrl--;
                if(!visited[w_ctrl][cur.y]) {
                    visited[w_ctrl][cur.y] = true;
                    queue.add(new Node(w_ctrl, cur.y, cur.dist + 1));
                }
            }

            if(cur.y-1 >= 0) {
                if(!visited[cur.x][cur.y-1]) {
                    visited[cur.x][cur.y - 1] = true;
                    queue.add(new Node(cur.x, cur.y - 1, cur.dist + 1));
                }

                w_ctrl = cur.y-1;
                while(w_ctrl > 0 && board[cur.x][w_ctrl] == 0)
                    w_ctrl--;
                if(!visited[cur.x][w_ctrl]) {
                    visited[cur.x][w_ctrl] = true;
                    queue.add(new Node(cur.x, w_ctrl, cur.dist + 1));
                }
            }

            if(cur.x+1 < 4) {
                if(!visited[cur.x+1][cur.y]) {
                    visited[cur.x + 1][cur.y] = true;
                    queue.add(new Node(cur.x + 1, cur.y, cur.dist + 1));
                }

                w_ctrl = cur.x+1;
                while(w_ctrl < 3 && board[w_ctrl][cur.y] == 0)
                    w_ctrl++;
                if(!visited[w_ctrl][cur.y]) {
                    visited[w_ctrl][cur.y] = true;
                    queue.add(new Node(w_ctrl, cur.y, cur.dist + 1));
                }
            }

            if(cur.y+1 < 4 ){
                if(!visited[cur.x][cur.y+1]) {
                    visited[cur.x][cur.y + 1] = true;
                    queue.add(new Node(cur.x, cur.y + 1, cur.dist + 1));
                }

                w_ctrl = cur.y+1;
                while(w_ctrl < 3 && board[cur.x][w_ctrl] == 0)
                    w_ctrl++;
                if(!visited[cur.x][w_ctrl]) {
                    visited[cur.x][w_ctrl] = true;
                    queue.add(new Node(cur.x, w_ctrl, cur.dist + 1));
                }

            }
        }
        return 0;
    }

    class Node{
        int x, y, dist;
        public Node(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}