import java.io.*;
import java.util.*;

public class Main{
    
    static String s, p;
    static int[] fail;
    static int n,m;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        p = br.readLine();
        n = s.length();
        m = p.length();

        getFailindex();
        int ans = KMP();

        System.out.println(ans);
    }

    static int KMP() {
		
		for(int i=0, j=0; i< n; i++) {
            //현재 글자가 불일치하면 fail 함수를 통해 수정된 위치로 옮김
			while(j>0 && s.charAt(i) != p.charAt(j)) j = fail[j-1];
			
			if(s.charAt(i) == p.charAt(j)) {
				
                if(j == m-1) {
                    //찾음
					return 1; 
				}else {
					j++;
				}
			}
		}
        //못찾음
		return 0;  
		
	}
	
	static void getFailindex(){
        fail = new int[m];
        for(int i =1, j=0; i<m; i++){
            while(j>0 && p.charAt(i) != p.charAt(j)) j = fail[j-1];
            if(p.charAt(i) == p.charAt(j)) fail[i] = ++j;
        }
    }
}