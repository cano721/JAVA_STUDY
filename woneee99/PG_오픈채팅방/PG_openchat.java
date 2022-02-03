import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
class PG_openchat {
    public String[] solution(String[] record) {
		Map<String, String> map = new HashMap<>();
		
		String []arr = new String[3];
		for(int i=0; i<record.length; i++) {
			arr = record[i].split(" ");
			if(arr[0].equals("Change")) {
				map.replace(arr[1], arr[2]);
			}
			if(arr[0].equals("Enter")) {
				if(map.containsKey(arr[1])) {
					map.replace(arr[1], arr[2]);
				}
				else {
					map.put(arr[1], arr[2]);
				}
			}
		}
		ArrayList<String> y = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<record.length; i++) {
			arr = record[i].split(" ");
			String res = map.get(arr[1]);
			
			if(arr[0].equals("Enter")){
				y.add(res + "님이 들어왔습니다.");
			}
			else if(arr[0].equals("Leave")) {
				y.add(res + "님이 나갔습니다.");
			}
		}
        String []result = new String[y.size()];
		for(int i=0; i<y.size(); i++) {
			result[i] = y.get(i);
		}
		return result; 
    }
}