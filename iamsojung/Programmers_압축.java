
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

    public class Programmers_압축 {
        static HashMap<String, Integer> hm = new HashMap();
        static HashSet<String> hs = new HashSet<>();
        static int index_alpht = 27;

        public int[] solution(String msg) {
            int[] answer;
            ArrayList<Integer> ansArr = new ArrayList<>();
            addHM();
            int index = 1;

            boolean flag = false;
            while (!msg.equals("")) {
                if (msg.length() == 1) {
                    ansArr.add(hm.get(msg));
                    break;
                }

                String str = msg.substring(0, index);

                if (hm.containsKey(str)) {
                    if (!hs.contains(str)) {
                        ansArr.add(hm.get(str));
                        hs.add(str);
                    }

                    index++;
                } else {
                    hm.put(str, index_alpht);
                    index_alpht++;
                    index--;
                    if(index>=0){
                        msg = msg.substring(index);
                    }else{
                        break;
                    }

                }
                if (index > msg.length() ) {
                    index = 0;

                }
            }
            answer = new int[ansArr.size()];
            for (int i = 0; i < ansArr.size(); i++) {
                answer[i] = ansArr.get(i);
            }

            return answer;
        }

        private static void addHM() {
            char a = 'A';
            for (int i = 0; i < 26; i++) {
                String str = Character.toString(a + i);
                hm.put(str, (i + 1));
            }
        }

        static class Point {
            String alphabet;
            int loc;

            Point(String alphabet, int loc) {
                this.alphabet = alphabet;
                this.loc = loc;
            }
        }


}
