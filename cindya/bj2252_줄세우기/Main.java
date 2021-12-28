package cindya.bj2252_줄세우기;

import java.io.*;
import java.util.*;

// 학생 클래스
class Student implements Comparable<Student>{
    int number, cnt;
    List<Student> nexts = new ArrayList<>();

    public Student(int number) {
        this.number = number;
    }

    public void addNext(Student next){
        nexts.add(next);
    }

    public void addCnt(){
        cnt++;
    }

    public void reduceCnt(){
        cnt--;
    }

    @Override
    public int compareTo(Student s) {
        return this.cnt - s.cnt;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        List<Student> students = new ArrayList<>();

        // 1번 ~ n번 학생까지 생성
        for(int i = 1; i <= n; i++)
            students.add(new Student(i));

        // 키 잰 기록을 확인
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            students.get(a - 1).addNext(students.get(b - 1)); // 작은 학생의 nexts에 큰 학생을 넣고
            students.get(b - 1).addCnt(); //큰 학생의 cnt를 증가
        }

        while (!students.isEmpty()){
            Collections.sort(students); // cnt 순으로 정렬
            Student student = students.remove(0); // 가장 앞의 학생을 students에서 삭제
            bw.write(student.number + " "); // 학생 번호 출력
            for(Student next : student.nexts) // nexts의 학생들 cnt를 감소
                next.reduceCnt();
        }

        br.close();
        bw.close();
    }
}
