import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] DP = new int[N+2];
        int max =0;
        for(int i= 1; i<N+1; i++){
            String[] info = br.readLine().split(" ");
            int T  =  Integer.parseInt(info[0]);
            int P =  Integer.parseInt(info[1]);
            max = Integer.max(max,DP[i]);
            if(i+T>N+1) continue;
            DP[i+T] = Integer.max(max+P,DP[i+T]); //그날까지 번 최대값 대입할것
        }
        System.out.println(Integer.max(max,DP[N+1]));
    }
}