import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
 
	public static void main(String[] args) throws IOException {
        int ans =0;
        int[] card;
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st=  new StringTokenizer(br.readLine());
        
        card = new int[n];
        
        for(int i =0; i<n; i++){
            card[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i =0; i< card.length; i++){
            for(int j =0; j< card.length; j++){
                for(int k=0; k< card.length; k++){
                    if(i != j && j != k && i!= k){
                        int sum = card[i]+card[j]+card[k];
                        if(sum<=m)
                            ans = Math.max(ans,sum);
                    }
                }
            }
        }
        System.out.println(ans);
        

    }
}