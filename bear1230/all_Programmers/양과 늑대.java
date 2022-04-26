import java.util.*;

class Solution {
    
    public int answer = 0;
    public int[] info;
    Map<Integer, Set<Integer>> map = new HashMap<>();
	
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        for (int i = 0; i < info.length; i++) {
            map.put(i, new HashSet<>());
        }

        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
        }
        dfs(1, 0, new HashSet<>(map.get(0)));
        return answer;
    }

    public void dfs(int sheep, int wolf, Set<Integer> node) {
        if (node.isEmpty()) {
            answer = Math.max(answer, sheep);
        }

        for (int next : node) {
            Set<Integer> N_node = new HashSet<>(node);
            N_node.remove(next);
            N_node.addAll(map.get(next));
            
            if (info[next] == 0) {
                dfs(sheep + 1, wolf, N_node);
            } else {
                if (wolf + 1 < sheep) {
                    dfs(sheep, wolf + 1, N_node);
                } else {
                    answer = Math.max(answer, sheep);        
                } 
            }
        }
    }
}
