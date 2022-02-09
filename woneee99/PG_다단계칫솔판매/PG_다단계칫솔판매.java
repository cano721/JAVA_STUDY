import java.util.HashMap;

class PG_다단계칫솔판매 {
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		HashMap<String, Person> people = new HashMap<>();

		int[] answer = new int[enroll.length];

		for (int i = 0; i < enroll.length; i++) {
			people.put(enroll[i], new Person(enroll[i], null, 0));
		}

		for (int i = 0; i < enroll.length; i++) {
			if (referral[i].equals("-"))
				continue;
			people.get(enroll[i]).parent = people.get(referral[i]);
		}
		for (int i = 0; i < seller.length; i++) {
			people.get(seller[i]).Cal(amount[i] * 100);
		}
		for (int i = 0; i < enroll.length; i++) {
			answer[i] = people.get(enroll[i]).profit;
		}
		return answer;
	}
}

class Person {
	String name;
	Person parent;
	int profit;

	public Person(String name, Person parent, int profit) {
		this.name = name;
		this.parent = parent;
		this.profit = profit;
	}

	public void Cal(int profit) {
		int parentfit = profit / 10;
		this.profit += profit - parentfit;
		if (parentfit != 0 && this.parent != null) {
			this.parent.Cal(parentfit);
		}
	}
}