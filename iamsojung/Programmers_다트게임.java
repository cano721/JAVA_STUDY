class Solution {
    public int solution(String dartResult) {

		int answer = 0;

		int[] arr = new int[3];
		int idx = 0;

		String[] str = dartResult.split("");

		int temp = 0;

		for(int i=1;i<dartResult.length();i++) {

			System.out.println("--"+str[i]);
			if(str[i].equals("0")&&str[i-1].equals("1")) {
				str[i]="10";
			}

			if(str[i].equals("S")) {
				temp =Integer.parseInt(str[i-1]);
				arr[idx]=temp;
				idx++;
			}else if(str[i].equals("D")) {
				temp =Integer.parseInt(str[i-1])*Integer.parseInt(str[i-1]);
				arr[idx]=temp;
				idx++;
			}else if(str[i].equals("T")) {
				temp =Integer.parseInt(str[i-1])*Integer.parseInt(str[i-1])*Integer.parseInt(str[i-1]);
				arr[idx]=temp;
				idx++;
			}else if(str[i].equals("*")) {
				idx--;
				if(idx>0) {
					arr[idx] = arr[idx]*2;
					arr[idx-1]=arr[idx-1]*2;
				}else {
					arr[idx] = arr[idx]*2;
				}
				idx++;
				i++;
			}else if(str[i].equals("#")) {
				idx--;
				arr[idx]=arr[idx]-arr[idx]*2;
				idx++;
				i++;
			}

		}
		for(int i=0;i<3;i++) {
			answer = answer+arr[i];
		}
		return answer;
	}
}