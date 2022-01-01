import java.io.*;
import java.util.*;


public class Main {
    static int n, m, index = 0;
    static HashMap<String, Person> hash = new HashMap<>();
    static HashMap<Integer, String> map = new HashMap<>();
    static HashSet<String> set = new HashSet<>();
    static List<Integer>[] list;
    static int[] indegree;

    static class Person {
        String name;
        int idx;
        double blood;

        public Person(String s, int i) {
            name = s;
            idx = i;
            blood = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indegree = new int[n*3];
        list = new ArrayList[n*3];
        for (int i = 0; i < n*3; i++) list[i] = new ArrayList<>();

        //왕 초기화..
        Person king = new Person(br.readLine(), index);
        king.blood =1;
        set.add(king.name);
        hash.put(king.name, king);
        map.put(index++, king.name); 

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                String t = s[j];
                if (set.add(t)) {
                    hash.put(t, new Person(t, index));
                    map.put(index++, t);
                }
            }
            // 부모 부모 -> 자식 만들기
            int child = hash.get(s[0]).idx;
            list[hash.get(s[1]).idx].add(child);
            list[hash.get(s[2]).idx].add(child);

            indegree[child]+=2;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < index; i++) {
            if (indegree[i] == 0) q.add(i);
        }

        while(!q.isEmpty()) {
            int now = q.poll();
            Person parent = hash.get(map.get(now));

            for (int next : list[now]) {
                String childName = map.get(next);
                Person child = hash.get(childName);
                child.blood += parent.blood/2;
                if(--indegree[next] == 0 ) q.add(next);
            }
        }

        double max = 0;
        String ans = "";
        for(int i = 0; i<m; i++){
            String candidate = br.readLine();
            if(set.contains(candidate)){
                double value = hash.get(candidate).blood;
                if(max < value){
                    max = value;
                    ans = candidate;
                }
            }
        }

        System.out.println(ans);

    }
}