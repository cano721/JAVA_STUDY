package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * � ��Ʈ��ũ�� ���� �ִ� N���� IP�ּҰ� �־�����.
 * �̸� �����Ͽ� ����� ����ũ�� ���ϰ� �׶��� ���� ũ�Ⱑ ���� IP �ּҸ� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static final int BIT_SIZE = 8;

	/**
	 * ����� ����ũ�� �Ҵ�� ip�� �������� ũ�ų� ���� 2^n-1�� 255.255.255.255���� ���ִ� ���̴�.
	 * �Ҵ�� ip�� 16~31������ 2^5-1�� ���ϰ�, 32~63������ 2^6-1�� ���Եȴ�.
	 * ���� ����� ����ũ�� 255.255.255.224, 255.255.255.192�� �� �� �ִ�.
	 * ���� ���� ���� ���� ���� ū ���� ���������� 1��Ʈ�� �ű�鼭 �� ���� �������� ���������� Ƚ���� ����.
	 * 2^Ƚ��-1�� 255.255.255.255���� �� ������ ����� ����ũ�� ���ϰ� ��� ip�� ����� ����ũ�� ���ԵǹǷ�
	 * �ƹ� ip�� ����� ����ũ�� and������ �ϸ� ���� ���� ���� ������ �ȴ�.
	 * 
	 * �̶�, long�� ip�ּҷ� ��ȯ�Ҷ� 0�� ���� 32��Ʈ���� ���� ���� ǥ���� ��� ��ȯ�� ������ ����Ƿ�
	 * long�� 32��Ʈ binary�� ǥ���� �� ��ȯ�Ѵ�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long min = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		while (n-- > 0) {
			long ip = binaryToLong(br.readLine());
			min = Math.min(min, ip);
			max = Math.max(max, ip);
		}
		int count = 0;
		long minIp = min;
		while (min != max) {
			min >>= 1;
			max >>= 1;
			count++;
		}
		long subnet = ((long) Integer.MAX_VALUE << 1) + 1;
		subnet -= Math.pow(2, count) - 1;
		minIp &= subnet;
		System.out.println(longToIp(minIp));
		System.out.println(longToIp(subnet));
	}

	public static long binaryToLong(String str) {
		String[] split = str.split("\\.");
		long bit = 0;
		for (int i = 0; i < split.length; i++) {
			bit <<= BIT_SIZE;
			bit |= Integer.parseInt(split[i]);
		}
		return bit;
	}

	public static String longToIp(long n) {
		String binaryNum = fillZero(Long.toBinaryString(n));
		StringBuilder ip = new StringBuilder();
		for (int i = 0; i + BIT_SIZE <= binaryNum.length(); i += BIT_SIZE) {
			ip.append(Integer.parseInt(binaryNum.substring(i, i + BIT_SIZE), 2));
			if (i + BIT_SIZE < binaryNum.length())
				ip.append('.');
		}
		return ip.toString();
	}

	public static String fillZero(String str) {
		for (int i = str.length(); i < BIT_SIZE * 4; i++)
			str = "0" + str;
		return str;
	}
}