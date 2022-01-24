/**
 * n개의 정수로 이루어진 배열이 주어지고,
 * 
 * 정수의 값이 변경되거나 구간의 곱이 양수,음수,0인지 출력
 * 
 * 세그먼트 트리 사용
 * 
 * 주의사항
 * 1.양수면 1, 음수면 -1로 저장해서 사용해야함
 * (곱하는 도중 범위 바깥으로 벗어나서 음수변경 가능성 있음)
 * 10^2^(10^5)
 * 
 * 2. end of file 처리
 * br.readLine() null로 들어옴
 * 비교문 == null로 break 처리
 */

import java.util.*;
import java.io.*;

public class BJ5676_음주코딩 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        while(true){
            String str = br.readLine();
            // 더이상 받을게 없다면
            if(str == null){
                break;
            }
            st = new StringTokenizer(str);
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            
            // 배열생성
            int[] arr = new int[n+1];
            for(int i = 1; i <= n; i++){
                int num = Integer.parseInt(st.nextToken());
                if(num > 0){
                    arr[i] = 1;
                }else if(num < 0){
                    arr[i] = -1;
                }
            }

            // 세그먼트 트리 초기화
            Segment stree = new Segment(n);
            stree.init(arr, 1, 1, n);
            
            // 명령어 실행
            for(int i = 0; i < k; i++){
                st = new StringTokenizer(br.readLine());

                String command = st.nextToken();

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 데이터 변경
                if(command.equals("C")){
                    if(b > 0){
                        arr[a] = 1;
                        stree.update(1, a, 1, 1, n);
                    }else if(b < 0){
                        arr[a] = -1;
                        stree.update(1, a, -1, 1, n);
                    }else{
                        arr[a] = 0;
                        stree.update(1, a, 0, 1, n);
                    }
                // 구간 곱셈
                }else{
                    long num = stree.multi(1, 1, n, a, b);
                    if(num > 0){
                        bw.write("+");
                    }else if(num < 0){
                        bw.write("-");
                    }else{
                        bw.write("0");
                    }
                }
            }
            bw.write("\n");
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

        public long init(int[] arr, int node, int start, int end){
            if(start == end){
                return tree[node] = arr[start];
            }

            return tree[node] = init(arr, node*2, start, (start+end)/2)
                                *init(arr, node*2+1, (start+end)/2+1, end); 
        }

        public long update(int node, int idx,int diff, int start, int end){
            if(idx > end || idx < start){
                return tree[node];
            }

            if(start == end){
                return tree[node] = diff;
            }

            return tree[node] = update(node*2, idx,diff, start, (start+end)/2)
                                *update(node*2+1, idx,diff, (start+end)/2+1, end);
        }

        public long multi(int node, int start, int end, int left, int right){
            if(start > right || end < left){
                return 1l;
            }

            if(start >= left && end <= right){
                return tree[node];
            }

            return multi(node*2, start, (start+end)/2, left, right)
                    *multi(node*2+1, (start+end)/2+1, end, left, right);
        }
    }
}
