package day2205.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5875_오타 {
    /*
    (()))())()()
  t 1210-1
  l 01
  r 00123
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = br.readLine().toCharArray();

        int l = 0, r = 0, t = 0;
        int result = 0;
        for (int i = 0, length = chars.length; i < length; i++) {
            if (chars[i] == '(') {
                l++;
                t++;
            } else {
                r++;
                t--;
            }

            if (t <= 1) l = 0;
            if (t == -1) {
                result = r;
                break;
            }
        }

        if (t > 0) result = l;

        System.out.println(result);
    }
}
