package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
브루트포스  
그룹 단어란 단어에 존재하는 모든 문자에 대해서, 각 문자가 연속해서 나타나는 경우만을 말한다. 예를 들면, ccazzzzbb는 c, a, z, b가 모두 연속해서 나타나고, 
kin도 k, i, n이 연속해서 나타나기 때문에 그룹 단어이지만, aabbbccb는 b가 떨어져서 나타나기 때문에 그룹 단어가 아니다.
단어 N개를 입력으로 받아 그룹 단어의 개수를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 단어의 개수 N이 들어온다. N은 100보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에 단어가 들어온다. 단어는 알파벳 소문자로만 되어있고 중복되지 않으며, 길이는 최대 100이다.

출력
첫째 줄에 그룹 단어의 개수를 출력한다.
*/

/*
3
happy
new
year

3

4
aba
abab
abcabc
a

1

5
ab
aa
aca
ba
bb

4

*/

public class Boj1316_그룹단어체커 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine()); // 단어의 개수 n
        int count = 0; // 그룹단어가 아닌 단어의 수

        for (int i = 0; i < n; i++) { // 
            String str = br.readLine(); // 한줄 단어 받음 
            int len = str.length(); // 위에서 받은 단어의 길이 

            loopOut:for (int j = 0; j < len-1; j++) {
                char ch = str.charAt(j);
                if (ch != str.charAt(j + 1)) { // 연달은 문자가 다를 때 
                    for (int k = j + 2; k < len; k++) { // k 는 다른 첫문자 다음 다음 부터 비교하므로 j+2 부터 시작 
                       if (ch == str.charAt(k)) { // 첫 문자와 일치하는 문자가 있어 그룹단어가 아님. count를 증가시킴
                           count++;
                           break loopOut;
                       } 
                    }
                }
            }
        }
        bw.write((n-count) + "\n"); // 전체에서 그룹단어가 아닌 count를 빼주면 그룹단어의 수가 나옴
        bw.flush();
        bw.close();
    }

}