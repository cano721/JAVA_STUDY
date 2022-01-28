import java.util.Arrays;
import java.util.Comparator;
public class PG_filesort {
    public String[] solution(String[] files) {
        
        Arrays.sort(files, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				String []file1 = cal(o1);
				String []file2 = cal(o2);
				
				int val = file1[0].compareTo(file2[0]);
				
				if(val == 0) {
					int num1 = Integer.parseInt(file1[1]);
					int num2 = Integer.parseInt(file2[1]);
					
					return num1-num2;
				}
				return val;
			}
		});
        return files;
    }
    static String[] cal(String str) {
		String head="", number="", tail="";
		int k;
		
		for(k=0; k<str.length(); k++) {
			if('0' <= str.charAt(k) && '9' >= str.charAt(k)) {
				break;
			}
			head += str.charAt(k);
		}
		for(; k<str.length(); k++) {
			if(Character.isDigit(str.charAt(k))) {
				number += str.charAt(k);
			}
			else break;
		}
		
		for(; k<str.length(); k++) {
			tail += str.charAt(k);
		}
		
		String []file = {head.toLowerCase(), number, tail};
		
		return file;
	}
}