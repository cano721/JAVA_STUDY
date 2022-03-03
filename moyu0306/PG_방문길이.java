import java.util.*;
class Solution {
    static HashSet<String> set = new HashSet<>();
    static int y = 0;
    static int x = 0;
    static int cnt =0;
    public int solution(String dirs) {

        
        char[] cDirs= dirs.toCharArray();
        for(char c :cDirs){
            move(c);
        }
        
        int answer = cnt;
        return answer;
    }
    
    static public void move(char dir){
        int posY =0;
        int posX =0;
        if(dir == 'L'){
            posY = y;
            posX = x-1;
        }else if(dir == 'R'){
            posY= y;
            posX= x+1;
        }else if(dir == 'U'){
            posY= y+1;
            posX= x;
        }else if(dir == 'D'){
            posY= y-1;
            posX= x;
        }
       
        if(posY>5||posY<-5||posX>5||posX<-5) return;
        
        
        
//         StringBuilder sb1 = new StringBuilder();
//         StringBuilder sb2 = new StringBuilder();
//         sb1.append(x);
//         sb1.append(y);
//         sb1.append(posX);
//         sb1.append(posY);
        
//         sb2.append(posX);
//         sb2.append(posY);
//         sb2.append(x);
//         sb2.append(y);
        
        String hash1 =  x +""+ y +""+ posX +""+ posY;
        String hash2 =  posX+""+ posY +""+ x +""+ y;
        x = posX;
        y = posY;
        if(!set.contains(hash1)){set.add(hash1); set.add(hash2); cnt++;}
    }
}