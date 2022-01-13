package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 어떤 네트워크에 속해 있는 N개의 IP주소가 주어진다.
 * 이를 참고하여 서브넷 마스크를 구하고 그때의 가장 크기가 작은 IP 주소를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static final int BIT_SIZE = 8;

	/**
	 * 서브넷 마스크는 할당된 ip의 개수보다 크거나 같은 2^n-1을 255.255.255.255에서 빼주는 것이다.
	 * 할당된 ip가 16~31까지는 2^5-1에 속하고, 32~63까지는 2^6-1에 포함된다.
	 * 따라서 서브넷 마스크는 255.255.255.224, 255.255.255.192로 알 수 있다.
	 * 따라서 가장 작은 값과 가장 큰 값을 오른쪽으로 1비트씩 옮기면서 두 수가 같아지는 지점까지의 횟수를 센다.
	 * 2^횟수-1을 255.255.255.255에서 뺀 값으로 서브넷 마스크를 구하고 모든 ip가 서브넷 마스크에 포함되므로
	 * 아무 ip나 서브넷 마스크와 and연산을 하면 가장 작은 값이 나오게 된다.
	 * 
	 * 이때, long을 ip주소로 변환할때 0과 같이 32비트보다 작은 수로 표현될 경우 변환에 오류가 생기므로
	 * long을 32비트 binary로 표현한 뒤 변환한다.
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