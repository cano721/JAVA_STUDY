class Solution {
  public int solution(String skill, String[] skill_trees) {
		int answer = 0;
		for (int i = 0; i < skill_trees.length; i++) {
			String[] s = skill_trees[i].split("");
			int idx = 0;
			boolean check = true;
;

			for (int j = 0; j < s.length; j++) {
				if (idx < skill.indexOf(s[j])) {
					check = false;
					break;
				} else if (idx == skill.indexOf(s[j])) {
					idx++;
				}
			}

			if (check) {
				answer++;
			}
		}
		return answer;
	}
}
