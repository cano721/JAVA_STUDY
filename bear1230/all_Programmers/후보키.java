import java.util.*;
/*
블로그 참조 :https://velog.io/@hyeon930/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%9B%84%EB%B3%B4%ED%82%A4-Java
*/
class Solution {
    public int solution(String[][] relation) {
        ArrayList<Integer> candidateKey = new ArrayList<>();

        int rowLen = relation.length;
        int colLen = relation[0].length;

        //비트마스킹으로 모든 경우의 조합 시도
        for(int set = 1 ; set < (1 << colLen) ; set++) {
            // 최소성 검사
            if(!isMinimal(set, candidateKey))
                continue;

            // 유일성 검사
            if(isUnique(set, rowLen, colLen, candidateKey, relation)) {
                candidateKey.add(set);
            }
        }

        return candidateKey.size();
    }
    
    private static boolean isUnique(int set, int rowLen, int colLen, ArrayList<Integer> candidateKey, String[][] relation) {
        HashMap<String, String> map = new HashMap<>();

        //자릿수 구하기
        for(int row = 0 ; row < rowLen ; ++row) {
            String dataByKeySet = "";

            for(int th = 0 ; th < colLen ; ++th) {
                //set과 같다면 dataByKeySet에 추가
                if((set & (1 << th)) != 0) {
                    dataByKeySet += relation[row][th];
                }
            }

            //이미 있다면 유일성 X -> false
            if(map.containsKey(dataByKeySet))
                return false;
            //hashmap에 저장
            else
                map.put(dataByKeySet, dataByKeySet);
        }

        return true;
    }

    private static boolean isMinimal(int set, ArrayList<Integer> candidateKey) {
        //선정된 후보키 조합이 들어있다면 최소성 만족 X
        for(int key : candidateKey) {
            //포함관계 구하기
            if((key & set) == key)
                return false;
        }

        return true;
    }
    
}
