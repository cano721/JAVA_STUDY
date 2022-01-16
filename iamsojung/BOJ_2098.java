import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static final int maxi = 987654321;
    static int check[][],arr[][];
    static int num,result;

    public static void main (String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        num = Integer.parseInt(st.nextToken());

        //초기화
        result=maxi;
        arr = new int[num][num];
        check = new int[num][(1<<num)];
        for(int i=0;i<num;i++)
            for(int k=0;k<(1<<num);k++)
                check[i][k]=maxi;

        for(int i=0;i<num;i++){
            str = br.readLine();
            st = new StringTokenizer(str);
            for(int j=0;j<num;j++){
                int val = Integer.parseInt(st.nextToken());
                arr[i][j]=val;
            }
        }
        result = dfs(0,0);

        System.out.print(result);
    }

    static int dfs(int cur, int knum){
        if(knum == ((1<<num)-1))
            return 0;
        int ret = check[cur][knum];
        if(ret!=maxi)
            return ret;
        for(int i=0;i<num;i++){
            int nv = arr[cur][i];
            if(nv==0 || cur==i)
                continue;
            int nk = knum|(1<<i);
            if(nk==knum || (i==0 && nk!=((1<<num)-1)))
                continue;
            int vv = nv+dfs(i,nk);
            ret = Math.min(ret,vv);
        }
        return check[cur][knum] = ret;
    }
}