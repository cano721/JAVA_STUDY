import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        
        int result = 0; 
        int tmp = Integer.parseInt(s);
        int sum = 0; 
        int n = 0;
        
        for(int i = 1; i < tmp; i++){ 
            n = i;
            sum = 0;
            while(n > 10){
                sum += n % 10;
                n /= 10;
            }
            sum += n;

            if(sum + i == tmp){
                if(result != 0){
                    result = Math.min(result, i);
                }
                else{
                    result = i;
                }
            }
        }
        System.out.println(result);
    }
}

