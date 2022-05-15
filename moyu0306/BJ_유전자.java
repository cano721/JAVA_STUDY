import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    static char[] input;
    static int[][] DP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        int len = input.length;
        DP = new int[len+1][len+1];
        for(int i=0; i<len+1; i++) Arrays.fill(DP[i],-1);
        System.out.println(getKOILength(0,len-1));
    }

    public static int getKOILength(int start, int end){
        if(DP[start][end] != -1) return DP[start][end];
        if(start>=end) return 0;

        int max = Integer.max(getKOILength(start+1,end),getKOILength(start,end-1));
        if(input[start]=='a'&&input[end]=='t'||input[start]=='g'&&input[end]=='c'){
            max = getKOILength(start+1,end-1)+2;
        }
        for(int i=1; start+i+1<end; i++){
            max = Integer.max(max,getKOILength(start,start+i)+getKOILength(start+i+1,end));
        }

        return DP[start][end] = max;
    }

}