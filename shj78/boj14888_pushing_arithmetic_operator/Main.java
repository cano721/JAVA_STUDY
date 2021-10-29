import java.util.Scanner;

public class Main {
    public static int MAX = Integer.MIN_VALUE;
    public static int MIN = Integer.MAX_VALUE;
    public static int[] operator = new int[4];
    public static int[] number;
    public static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        number = new int[n];

        for(int i = 0; i<n;i++){
            number[i] = sc.nextInt();
        }

        for(int i = 0; i<4;i++){
            operator[i] = sc.nextInt();
        }

        dfs(number[0],1);

        System.out.println(MAX);
        System.out.println(MIN);
    }

    public static void dfs(int num, int index){
        if(index == n){ //index의 값이 n과 같아지면 내가 원하는 계산이 끝남
            MAX = Math.max(MAX,num);
            MIN = Math.min(MIN,num);
            return;
        }

        for(int i = 0; i<4;i++){
            //연산자가 하나라도 있으면 if문 안으로 들어온다
            if(operator[i]>0){
                operator[i]--;

                switch (i){
                    case 0:dfs(num + number[index],index+1); break;
                    case 1:dfs(num - number[index],index+1); break;
                    case 2:dfs(num * number[index],index+1); break;
                    case 3:dfs(num / number[index],index+1); break;
                }
                //백트리킹이므로 연산자 개수를 다시 돌려놓아야 한다.
                operator[i]++;
            }
        }
    }
}