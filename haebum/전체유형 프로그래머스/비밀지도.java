// /**
//     1. 비트마스크
    
//     2. 배열 풀이
    
//     비트마스크 풀이
    
//     1) 지도1과 지도2를 배열에 저장
    
//     2) 두개의 합집합을 해서 새로운 배열에 담기
    
//     3) 배열의 원소를 5자리 바이너리로 뽑아서 # 또는 공백 출력
//     3-1) 모자른 자리수 만큼 앞에 공백 집어넣기
// **/

// class Solution {
//     public String[] solution(int n, int[] arr1, int[] arr2) {
//         String[] answer = new String[n];
//         int[] map = new int[n];
        
//         // 합집합
//         for(int i = 0; i < n; i++){
//             map[i] = arr1[i] | arr2[i];
//         }
        
//         // 맵 돌면서 출력문 만들기
//         for(int i = 0; i < n; i++){
//             int num = map[i];
//             String str = "";
//             // 이진수 뽑기
//             String code = Integer.toBinaryString(num);
//             // 자리수 맞추기
//             for(int j = 0; j < (n-code.length()); j++){
//                 str += " ";
//             }
//             // 이진수 해당 문자로 변경
//             for(int j = 0; j < code.length(); j++){
//                 str += (code.charAt(j) == '1') ? "#" :" ";
//             }
//             answer[i] = str;
//         }
//         return answer;
//     }
// }