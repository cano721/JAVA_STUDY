import java.util.*;

class PG_전화번호목록 {
	public boolean solution(String[] phone_book) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < phone_book.length; i++)
			map.put(phone_book[i], i);

		boolean chk = true;
		for (int i = 0; i < phone_book.length; i++) {
			for (int j = 1; j < phone_book[i].length(); j++) {
				String q = phone_book[i].substring(0, j);
				if (map.containsKey(q))
					chk = false;
			}
		}
		return chk;
	}
}