/**
    1. 문자열을 파싱
    1-1) 헤더는 숫자를 만나기전은 헤더로 넣기
    1-2) 숫자는 최대 5글자까진 number로 넣기
    1-3) 나머진 tail로 넣기
    
    2. 파싱 문자열 정렬
    2-1) head는 대소문자 없이 문자 비교
    2-2) 숫자비교
    2-3) 들어온 순서대로 정렬
    
    3. 정렬된 순으로, 다시 원본 문자열로 합치기
**/
import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        ArrayList<File> arrList = new ArrayList<>();
        
        for(int i = 0; i < files.length; i++){
            String file = files[i];
            File f = parsing(file);
            arrList.add(f);
        }
        
        arrList.sort(Comparator.comparing(File::getHead).thenComparing(File::getNumber));
        
//         Collections.sort(arrList, new Comparator<File>(){
           
//             @Override
//             public int compare(File o1, File o2){
//                 if(o1.head.toUpperCase().compareTo(o2.head.toUpperCase()) == 0){
//                     return Integer.parseInt(o1.number) - Integer.parseInt(o2.number);
//                 }else{
//                     return o1.head.toUpperCase().compareTo(o2.head.toUpperCase());
//                 }
//             }
//         });
        
        String[] answer = new String[arrList.size()];
        
        for(int i = 0; i < arrList.size(); i ++){
            File f = arrList.get(i);
            String str = f.head + f.number + f.tail;
            answer[i] = str;
        }
        return answer;
    }
    
    public File parsing(String file){
        String head = "";
        String number = "";
        String tail = "";
        
        boolean check = false;
        int ncnt = 0;
        for(int i = 0; i < file.length(); i++){
            char c = file.charAt(i);
            // 처음 숫자를 만나면 앞은 헤더로 넣기
            // 숫자는 바로 number에 넣기
            if(check == false && Character.isDigit(c)){
                head = file.substring(0,i);
                number += String.valueOf(c);
                ncnt++;
                check = true;
            // 그 외 숫자는 최대 5개 들어갈 수 있음
            }else if(ncnt < 5 && Character.isDigit(c)){
                number += String.valueOf(c);
                ncnt++;
            //숫자부분이 지났는데 문자가 들어오면 그후는 꼬리
            }else if(ncnt >= 5 || check == true && !Character.isDigit(c)){
                tail = file.substring(i,file.length());
                break;
            }
        }
        
        return new File(head,number,tail);
    }
    
    public class File{
        String head;
        String number;
        String tail;
        
        public File(String head, String number,String tail){
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
        
        public String getHead() {
            return head.toUpperCase();
        }
        
        public int getNumber(){
            return Integer.parseInt(number);
        }

        @Override
        public String toString() {
            return head + number + tail;
        }
    }
}