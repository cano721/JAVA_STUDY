package cindya.bj1766_문제집;

import java.io.*;
import java.util.*;

// 문제 클래스
class Question implements Comparable<Question>{
    int number, cnt;
    List<Question> next = new ArrayList<>();

    public Question(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(Question q) {
        // cnt 순서, number 순서
        return this.cnt != q.cnt ? this.cnt - q.cnt : this.number - q.number;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        List<Question> questions = new ArrayList<>();

        for(int i = 0; i < n; i++)
            questions.add(new Question(i + 1));

        while (m-- > 0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1, b = Integer.parseInt(st.nextToken()) - 1;
            questions.get(a).next.add(questions.get(b)); // a의 다음 문제로 b를 저장
            questions.get(b).cnt++; // b의 참조 횟수 증가
        }

        while (!questions.isEmpty()) {
            Collections.sort(questions); // 문제 정렬
            Question question = questions.remove(0); // 첫번째 문제 번호 출력
            bw.write(question.number + " "); // 출력
            // 다음 문제들 참조 횟수 줄이기
            for (Question nq : question.next)
                nq.cnt--;
        }
        br.close();
        bw.close();
    }
}
