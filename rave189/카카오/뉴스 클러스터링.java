package Programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
    
	/**
	 * �� ���ڿ��� �־�����.
	 * �� ���ڿ��� �� ���ھ� ��� �ϳ��� ���ҷ� �����.
	 * ������� �� ���տ� ���ؼ� ������/������ * 65536�� ���ϴ� ����
	 * �� ������ �������� ��� ������/������ = 1�� ����Ѵ�.
	 * ������ ���Ҵ� ���ĺ��� �����ϴ�. (����, Ư�� ���� ���� ��� �����̴�.)
	 * AA, Aa, aa ���� ��� ���� �����̴�.
	 * @param str1 ù ��° ���ڿ�
	 * @param str2 �� ��° ���ڿ�
	 * @return ������/������ * 65536
	 */
    public int solution(String str1, String str2) {
        int cross = 0;
        int union = 0;
        Map<String, Integer> hash1 = new HashMap<>();
        Map<String, Integer> hash2 = new HashMap<>();
        makeHash(str1.toLowerCase(), hash1);
        makeHash(str2.toLowerCase(), hash2);
        if(hash1.isEmpty() && hash2.isEmpty())
            return 65536;
        ArrayList<String> removeList = new ArrayList<>();
        for(String key: hash2.keySet()) {
            if(hash1.containsKey(key)) {
                int aCnt = hash1.get(key);
                int bCnt = hash2.get(key);
                cross += Math.min(aCnt, bCnt);
                union += Math.max(aCnt, bCnt);
                removeList.add(key);
            } else {
                hash1.put(key, hash2.get(key));
            }
        }
        for(String key: removeList) {
            hash1.remove(key);
        }
        for(String key: hash1.keySet()) {
            union += hash1.get(key);
        }
        return (int)(65536 * (cross/(double)union));
    }
    
    public void makeHash(String s, Map<String, Integer> hash) {
        for(int i=0; i<s.length()-1; i++) {
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(i+1);
            if(isAlphabet(ch1) && isAlphabet(ch2)) {
                String element = ch1+""+ch2;
                hash.put(element, hash.getOrDefault(element, 0) + 1);
            }
        }
    }
    
    public boolean isAlphabet(char ch) {
        return 'a' <= ch && ch <= 'z';
    }
}