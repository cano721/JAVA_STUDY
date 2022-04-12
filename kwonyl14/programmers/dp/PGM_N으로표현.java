import java.util.*;

class Solution {
    public int solution(int N, int number) {
        HashSet<Integer> dp[] = new HashSet[8];
        for (int i = 0; i < 8; i++) {
            dp[i] = new HashSet<>();
        }
        dp[0].add(N);
        
        if (N == number) return 1;
        
        for (int k = 1; k < 8; k++) { 
        
            // DP[k]에 NNN...(k+1만큼 반복)과 같은 형태 삽입
            dp[k].add(get_Ns(N, k));

            // DP[k]에 사칙 연산의 결과또한 삽입
            for (int i = 0; i < k; i++) { 
                for (int j = 0; j < k; j++) { 
                    if (i + j + 1 != k) continue; 
                    // i+j+1 == k 일때
                    for (int a : dp[i]) { 
                        for (int b : dp[j]) { 
                            dp[k].add(a + b); 
                            // 검사가 필요한 연산들

                            // (1) 음수 존재하면 안됨
                            if (a - b > 0) 
                                dp[k].add(a - b); 

                            dp[k].add(a * b);

                            // (2) 0 존재하면 안됨
                            if (a / b > 0) dp[k].add(a / b); 
                        } 
                    } 
                } 
            }

            //DP set에 number에 해당하는 값이 있으면 k+1을 반환
            Iterator iter = dp[k].iterator();
            while (iter.hasNext()) {
                int num = (int) iter.next();
                if (num == number) {
                    return k+1;
                }
            }
        } 
        
        return -1;
    }
    
    int get_Ns(int N, int idx) { 
        // NN(idx==1), NNN(idx==2), NNNN(idx==3)...과 같은 형태만드는 함수
        int result = N; 
        for (int i = 1; i <= idx; i++) { 
            result = result * 10 + N; 
        } 
        
        return result; 
    } 
}