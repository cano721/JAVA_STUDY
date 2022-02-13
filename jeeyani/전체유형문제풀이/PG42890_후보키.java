package 전체유형문제풀이;

import java.util.*;


/*
유일성
{이름, 학년}, {전공, 학년}의 집합은 중복 칼럼이 존재하기 때문에 후보키가 될 수 없다.

최소성
{학번, 이름, 전공}은 {학번}만으로도 후보키가 될 수 있기 때문에 굳이 다른 튜플들과 묶일 필요가 없다.

---------------------------------------------------------------------------------------

1. 조합을 이용하여 모든 경우의 수 구하기

2. 구한 경우의 수에서 유일성과 최소성을 확인하기 [비트 마스킹]

2-1. 유일성 : 중복체크

2-2. 최소성 : 비트 마스킹을 이용하여, 각 상태의 부분집합과 상태를 체크


https://loosie.tistory.com/430
*/


public class PG42890_후보키 {

	public static void main(String[] args) {

		String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};

		int result = solution(relation);

		System.out.println(result);

	}
	
	static String[][] relations;
    static Stack<Integer> stack;
    static List<Integer> list;
    static boolean[] check;
    static int row, col;

	private static int solution(String[][] relation) {
		relations = relation;
        col = relations.length;
        row = relations[0].length;
        
        list = new ArrayList<>();
        
        //행의 길이(컬럼개수) 만큼 초기화
        for(int i=0; i<row; i++){
            stack = new Stack<>();
            check = new boolean[row];
            getComb(0, i+1);
        }
        
        return list.size();
	}

	private static void getComb(int pos, int r) {
		//탈출조건 : 모든 경우를 다 탐색한 경우
        if(stack.size() == r){
            
            List<Integer> result = new ArrayList<>();
            for(int cnt : stack){
                result.add(cnt);
            }
            
            //구한 경우의 수(집합)를 이용하여, 유일성 체크
            if(isUniKey(result)){
                int candKey = 0;
                for(int num : result){
                	//새로운 후보키의 비트(ex, {1,2} > 110 )
                	candKey |= 1 << (num);
                }
                //부분 집합에 해당되지 않는지, 최소성 체크
                if(!isMinKey(candKey)){
                    list.add(candKey);
                }
            }
            return;
        }
        
        //확인여부를 체크하며 재귀
        for(int i=pos; i<row; i++) {
			if(!check[i]) {
				check[i] = true;
				stack.push(i);
				getComb(i,r);
				stack.pop();
				check[i] = false;
			}
		}
		
	}
	//최소성 체크
	//이미 저장된 후보키가 새로운 후보키의 부분집합인지 체크
	private static boolean isMinKey(int candKey) {
		for(int i=0; i<list.size(); i++) {
			int ansData = list.get(i);
			
			if((ansData & candKey) == ansData) return true;
		}
		//부분집합에 해당하지 않으면 최소성을 만족
		return false;
	}

	//유일성체크
	private static boolean isUniKey(List<Integer> rowList) {
		Set<String> set = new HashSet<>();
        for(int i=0; i<col; i++) {
        	String data = "";
        	for(int row : rowList) {
        		data += relations[i][row];
        	}
        	
        	//중복되는지 확인
        	if(set.contains(data)) {
        		return false;
    		}
        	
    		set.add(data);
        }
        return true;
	}

}