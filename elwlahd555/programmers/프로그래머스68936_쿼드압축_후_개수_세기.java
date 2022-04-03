package elwlahd555.programmers;

public class 프로그래머스68936_쿼드압축_후_개수_세기 {
	public static void main(String[] args) {
		int arr[][]= {{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}};
		System.out.println(solution(arr));
	}
	private static int map[][];
	private static int answer[];
    public static int[] solution(int[][] arr) {
    	map=arr;
        answer = new int[2];
        dfs(0,0,arr.length);
        return answer;
    }
	private static void dfs(int x, int y, int N) {
		int init = map[x][y];
		for (int i = x; i < x + N; i++) {
			for (int j = y; j < y + N; j++) {
				if (map[i][j] != init) {
					int nn = N / 2;
					dfs(x, y, nn);
					dfs(x, y + nn, nn);
					dfs(x + nn, y, nn);
					dfs(x + nn, y + nn, nn);
					return;
				}
			}
		}
        answer[init] += 1;
	}
}
