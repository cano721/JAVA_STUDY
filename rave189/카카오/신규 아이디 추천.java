package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	/**
	 * �ű� �̿����� ���̵� �־����� ������ ���� ������ ���� �ű� ���̵� ��õ���ַ��� �Ѵ�.
	 * ������ ���� ���ڿ��� ����ϴ� ����
	 * 1�ܰ� new_id�� ��� �빮�ڸ� �����Ǵ� �ҹ��ڷ� ġȯ�մϴ�.
	 * 2�ܰ� new_id���� ���ĺ� �ҹ���, ����, ����(-), ����(_), ��ħǥ(.)�� ������ ��� ���ڸ� �����մϴ�.
	 * 3�ܰ� new_id���� ��ħǥ(.)�� 2�� �̻� ���ӵ� �κ��� �ϳ��� ��ħǥ(.)�� ġȯ�մϴ�.
	 * 4�ܰ� new_id���� ��ħǥ(.)�� ó���̳� ���� ��ġ�Ѵٸ� �����մϴ�.
	 * 5�ܰ� new_id�� �� ���ڿ��̶��, new_id�� "a"�� �����մϴ�.
	 * 6�ܰ� new_id�� ���̰� 16�� �̻��̸�, new_id�� ù 15���� ���ڸ� ������ ������ ���ڵ��� ��� �����մϴ�.
	 * 	���� ���� �� ��ħǥ(.)�� new_id�� ���� ��ġ�Ѵٸ� ���� ��ġ�� ��ħǥ(.) ���ڸ� �����մϴ�.
	 * 7�ܰ� new_id�� ���̰� 2�� ���϶��, new_id�� ������ ���ڸ� new_id�� ���̰� 3�� �� ������ �ݺ��ؼ� ���� ���Դϴ�.
	 * 
	 * ����ǥ�������� ���� ��� ������ ǥ���� �� �ִ�.
	 * @param new_id �Է� ���ڿ�
	 * @return ��� ������ ������ ���ڿ�
	 */
	public String solution(String new_id) {
		new_id = new_id.toLowerCase();
		new_id = removeInValidChar(new_id);
		new_id = new_id.replaceAll("\\.{2,}", "\\.");
		new_id = removeFirstCloseChar(new_id);
		new_id = removeLastCloseChar(new_id);
		if (new_id.equals(""))
			new_id = "a";
		if (new_id.length() >= 16)
			new_id = new_id.substring(0, 15);
		new_id = removeLastCloseChar(new_id);
		if (new_id.length() <= 2) {
			char lastCh = new_id.charAt(new_id.length() - 1);
			while (new_id.length() < 3)
				new_id += lastCh;
		}
		return new_id;
	}

	public String removeInValidChar(String id) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < id.length(); i++) {
			char ch = id.charAt(i);
			if (isValid(ch))
				result.append(ch);
		}
		return result.toString();
	}

	public boolean isValid(char ch) {
		return ch == '-' || ch == '_' || ch == '.' || ('a' <= ch && ch <= 'z') || ('0' <= ch && ch <= '9');
	}

	public String removeFirstCloseChar(String id) {
		if (id.length() > 0 && id.charAt(0) == '.')
			return id.substring(1);
		return id;
	}

	public String removeLastCloseChar(String id) {
		if (id.length() > 0 && id.charAt(id.length() - 1) == '.')
			return id.substring(0, id.length() - 1);
		return id;
	}
}