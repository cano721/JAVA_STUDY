import java.util.ArrayList;

public class PG_기능개발 {

	public static void main(String[] args) {
		int[] progresses = { 93, 30, 55 };
		int[] speeds = { 1, 30, 5 };

		int[] day = new int[progresses.length];
		for (int i = 0; i < progresses.length; i++) {
			day[i] = (100 - progresses[i]) / speeds[i];
			if ((100 - progresses[i]) % speeds[i] != 0)
				day[i] += 1;
			// day[i] = (int)Math.ceil((100-progresses[i])/(double)speeds[i]);
		}

		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < progresses.length; i++) {
			int k = 1;
			int x = day[i];
			while (i + 1 < progresses.length && day[i + 1] < x) {
				k++;
				i++;
			}
			arr.add(k);
		}
		int[] answer = new int[arr.size()];
		for (int i = 0; i < arr.size(); i++) {
			answer[i] = arr.get(i);
		}
	}

}
