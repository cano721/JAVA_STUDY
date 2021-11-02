import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ2529 {
    static int k;
    static ArrayList<String> results = new ArrayList<>();
    static boolean[] check = new boolean[10]; // 0~9까지 값 check하기
    static char[] input = new char[10]; // 입력받은 부등호를 넣을 공간

    // 답이 될 수 있는 숫자 문자열을 만듭니다. 재귀로 진행하므로 종단조건은 필수적
    static void makeNum(int startNum, String ans){
        if(startNum == k+1){
            results.add(ans);
            return;
        }
        for(int i = 0; i<=9;i++){
            if(check[i]) // 이미 사용한 숫자라면 다음으로 넘어가기
                continue;

            if(startNum == 0 || checkAns(ans.charAt(startNum-1),(char)(i+'0'),input[startNum-1])){
                check[i] = true;
                makeNum(startNum+1, ans + Integer.toString(i));
                check[i] = false;

            }
        }
    }

    static boolean checkAns(char num1, char num2, char comp){
        if(comp == '<'){
            if(num1>num2)
                return false;
        }
        else if(comp == '>'){
            if(num1 < num2){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i<k;i++){
            input[i] = st.nextToken().charAt(0);
        }
        // 답을 찾을때는 0부터시작하고 첫 문자열은 빈문자열로 시작하자.
        makeNum(0,"");

        Collections.sort(results);
        System.out.println(results.get(results.size()-1));
        System.out.println(results.get(0));
    }
}
