package elwlahd555.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class 프로그래머스72412_순위_검색 {
	public static void main(String[] args) {
		String info[] = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String query[] = { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };
		System.out.println(solution(info, query));
	}

	private static Map<String, ArrayList<Integer>> infoMap = new HashMap();

	public static int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];

		// 키 만들기
		for (int i = 0; i < info.length; i++) {
			makeKey(info[i]);
		}

		// value값들 정렬
		ArrayList<String> savekey = new ArrayList();
		for (Map.Entry<String, ArrayList<Integer>> entry : infoMap.entrySet()) {
			if (savekey.contains(entry.getKey())) {
				continue;
			} else {
				savekey.add(entry.getKey());
				Collections.sort(entry.getValue());
			}

		}

		// query부분 처리
		for (int i = 0; i < query.length; i++) {
			String[] temp = query[i].split(" ");

			String key = temp[0] + temp[2] + temp[4] + temp[6];

			if (!infoMap.containsKey(key)) {
				answer[i] = 0;
				continue;
			}
			ArrayList<Integer> arr = infoMap.get(key);

			if ("-".equals(temp[7])) {
				answer[i] = arr.size();
			} else {
				int score = Integer.parseInt(temp[7]);
				// 이분탐색
				answer[i] = binarySearch(arr, score);
			}

		}

		// System.out.println(infoMap.toString());
		return answer;

	}

	public static void makeKey(String info) {

		String[] temp = info.split(" ");

		String[] language = { temp[0], "-" };
		String[] part = { temp[1], "-" };
		String[] career = { temp[2], "-" };
		String[] food = { temp[3], "-" };
		int score = Integer.parseInt(temp[4]);

		for (int i = 0; i < language.length; i++) {
			for (int j = 0; j < part.length; j++) {
				for (int k = 0; k < career.length; k++) {
					for (int p = 0; p < food.length; p++) {
						String key = language[i] + part[j] + career[k] + food[p];
						if (infoMap.containsKey(key)) {
							ArrayList<Integer> arr = infoMap.get(key);
							arr.add(score);
							// Collections.sort(arr);
							infoMap.put(key, arr);
						} else {
							ArrayList<Integer> arr = new ArrayList();
							arr.add(score);
							infoMap.put(key, arr);
						}

					}
				}
			}
		}

		return;
	}

	public static int binarySearch(ArrayList<Integer> arr, int score) {

		int mid = 0;
		int end = arr.size();
		int start = 0;

		while (end > start) // end가 start보다 같거나 작아지면, 그 값이 Lower Bound이므로 반복을 종료한다.
		{
			mid = (start + end) / 2;
			if (arr.get(mid) >= score) // 중간값이 원하는 값보다 크거나 같을 경우, 끝값을 중간값으로 설정하여 다시 탐색한다.
				end = mid;
			else
				start = mid + 1; // 중간값이 원하는 값보다 작을 경우, 시작값을 중간값+1로 설정하여 다시 탐색한다.
		}
		return arr.size() - start;

	}
}
