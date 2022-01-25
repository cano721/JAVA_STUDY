// /**
//     문자열 파싱을 어떻게 할 것인가?
    
//     문자열을 포문을 돌면서 쌓기
//     1. 문자가 나오면 앞에 숫자를 인트형으로 변환
    
//     2. 문자에따른 곱하기 진행
    
//     3. 배열에 담기
//     3-1) 배열 인덱스를 만들어두고 증가하는 형태
//     3-2) 담은후엔 임시 문자열 초기화
    
//     4. * 이나 #이 나오면 해당하는 배열원소를 변경
//     4-1) 현재 배열 인덱스에따른 처리
    
//     5. 최종 배열 합계 출력
    
    


// **/
// class Solution {
//     public int solution(String dartResult) {
//         int answer = 0;
        
//         String[] arr2 = {"string","s22"};
//         String arr3 = arr2[1];

//         int[] arr = new int[3];
//         String str = "";

//         int idx = 0;
//         for(int i = 0; i < dartResult.length(); i++){
//             // 스타상
//             if(dartResult.charAt(i) == '*'){
//                 if(idx == 1){
//                     arr[idx-1] *= 2;
//                 }else{
//                     arr[idx-1] *= 2;
//                     arr[idx-2] *= 2;
//                 }
//             // 아차상
//             }else if(dartResult.charAt(i) == '#'){
//                 arr[idx-1] *= -1;
//             // 숫자일 경우
//             }else if(dartResult.charAt(i) < 'A'){
//                 str += String.valueOf(dartResult.charAt(i) -'0');
//             // 문자
//             }else{
//                 int score = Integer.parseInt(str);
//                 if(dartResult.charAt(i) == 'S'){
//                     arr[idx++] = score;
//                 }else if(dartResult.charAt(i) == 'D'){
//                     arr[idx++] = (int)Math.pow(score,2);
//                 }else{
//                     arr[idx++] = (int)Math.pow(score,3);
//                 }
//                 // 초기화
//                 str = "";
//             }
//         }
        
//         for(int v: arr){
//             answer += v;
//         }
//         return answer;
//     }
// }