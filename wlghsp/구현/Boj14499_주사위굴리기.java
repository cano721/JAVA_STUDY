package baekjoon.goldⅣ;

/*


크기가 N×M인 지도가 존재한다. 지도의 오른쪽은 동쪽, 위쪽은 북쪽이다. 이 지도의 위에 주사위가 하나 놓여져 있으며, 주사위의 전개도는 아래와 같다. 지도의 좌표는 (r, c)로 나타내며, r는 북쪽으로부터 떨어진 칸의 개수, c는 서쪽으로부터 떨어진 칸의 개수이다.

  2
4 1 3
  5
  6

주사위는 지도 위에 윗 면이 1이고, 동쪽을 바라보는 방향이 3인 상태로 놓여져 있으며, 놓여져 있는 곳의 좌표는 (x, y) 이다. 가장 처음에 주사위에는 모든 면에 0이 적혀져 있다.

지도의 각 칸에는 정수가 하나씩 쓰여져 있다. 주사위를 굴렸을 때, 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다. 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.

주사위를 놓은 곳의 좌표와 이동시키는 명령이 주어졌을 때, 주사위가 이동했을 때 마다 상단에 쓰여 있는 값을 구하는 프로그램을 작성하시오.

주사위는 지도의 바깥으로 이동시킬 수 없다. 만약 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안 된다.
입력

첫째 줄에 지도의 세로 크기 N, 가로 크기 M (1 ≤ N, M ≤ 20), 주사위를 놓은 곳의 좌표 x, y(0 ≤ x ≤ N-1, 0 ≤ y ≤ M-1), 그리고 명령의 개수 K (1 ≤ K ≤ 1,000)가 주어진다.

둘째 줄부터 N개의 줄에 지도에 쓰여 있는 수가 북쪽부터 남쪽으로, 각 줄은 서쪽부터 동쪽 순서대로 주어진다. 주사위를 놓은 칸에 쓰여 있는 수는 항상 0이다. 지도의 각 칸에 쓰여 있는 수는 10 미만의 자연수 또는 0이다.

마지막 줄에는 이동하는 명령이 순서대로 주어진다. 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4로 주어진다.
출력

이동할 때마다 주사위의 윗 면에 쓰여 있는 수를 출력한다. 만약 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안 된다.


 */

//블로그 참고함.... 공부하자..

import java.io.*;
import java.util.*;

public class Boj14499_주사위굴리기 {
  static int n, m, x, y, k;
  static int map[][];
  static int dx[] = { 0, 0, -1, 1 };
  static int dice[];
  static int dy[] = { 1, -1, 0, 0 };
  static Queue<Integer> q = new LinkedList<>();
  static ArrayList<Integer> direction = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] t = br.readLine().split(" ");

    n = Integer.parseInt(t[0]);
    m = Integer.parseInt(t[1]);
    x = Integer.parseInt(t[2]);
    y = Integer.parseInt(t[3]);
    k = Integer.parseInt(t[4]);

    map = new int[n][m];
    dice = new int[7];

    for (int i = 0; i < n; i++) {
      String[] input = br.readLine().split(" ");
      for (int j = 0; j < input.length; j++) {
        map[i][j] = Integer.parseInt(input[j]);
      }
    }

    String order[] = br.readLine().split(" ");

    for (int i = 0; i < order.length; i++) {
      q.add(Integer.parseInt(order[i]));
    }
    solve();
  }

  public static void solve() {
    while (!q.isEmpty()) {
      int d = q.poll();
      int nx = x + dx[d - 1];
      int ny = y + dy[d - 1];
      if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
        change_dice(d);
        if (map[nx][ny] == 0) {
          map[nx][ny] = dice[6];
        } else {
          dice[6] = map[nx][ny];
          map[nx][ny] = 0;
        }
        System.out.println(dice[1]);
        x = nx;
        y = ny;
      }
    }
  }

  public static void change_dice(int d) {
    int temp[] = new int[7];
    for (int i = 1; i <= 6; i++) {
      temp[i] = dice[i];
    }

    switch (d) {
      case 1: // 동쪽으로 굴릴때
        dice[1] = temp[2];
        dice[3] = temp[1];
        dice[6] = temp[3];
        dice[2] = temp[6];
        break;
      case 2: // 서쪽으로 굴릴 때
        dice[1] = temp[3];
        dice[2] = temp[1];
        dice[6] = temp[2];
        dice[3] = temp[6];
        break;
      case 3: // 북쪽으로 굴릴 때
        dice[4] = temp[1];
        dice[6] = temp[4];
        dice[5] = temp[6];
        dice[1] = temp[5];
        break;
      case 4: // 남쪽으로 굴릴 때
        dice[5] = temp[1];
        dice[6] = temp[5];
        dice[4] = temp[6];
        dice[1] = temp[4];
        break;
    }

  }
}