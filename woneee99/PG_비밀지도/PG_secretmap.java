
public class PG_secretmap {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];


        for(int i=0; i<n; i++) {
            StringBuilder re = new StringBuilder();

            String one = cal(arr1[i], n);
            String two = cal(arr2[i], n);

            for(int j=0; j<n; j++) {
                if(one.charAt(j)=='1' || two.charAt(j)=='1') {
                    re.append("#");
                }
                else {
                    re.append(" ");
                }
            }
            answer[i] = re.toString();
        }
        return answer;
    }
    static String cal(int m, int n) {
        StringBuilder sb = new StringBuilder();
        int x = m;
        while(x >= 1) {
            m = x % 2;
            x = x / 2;
            sb.append(m);
        }

        while(n-sb.length()>0) {
            sb.append(0);
        }

        return sb.reverse().toString();
    }

}
