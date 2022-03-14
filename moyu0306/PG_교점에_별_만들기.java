import java.util.*;
class Solution {

    ArrayList<int[]> list = new ArrayList<>();
    int ymax = Integer.MIN_VALUE;
    int ymin = Integer.MAX_VALUE;
    int xmin = Integer.MAX_VALUE;
    int xmax = Integer.MIN_VALUE;

    public String[] solution(int[][] line) {
        for(int i=0; i<line.length; i++){
            for(int j=i+1;j<line.length; j++){
                addPoint(line[i],line[j]);
            }
        }
        
        Collections.sort(list, (x,y) -> {
            if (x[1] == y[1]) return x[0]-y[0];
            else return y[1] - x[1];
        });
            
        int xsize = xmax - xmin+1;
        int ysize = ymax - ymin+1;
        boolean[][] marks = new boolean[ysize][xsize];
        String[] answer = new String[ysize];
        
        for(int[] spot : list){
            int x = spot[0] - xmin;
            int y = ymax - spot[1];
            marks[y][x] = true;
        }
        
        for(int i=0; i< marks.length; i++){
             StringBuilder sb = new StringBuilder();
                for(int j=0; j<marks[0].length;j++){
                    if(marks[i][j]) sb.append("*");
                    else sb.append(".");
                }
            answer[i] = sb.toString();
        }
       return answer; 
    }
    
    public void addPoint(int[] l1, int[] l2){
        int A = l1[0], B = l1[1], E=l1[2];
        int C = l2[0], D = l2[1], F=l2[2];
        long over = (long)A*D - (long)B*C;
        if (over==0) return; 
        int x = (int) (((long)B*F -(long)E*D)/over);
        int y = (int) (((long)E*C -(long)A*F)/over);
        
        if(A*x+B*y+E==0&&C*x+D*y+F==0){
            xmin = Integer.min(xmin, x);
            xmax = Integer.max(xmax, x);
            ymax = Integer.max(ymax, y);
            ymin = Integer.min(ymin, y);
            list.add(new int[]{x,y});
        }
    }
    
     
 }