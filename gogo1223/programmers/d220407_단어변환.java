package programmers;

public class d220407_단어변환 {

	public static void main(String[] args) {
		String[] words = {"hot", "dot", "dog", "lot", "log"};//{"hot", "dot", "dog", "lot", "log", "cog"};
		int answer = solution("hit", "cog", words);
		System.out.println(answer);
	}
	static int min = Integer.MAX_VALUE;
	private static int solution(String begin, String target, String[] words) {
		boolean[] visited = new boolean[words.length];
		return bfs(begin, target, words, visited, 0);
	}

	private static int bfs(String begin, String target, String[] words, boolean[] visited, int depth) {
		if(begin.equals(target)) {
			return depth;
		}else if(depth == words.length) {
			return 0;
		}
		
		for (int i = 0; i < words.length; i++) {
			if(checkOneWord(words[i], begin) && !visited[i]) {
				visited[i] = true;
				int ans = bfs(words[i], target, words, visited, depth+1);
				if(min != 0 && ans != 0) {
					min = Math.min(min, ans);
				}else if(min == 0) {
					min = ans;
				}
				visited[i] = false;
			}
		}
		if(min == Integer.MAX_VALUE) min = 0;
		return min; 
	}

	private static boolean checkOneWord(String string, String begin) {
		int count = 0;
		for (int i = 0; i < string.length(); i++) {
			if(string.charAt(i) == begin.charAt(i)) {
				count++;
			}
		}
		
		return count == string.length() - 1 ? true : false;
	}

}
