package cindya.bj11729_하노이탑이동순서;

import java.io.*;

public class Main {
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        br.close();

        bw.write((int)Math.pow(2, n) - 1 + "\n");
        hanoi(1, 3,2, n);

        bw.flush();
        bw.close();
    }

    private static void hanoi(int from, int to, int middle, int n) throws IOException{
        if(n > 0){
            hanoi(from, middle, to, n - 1);
            bw.write(from + " " + to + "\n");
            hanoi(middle, to, from, n - 1);
        }
    }
}
