import java.util.*;

class Solution {

    static class Node {
        String now;
        int cnt;

        public Node (String now, int cnt) {
            this.now = now;
            this.cnt = cnt;
        }
    }

    int n;

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        this.n = words.length;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(begin, 0));

        boolean visited[] = new boolean[n];

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.now.equals(target)) {
                answer = cur.cnt;
                break;
            }

            for (int i = 0; i < n; i++) {
                if (!visited[i] && isChangable(cur.now, words[i])) {
                    visited[i] = true;
                    queue.offer(new Node(words[i], cur.cnt+1));
                }
            }
        }

        return answer;
    }

    public boolean isChangable(String now, String that) {
        int length = now.length();
        int cnt = 0;
        for (int i = 0; i < length; i++) {
            if (now.charAt(i) == that.charAt(i)) continue;
            cnt++;
            if (cnt > 1) return false;
        }

        return true;
    }
}