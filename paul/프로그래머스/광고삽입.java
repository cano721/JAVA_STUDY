/**
 *  테스트 케이스 7, 8, 11, 12, 18, 24 실패..
 */
import java.util.*;
class Solution {
    static long[] playTime;
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int play = hmmToSeconds(play_time);
        int adv = hmmToSeconds(adv_time);
        //System.out.println(play + " , " + adv);
        playTime = new long[play+2];
        
        for(int i =0; i< logs.length; i++){
            String[] token = logs[i].split("-");
            int startTime = hmmToSeconds(token[0]);
            int endTime = hmmToSeconds(token[1]);
            
            //show(startTime, endTime);
            
            playTime[startTime]++;
            playTime[endTime+1]--;
        }
        
        int left = 0, right = 0;
        long sum = 0, now = 0, max = 0, st =0;
        while(right <= play + 1){
            while(right - left != adv + 1){
                now += playTime[right++];
                sum += now;
            }
            
            if(max < sum){
                max = sum;
                st = left;
            }
            
            now -= playTime[left++];
            now += playTime[right++];
            sum += now;
        }
        
        answer = secondsTohmm(st);
        
        return answer;
    }
    
    static int hmmToSeconds(String time){
        String[] hmm = time.split(":");
        
        int hour = Integer.parseInt(hmm[0]) * 3600;
        int minute = Integer.parseInt(hmm[1]) *60;
        int seconds = Integer.parseInt(hmm[2]);
        return hour + minute + seconds;
    }
    
    static String secondsTohmm(long ttime){
        int time = (int)ttime;
        int hour = time/3600;
        int minute = (time%3600)/60;
        int seconds = (time%3600)%60;
        return String.format("%02d", hour) + ":" 
            + String.format("%02d", minute) + ":"
            + String.format("%02d", seconds);
    }
    
    static void show(int t1, int t2){
        System.out.println(t1 + " , " + t2);
    }
}