import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n16172_나는친구가적다Large {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String S = br.readLine().replaceAll("[0-9]", "");
        String K = br.readLine();
        char[] s = S.toCharArray();
        char[] p = K.toCharArray();
        /* getPi */
        int[] pi = new int[K.length()];
        int j = 0;
        for (int i = 1; i < p.length; i++) {
			while(j > 0 && p[i] != p[j]) {
				j = pi[j-1];
			}
			if(p[i] == p[j]) {
				pi[i] = ++j;
			}
		}
        /*kmp 알고리즘*/
        j = 0;
        int answer = 0;
        for (int i = 0; i < s.length; i++) {
			while(j > 0 && s[i] != p[j]) {
				j = pi[j-1];
			}
			if(s[i] == p[j]) {
				if(j == p.length - 1) {
					answer = 1;
					break;
				} else {
					j++;
				}
			}
		}
        
        System.out.println(answer);
	}

}
