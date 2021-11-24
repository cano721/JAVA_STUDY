package cindya.bj2011_암호코드;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<String, Integer> memory = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String code = scanner.next();
        int res;
        if(!code.matches("^[1-9][0-9]*$")) res = 0; // 부적절한 입력일 경우 0 출력
        else res = getNumberOfCases(code);
        System.out.println(res);
        scanner.close();
    }

    private static int getNumberOfCases(String code){
        // code의 길이가 2보다 짧을 때
        if(code.length() <= 2) {
            if(code.length() == 1 && !code.equals("0")) return 1; // 길이가 1이고 0이 아니면 한가지 경우
            switch (code.charAt(0)){ // 첫번째 글자가
                case '0': return 0; // 0이면 불가능한 경우
                case '2': // 2일 때
                    if(code.charAt(1) > '6') return 1; // 두번째 글자가 6보다 크면 한가지 경우
                case '1': // 1이거나 2이면서 2번째 글자가 6과 같거나 작을 때
                    if(code.charAt(1) == '0') return 1; // 두번째 글자가 0이면 한가지 경우
                    return 2; // 0이 아니면 2가지 경우
                default: // 3 이상일 때
                    if(code.charAt(1) == '0') return 0; // 두번째 글자가 0이며 불가능한 경우
                    return 1; // 0이 아니면 한가지 경우
            }
        }
        // code의 길이가 2보다 길 때
        if(memory.containsKey(code)) return memory.get(code); // code의 결과가 저장되어 있으면 그 값을 반환
        int res = 0;
        if(code.substring(0, 1).compareTo("0") > 0) { // 첫번째 글자가 0보다 크면
            res += getNumberOfCases(code.substring(1)); // 첫번째를 뺀 문자열의 결과를 더함
            if (Integer.parseInt(code.substring(0, 2)) <= 26) // 두번째 글자까지 잘랐을 때 26과 같거나 작으면
                res += getNumberOfCases(code.substring(2)); // 두번째 글자까지 뺀 문자열의 결과를 더함
        }
        res %= 1000000; // 결과를 백만으로 나눈 나머지를 취함
        memory.put(code, res); // 결과를 저장
        return res;
    }
}
