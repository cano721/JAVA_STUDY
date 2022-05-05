package elwlahd555.baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class baekjoon15685_드래곤_커브 {
    private static int N = 101;
 
    private static boolean[][] map = new boolean[N][N];
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 커브의 개수
 
        while (N > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt(); // 시작 방향
            int g = sc.nextInt(); // 세대
 
            draw(x, y, getDirects(d, g));
            N--;
        }
 
        System.out.println(result());
    }
 
    public static List<Integer> getDirects(int d, int g) {
        List<Integer> directs = new ArrayList<>();
        directs.add(d);
 
        while (g > 0) {
        	int size=directs.size();
            for (int i = size - 1; i >= 0; i--) {
                directs.add((directs.get(i) + 1) % 4);
            }
            g--;
        }
        return directs;
    }
 
    public static void draw(int x, int y, List<Integer> directs) {
        map[x][y] = true;
 
        for (int direct : directs) {
            switch (direct) {
                case 0:
                    map[++x][y] = true;
                    break;
                case 1:
                    map[x][--y] = true;
                    break;
                case 2:
                    map[--x][y] = true;
                    break;
                case 3:
                    map[x][++y] = true;
                    break;
            }
        }
    }
 
    private static int result() {
        int count = 0;
 
        for (int x = 0; x < N-1; x++) {
            for (int y = 0; y < N-1; y++) {
                if (map[x][y] && map[x + 1][y] && map[x][y + 1] && map[x + 1][y + 1])
                    count++;
            }
        }
 
        return count;
    }
}
