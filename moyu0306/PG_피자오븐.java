import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder("");
        for(int i=0; i<N; i++){
            int num = Integer.parseInt(br.readLine());
            calcShortest(num,sb);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    public static int button60(int num, StringBuilder sb){
        int i = num/60;
        int left = num % 60;
        if(left>35){
            left -= 60;
            i++;
        }
        sb.append(i);
        sb.append(" ");
        return left;
    }

    public static int button10(int num, StringBuilder sb){
      int i = num/10;
      int left = num%10;
        if(left>5){
            left-=10;
            i++;
        }
        sb.append(i);
        sb.append(" ");
        return left;
    }

    public static int buttonM10(int num, StringBuilder sb){
        int i = (-num)/10;
        int left = (-num)%10;
        if(left>5){
            left -= 10;
            i++;
        }
        sb.append(i);
        sb.append(" ");
        return -left;
    }

    public static void button1(int num, StringBuilder sb){
        sb.append(num);
        sb.append(" ");
    }

    public static void buttonM1(int num, StringBuilder sb){
        sb.append(-num);
    }
    public static void calcShortest(int num, StringBuilder sb){
        num = button60(num,sb);
        if(num>0){
          num = button10(num,sb);
          sb.append(0);
          sb.append(" ");
        }else{
            sb.append(0);
            sb.append(" ");
            num = buttonM10(num,sb);
        }

        if(num>0){
            button1(num,sb);
            sb.append(0);
            sb.append(" ");
        }else{
            sb.append(0);
            sb.append(" ");
            buttonM1(num,sb);
        }
    }
}