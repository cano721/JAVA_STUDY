class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        while (n>0) {
            int left = n % k;
            sb.append(left);
            n = n / k;
            if(n==0)
                break;
        }
        //       System.out.println(sb.reverse());
        String[] sosu = sb.reverse().toString().split("0");

        for (int i = 0; i < sosu.length; i++) {
            boolean flag = false;
            if (sosu[i].equals(""))
                continue;
            long num = Long.parseLong(sosu[i]);

            if (num == 1)
                continue;

            for (long j = 2; j <= Math.sqrt(num); j++) {
                if (num % j == 0) {
                    flag = true;
                    break;
                }
            }
            if(!flag){

                answer++;
            }
        }
        System.out.println(answer);
        return answer;
    }
}