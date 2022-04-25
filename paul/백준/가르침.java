import java.io.*;
import java.util.*;

public class Main {

    static int max = 0, n, k, check = (1 << 'a' - 'a') | (1 << 'n' - 'a') | (1 << 't' - 'a') | (1 << 'i' - 'a') | (1 << 'c' - 'a');
    static String[] strs;

    static void DFS(int x, int depth){

        if(depth == k){
            int cnt = 0;
            
            for(String str:strs){
                char[] arr = str.toCharArray();
                boolean flag = true;

                for(char c:arr)
                    if((check & (1 << c-'a')) == 0){
                        flag = false;
                        break;
                    }

                if(flag) cnt++;
            }
            max = Math.max(max, cnt);
            return;
        }

        for(int i=x; i<26; i++)
            if((check & (1 << i)) == 0) {
                check |= (1 << i);
                DFS(i, depth + 1);
                check &= ~(1 << i);
            }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        strs = new String[n];
        for(int i=0; i<n; i++)
            strs[i] = br.readLine();

        if(k == 5)
            DFS(0, 5);

        else if(k > 5) {
            for (int i = 0; i < 26; i++)
                if ((check & (1 << i)) == 0) {
                    check |= (1 << i);
                    DFS(i, 6);
                    check &= ~(1 << i);
                }
        }

        System.out.println(max);
    }
}