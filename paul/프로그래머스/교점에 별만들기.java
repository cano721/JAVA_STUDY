import java.util.*;

// 100,000 * 100,000 오버플로우 주의
class Solution {
    static class Pair{
        long y, x;
        public Pair(long yy, long xx){
            y =yy;
            x =xx;
        }
    }
    static List<Pair> pairs = new ArrayList<>();
    static long minY = Long.MAX_VALUE;
    static long maxY = Long.MIN_VALUE;
    
    static long minX = Long.MAX_VALUE;
    static long maxX = Long.MIN_VALUE;
    
    
    public String[] solution(int[][] line) {
        for(int i =0; i< line.length; i++){
            for(int j = i+1; j<line.length; j++){
                check(line, i, j);
            }
        }
        
        boolean[][] graph = new boolean[(int)(maxY-minY+1) ][(int)(maxX-minX+1)];
        for(Pair p : pairs){
            graph[(int)(maxY-p.y)][(int)(p.x-minX)] = true;
        }
        
        String[] answer = new String[graph.length];
        StringBuilder sb;

        for(int i = 0; i < graph.length; i++){
            sb = new StringBuilder();
            for(int j = 0; j < graph[0].length; j++){
                if(graph[i][j]){
                    sb.append("*");
                }else{
                    sb.append(".");
                }
            }
            answer[i] = sb.toString();
        }
        
        return answer;
    }
    
   
    static void check(int[][] line, int first, int second){
        long a = line[first][0];
        long b = line[first][1];
        long e = line[first][2];
        
        long c = line[second][0];
        long d = line[second][1];
        long f = line[second][2];
        
        long adbc = a*d - b*c;
        long x = b*f - e*d;
        long y = e*c - a*f;
        
        if(adbc == 0 ) return;
        if(x%adbc != 0 || y %adbc != 0) return;
        
        pairs.add(new Pair(y/adbc, x/adbc));
        
        // y 범위
        minY = Math.min(minY, y/adbc);
        maxY = Math.max(maxY, y/adbc);
        
        // x 범위
        minX = Math.min(minX, x/adbc);
        maxX = Math.max(maxX, x/adbc);        
    }
}