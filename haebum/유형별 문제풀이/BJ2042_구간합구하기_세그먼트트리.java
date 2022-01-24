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

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] arr = new long[n+1];

        for(int i = 1; i <= n; i ++){
            arr[i] = Long.parseLong(br.readLine());
        }
        Segment stree = new Segment(n);
        stree.init(arr, 1, 1, n);

        for(int i = 0; i < m+k; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1){
                arr[b] = c;
                stree.update(1,b,1,n,c -arr[b]);
            }else{
                bw.write(stree.sum(1, 1, n, b, (int)c) +"\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static class Segment{
        long tree[];
        int treeSize;
        // 세그먼트 생성자
        public Segment(int arrSize){

            int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
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

        public void update(int node, int idx, int start, int end, long diff){
            // 현재 배열 범위에 바뀐 노드가 해당하지 않으면 종료
            if(idx < start || end < idx){
                return;
            }
            // 현재노드 바뀐만큼 변경
            tree[node] += diff;

            // 리프노드가 아니면 자식도 변경
            if(start != end){
                update(node*2,idx,start,(start+end)/2,diff);
                update(node*2+1,idx,(start+end)/2+1,end,diff);
            }
        }

        public long sum(int node, int start, int end, int left, int right){
            // 현재 구간이 구하고자 하는 특정 구간이 아니면 0 반환
            if(start > right || left > end){
                return 0;
            }

            // 현재 구간이 특정 구간에 완전 포함되면 그대로 반환
            if(start >= left && end <= right){
                return tree[node];
            }

            // 그외에 포함하지만 완전 포함되지 않는 경우면 자식까지 내려가서 확인
            return sum(node*2,start, (start+end)/2, left, right)
                    + sum(node*2+1,(start+end)/2+1, end, left, right);
        }
    }
}
