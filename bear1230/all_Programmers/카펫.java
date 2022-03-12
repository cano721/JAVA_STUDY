class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown+yellow;
        for(int i =3; i< sum; i++){
            int col = sum /i;
            int row = sum / col;

            if((row-2)*(col-2) == yellow && row >= col){
                answer[0] = row;
                answer[1] = col;
            }
        }
        return answer;
    }
}
