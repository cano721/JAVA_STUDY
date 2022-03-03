package Programmers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String[] query = { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };
		for (int v : solution.solution(info, query)) {
			System.out.println(v);
		}
	}
}

class Solution {
	int result;

	/**
	 * �����ڵ��� ������ �־�����.
	 * ������ ���߾��, ����, ���, �ҿ�Ǫ��, ������ �����Ǿ� ������,
	 * ���߾��� cpp, java, python �� �ϳ��̴�.
	 * ������ backend, frontend �� �ϳ��̴�.
	 * ����� junior, senior �� �ϳ��̴�.
	 * �ҿ�Ǫ��� chicken, pizza �� �ϳ��̴�.
	 * ������ 1�� ~ 100000�� ������ �ڿ����̴�.
	 * 
	 * �հ��ڸ� �̾ƾ� �ϴµ� ������ ������ �����ȴ�.
	 * �� ������ '-'�� ��� �ش� �׸��� ������� �ʰڴٴ� �ǹ��̴�.
	 * ������ ��� ������ ����� ���� ���ϴ� ����
	 * @param info �����ڵ��� ����
	 * @param query ���ǵ�
	 * @return ���ǿ� �´� ��� ��
	 */
	public int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		Node root = new Node();
		for (String people : info) {
			String[] split = people.split(" ");
			Node cur = root;
			for (int i = 0; i < split.length - 1; i++) {
				cur.hash.putIfAbsent(split[i], new Node());
				cur = cur.hash.get(split[i]);
			}
			cur.scoreList.add(Integer.parseInt(split[split.length - 1]));
		}
        sort(root);
		for (int i = 0; i < query.length; i++) {
			String[] split = query[i].split(" ");
			Node cur = root;
			result = 0;
			search(split, 0, cur);
			answer[i] = result;
		}
		return answer;
	}
    
    public void sort(Node cur) {
        if(cur.scoreList.size() > 0) {
            cur.scoreList.sort(Comparator.naturalOrder());
            return;
        }
        for(String next: cur.hash.keySet()) {
            sort(cur.hash.get(next));
        }
    }

	public void search(String[] infos, int idx, Node cur) {
		if (idx == infos.length) {
			int passScore = Integer.parseInt(infos[idx - 1]);
            result += getTotalPassPeople(cur.scoreList, passScore);
			return;
		}
		if (infos[idx].equals("-")) {
			for (String key : cur.hash.keySet())
				search(infos, idx + 2, cur.hash.get(key));
		} else if (cur.hash.containsKey(infos[idx])) {
			search(infos, idx + 2, cur.hash.get(infos[idx]));
		}
	}
    
    public int getTotalPassPeople(ArrayList<Integer> scoreList, int passScore) {
        int left = 0, right = scoreList.size()-1;
        while(left <= right) {
            int mid = (left + right)/2;
            if(scoreList.get(mid) >= passScore)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return scoreList.size() - left;
    }
}

class Node {
	ArrayList<Integer> scoreList = new ArrayList<>();
	HashMap<String, Node> hash = new HashMap<>();
}