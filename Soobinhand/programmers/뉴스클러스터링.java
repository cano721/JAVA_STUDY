package Soobinhand.programmers;

import java.util.ArrayList;

public class 뉴스클러스터링 {
    public int solution(String str1, String str2) {
        int answer = 0;
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<String> inter = new ArrayList<>();
        ArrayList<String> union = new ArrayList<>();
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        for (int i=0;i<str1.length()-1;i++){
            char first = str1.charAt(i);
            char second = str1.charAt(i + 1);
            if (first >= 'a' && first <= 'z' && second <= 'z' && second >= 'a'){
                list1.add(first+""+second);
            }
        }
        for (int i=0;i<str2.length()-1;i++){
            char first = str2.charAt(i);
            char second = str2.charAt(i + 1);
            if (first >= 'a' && first <= 'z' && second <= 'z' && second >= 'a'){
                list2.add(first+""+second);
            }
        }
        for (String s : list1){
            if (list2.remove(s)){
                inter.add(s);
            }
            union.add(s);
        }
        union.addAll(list2);

        double interSize = inter.size();
        double unionSize = union.size();
        if (unionSize == 0){
            return 65536;
        }
        answer = (int) (interSize / unionSize * 65536);
        return answer;
    }
}
