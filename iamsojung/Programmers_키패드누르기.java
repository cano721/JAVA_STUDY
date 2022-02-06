import java.util.*;

class Solution {
    static public String solution(int[] numbers, String hand) {

        String answer = "";
        StringBuilder sb = new StringBuilder();
        HashSet<Integer> rightHs = new HashSet<>();
        HashSet<Integer> leftHs = new HashSet<>();

        rightHs.add(3);
        rightHs.add(6);
        rightHs.add(9);

        leftHs.add(1);
        leftHs.add(4);
        leftHs.add(7);

        int left = 10;
        int right = 11;

        int[] value = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] x = {3,0, 0, 0,1, 1, 1, 2, 2, 2,3,3};
        int[] y = {1,0, 1, 2, 0, 1, 2, 0, 1, 2,0,2};

        for (int i = 0; i < numbers.length; i++) {

            //System.out.println(i+" "+numbers[i] + "현재 left : " + left + " 현재 right : " + right);
            //System.out.println();
            if (rightHs.contains(numbers[i])) {
                sb.append("R");
                right = numbers[i];

            } else if (leftHs.contains(numbers[i])) {
                sb.append("L");
                left = numbers[i];

            } else {

//                if(left-1<0)
//                    left = left+1;
//                if(right-1<0){
//                   right = right+1;
//                }
                int tempX = x[left];
                int tempY = y[left];

                int temp1X = x[right];
                int temp1Y = y[right];


                int diffL = Math.abs(tempX - x[numbers[i]]) + Math.abs(tempY - y[numbers[i]]);
                //System.out.println(tempX+" "+tempY+"            "+x[numbers[i]]+" "+y[numbers[i]]+" "+diffL);

                int diffR = Math.abs(temp1X - x[numbers[i]]) + Math.abs(temp1Y - y[numbers[i]]);
                //System.out.println(temp1X+" "+temp1Y+"            "+x[numbers[i]]+" "+y[numbers[i]]+" "+diffR);

                //System.out.println(tempX+" "+tempY+" "+diffL+"            "+temp1X+" "+temp1Y+" "+diffR);
                if (diffL > diffR) {
                    right = numbers[i];
                    sb.append("R");
                } else if (diffL < diffR) {
                    left = numbers[i];
                    sb.append("L");
                } else if (diffL == diffR) {
                    if (hand.equals("right")) {
                        right = numbers[i];
                        sb.append("R");
                    } else if(hand.equals("left")){
                        left = numbers[i];
                        sb.append("L");
                    }

                }
                //System.out.println(i+" "+numbers[i] + "현재 left : " + left + " 현재 right : " + right + "  " + diffL + " " + diffR);
            }

        }

        answer = sb.toString();
        //System.out.println(answer);

        return answer;
    }
}