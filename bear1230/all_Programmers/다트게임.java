class Solution {
    public int solution(String dartResult) {
       int answer = 0;
        int[] score = new int[3];
        int idx = 0;

        for (int i = 0; i < dartResult.length(); i++) {
            char ch = dartResult.charAt(i);

            if (ch == 'S') {
                continue;
            } else if (ch == 'D') {
                score[idx-1] = (int) Math.pow(score[idx-1], 2);
            } else if (ch == 'T') {
                score[idx-1] = (int) Math.pow(score[idx-1], 3);
            } else if (ch == '*') {
                score[idx-1] *= 2;

                if (idx != 1) 
                    score[idx-2] *= 2;
            } else if (ch == '#') {
                score[idx-1] *= -1;
            } else {
                if (dartResult.charAt(i+1) == '0') {
                    score[idx] = 10;
                    i++;
                } else {
                    score[idx] = ch - '0';
                }
                idx++;
            }
        }

        for (int i = 0; i < 3; i++) 
            answer += score[i];

        return answer;
    }
}
