class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {

        int[] answer = new int[queries.length];

        int[][] map = new int[rows][columns];
        int cnt =0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cnt++;
                map[i][j] = cnt;

            }
        }


        for (int i = 0; i < queries.length; i++) {
            int min = Integer.MAX_VALUE;
            int x1 = queries[i][0]-1;
            int y1 = queries[i][1]-1;
            int x2 = queries[i][2]-1;
            int y2 = queries[i][3]-1;
            System.out.println(x1+" "+y1+" "+x2+" "+y2);

            //위 가로
            int temp = map[x1][y1];
            for (int j = y1+1; j <= y2; j++) {
                int temp1 = map[x1][j];
                map[x1][j] = temp;
                temp = temp1;
                min = Math.min(min, map[x1][j]);
            }
            //오른쪽 세로
            for (int j = x1+1; j <=x2 ; j++) {
                int temp1 = map[j][y2];
                map[j][y2] = temp;
                temp = temp1;
                min = Math.min(min, map[j][y2]);
            }
            //아래 가로
            for (int j = y2-1; j >=y1 ; j--) {
                int temp1 = map[x2][j];
                map[x2][j] = temp;
                temp = temp1;
                min = Math.min(min, map[x2][j]);
            }
            //왼쪽 세로
            for (int j = x2-1; j >=x1 ; j--) {
                int temp1 = map[j][y1];
                map[j][y1] = temp;
                temp = temp1;
                min = Math.min(min, map[j][y1]);
            }

//            for (int j = 0; j < rows; j++) {
//                for (int k = 0; k < columns; k++) {
//                    System.out.printf("%4d",map[j][k]);
//                }
//                System.out.println();
//            }

            //System.out.println(min);
            answer[i] = min;
        }
        return answer;
    }
}