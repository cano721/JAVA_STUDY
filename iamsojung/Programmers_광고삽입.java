class 광고삽입 {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = strToSecond(play_time);
        int advTime = strToSecond(adv_time);
        int[] counts = new int[playTime + 1];

        for (String log : logs) {
            String[] splits = log.split("-");
            int startViewTime = strToSecond(splits[0]);
            int endViewTime = strToSecond(splits[1]);

            for (int i = startViewTime; i < endViewTime; i++) {
                counts[i]++;
            }
        }

        int startTime = 0;
        int endTime = advTime;
        long sum = 0;
        for (int i = startTime; i < endTime; i++) {
            sum += counts[i];
        }

        long max = sum;
        int maxStartTime = 0;
        while (endTime <= playTime) {
            sum -= counts[startTime];
            sum += counts[endTime];
            if(sum > max) {
                max = sum;
                maxStartTime = startTime + 1;
            }
            startTime++;
            endTime++;
        }
        return secondToStr(maxStartTime);
    }

    static int strToSecond(String str) {
        String[] split = str.split(":");
        return Integer.parseInt(split[0]) * 60 * 60 + Integer.parseInt(split[1]) * 60 + Integer.parseInt(split[2]);
    }

    static String secondToStr(int time) {
        int hour = time / 3600;
        time %= 3600;
        int minute = time / 60;
        int second = time % 60;

        String strHour = hour > 9 ?  String.valueOf(hour) : "0" + hour;
        String strMinute = minute > 9 ?  String.valueOf(minute) : "0" + minute;
        String strSecond = second > 9 ?  String.valueOf(second) : "0" + second;

        return String.join(":", strHour, strMinute, strSecond);
    }
}