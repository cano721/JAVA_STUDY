/**
 * 특정 구간의 합을 구할거고, 원본 데이터가 계속 변경됨
 * 
 * 세그먼트 트리 or 펜윅 트리 중 세그먼트 트리 이용하여 푼 코드
 * 
 * 입력으로 주어지는 값은 long이라는 점에서 주의
 * 
 */

import java.io.*;
import java.util.StringTokenizer;

public class BJ2042_구간합구하기_세그먼트트리 {

    public static long[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new long[n+1];

        for(int i = 1; i <= n; i ++){
            arr[i] = Long.parseLong(br.readLine());
        }

        for(int i = 0; i < m+k; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1){

            }else{

            }
        }
    }

    public static class Segment{
        long tree[];
        int treeSize;
        // 세그먼트 생성자
        public Segment(int arrSize){

            int h = (int)Math.ceil(Math.log(arrSize) / Math.log(2));
            this.treeSize = (int) Math.pow(2,h+1);
            tree = new long[treeSize];
        }

        public long init(long[] arr, int node, int start, int end){

            // 리프노드면 원소 담기
            if(start == end){
                return tree[node] = arr[start];
            }

            //아니면
            return tree[node] =
            init(arr,node*2,start,(start+end)/2)
            + init(arr,node*2+1,(start+end)/2 +1, end);
        }
    }
}
