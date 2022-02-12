// 전체 코드
import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    static ArrayList<HashSet<Integer>> candidateKey;
    static String Table[][];
    static int length;
    static int answer;
    public static int solution(String[][] relation) {
        answer = 0;
        candidateKey = new ArrayList<HashSet<Integer>>();
        Table = relation;
        length = relation[0].length;
        for(int i = 1; i <= length; i++) {
            makeSet(-1, i, 0, new HashSet<Integer>());
        }

        return answer;
    }

    public static void makeSet(int index, int target, int count, HashSet<Integer> set) {

        if(count == target) {
            if(!isUnique(set)) {
                return;
            }
            for(HashSet<Integer> key: candidateKey) {
                if(set.containsAll(key)) {
                    return;
                }
            }
            candidateKey.add(set);
            answer++;
            return;
        }

        for(int i = index + 1; i < length; i++) {
            HashSet<Integer> newSet = new HashSet<Integer>(set);
            newSet.add(i);
            makeSet(i, target, count + 1, newSet);
        }

    }

    public static boolean isUnique(HashSet<Integer> set) {

        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i < Table.length; i++) {
            String temp = "";
            for(int index: set) {
                temp+= Table[i][index];
            }
            if(!list.contains(temp)) {
                list.add(temp);
            }else {
                return false;
            }
        }
        return true;
    }
}