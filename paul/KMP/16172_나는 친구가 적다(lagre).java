import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static String str, keyWord;
    static int n, m;
    static int[] fail;
    public static void main(String[] args) throws Exception{
        input();
        pro();        
    }

    public static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().replaceAll("[0-9]", "");       
        keyWord = br.readLine();
        n = str.length();
        m = keyWord.length();
    }

    public static void pro(){
        getFailIndex();
        int cnt = kmp();
        System.out.println(cnt);
    }

    public static int kmp() {
        
        for(int i=0, j=0; i<n; i++) {

            while(j>0 && str.charAt(i)!=keyWord.charAt(j)) {
                j = fail[j-1];
            }

            if(str.charAt(i)== keyWord.charAt(j)) {
                if(j==m-1) return 1;
                else j++;
            }
        }
        return 0;
    }

    public static void getFailIndex() {
        fail = new int[m];

        for(int i=1, j=0; i<m; i++) {
            while(j>0 && keyWord.charAt(i)!= keyWord.charAt(j)) {
                j = fail[j-1];
            }

            if(keyWord.charAt(i)== keyWord.charAt(j)) {
                fail[i] = ++j;
            }
        }

    }
}