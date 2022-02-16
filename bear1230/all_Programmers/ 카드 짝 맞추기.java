import java.util.*;

class Solution {
	static class Card {
		int x;
		int y;
		int cnt;

		public Card(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static int map[][];

	static int a[] = { 0, 0, 1, -1 };
	static int b[] = { 1, -1, 0, 0 };

	public int solution(int[][] board, int x, int y) {
		map = board;

		return change(new Card(x, y, 0));
	}

	public int change(Card card) {
		int ret = Integer.MAX_VALUE;

		for (int i = 1; i <= 6; i++) {

			ArrayList<Card> cardList = new ArrayList<>();
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					if (map[j][k] == i) {
						cardList.add(new Card(j, k, 0));
					}
				}
			}

			if (cardList.isEmpty())
				continue;

			int a = bfs(card, cardList.get(0)) + bfs(cardList.get(0), cardList.get(1)) + 2;
			int b = bfs(card, cardList.get(1)) + bfs(cardList.get(1), cardList.get(0)) + 2;

			for (int j = 0; j < 2; j++) {
				map[cardList.get(j).x][cardList.get(j).y] = 0;
			}
			
			ret = Math.min(ret, a + change(cardList.get(1)));
			ret = Math.min(ret, b + change(cardList.get(0)));

			for (int j = 0; j < 2; j++) {
				map[cardList.get(j).x][cardList.get(j).y] = i;
			}

		}

		return ret;
	}

	public static int bfs(Card cur, Card next) {
		boolean visited[][] = new boolean[4][4];

		Queue<Card> que = new LinkedList<>();
		que.add(cur);

		while (!que.isEmpty()) {
			Card card = que.poll();

			if (card.x == next.x && card.y == next.y) {
				return card.cnt;
			}

			for (int i = 0; i < 4; i++) {
				int mx = card.x + a[i];
				int my = card.y + b[i];

				if (mx < 0 || my < 0 || mx > 3 || my > 3)
					continue;

				if (!visited[mx][my]) {
					visited[mx][my] = true;
					que.add(new Card(mx, my, card.cnt + 1));
				}

				for (int j = 0; j < 2; j++) {
					mx += a[i];
					my += b[i];
				}

				if (!visited[mx][my]) {
					visited[mx][my] = true;
					que.add(new Card(mx, my, card.cnt + 1));
				}

			}

		}

		return Integer.MAX_VALUE;
	}

}
