class Solution {
    public int solution(String dartResult) {
        int answer = 0;

        int []arr = new int[3];
        int num=0, check=0;

        for(int i=0; i<dartResult.length(); i++) {
            char ch = dartResult.charAt(i);
            if(Character.isDigit(ch)) {
                if(ch == '1' && dartResult.charAt(i+1)-'0' == 0) {
                    num = 10;
                    i++;
                }
                else num = ch-'0';
            }
            else {
                if(ch == 'S') {
                    arr[check++] += num;
                }
                else if(ch == 'D') {
                    arr[check++] += Math.pow(num,2);
                }   
                else if(ch == 'T') {
                    arr[check++] += Math.pow(num,3);
                }
                else if(ch == '*') {
                    if(check-2 >= 0) arr[check-2] *= 2;
                    arr[check - 1] *= 2;

                }
                else if(ch == '#') {
                    arr[check-1] *= -1;
                }
            }
        }

        for(int result : arr) {
            answer += result;
        }
        return answer;
    }
}