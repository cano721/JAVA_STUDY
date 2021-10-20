import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2846 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] input = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i<input.length;i++){
            input[i] = Integer.parseInt(st.nextToken());
        }
        int ans = 0;
        int lowIndex = 0;
        int highIndex = 0;
        for(int j = 0; j< input.length-1; j++){
            if(input[j] < input[j+1]){
                highIndex ++;
            }else{
                ans = Math.max(ans, input[highIndex]-input[lowIndex]);
                lowIndex = j+1;
                highIndex = j+1;
            }
        }
        ans = Math.max(ans, input[highIndex]-input[lowIndex]);
        System.out.println(ans);
    }
}
