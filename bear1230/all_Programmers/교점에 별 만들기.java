import java.util.*;

class Solution {    
    public String[] solution(int[][] line) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        HashSet<String> hash = new HashSet<>();
        for(int i=0;i<line.length-1;i++){
            for(int j=i+1;j<line.length;j++){
                int tmp = line[i][0]*line[j][1] - line[i][1]*line[j][0];
                int tx = line[i][1]*line[j][2] - line[i][2]*line[j][1];
                int ty = line[i][2]*line[j][0] - line[i][0]*line[j][2];
                if(tmp==0 || tx%tmp!=0 || ty%tmp!=0 ) continue; 

                int x = tx/tmp;
                int y = ty/tmp;

                hash.add(x+","+y);
                minX = Math.min(minX,x);
                minY = Math.min(minY,y);
                maxX = Math.max(maxX,x);
                maxY = Math.max(maxY,y);
            }
        }
        if(minX==Integer.MAX_VALUE){
            String [] answer = new String [1];
            answer[0] = "*";
            return answer;
        }
        
        String [] answer = new String [maxY - minY + 1];
        int idx = 0;
        for(int i=maxY;i>=minY;i--){
            StringBuilder sb = new StringBuilder();
            for(int j=minX;j<=maxX;j++){
                if(hash.contains(j+","+i))
                    sb.append("*");
                else
                    sb.append(".");
            }
            answer[idx++] = sb.toString();
        }
        return answer;
    }
}
