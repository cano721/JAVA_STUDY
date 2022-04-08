package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjoon17143_낚시왕{
  static class Shark {
    int idx, x, y, speed, dir, size;
    // 죽은(먹혔거나 잡힌) 상어인지 표시
    boolean died;

    public Shark() {
      this.died = true;
    }

    public Shark(int idx, int x, int y, int speed, int dir, int size) {
      this.idx = idx;
      this.x = x;
      this.y = y;
      this.speed = speed;
      this.dir = dir;
      this.size = size;
      this.died = false;
    }

    public void turnBack() {
      switch (this.dir) {
        case 1:
          this.dir = 2;
          break;
        case 2:
          this.dir = 1;
          break;
        case 3:
          this.dir = 4;
          break;
        case 4:
          this.dir = 3;
          break;
      }
    }

    public void move(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public void getEaten() {
      this.died = true;
    }
  }

  static int R, C;
  static int[][] map;
  static int[] dx = { 0, -1, 1, 0, 0 }, dy = { 0, 0, 0, 1, -1 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.valueOf(st.nextToken());
    C = Integer.valueOf(st.nextToken());
    int M = Integer.valueOf(st.nextToken());
    map = new int[R][C];

    ArrayList<Shark> sharks = new ArrayList<Shark>();
    // 상어의 인덱스와 배열의 인덱스를 맞추기 위해
    // map에 0은 상어가 존재하지 않는 의미로 사용되기 때문에
    // 0번째에는 빈 상어 넣음
    sharks.add(new Shark());

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int x = Integer.valueOf(st.nextToken()) - 1;
      int y = Integer.valueOf(st.nextToken()) - 1;
      int speed = Integer.valueOf(st.nextToken());
      int dir = Integer.valueOf(st.nextToken());
      int size = Integer.valueOf(st.nextToken());

      // 각 위치에서 다시 그 자리 상태 그대로 돌아올 수 있는 값으로 나눈 나머지
      // 즉, 최소한의 속력으로 재정의
      if (dir == 1 || dir == 2)
        speed = speed % ((R - 1) * 2);
      else
        speed = speed % ((C - 1) * 2);

      sharks.add(new Shark(i + 1, x, y, speed, dir, size));
      // 지도에 idx번째 상어가 있다는 표시
      map[x][y] = i + 1;
    }

    int total = M == 0 ? 0 : goFishing(sharks);

    bw.write(total + "\n");
    bw.flush();
    bw.close();
  }

  static int goFishing(ArrayList<Shark> sharks) {
    // 낚시왕이 잡은 물고기 무게의 합
    int total = 0;

    for (int person = 0; person < C; person++) {

      for (int i = 0; i < R; i++) {
        // 낚시왕이 위치한 열에서 땅과 가장 가까운 물고기를 발견하면
        if (map[i][person] != 0) {
          int sharkIdx = map[i][person];

          // 잡아서 무게 더함
          total += sharks.get(sharkIdx).size;
          // 물고기 죽었다고 표시
          // 1. 상어 배열에서 died property를 true로 변환
          sharks.get(sharkIdx).getEaten();
          // 2. 지도에서 흔적 지움
          map[i][person] = 0;
          break;
        }
      }

      // 상어 이동
      moveShark(sharks);

    }

    return total;
  }

  static void moveShark(ArrayList<Shark> sharks) {
    int[][] newMap = new int[R][C];

    for (Shark shark : sharks) {
      // 이미 죽은 상어이면 pass
      if (shark.died)
        continue;

      // 상어 속력만큼 이동
      for (int i = 0; i < shark.speed; i++) {
        int nx = shark.x + dx[shark.dir];
        int ny = shark.y + dy[shark.dir];

        // 만약 지도 범위를 벗났다면
        if (!isIn(nx, ny)) {
          // 방향을 바꿔서
          shark.turnBack();
          // 다시 이동
          nx = shark.x + dx[shark.dir];
          ny = shark.y + dy[shark.dir];
        }
        // 바뀐 상어 위치 입력
        shark.move(nx, ny);
      }

      int already = newMap[shark.x][shark.y];

      // 상어가 이동한 위치에 아무도 없으면
      if (already == 0) {
        // 상어 표시
        newMap[shark.x][shark.y] = shark.idx;
      } else if (sharks.get(already).size < shark.size) {
        // 이동한 위치에 어떤 상어가 있는데
        // 현재 상어보다 크기가 작은 상어면
        // 죽었다고 표시
        sharks.get(already).getEaten();
        newMap[shark.x][shark.y] = shark.idx;
      } else {
        // 현재 상어보다 더 큰 상어면
        // 현재 상어가 죽음
        shark.getEaten();
      }
    }

    // 상어가 이동한 위치들을 원본 지도로 복사
    copyMap(newMap);
  }

  static boolean isIn(int x, int y) {
    if (0 <= x && x < R && 0 <= y && y < C)
      return true;
    return false;
  }

  static void copyMap(int[][] newMap) {
    for (int i = 0; i < R; i++) {
      System.arraycopy(newMap[i], 0, map[i], 0, C);
    }
  }

}
