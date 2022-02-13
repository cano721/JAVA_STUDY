package programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class d220214_후보키 {

	public static void main(String[] args) {
		String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
		System.out.println(solution(relation));
	}
	static String[][] relations;
	static Stack<Integer> s;
	static List<Integer> ans;
	static boolean[] check;
	static int row, col;
	private static int solution(String[][] relation) {
		relations = relation;
		col = relation.length;
		row = relation[0].length;

		ans = new ArrayList<>();
		for(int i=0; i<row; i++) {
			s= new Stack<>();
			check= new boolean[row];
			comb(0, i+1);
		}
		return ans.size();
	}
	
	static void comb(int pos, int r) {
		if(s.size() == r) {
			List<Integer> res = new ArrayList<>();
			for(int num : s) {
				res.add(num);
			}
			
			if(isSubKey(res)) { //유일성
				int cur =0;
				for(int num : res) {
					cur |= 1<<(num);
				}
				if(!isSubSet(cur)) { //최소성
					ans.add(cur); // 후보키 만족할 경우 후보키로 저장
				}
			}
			return;
		}
		for(int i=pos; i<row; i++) {
			if(!check[i]) {
				check[i] = true;
				s.push(i);
				comb(i,r);
				s.pop();
				check[i] = false;
			}
		}
	}
	/*유일성체크*/
	static boolean isSubKey(List<Integer> rowList) {
		Set<String> set = new HashSet<>();
        for(int i=0; i<col; i++) {
        	String data = "";
        	for(int row : rowList) {
        		data += relations[i][row];
        	}
        	if(set.contains(data)) {
        		return false;
    		}
    		set.add(data);
        }
        return true;
	}
	/*최소성체크*/
	static boolean isSubSet(int now) {
		for(int i=0; i<ans.size(); i++) {
			int ansData = ans.get(i);
			if((ansData & now) == ansData) return true;
		}
		return false;
	}
	
}
