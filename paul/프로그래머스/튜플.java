import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer;

        String[] arr = s.substring(1, s.length()).split("}");
        List<Integer>[] list = new ArrayList[arr.length];
        tokenizer(arr, list);
        Arrays.sort(list, (o1,o2) -> o1.size()- o2.size()) ;

        answer = new int[list.length];

        Set<Integer> set = new HashSet<>();
        for(int i =0; i< list.length; i++){
            for(int j = 0; j < list[i].size(); j++){
                int k = list[i].get(j);
                if(set.add(k)) answer[i] = k;
            }
        }
        return answer;
    }

    static void tokenizer(String[] arr,List<Integer>[] list){

        for(int i =0; i < arr.length; i++){
            list[i] = new ArrayList<>();
            String t = "";
            for(int j =0; j< arr[i].length(); j++){
                char k = arr[i].charAt(j);
                if(k >= '0' && k <= '9') t += k;
                else{
                    if(t.length() >0) list[i].add(Integer.parseInt(t));
                    t="";
                }
            }
            list[i].add(Integer.parseInt(t));
        }
    }
}