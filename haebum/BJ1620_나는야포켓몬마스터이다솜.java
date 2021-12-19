import java.io.*;
import java.util.*;

/**
 * 
 */
public class BJ1620_나는야포켓몬마스터이다솜 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String,Integer> map = new LinkedHashMap<>();
        String[] arr = new String[n+1];
        for(int i = 1; i <= n; i++){
            String name = br.readLine();
            map.put(name,i);
            arr[i] = name;
        }
        for(int i = 0; i < m; i++){
            String name = br.readLine();
            boolean isNumeric =  name.matches("[+-]?\\d*(\\.\\d+)?");
            if(isNumeric){
                bw.write(arr[Integer.parseInt(name)] +"\n");
            }else{
                bw.write(map.get(name)+"\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
