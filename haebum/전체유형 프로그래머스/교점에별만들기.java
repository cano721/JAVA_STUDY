/**
    1. 각 선들이 만나는 지점을 교점들을 저장
    
    2. 최소, 최대 좌표를 확인하여 맵 만들기
    
    3. 해당 교점 좌표들을 맵에 체크해두기
    
    4. 형식대로 반환
**/

import java.util.*;

class Solution {
    
    public class Point{
        long x;
        long y;
        
        public Point(long x, long y){
            this.x = x;
            this.y = y;
        }
    }
    
    public String[] solution(int[][] line) {
        
        Set<Point> set = new HashSet<>();
        
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;
        
        for(int i = 0; i < line.length; i++){
            for(int j = i+1; j < line.length; j++){
                
                long a = line[i][0];
                long b = line[i][1];
                long e = line[i][2];
                
                long c = line[j][0];
                long d = line[j][1];
                long f = line[j][2];
                
                long adbc = a*d - b*c;
                if(adbc == 0){
                    continue;
                }
                
                long bfed = b*f - e*d;
                if(bfed % adbc != 0){
                    continue;
                }
                
                long ecaf = e*c - a*f;
                if(ecaf % adbc != 0){
                    continue;
                }
                
                long x = bfed / adbc;
                long y = ecaf / adbc;
                
                minX = Math.min(minX,x);
                maxX = Math.max(maxX,x);
                minY = Math.min(minY,y);
                maxY = Math.max(maxY,y);
                
                set.add(new Point(x,y));
            }
        }
        
        boolean[][] map = new boolean[(int)(maxY -minY +1)][(int)(maxX -minX +1)];
        
        
        for(Point p : set){
            int x = (int)(p.x - minX);
            int y = (int)(maxY -p.y);
            
            map[y][x] = true;
        }
        
        String[] answer = new String[map.length];
        StringBuilder sb;
        
        for(int i = 0; i < map.length; i++){
            sb = new StringBuilder();
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j]){
                    sb.append("*");
                }else{
                    sb.append(".");
                }
            }
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}