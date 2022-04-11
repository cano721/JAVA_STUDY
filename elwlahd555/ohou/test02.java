package elwlahd555.ohou;

import java.util.HashMap;
import java.util.LinkedList;

public class test02 {
	public static void main(String[] args) {
		String call= "abcabcdefabc";
		System.out.println(solution(call));
	}

	private static String solution(String call) {
		int max=0;
		int stringsize=0;
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		for (int i = 0; i < call.length(); i++) {
			for (int j = i+1; j <= call.length(); j++) {
				String temp=call.substring(i, j);
				if(map.containsKey(temp)) {
					map.replace(temp, map.get(temp)+1);
					if(max<map.get(temp)) {
						max=map.get(temp);
						stringsize=temp.length();
					}else if(max==map.get(temp)&&stringsize<temp.length()) {
						stringsize=temp.length();
					}
				}else {
					map.put(temp, 1);
				}
			}
		}
		LinkedList<String> maxString=new LinkedList<String>();
		LinkedList<String> result=new LinkedList<String>();
		for (String s : map.keySet()) {
			if(map.get(s)==max&&stringsize==s.length()) {
				maxString.add(s);
				result.add(s);
			}
		}
		while(!maxString.isEmpty()) {
			String temps=maxString.poll();
			for (int i = 0; i < temps.length(); i++) {
				String temptemp="";
				if(temps.charAt(i)!=temps.toUpperCase().charAt(i)) {

				}
			}
		}
		int size=maxString.size();
		for (int i = 0; i < size; i++) {
			call=call.replaceAll(maxString.poll(), "-");
		}
		call=call.replaceAll("-", "");
		return call;
	}
}
