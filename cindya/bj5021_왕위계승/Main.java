package cindya.bj5021_왕위계승;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 사람 클래스
class Person{
    String name;
    int cnt = 0;
    double blood = 0;
    List<Person> next = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        Map<String, Person> persons = new HashMap<>();
        Queue<Person> queue = new LinkedList<>();
        List<String> candidates = new ArrayList<>();
        String answer = "";
        double maxBlood = 0;

        Person king = new Person(br.readLine());
        king.blood = 1; // 왕의 피를 1로 설정
        persons.put(king.name, king);
        for(int i = 0; i < n; i++){
            String[] p = br.readLine().split(" ");
            for(String name : p)
                persons.putIfAbsent(name, new Person(name));
            Person person = persons.get(p[0]);
            person.cnt = 2;
            persons.get(p[1]).next.add(person);
            persons.get(p[2]).next.add(person);
        }

        for(int i = 0; i < m; i++)
            candidates.add(br.readLine());

        // cnt가 0인 사람들 큐에 삽입
        for(String key : persons.keySet())
            if(persons.get(key).cnt == 0) queue.offer(persons.get(key));

        while (!queue.isEmpty()){
            Person person = queue.poll();
            for(Person np : person.next){
                np.cnt--;
                // 부모의 피의 반을 더함
                np.blood += person.blood / 2;
                // cnt가 0이면 큐에 삽입
                if(np.cnt == 0)
                    queue.offer(np);
            }
        }

        // 후보자 중 피가 가장 1에 가까운 사람이 왕
        for(String name : candidates){
            if(persons.containsKey(name) && persons.get(name).blood > maxBlood){
                answer = name;
                maxBlood = persons.get(name).blood;
            }
        }

        System.out.println(answer);
        br.close();
    }
}
