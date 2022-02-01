import java.util.*;
public class PG_tuple {
    public int[] solution(String s) {
        
        String []str = s.split("[}]+[,]+[{]|[{]{2}|[}]{2}");
        String[][] temp = new String[str.length][];
        
        for (int i=0; i<str.length; i++) {
            temp[i] = str[i].split(",");
        }
        
        Arrays.sort(temp, (o1, o2) -> o1.length-o2.length);
        Set<String> set = new HashSet<>();
        int []answer = new int[temp.length-1];
        
        for(int i=1; i<temp.length; i++) {
        	for(int j=0; j<temp[i].length; j++) {
        		if(!set.contains(temp[i][j])) {
        			answer[i-1] = Integer.parseInt(temp[i][j]); 
        			set.add(temp[i][j]);
        		}
        	}
        }
        return answer;
    }
}