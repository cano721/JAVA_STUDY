/**
 * 특정 구간의 곱을 구할거고, 원본 데이터가 계속 변경됨
 * 
 * 세그먼트 트리 or 펜윅 트리 중 펜윅 트리 이용하여 푼 코드
 * 
 * 입력으로 주어지는 값은 long이라는 점에서 주의
 * 
 */

import java.io.*;
import java.util.StringTokenizer;

public class BJ11505_구간곱구하기_세그먼트트리 {

    public static int mod = 1_000_000_007;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] arr = new long[n+1];

        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Segment stree =  new Segment(n);
        stree.init(arr, 1, 1, n);

        for(int i = 0; i < m+k; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            if(a == 1){
                stree.update(1, b, 1, n, c);
                arr[b] = c;
            }else{
                bw.write(stree.multi(1, 1, n, b, c) + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    public static class Segment{
        long[] tree;
        int treeSize;

        public Segment(int arrSize){

            int h = (int) Math.ceil(Math.log(arrSize)/Math.log(2));
            treeSize = (int) Math.pow(2,h+1);

            tree = new long[treeSize];
        }

        public long init(long[] arr, int node, int start, int end){
            if(start == end){
                return tree[node] = arr[start];
            }

            return tree[node] =
                    (init(arr, node*2, start, (start+end)/2)%mod
                    * init(arr, node*2+1, (start+end)/2+1, end)% mod)%mod;
        }

        public long update(int node, int idx, int start, int end,long change){
            // 현재 구간이 변경된것과 관련 없으면
            if(idx < start || idx > end){
                return tree[node];
            }

            // 리프노드면 변경된 값으로 변경
            if(start == end){
                return tree[node] = change;
            }

            // 아니면 자식의 곱을 리턴(재귀형태로 내려가기)
            return tree[node] = (update(node*2,idx,start,(start+end)/2,change)%mod
                        * update(node*2 +1,idx,(start+end)/2+1,end,change)%mod)%mod; 

        
        }

        public long multi(int node, int start, int end, int left, int right){
            if(end < left || start > right){
                return 1;
            }

            if(start >= left && end <= right){
                return tree[node];
            }

            return (multi(node*2, start, (start+end)/2, left, right) % mod
                    * multi(node*2+1,(start+end)/2+1,end,left,right) %mod)%mod;
        }
    }
}
