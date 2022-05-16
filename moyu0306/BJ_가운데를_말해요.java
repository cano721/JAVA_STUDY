   import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.util.ArrayList;

public class Main {
        static ArrayList<Integer> input;
        static ArrayList<Integer> list;
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            list = new ArrayList<>();
            input = new ArrayList<>();
            int N = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
//            for(int i=0; i<N; i++) input.add(Integer.parseInt(br.readLine()));
            for(int i=0; i< N; i++){
                getPos(Integer.parseInt(br.readLine()));
                sb.append(list.get((list.size()-1)/2));
                sb.append("\n");
            }
                System.out.println(sb.toString());
        }

        public static void getPos(int num){
            int hi = list.size()-1;
            int lo = 0;
            int mid = 0;
            int val = num;
            while(lo<=hi){
                mid = (hi+lo)/2;
                val = list.get(mid);
                if(val>=num) hi = mid - 1;
                else if(val<num) lo = mid +1;
            }
            if(val>=num) list.add(mid,num);
            else list.add(mid+1,num);
        }
}