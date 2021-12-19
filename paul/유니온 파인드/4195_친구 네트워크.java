import java.util.*;
import java.io.*;


public class Main {
    static int t, f;
    static Map<String, Integer> map;
    static int[] root, cnt;
    static class Node{
        String n;

    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        
        
        StringBuilder sb = new StringBuilder();
        while(t -- > 0){
            f = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            root = new int[2*f];
            cnt = new int[2*f];
            Arrays.fill(cnt, 1);
            int index = 0;
            for(int i =0; i<f; i++){
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b= st.nextToken();
                if(!map.containsKey(a)){
                    map.put(a, index);
                    root[index] = index;
                    index++;
                }

                if(!map.containsKey(b)){
                    map.put(b, index);
                    root[index] = index;
                    index++;
                }

                sb.append(union(map.get(a), map.get(b)) + "\n");
                
            }
        }

        System.out.println(sb);
        
    }

    static int find(int n){
       if(root[n] == n) return n;
       return root[n] = find(root[n]);
    }

    static int union(int a, int b){
        a = find(a);
        b = find(b);
        if(a == b) return cnt[a];
        if(a < b) {
            root[b] = a;
            return cnt[a] += cnt[b];
        }
        else{
            root[a] = b;
            return cnt[b] += cnt[a];
        }
    }
       
}


