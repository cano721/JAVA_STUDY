import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ3085 {

    public static int check(char [][] candy){
        int ans = 1;
        int length = candy.length;

        int temp;

        for(int i = 0; i<length;i++){
            temp = 1;
            for(int j = 1; j<length; j++){
                if(candy[i][j] == candy[i][j-1]){
                    temp++;
                }else{
                    //System.out.println(i+" "+j+" => "+temp+"개로 추정중");
                    temp = 1;
                }
                if(ans<temp){
                    ans = temp;
                }
            }

            temp = 1;
            for(int j = 1; j<length; j++){
                if(candy[j][i] == candy[j-1][i]){
                    temp++;
                }else{
                    //System.out.println(i+" "+j+" => "+temp+"개로 추정중");
                    temp = 1;
                }
                if(ans<temp){
                    ans = temp;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char [][] candy = new char[N][N];
        for(int i = 0;i<N;i++){
            String str = br.readLine();
            for(int j = 0;j<N;j++){
                candy[i][j] = str.charAt(j);
            }
        }

        int ans = 0;
        char temp;
        for(int i = 0;i<N;i++){
            for(int j = 0; j<N-1; j++){
                //좌우로 바꾸면서 확인
                temp = candy[i][j];
                candy[i][j] = candy[i][j+1];
                candy[i][j+1] = temp;
                //System.out.println("한번 돌때마다"+i+" "+j+" => "+check(candy));
                ans = Math.max(ans,check(candy));

                temp = candy[i][j];
                candy[i][j] = candy[i][j+1];
                candy[i][j+1] = temp;
            }
        }
        //System.out.println("세로세로시작");
        for(int i = 0;i<N-1;i++){
            for(int j = 0; j<N; j++){
                //좌우로 바꾸면서 확인
                temp = candy[i][j];
                candy[i][j] = candy[i+1][j];
                candy[i+1][j] = temp;
                //System.out.println("한번 돌때마다"+i+" "+j+" => "+check(candy));
                ans = Math.max(ans,check(candy));

                temp = candy[i][j];
                candy[i][j] = candy[i+1][j];
                candy[i+1][j] = temp;
            }
        }

        System.out.println(ans);
    }
}
