/**
 * 여러개의 특정 구간 내 최솟값과 최댓값 구하기
 * 
 * 세그먼트 풀이
 */

import java.util.*;
import java.io.*;

public class BJ2357_최솟값과최댓값_펜윅 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[n+1];

        MinFenwick minftree = new MinFenwick(n);
        MaxFenwick maxftree = new MaxFenwick(n);

        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(br.readLine());
            minftree.update(i, arr[i]);
            maxftree.update(i, arr[i]);
        }


        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(minftree.min(arr,a, b) +" ");
            bw.write(maxftree.max(arr,a, b) +"\n");
        }

        bw.flush();
        bw.close();
    }

    public static class MinFenwick{
        int[] tree;
        int[] tree2;

        public MinFenwick(int arrSize){
            tree = new int[arrSize+1];
            tree2 = new int[arrSize+1];
            Arrays.fill(tree,Integer.MAX_VALUE);
            Arrays.fill(tree2,Integer.MAX_VALUE);
        }

        public void update(int idx,int diff){
            int idx2 = idx;

            while(idx < tree.length){

                tree[idx] = Math.min(tree[idx],diff);

                idx += idx & -idx;
            }

            while(idx2 > 0){
                tree2[idx2] = Math.min(tree2[idx2],diff);

                idx2 -= idx2 & -idx2;
            }
        }

        public int min(int[] arr,int a,int b){
            int temp = Integer.MAX_VALUE;

            int prev = a;
            int cur = prev + (prev & - prev);
            while(cur <= b){
                temp = Math.min(temp,tree2[prev]);
                prev = cur;
                cur = prev + (prev & -prev);
            }

            temp = Math.min(temp,arr[prev]);

            prev = b;
            cur = prev - (prev & -prev);
            while(cur >= a){
                temp = Math.min(temp, tree[prev]);
                prev = cur;
                cur = prev - (prev & -prev);
            }
            return temp;
        }
    }

    public static class MaxFenwick{
        int[] tree;
        int[] tree2;

        public MaxFenwick(int arrSize){
            tree = new int[arrSize+1];
            tree2 = new int[arrSize+1];
            Arrays.fill(tree,Integer.MIN_VALUE);
            Arrays.fill(tree2,Integer.MIN_VALUE);
        }

        public void update(int idx,int diff){
            int idx2 = idx;

            while(idx < tree.length){

                tree[idx] = Math.max(tree[idx],diff);

                idx += idx & -idx;
            }

            while(idx2 > 0){
                tree2[idx2] = Math.max(tree2[idx2],diff);

                idx2 -= idx2 & -idx2;
            }
        }

        public int max(int[] arr,int a,int b){
            int temp = Integer.MIN_VALUE;

            int prev = a;
            int cur = prev + (prev & - prev);
            while(cur <= b){
                temp = Math.max(temp,tree2[prev]);
                prev = cur;
                cur = prev + (prev & -prev);
            }

            temp = Math.max(temp,arr[prev]);

            prev = b;
            cur = prev - (prev & -prev);
            while(cur >= a){
                temp = Math.max(temp, tree[prev]);
                prev = cur;
                cur = prev - (prev & -prev);
            }
            return temp;
        }
    }
}
