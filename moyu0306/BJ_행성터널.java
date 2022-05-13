   import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.util.Arrays;
    import java.util.Comparator;
    import java.util.PriorityQueue;

public class Main {
        static int N;
        static Planet[] planets;
        static boolean[] visited;
        static int[] parent;
        static PriorityQueue<int[]> pq;
        static int sum;
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            planets = new Planet[N];
            visited = new boolean[N];
            parent = new int[N];
            sum = 0;
            pq = new PriorityQueue<>((a,b)->{return a[2]-b[2];});
            int count = 0;
            for(int i=0; i< N; i++) parent[i] = i;
            for(int i=0; i< N; i++){
                String[] planetInfo = br.readLine().split(" ");
                planets[i] = new Planet(i,
                        Integer.parseInt(planetInfo[0]),
                        Integer.parseInt(planetInfo[1]),
                        Integer.parseInt(planetInfo[2])
                );
            }

            Arrays.sort(planets, Comparator.comparingInt(a -> a.x));
            for(int i=0; i<N-1; i++){
                pq.offer(new int[]{planets[i].num,planets[i+1].num,planets[i+1].x-planets[i].x});
            }
            Arrays.sort(planets, Comparator.comparingInt(a -> a.y));
            for(int i=0; i<N-1; i++){
                pq.offer(new int[]{planets[i].num,planets[i+1].num,planets[i+1].y-planets[i].y});
            }
            Arrays.sort(planets, Comparator.comparingInt(a -> a.z));
            for(int i=0; i<N-1; i++){
                pq.offer(new int[]{planets[i].num,planets[i+1].num,planets[i+1].z-planets[i].z});
            }
            while(count<N-1){
                int[] edge = pq.poll();
                if(find(edge[0])==find(edge[1])) continue;
                else{
                    sum += edge[2];
                    union(edge[0],edge[1]);
                    count++;
                }
            }
            System.out.println(sum);
        }

        public static int find(int i){
            if(parent[i]==i) return i;
            else{
                return parent[i] = find(parent[i]);
            }
        }

        public static void union(int i, int j){
            i = find(i);
            j = find(j);
            if(i>j) parent[i] = j;
            else parent[j] = i;
        }
}
class Planet{
    int num;
    int x;
    int y;
    int z;
    public Planet(int n,int a, int b, int c){
        num = n;
        x = a;
        y = b;
        z = c;
    }
}