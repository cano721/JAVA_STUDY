/**
 * 사탕 맛 : 1 ~ 1_000_000
 * 명령어 개수 : 1 ~ 100_000
 * 사탕의 개수 : 0 ~ 2_000_000_000
 * 
 * 해당순위의 사탕을 꺼내는건,
 * 앞 순서의 맛의 사탕의 개수를 넘어야함.
 * ex) 2순위사탕 꺼낼때, 1번맛 2개라면 1번맛 사탕 꺼내짐.
 * 3순위사탕 꺼낼때, 1번맛 사탕이 2개면 그다음번맛 사탕 꺼내짐.
 * 
 * 세그먼트 트리로 각 사탕의 개수합을 저장해두기
 * 
 * 세그먼트 트리로 구성(구간합)
 * 
 * 리프 노드는 각각 맛의 사탕의 개수 저장
 * 
 * 부모 노드는 리프노드 사탕의 개수 합
 * 
 * 트리 배열은
 * int h = (int)Math.ceil(Math.log(10^6)/Math.log(2))
 * int treeSize = (int)Math.pow(2,h+1);
 * 
 * init은 필요 없음
 * 
 * 1. 명령어(순위에따른 사탕 꺼내기)
 * 
 * 순위에따른 사탕 꺼내기 명령어가 들어오면,
 * 1~100만번 맛의 사탕을 돌며,
 * 1번맛~n번맛까지의 사탕개수 파악 했을때, 현재개수가 순위보다 낮으면,
 * 1번맛 사탕 빼기(리프노드 개수 감소)
 * 
 * 포문 사용 시
 * 10만개의 명령어 10^6 * log(10^6)  = 10^5*10^6^6 시간초과로
 * 
 * 몇번째 사탕까지 먹어야할지를 이분탐색으로 계산
 * 10^5 *6*6 으로 시간내 계산완료!
 * 
 * 2. 명령어(사탕 채우거나 감소)
 * 사탕상자에 사탕 넣거나 빼기(개수와 맛)
 * 
 * 해당 리프노드의 idx도착하면 개수 변경
 * 
 */

import java.util.*;
import java.io.*;

public class BJ2243_사탕상자 {
    public static int arrSize = 1_000_000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Segment stree = new Segment(arrSize);

        for(int i = 0; i < n; i ++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());

            // 순위에 따른 사탕 빼기
            if(a == 1){
                // 순위
                int b = Integer.parseInt(st.nextToken());
                // 어느 사탕맛까지 개수를 더했을때 순위가 나오는지 확인
                int num = binary_search(b, stree);
                stree.update(1, num, -1, 1, arrSize);
                bw.write(num +"\n");
            // 사탕상자 정보 변경
            }else{
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                stree.update(1, b, c, 1, arrSize);
            }
        }

        bw.flush();
        bw.close();
    }

    //몇번째 사탕까지 먹었을때 순위가 있는지 확인
    public static int binary_search(int b,Segment stree){
        int start = 1;
        int end = 1_000_000;

        int result = 0;
        while(start <= end){
            int mid = (start+end)/2;

            int candyNum = stree.sum(1,1,arrSize, 1, mid);

            if(b <= candyNum){
                end = mid-1;
                result = mid;
            }else{
                start = mid+1;
            }
        }

        return result;
    }

    public static class Segment{
        int[] tree;
        int treeSize;

        public Segment(int arrSize){
            int h = (int) Math.ceil(Math.log(arrSize)/Math.log(2));
            treeSize = (int)Math.pow(2,h+1);
            tree = new int[treeSize];
        }

        public void update(int node, int idx, int diff, int start,int end){
            // 변경된 노드가 현재 범위 바깥
            if(idx > end || idx < start){
                return;
            }
            // 현재 범위가 상관있다면 차이만큼 더해주기
            tree[node] += diff;

            //리프노드 아니면 자식노드 가기
            if(start != end){
                update(node*2, idx, diff, start, (start+end)/2);
                update(node*2+1, idx, diff, (start+end)/2+1, end);
            }
        }

        //구간합 구하기
        public int sum(int node, int start, int end, int left, int right){
            // 범위 바깥은 0반환
            if(start > right || end < left){
                return 0;
            }

            // 범위 내 완전 포함이면 해당 노드값 반환
            if(start >= left && end <= right){
                return tree[node];
            }

            //아니면 자식노드 들어가서 두개의 합 반환
            return sum(node*2, start, (start+end)/2, left, right)
                    + sum(node*2+1, (start+end)/2+1, end, left, right);
        }
    }
}
