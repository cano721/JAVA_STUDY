package Feburary;


import java.util.ArrayList;
import java.util.HashMap;

public class Programmers_오픈채팅방 {

    public static void main(String[] args) {

        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        solution(record);
    }

    static public String[] solution(String[] record) {
        String[] answer = {};
        ArrayList<Point> arr = new ArrayList<>();
        HashMap<String, String> hm = new HashMap<>();
        for (int i = 0; i < record.length; i++) {
            String[] temp = record[i].split(" ");
            if (temp[0].equals("Enter")) {
                hm.put(temp[1], temp[2]);
                arr.add(new Point(temp[1],"Enter"));
            } else if (temp[0].equals("Leave")) {
                //hm.remove(temp[1]);
                arr.add(new Point(temp[1],"Leave"));
            } else {
                hm.put(temp[1], temp[2]);
            }
        }
        answer = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).message.equals("Enter")){
               answer[i] = hm.get(arr.get(i).uid)+"님이 "+"들어왔습니다.";
            }else if(arr.get(i).message.equals("Leave")){
                answer[i] = hm.get(arr.get(i).uid)+"님이 "+"나갔습니다.";
            }
        }
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);

        }

        return answer;
    }
    static class Point{
        String uid;
        String message;

        Point(String uid, String message){
            this.uid = uid;
            this.message = message;
        }

    }
}
