class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (int i = 0; i < skill_trees.length; i++) {

            int loc = 0;
            boolean flag = true;


            for (int j = 0; j < skill_trees[i].length(); j++) {

                if (skill.indexOf(skill_trees[i].charAt(j)) > -1) {

                    if (skill.charAt(loc) == skill_trees[i].charAt(j)) {

                        loc++;
                    } else {
                        flag = false;
                        break;
                    }


                }


            }

            if (flag) {
                answer++;
            }

        }
        return answer;

    }
}