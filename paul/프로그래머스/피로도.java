class Solution {
    static boolean[] vis;
    static int answer = -1;
    public int solution(int k, int[][] dungeons) {
        vis = new boolean[dungeons.length];
        
        dfs(0, k, dungeons);
        
        return answer;
    }
    
    static void dfs(int picked, int remain, int[][] dungeons){
        answer = Math.max(answer, picked);
        if(picked == dungeons.length) return;
        
        for(int i =0; i<dungeons.length; i++){
            if(vis[i]) continue;
            if(remain < dungeons[i][0] ) continue;
            vis[i] =true;
            dfs(picked+1, remain - dungeons[i][1], dungeons);
            vis[i] = false;
        }
        
    }
}