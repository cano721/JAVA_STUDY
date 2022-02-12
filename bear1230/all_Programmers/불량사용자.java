import java.util.*;

class Solution {
    public int solution(String[] user_id, String[] banned_id) {
       ArrayList<Integer>[] list = new ArrayList[banned_id.length];

        for (int i = 0; i < banned_id.length; i++) {
            String tmp = banned_id[i];
            list[i] = new ArrayList<>();

            for (int j = 0; j < user_id.length; j++) {
                String user = user_id[j];
                if (user.length() != tmp.length())
                    continue;

                boolean check = true;
                for (int k = 0; k < user.length(); k++) {
                    if (tmp.charAt(k) == '*')
                        continue;
                    if (tmp.charAt(k) != user.charAt(k)) {
                        check = false;
                        break;
                    }
                }
                if (check) list[i].add(j);
            }
        }
        
        visited = new ArrayList<>();
        for(int i = 0; i < list[0].size(); i++) {
            int temp = list[0].get(i);
            dfs(list, temp, 1, 1 << temp);
        }
        return visited.size();
    }

    static ArrayList<Integer> visited;
    
    public void dfs(ArrayList<Integer>[] list, int num, int idx, int v) {
        if(idx == list.length) {
            for(int visit : visited) {
                if(visit == v) return;
            }
            visited.add(v);
            return;
        }

        for(int i = 0; i < list[idx].size(); i++) {
            int tmp = list[idx].get(i);
            if((v & (1 << tmp)) > 0) continue; 
            dfs(list, tmp, idx + 1, v | (1 << tmp));
        }
    }
}
