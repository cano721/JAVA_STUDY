package programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class d220212_불량사용자 {

	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "abc1**"};
		int answer = solution(user_id, banned_id);
		System.out.println(answer);

	}
	private static int bannedIdSize;
    private static HashSet<HashSet<String>> result = new HashSet<>();
    
    public static int solution(String[] user_id, String[] banned_id) {
        bannedIdSize = banned_id.length;
        int answer = 0;
        
        List<Set<String>> matchedIdList = new ArrayList<>();
        
        for (String bannedId : banned_id) {
            HashSet<String> userSet = new HashSet<>();
            for (String userId : user_id) {
                if (Pattern.matches(bannedId.replace("*", "."), userId)) {
                    userSet.add(userId);
                }
            }
            
            matchedIdList.add(userSet);
        }
        
        int index = 0;
        dfs(index, matchedIdList, new HashSet<>());
        answer = result.size();
        
        return answer;
    }
    
    public static void dfs(int index, List<Set<String>> matchedIdList, HashSet<String> userSet) {
        if (userSet.size() == bannedIdSize) {
            result.add(new HashSet<>(userSet));
            
            return;
        }
        
        for (String userId : matchedIdList.get(index)) {
            if (!userSet.contains(userId)) {
                userSet.add(userId);
                dfs(index + 1, matchedIdList, userSet);
                userSet.remove(userId);
            }
        }
    }
    
}
