class Solution {
    public String solution(int n) {
    String answer = "";
    int share = n;
    int remainder = -1;

    while (share!=0) {
        remainder = share % 3;
        share = share / 3;

        if (remainder == 0) { //나누어 떨어질 경우 몫을 1빼준다.
            answer = "4" + answer;
            share--;
        }
        else if (remainder == 1) {
            answer = "1" + answer;
        }
        else if (remainder == 2) {
            answer = "2" + answer;
        }
    }

    return answer;
    }
}