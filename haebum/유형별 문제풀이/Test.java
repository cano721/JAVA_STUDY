public class Test {
    public static void main(String[] args) {
        String play_time = "00:01:00";
        String adv_time = "00:00:30";
        String[] logs = {"00:00:00-00:00:15","00:00:24-00:00:30", "00:00:30-00:00:59", "00:00:31-00:01:00"};
        solution(play_time, adv_time, logs);
    }

    public static String solution(String play_time, String adv_time, String[] logs) {
        
        int len = timeParsing(play_time);
        
        int[] sArr = new int[len+2];
        
        for(String log : logs){
            String[] strs = log.split("-");
            
            int start = timeParsing(strs[0]);
            int end = timeParsing(strs[1]);
            
            sArr[start] += 1;
            sArr[end+1] -= 1;
        }
        
        for(int i = 1; i < len+1; i++){
            sArr[i] += sArr[i-1];
        }
        
        // 0은 별도 처리 
        int[] sumsArr = new int[len+1];
        

        for(int i = 0; i < len+1; i++){
            if(i == 0){
                sumsArr[i] = sArr[i];
            }else{
                sumsArr[i] += sumsArr[i-1] + sArr[i];
            }
        }
        
        int adv = timeParsing(adv_time);
        
        int max = 0;
        int cur = 0;
        // 1이 0초를 뜻
        for(int i = 0; i <= len-adv; i++){
            if(i == 0){
                max = sumsArr[adv];
            }else{
                if(max < sumsArr[i+adv] -sumsArr[i-1]){
                    max = sumsArr[i+adv] - sumsArr[i-1];
                    cur = i;
                }
            }
        }
        String answer = stringParsing(cur);
        System.out.println(cur);
        System.out.println(answer);
        return answer;
    }
    
    public static String stringParsing(int max){
        
        String result = "";
        
        int h = max/3600;
        result += String.format("%02d:",h);
        
        max%= 3600;
        int m = max/60;
        result += String.format("%02d:",m);
        
        max %= 60;
        int s = max;
        result += String.format("%02d",s);
        
        return result;
    }
    
    public static int timeParsing(String time){
        int result = 0;
        String[] times = time.split(":");
        
        result += Integer.parseInt(times[0])*3600;
        result += Integer.parseInt(times[1])*60;
        result += Integer.parseInt(times[2]);
        
        return result;
    }
}
