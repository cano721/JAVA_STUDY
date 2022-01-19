import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S, S2, K;
        int[] pi;

        S = br.readLine();
        K = br.readLine();

        S2 = S.replaceAll("[0-9]", "");

        pi = new int[S2.length()];

        for (int i=1, j=0; i<S2.length(); i++) {

            while (j > 0 && S2.charAt(i) != S2.charAt(j)) {
                j = pi[j-1];
            }

            if (S2.charAt(i) == S2.charAt(j)) {
                pi[i] = ++j;
            }
        }

        for (int i=0, j=0; i<S2.length(); i++) {

            while (j > 0 && S2.charAt(i) != K.charAt(j)) {
                j = pi[j-1];
            }

            if (S2.charAt(i) == K.charAt(j)) {
                if (j == K.length() - 1) {
                    System.out.println(1);
                    return;
                }
                else {
                    j++;
                }
            }
        }

        System.out.println(0);
    }
}