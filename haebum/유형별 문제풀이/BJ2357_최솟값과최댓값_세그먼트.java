/**
 * 여러개의 특정 구간 내 최솟값과 최댓값 구하기
 * 
 * 세그먼트 풀이
 */

import java.util.*;
import java.io.*;

public class BJ2357_최솟값과최댓값_세그먼트 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[n+1];

        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        MinSegment minstree = new MinSegment(n);
        MaxSegment maxstree = new MaxSegment(n);

        minstree.init(arr, 1, 1, n);
        maxstree.init(arr, 1, 1, n);

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write(minstree.min(1, 1, n, a, b) +" ");
            bw.write(maxstree.max(1, 1, n, a, b) +"\n");
        }

        bw.flush();
        bw.close();
    }

    // 민값 찾는 세그먼트
    public static class MinSegment{
        int[] tree;
        int treeSize;
        public MinSegment(int arrSize){
            int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));

            treeSize = (int) Math.pow(2,h+1);
            tree = new int[treeSize];
        }

        public int init(int[] arr, int node, int start, int end){
            if(start == end){
                return tree[node] = arr[start];
            }

            return tree[node] =
                    Math.min(init(arr, node*2, start, (start+end)/2)
                    ,init(arr,node*2+1,(start+end)/2+1,end));
        }

        public int update(int node, int idx, int start, int end){
            if(end < idx || idx < start){
                return Integer.MAX_VALUE;
            }

            if(start != end){
                tree[node] = Math.min(update(node*2, idx, start, (start+end)/2),
                update(node*2 +1, idx, (start+end)/2 +1, end));
            }

            return tree[node];
        }

        public int min(int node, int start, int end, int left, int right){
            if(end < left || start > right){
                return Integer.MAX_VALUE;
            }

            if(start >= left && end <= right){
                return tree[node];
            }

            return Math.min(min(node*2,start,(start+end)/2,left,right),
                            min(node*2+1, (start+end)/2+1, end, left, right));
        }
    }

    //맥스값 찾는 세그먼트
    public static class MaxSegment{
        int[] tree;
        int treeSize;
        public MaxSegment(int arrSize){
            int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));

            treeSize = (int) Math.pow(2,h+1);
            tree = new int[treeSize];
        }

        public int init(int[] arr, int node, int start, int end){
            if(start == end){
                return tree[node] = arr[start];
            }

            return tree[node] =
                    Math.max(init(arr, node*2, start, (start+end)/2)
                    ,init(arr,node*2+1,(start+end)/2+1,end));
        }

        public int update(int node, int idx, int start, int end){
            if(end < idx || idx < start){
                return 0;
            }

            if(start != end){
                tree[node] = Math.max(update(node*2, idx, start, (start+end)/2),
                update(node*2 +1, idx, (start+end)/2 +1, end));
            }

            return tree[node];
        }

        public int max(int node, int start, int end, int left, int right){
            if(end < left || start > right){
                return 0;
            }

            if(start >= left && end <= right){
                return tree[node];
            }

            return Math.max(max(node*2,start,(start+end)/2,left,right),
                            max(node*2+1, (start+end)/2+1, end, left, right));
        }
    }
}
