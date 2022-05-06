import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static Stack<Character> s;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String word = br.readLine();
        int len = input.length();
        int wlen = word.length();
        s = new Stack<>();
        for(int i=0; i<len;i++){
            s.push(input.charAt(i));
            if(s.size()>=wlen&&matchString(word)){
                for(int j=0; j<wlen; j++) s.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char c: s){
            sb.append(c);
        }
        if(sb.length()==0) System.out.println("FRULA");
        else System.out.println(sb.toString());
    }

    public static boolean matchString(String word){
        boolean flag = true;
        for(int i= 0;i<word.length(); i++){
            if(s.get(s.size()-word.length()+i)!=word.charAt(i)){
                flag = false;
                break;
            }
        }
        return flag;
    }
}