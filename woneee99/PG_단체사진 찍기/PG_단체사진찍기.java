
public class PG_단체사진찍기 {
	static int answer = 0;
	static boolean []checked;
	static int[] idx;
	static char[] ch = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};;
	public static void main(String[] args) {
		checked = new boolean[26];
		idx = new int[26];
		
		int n = 2;
		String []data = {"N~F=0", "R~T>2"};
		cal(0, data);
		
		System.out.println(answer);
		
	}
	static void cal(int cnt, String []data) {
		if(cnt == 8) {
			if(check(data)) answer++;
			return;
		}
		for(int i=0; i<8; i++) {
			int ck = ch[i] - 'A';
			if(checked[ch[i] - 'A']) continue;
			checked[ch[i] - 'A'] = true;
			idx[ch[i] - 'A'] = cnt;
			cal(cnt+1, data);
			checked[ch[i] - 'A'] = false;
		}
	}
	static boolean check(String []data) {
		for(int i=0; i<data.length; i++) {
            char a = data[i].charAt(0);
            char b = data[i].charAt(2);
            char op = data[i].charAt(3);
            int dist = data[i].charAt(4)-'0';
            
            int diff = Math.abs(idx[a-'A'] - idx[b-'A'])-1;
            switch (op) {
	            case  '=' :
	            	if(diff != dist) return false;
	            	break;
	            
				case  '>' :
					if(diff <= dist) return false;
					break;	
        
				case  '<' :
			    	if(diff >= dist) return false;
			    	break;
            }
		}
		return true;
	}
}
