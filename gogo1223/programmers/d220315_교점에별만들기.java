package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class d220315_교점에별만들기 {

	public static void main(String[] args) {
		int[][] line = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};
		 String[] ans = solution(line);
		 for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}
//	Ax + By + E = 0
//	Cx + Dy + F = 0
//	x = (BF - ED)/(AD - BC), y = (EC - AF)/(AD - BC)
	private static String[] solution(int[][] line) {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE,
				maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
		for (int i = 0; i < line.length - 1; i++) {
			for (int j = i+1; j < line.length; j++) {
				//평행 여부 판별
				int AD_BC = line[i][0]*line[j][1] - line[i][1]*line[j][0];
				if(AD_BC == 0) continue;
				//정수 여부 판별
				int BF_ED = line[i][1]*line[j][2] - line[i][2]*line[j][1];
				int EC_AF = line[i][2]*line[j][0] - line[i][0]*line[j][2];
				if(BF_ED % AD_BC != 0 || EC_AF % AD_BC != 0) continue;
				
				int x = BF_ED / AD_BC;
				int y = EC_AF / AD_BC;
				//x, y 순서쌍 저장
				if(map.containsKey(y)) {
					if(map.get(y).contains(x)) continue;
					else map.get(y).add(x);
				}else {
					map.put(y, new ArrayList<>());
					map.get(y).add(x);
				}
				minX = Math.min(minX, x);
				minY = Math.min(minY, y);
				maxX = Math.max(maxX, x);
				maxY = Math.max(maxY, y);
			}
		}
		String[] answer = new String[maxY - minY + 1];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < maxX - minX + 1; i++) {
			sb.append(".");
		}
		Arrays.fill(answer, sb.toString());
		for(Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
			int ny = maxY - entry.getKey();
			for (int i = 0; i < entry.getValue().size(); i++) {
				int nx = entry.getValue().get(i) - minX;
				answer[ny] = answer[ny].substring(0, nx) + "*" + answer[ny].substring(nx+1);
			}
		}
		return answer;
	}

}
