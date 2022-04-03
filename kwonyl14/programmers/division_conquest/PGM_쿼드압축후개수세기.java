class Solution {
    
    int[] answer = new int[2];;
    int[][] arr;
    public int[] solution(int[][] arr) {
        //압축 시도
        this.arr = arr;
        compress(0, 0, arr.length);
        return answer;
    }
    
    void compress(int r, int c, int range) {
        //현재 구역에 같은 수만 있는지 카운트
        int nowNum = arr[r][c];
        boolean flag = true;
        Loop:
        for (int i = r; i < r + range; i++) {
            for (int j = c; j < c + range; j++) {
                if (arr[i][j] != nowNum) {
                    flag = false;
                    break Loop;
                }
            }
        }
        
        //같은 구역이 아니라면 4개로 분할해서 다시 압축 시도
        if (flag == false && range > 1) {
            compress(r, c, range/2);
            compress(r, c+range/2, range/2);
            compress(r+range/2, c, range/2);
            compress(r+range/2, c+range/2, range/2);
        }
        //같은 구역이라면 리턴
        else {
            answer[nowNum]++;
            return;
        }
    }
}