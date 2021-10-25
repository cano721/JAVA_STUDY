import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //e s m
        int E=1, S=1, M=1;
        int e = sc.nextInt();
        int s = sc.nextInt();
        int m = sc.nextInt();
        
        for(int i=1;; i++){
            if(E ==e && S==s && M==m){
                System.out.println(i);
                break;
            }
            E++;
            S++;
            M++;
            if(E == 16)
                E=1;
            if(S ==29)
                S=1;
            if(M==20)
                M=1;      
        }
        
    }
}