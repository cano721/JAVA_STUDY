package elwlahd555.sk;

public class sk_test01 {
	public static void main(String[] args) {
		int money = 4578;
		int costs[] = { 1, 4, 99, 35, 50, 1000 };
		System.out.println(solution(money, costs));
	}

	public static int solution(int money, int[] costs) {
		int map[] = new int[money + 1];
		for (int i = 1; i < map.length; i++) {
			map[i] = map[i - 1] + costs[0];
			if (i % 5 == 0) {
				map[i] = Math.min(map[i - 5] + costs[1], map[i]);
			}
			if (i % 10 == 0) {
				map[i] = Math.min(map[i - 10] + costs[2], map[i]);
			}
			if (i % 50 == 0) {
				map[i] = Math.min(map[i - 50] + costs[3], map[i]);
			}
			if (i % 100 == 0) {
				map[i] = Math.min(map[i - 100] + costs[4], map[i]);
			}
			if (i % 500 == 0) {
				map[i] = Math.min(map[i - 500] + costs[5], map[i]);
			}
		}
		return map[money];
	}
}
