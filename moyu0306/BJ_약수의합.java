package com.company;

    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.util.Arrays;

public class Main {
        static int N;
        static long[] f;
        static long[] g;
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N= Integer.parseInt(br.readLine());
            f= new long[1000001];
            g= new long[1000001];
            g[1] = 1;
            Arrays.fill(f,1);
            for(int i= 2; i< 1000001; i++){
                for(int j = 1; j*i < 1000001; j++){
                    f[i*j] += i;
                }
            }
            for(int i = 2; i<1000001;i++){
                g[i] = g[i-1]+f[i];
            }
            StringBuilder sb = new StringBuilder();
            while(N-- >0) {
                sb.append(g[Integer.parseInt(br.readLine())]+"\n");
            }
            System.out.println(sb.toString());
        }
    }