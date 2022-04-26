import java.util.*;
class Solution {
    
    int[] l = new int[20];
    int[] r = new int[20];
    int[] val = new int[20];
    int n;
    int ans = 1;
    int[] vis = new int[1<<17]; 
    
   
    void solve(int state){
        if(vis[state] == 1) return;
        vis[state] = 1;

        int wolf = 0, num = 0;
        for(int i = 0; i < n; i++){
            if((state & (1<<i)) != 0){
                num++;
                wolf+=val[i];
            }
        }

        if(2*wolf >= num) return;
        
        ans = Math.max(ans,num-wolf);
        
        for(int i = 0; i < n; i++){
            
            if((state & (1<<i)) == 0) continue;
            
            if(l[i] != -1) solve(state | (1<<l[i]));
            
            if(r[i] != -1) solve(state | (1<<r[i]));
        }
    }
    
    
    public int solution(int[] info, int[][] edges) {
        n = info.length;
        Arrays.fill(l, -1);
        Arrays.fill(r, -1);
        for(int i = 0; i < n; i++) val[i] = info[i];
        for(int i = 0; i < n-1; i++){
            int a = edges[i][0]; 
            int b = edges[i][1]; 
            if(l[a] == -1) l[a] = b;
            else r[a] = b;
        }
        solve(1); 
        return ans;
    }
}