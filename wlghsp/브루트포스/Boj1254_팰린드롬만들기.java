package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

브루트포스 

동호와 규완이는 212호에서 문자열에 대해 공부하고 있다. 규완이는 팰린드롬을 엄청나게 좋아한다. 팰린드롬이란 앞에서부터 읽으나 뒤에서부터 읽으나 같게 읽히는 문자열을 말한다.

동호는 규완이를 위한 깜짝 선물을 준비했다. 동호는 규완이가 적어놓고 간 문자열 S에 0개 이상의 문자를 문자열 뒤에 추가해서 팰린드롬을 만들려고 한다. 동호는 가능하면 가장 짧은 문자열을 만들려고 한다.

동호가 만들 수 있는 가장 짧은 팰린드롬의 길이를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 문자열 S가 주어진다. S는 알파벳 소문자로만 이루어져 있고, 길이는 최대 50이다.

출력
첫째 줄에 동호가 만들 수 있는 가장 짧은 팰린드롬의 길이를 출력한다.

*/

/*
abab

5

abacaba

7

qwerty

11

*/

public class Boj1254_팰린드롬만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        int shortLen = solve(word);
        
        System.out.println(shortLen);
    }

    public static int solve(String word) {
        // 입력 받은 문자열이 이미 팰린드롬일 때 
        if (word.equals(new StringBuilder(word).reverse().toString())) {
            return word.length();
        }

        for (int i = 1; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder(word);

            // 앞에서부터 길이 1씩 늘려가면서 뒤에 붙인다.
            sb.append(new StringBuilder(word.substring(0, i)).reverse());

            // 팰린드롬 체크
            if(sb.toString().equals(sb.reverse().toString())) {
                return sb.length();
            }
        }
        return -1;
    }


    
}
