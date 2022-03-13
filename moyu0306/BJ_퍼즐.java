import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static HashSet<String> set;
    static boolean flag = false;
    static Queue<Pair> q;
    static int[] dy = new int[]{-1,0,1,0};
    static int[] dx = new int[]{0,-1,0,1};
    public static void main(String[] args) throws IOException {
	// write your code here
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        set = new HashSet<>();
        q = new LinkedList<>();
        int[][] num = new int[3][3];
        int[] zero= new int[]{0,0};
        for(int i=0; i<3; i++){
            String[] split = br.readLine().split(" ");
            num[i]= new int[]{Integer.parseInt(split[0]),Integer.parseInt(split[1]),Integer.parseInt(split[2])};
            for(int j=0;j<3; j++){
                if(num[i][j]==0) zero = new int[]{i,j};
            }
        }

        q.offer(new Pair(num,0,zero));
        addSet(num);
        if(flag){System.out.println(0); return;}

        while(!q.isEmpty()){
            Pair pair = q.poll();
            int r = pair.zero[0];
            int c = pair.zero[1];
            int[][] arr = pair.arr;
            for(int i=0; i<4; i++){
                int posR = r+dy[i];
                int posC = c+dx[i];
                if(posR<0||posR>=3||posC<0||posC>=3) continue;
                swap(arr,r,c,posR,posC);
                if(!addSet(arr)) q.offer(new Pair(clone(arr),pair.cnt+1,new int[]{posR,posC}));
                swap(arr,r,c,posR,posC);
            }
            if(flag) {System.out.println(pair.cnt+1); return;}
        }

        System.out.println(-1);

    }
    public static  int[][] clone(int[][] arr){ // object.clone은 primitive or immutable에 한해서만 deep copy가 이루어짐. 객체의 경우 shallow copy
                                                // 2차원 배열은 각 row에 위치한 1차원 배열들을 참조하는 shallow copy가 일어남.
        int[][] newarr = new int[arr.length][arr.length];
        for(int i=0;i<arr.length;i++){
            for(int j=0; j<arr.length;j++){
                newarr[i][j] = arr[i][j];
            }
        }
        return newarr;
    }
    public static void swap(int[][] arr,int r, int c, int posR, int posC){
        int tmp = arr[r][c];
        arr[r][c] = arr[posR][posC];
        arr[posR][posC] = tmp;
    }
    public static boolean addSet(int[][] num){
        StringBuffer sb = new StringBuffer();
        for(int i=0; i< num.length;i++){
            for(int j=0; j<num[0].length; j++){
                sb.append(num[i][j]);
            }
        }

        String key = sb.toString();
        if(key.equals("123456780")) flag = true;
        if(set.contains(key)) return true;
        else{
            set.add(key);
            return false;
        }
    }
}
class Pair{
    int[][] arr;
    int cnt;
    int[] zero;
    public Pair(int[][] arr, int cnt, int[] zero){
        this.arr = arr;
        this.cnt = cnt;
        this.zero = zero;
    }
}