import java.util.Scanner;
 
public class Main {
	public int solution(){
		int answer=0;

		return answer;
	}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

		Main T = new Main();

        n = sc.nextInt();

        arr = new int[n][n];
        for(int i=0;i<n;i++) {
            String str = sc.next();
            for(int j=0;j<n;j++)
                arr[i][j] = str.charAt(j);
        }

        
        

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++)
                T.solution(i,j);
        }
    }
    

    

}