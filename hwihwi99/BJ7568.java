import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 처음에 키는 같은데 몸무게가 더 크면 덩치라고 생각해서 문제를 풀었는데
 * 코드도 복잡해지고 틀렸다는 메세지를 보았습니다.
 *
 * 그래서 다시 문제를 읽어보니
 *
 * x > p 그리고 y > q 이라면 우리는 A의 덩치가 B의 덩치보다 "더 크다"고 말한다.
 *
 * 라는 말이 있었고 아래와 같이 키와 몸무게가 모두 큰 사람만을 고려해서 문제를 풀었습니다.
 *
 * */
public class BJ7568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [][] data = new int [N][2];
        StringTokenizer st;
        for(int i = 0; i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }
        int person;
        int [] answer = new int[N];
        for(int i = 0; i<N;i++){
            person = 0;
            for(int j = 0; j<N;j++){

                if(i==j)
                    continue;

                if(data[i][0] <data[j][0] && data[i][1] < data[j][1]){
                    person++;
                }
            }
            answer[i] = person + 1;
        }

        for(int i = 0; i<answer.length;i++){
            System.out.print(answer[i]+" ");
        }
    }
}
