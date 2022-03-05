import java.util.*;

class Solution {
    static HashMap<String,ArrayList<Integer>> map;
    static HashMap<String,Boolean> isSortedMap;
    public int[] solution(String[] info, String[] query) {
        map = new HashMap<>();
        isSortedMap = new HashMap<>();
        int[] answer = new int[query.length];
        for(int i=0; i<info.length; i++){
            String[] cond = info[i].split(" ");
            mkList(cond,new String(),0,0,4);
        }
        
        for(int j=0; j<query.length; j++){
            answer[j] = answerQuery(query[j]);
        }
        
        return answer;
    }
    static public int answerQuery(String query){
        String[] splits = query.split(" and ");
        String[] splits2 = splits[3].split(" ");
        int point = Integer.parseInt(splits2[1]);
        String key = new StringBuilder().append(splits[0]).append(splits[1]).append(splits[2]).append(splits2[0]).toString();
        ArrayList<Integer> list = map.getOrDefault(key,new ArrayList<Integer>());
        if(!isSortedMap.getOrDefault(key,false)){
            Collections.sort(list);
            map.put(key,list);
            isSortedMap.put(key,true);
        }
        return count(point,list);

    }
    static public void mkList(String[] cond, String sb,int idx, int cnt, int size){
        if(cnt == size){
            String key = sb;
            ArrayList<Integer> list = map.getOrDefault(key,new ArrayList<Integer>());
            list.add(Integer.parseInt(cond[4]));
            map.put(key,list);
            return;
        }  
            int pre = sb.length();
            mkList(cond,sb.append(cond[idx]),idx+1,cnt+1,size);
            sb.delete(pre,sb.length());
            mkList(cond,sb.append("-"),idx+1,cnt+1,size);
    }
    
   public static int count(int point, ArrayList<Integer> points){
        int high= points.size();
        int low =0;
        int mid =(high+low)/2;

        while(low<mid){
            if(points.get(mid)>=point) high = mid;
            else low = mid;
            mid = (high+low)/2;
        }

        if(points.size()==0) return 0;
        if(points.get(mid)>=point) return points.size()-mid;
        else return points.size()-mid-1;

    }

}