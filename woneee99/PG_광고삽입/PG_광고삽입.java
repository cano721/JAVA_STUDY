class PG_광고삽입 {
    public String solution(String play_time, String adv_time, String[] logs) {
		int ptime = cal(play_time);
		int adtime = cal(adv_time);

		long []csum = new long[ptime+1];
		for(String log : logs) {
			String []tmp = log.split("-");
			int start = cal(tmp[0]);
			int end = cal(tmp[1]);
			csum[start]++;
			csum[end]--;
		}
		
		
		for(int i=1; i<=ptime; i++) csum[i] += csum[i-1]; //i초에서 재생중인 횟수
		for(int i=1; i<=ptime; i++) csum[i] += csum[i-1]; //i초까지 누적 재생시간
		
		long max = csum[adtime-1];
		
		int idx = 0;
		for(int i=0; i+adtime<=ptime; i++) {
			long tmp = csum[i+adtime] - csum[i];
			if(tmp > max) {
				max = tmp;
				idx = i+1;
			}
		}
		int hour = idx / 3600;
		idx = idx % 3600;
		int min = idx / 60;
		idx = idx % 60;
		return String.format("%02d:%02d:%02d", hour,min,idx);

	}
    int cal(String time) {
		String []trans = time.split(":");
        int t =0;
		t += Integer.parseInt(trans[0])*3600;
		t += Integer.parseInt(trans[1]) * 60;
		t += Integer.parseInt(trans[2]);
		return t;
	}
}