class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long start = 0;
        long end = (long) Math.pow(10, 15);
        long ans = Long.MAX_VALUE;

        int len = s.length;
        while (start <= end) {
            long mid = (start + end) / 2;

            long gold = 0;
            long silver = 0;
            long add = 0;

            for (int i = 0; i < len; i++) {
                long nowG = g[i];
                long nowS = s[i];
                long nowW = w[i];
                long nowT = t[i];

                long cnt = mid / (nowT * 2);
                if (mid % (nowT * 2) >= nowT) {
                    cnt++;
                }

                gold += (nowG < cnt * nowW) ? nowG : cnt * nowW;
                silver += (nowS < cnt * nowW) ? nowS : cnt * nowW;
                add += (nowG + nowS < cnt * nowW) ? nowG + nowS : cnt * nowW;
                
            }

            if (gold >= a && silver >= b && add >= a + b) {
                end = mid - 1;
                ans = Math.min(ans, mid);
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }
}
