import java.util.*;
class Solution {
     
    static String[] lang = {"cpp", "java", "python"};
    static String[] field = {"backend", "frontend"};
    static String[] career = {"junior", "senior"};
    static String[] food = {"chicken", "pizza"};
    static List<String[]> list = new ArrayList<>();
    static int ncnt = 0;
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        list.add(lang);
        list.add(field);
        list.add(career);
        list.add(food);
        
        Map<String, Integer> map = new HashMap<>();
        for(String s : info){
            String[] arr = s.split(" ");
            String key = "";
            int value = Integer.parseInt(arr[4]);
            for(int i =0; i< arr.length-1; i++) key += arr[i];
            map.put(key, value);
        }
        
        int idx =0;
        for(String s : query){
            
            //ArrayList<String>[] arrList = new ArrayList<>()[4];
            int[] cnts = new int[4];
            ncnt = 0;
            //for(int i =0; i<4; i++) arrList[i] = new ArrayList<>();
            
            String[] sarr = s.split(" ");
            String[] arr = new String[5];
            for(int i =0; i<sarr.length-1; i++){
                //System.out.println("######38" + arr[i]);
                if(sarr[i].equals("and")) continue;
                else if(sarr[i].equals("-")) {
                    
                    cnts[i/2] = list.get(i/2).length;
                    //System.out.println("####41   " + cnts[i/2]);
                }
                else cnts[i/2] = 1;

                arr[i/2] = sarr[i];
            }
            arr[4] = sarr[7];
            
            dfs("", 0, cnts, arr, map);
            
            answer[idx++] = ncnt;
        }
        
        return answer;
    }
    
    
    static void dfs(String key, int idx, int[] cnt, String[] arr, Map<String, Integer> map){
        if(idx == 4){
            System.out.println(key + "  value : " + map.get(key));
            if(map.containsKey(key) && Integer.parseInt(arr[4]) <= map.get(key)){
                
                ncnt++;
            }
            
            return; 
        }
        if(cnt[idx] == 1) dfs(key + arr[idx], idx+1, cnt, arr, map);
        else{
            for(int i =0; i< cnt[idx]; i++){
                dfs(key + list.get(idx)[i], idx+1, cnt, arr, map);
            }
        }
        
    }
    
}