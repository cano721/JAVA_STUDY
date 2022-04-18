import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static LinkedList<Integer>[] topnies;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        topnies = new LinkedList[5];
        // 링크드리스트 배열안에 0~5번 인덱스들은 현재 초기화가 안돼있어 null인 상태이므로 링크드 리스트로 초기화
        for (int i = 0; i < 5; i++) {
            topnies[i] = new LinkedList<>();
        }

        // 각 배열 1~4에 톱니 정보 초기화
        for (int i = 1; i <= 4; i++) {
            char[] topni = br.readLine().toCharArray();
            for (int j = 1; j <= 8; j++) {
                topnies[i].offer(topni[j-1] - '0');
            }
        }


        int rotaion = Integer.parseInt(br.readLine());
        for (int i = 0; i < rotaion; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            // 현재 톱니를 돌린다면 같이 돌아갈 톱니를 고름
            int rotateCount = 0;
            for (int j = number; j < 4+number; j++) {
                if (j <= 3 && !Objects.equals(topnies[j].get(2), topnies[j + 1].get(6))) {
                    // 오른쪽으로 몇개까지 돌아갈지 카운트
                    rotateCount += 1;
                } else {
                    break;
                }
            }

            // 돌아갈 횟수만큼 오른쪽 톱니를 돌림
            for (int j = 1; j < rotateCount + 1; j++) {
                rotate(number + j, (int) (direction * Math.pow(-1, j)));
            }

            rotateCount = 0;

            for (int j = number; j > 0; j--) {
                if (j >= 2 && !Objects.equals(topnies[j].get(6), topnies[j-1].get(2))) {
                    // 왼쪽으로 몇개까지 돌아갈지 카운트
                    rotateCount += 1;
                } else {
                    break;
                }
            }

            // 돌아갈 횟수만큼 오른쪽 톱니를 돌림
            for (int j = 1; j < rotateCount + 1; j++) {
                rotate(number - j, (int) (direction * Math.pow(-1, j)));
            }

            // 현재 톱니는 무조건 돌아감
            rotate(number, direction);
        }

        int ans = 0;
        for (int i = 1; i < 5; i++) {
            ans += topnies[i].getFirst() * Math.pow(2, i-1);
        }
        System.out.println(ans);
    }

    private static void rotate(int number, int direction) {
        if (direction == 1) {
            // 오른쪽으로 회전
            topnies[number].addFirst(topnies[number].removeLast());
        } else {
            topnies[number].addLast(topnies[number].removeFirst());
        }
    }
}
