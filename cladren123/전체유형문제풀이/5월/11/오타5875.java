package studyGroup.may.may11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
첫번째 방법
for문을 이용해 한 번씩 모든 괄호를 바꾼다.
오타가 있는지 확인하는 check 함수에 넣어서 판별한다.
-> 메모리 초과

https://velog.io/@tlsalswls123/%EB%B0%B1%EC%A4%80-5875-%ED%8C%8C%EC%9D%B4%EC%8D%AC
 */

public class 오타5875 {

    static String sent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sent = br.readLine();
        int n = sent.length();

        int left = 0;
        int right = 0;
        int total = 0;
        int result = 0;

        for(int i = 0; i < n; i++)
        {
            if(sent.charAt(i) == '(')
            {
                left += 1;
                total += 1;
            }
            else
            {
                right += 1;
                total -= 1;
            }

            if(total == 1)
            {
                left = 0;
            }

            if(total == -1)
            {
                result = right;
                break;
            }
        }

        if(total == 2)
            result = left;



        System.out.println(result);


    }




}
