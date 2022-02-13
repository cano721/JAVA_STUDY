import java.util.*;
import java.io.*;
class Solution {
    
    static boolean[] vis;
    static int answer = 0;
    static Set<String> ch = new HashSet<>();
    public int solution(String[][] relation) {
        
        
        for(int i = 1; i <= relation[0].length; i++){
            dfs(new ArrayList<Integer>(), 0, i, relation, "");
        }
        
        return answer;
    }
    
    static public void dfs(List<Integer> list, int k, int m, String[][] relation, String key){
        if(list.size() == m){
            //System.out.println("in");
            //list에 뽑은거 들어있음.
            Set<String> set = new HashSet<>();
            boolean flag = true;
            for(int i =0; i< relation.length; i++){
                String s = "";
                for(int j : list){
                    s += relation[i][j];
                }
                if(!set.add(s)){
                    flag= false;
                    return;
                }
            }
            System.out.println(key);
            ch.add(key);
            answer++;
            return;
        }
        
        for(int i =k; i< relation[0].length; i++){
            if(ch.contains(key + Integer.toString(i))) continue;
            list.add(i);
            dfs(list, i+1, m, relation, key + Integer.toString(i));
            list.remove(list.size()-1);
        }
        
    }
}