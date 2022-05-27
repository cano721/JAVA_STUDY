package studyGroup.may.may26;

public class 풍선터뜨리기 {

    public static void main(String[] args) {

        int[] a = {9,-1,5};
        System.out.println(solution(a));

    }

    public static int solution(int[] a) {
        int answer = 0;

        int n = a.length;

        if(n == 1)
            return 1;

        int[] left = new int[n];
        int[] right = new int[n];

        int lnum = a[0];
        int rnum = a[n - 1];

        left[0] = a[0];
        right[n-1] = a[n-1];

        for(int i = 1; i < n; i++)
        {
            if(lnum > a[i]) lnum = a[i];
            left[i] = lnum;
        }

        for(int i = n-2; i >= 0; i--)
        {
            if(rnum > a[i]) rnum = a[i];
            right[i] = rnum;
        }

        // System.out.println(Arrays.toString(left));
        // System.out.println(Arrays.toString(right));

        for(int i = 0; i < n; i++)
        {
            if(a[i] > left[i] && a[i] > right[i]) continue;
            else
                answer += 1;
        }




        return answer;
    }


}
