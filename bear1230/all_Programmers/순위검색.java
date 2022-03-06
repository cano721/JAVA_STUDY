import java.util.*;

class Solution {
    Map<String,List<Integer>> map = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for (int i = 0; i < info.length; i++) {
            dfs("", 0, info[i].split(" "));
        }

        for (String key : map.keySet()) {
            List<Integer> values = map.get(key);
            Collections.sort(values);
        }

        for (int i = 0; i < query.length; i++) {
            String str = "";
            String[] arr = query[i].split(" ");

            for (int j = 0; j < arr.length-1; j++) {
                if (arr[j].equals("and")) continue;
                str += arr[j];
            }

            answer[i] = count(str, Integer.parseInt(arr[arr.length-1]));
        }

        return answer;
    }

    
    private void dfs(String str, int depth, String[] info) {
        if (depth == 4) {
            if (!map.containsKey(str)) {
                List<Integer> scores = new ArrayList<>();
                scores.add(Integer.parseInt(info[4]));
                map.put(str, scores);
            } else {
                map.get(str).add(Integer.parseInt(info[4]));
            }
            return;
        }

        dfs(str + "-", depth + 1, info);
        dfs(str + info[depth], depth + 1, info);
    }

    
    private int count(String str, int score) {
        if (!map.containsKey(str)) return 0;

        List<Integer> scores = map.get(str);
        int start = 0, end = scores.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (scores.get(mid) < score) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return scores.size() - start;
    }

}
