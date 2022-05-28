import java.util.*;

class Solution {
    public static long[] solution(long k, long[] room_number) {
        long[] ans = new long[room_number.length];
        HashMap<Long, Long> map = new HashMap<>();

        for(int i=0; i<room_number.length; i++) {
            ArrayList<Long> list = new ArrayList<>();
            long val = room_number[i];

            if(!map.containsKey(val)) {
                map.put(val, val+1);
                ans[i] = val;
            }
            else {

                long parent = map.get(val);
                list.add(val);
                while(true) {

                    if(!map.containsKey(parent)) {
                        map.put(parent, parent+1);  
                        ans[i] = parent;
                        break;
                    }
                    else {
                        list.add(parent); 
                        parent = map.get(parent);
                    }
                }
                for(long l : list)
                    map.put(l, parent+1);
            }
        }
        return ans;
    }
}
