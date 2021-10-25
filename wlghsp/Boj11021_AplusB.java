package baekjoon.bronzeⅢ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
5
1 1
2 3
3 4
9 8
5 2

Case #1: 2
Case #2: 5
Case #3: 7
Case #4: 17
Case #5: 7

*/


public class Boj11021_AplusB {
    
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      int N = Integer.parseInt(br.readLine());

      StringTokenizer st;
      for (int i = 0; i < N; i++) {
          st = new StringTokenizer(br.readLine(), " ");
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());

          bw.write("Case #"+ (i+1) + ": "+(a+b) + "\n");

      }
      bw.flush();
      bw.close();

    }
}
