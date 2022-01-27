// /**
//     조건
//     1. 패턴문자열 1439
//     2. 음악 개수 100
//     3. 재생시간 3600 분
//     4. 일치 곡 반환(재생시간 가장 긴 음악, 입력 먼저 음악)
    
//     풀이
//     1. 음악정보를 돌며, 정보 변환 후 우선순위 큐에 넣기
//     1-1) 클래스로 정보 변환. 재생시간,음악제목,재생기간에 따른 악보
//     1-1-1) 악보 중 #이 있으므로 따로 파싱해줘서 처리
//     1-2) 재생시간 기준으로 내림차순 정렬, 번호 순 오름차순 정렬
    
//     2. 우선순위큐에서 꺼낸 음악 악보를 돌며, 패턴 악보와 일치여부 확인
//     100(음악 개수)*3600(악보 최대길이) = 360000 = 10^5 보다 조금 크므로
//     포문으로 충분히 가능
    
    
//     3. 맞으면, 해당 음악 제목 반환 후 종료
    
//     4. 없으면 , "(None)" 반환
// **/

// import java.util.*;
// import java.io.*;

// class Solution {
//     public String solution(String m, String[] musicinfos) {
//         String answer = "";
//         m = parsingMusic(m);
//         PriorityQueue<Music> q = new PriorityQueue<>();
        
//         for(int i = 0; i < musicinfos.length; i++){
//             Music cur = parsing(musicinfos[i],i);
//             q.offer(cur);
//         }
        
//         while(!q.isEmpty()){
//             Music cur = q.poll();
//             String music = cur.music;
//             int idx = 0;
//             for(int i = 0; i < music.length(); i++){
//                 if(music.charAt(i) == m.charAt(idx)){
//                     idx++;
//                 }else{
//                     idx = 0;
//                     if(music.charAt(i) == m.charAt(idx)){
//                         idx++;
//                     }
//                 }
//                 if(idx == m.length()){
//                     return cur.name;
//                 }
//             }
//         }
        
//         return "(None)";
        
//     }
    
//     // 음악 클래스 반환
//     public Music parsing(String musicinfos2,int cnt){
//         String[] musicinfos = musicinfos2.split(",");
//         int start = parsingInt(musicinfos[0]);
//         int end = parsingInt(musicinfos[1]);
//         int playTime = end -start;
//         String music =  parsingMusic(musicinfos[3]);
        
//         String str = "";
//         if(music.length() >= playTime){
//             str += music.substring(0,playTime);
//         }else{
//             int re = playTime / music.length();
//             for(int i = 0; i < re; i++){
//                 str += music;
//             }
//             int sub = playTime % music.length();
//             str += music.substring(0,sub);
//         }
        
//         return new Music(playTime,cnt,musicinfos[2],str);
        
//     }
    
//    // 악보를 숫자형태의 스트링으로 변경(# 처리해주기위한 파싱)
//     public static String parsingMusic(String music){
        
//         music = music.replaceAll("C#", "H");
//         music = music.replaceAll("D#", "I");
//         music = music.replaceAll("F#", "J");
//         music = music.replaceAll("G#", "K");
//         music = music.replaceAll("A#", "L");
        
//         return music;
//     }
    
//     // 플레이시간 반환
//     public int parsingInt(String time){
//         int result = 0;
//         String[] times = time.split(":");
//         result += (60 * Integer.parseInt(times[0]));
//         result += Integer.parseInt(times[1]);
//         return result;
//     }
    
//     // 음악 클래스
//     public static class Music implements Comparable<Music>{
//         int playTime;
//         int number;
//         String name;
//         String music;
//         public Music(int playTime, int number,String name, String music){
//             this.playTime = playTime;
//             this.number = number;
//             this.name = name;
//             this.music = music;
//         }
        
//         @Override
//         public int compareTo(Music o){
//             if(this.playTime > o.playTime){
//                 return -1;
//             }else if(this.playTime < o.playTime){
//                 return 1;
//             }else{
//                 if(this.number > o.number){
//                     return 1;
//                 }else{
//                     return -1;
//                 }
//             }
//         }
//     }
// }