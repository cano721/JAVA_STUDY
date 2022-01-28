import java.util.*

// 프로그래머스 파일명 정렬 문제
static class FileName implements Comparable<FileName>{
    String head;
    String number;
    String tail;

    public String getFileName() {
        return head + number + tail;
    }

    public FileName(String name) {
        StringBuilder headBuilder = new StringBuilder();
        char[] chars = name.toCharArray();
        int idx = 0;
        while(idx < chars.length && (chars[idx] - '0' < 0 || chars[idx] - '0' > 9)) {
            headBuilder.append(chars[idx]);
            idx++;
        }
        head = headBuilder.toString();


        StringBuilder numberBuilder = new StringBuilder();
        while(idx < chars.length && chars[idx] - '0' >= 0 && chars[idx] - '0' <= 9) {
            numberBuilder.append(chars[idx]);
            idx++;
        }
        number = numberBuilder.toString();
        tail = name.substring(idx);
    }

    @Override
    public int compareTo(FileName o) {
        if(!head.equalsIgnoreCase(o.head)) {
            return head.compareToIgnoreCase(o.head);
        }
        return Integer.compare(Integer.parseInt(number), Integer.parseInt(o.number));
    }
}

public class Solution {
    public String[] solution(String[] files) {
        List<FileName> fileNames = new ArrayList<>();
        String[] answer = new String[files.length];
        for (String fileName : files) {
            fileNames.add(new FileName(fileName));
        }

        Collections.sort(fileNames);

        for(int i = 0; i < answer.length; i++) {
            answer[i] = fileNames.get(i).getFileName();
        }
        return answer;
    }
}