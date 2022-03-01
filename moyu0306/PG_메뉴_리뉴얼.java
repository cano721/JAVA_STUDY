import java.util.*;
class Solution {
    static ArrayList<HashSet<String>> wordList = new ArrayList<>();
    static HashMap<String,Integer>wordMap = new HashMap<>();
    static ArrayList<String> answer = new ArrayList<>();
    static int max = 0;
    public String[] solution(String[] orders, int[] course) {
        for(int i=0; i<course.length; i++) wordList.add(new HashSet<String>());

        for(int i=0; i<course.length; i++){
            max =0;
            for(int j=0; j<orders.length; j++) {
                char[] order =orders[j].toCharArray();// string 내 character는 char array로 변경후 실행
                Arrays.sort(order);
                addWord(new String(order),"",0,0,course[i],i);

            }

            for(String word:wordList.get(i)){
                if(max==wordMap.get(word)&&max>1) answer.add(word);

            }
        }
        Collections.sort(answer);
        String[] result = answer.toArray(new String[answer.size()]);
        return result;

    }

    public static void addWord(String word, String cand,int idx, int cnt,int size,int courseIdx) {//combination (참조할 것,전달,idx,cnt,size)
        if (cnt == size) {
            wordList.get(courseIdx).add(cand);
            wordMap.put(cand, wordMap.getOrDefault(cand, 0) + 1);
            max = Integer.max(max, wordMap.get(cand)); // 미리 max를 계산해놓기. 차후에 하게 되면 구현이 복잡해짐.
            return;
        }

        for (int i = idx; i < word.length(); i++) {
            addWord(word, cand + word.charAt(i), i + 1, cnt + 1, size, courseIdx);
        }

    }