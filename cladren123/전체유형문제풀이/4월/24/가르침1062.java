package studyGroup.april.april24;

import java.util.*;
import java.lang.*;
import java.io.*;

/*
anta ~ tica

a : 0
c : 2
i : 8
n : 13
t : 19

 */

public class 가르침1062 {

    static int n; // 단어의 개수
    static int k; // 가르치는 글자
    static String[] words;

    static ArrayList<Integer> gibon;

    static int m;
    static ArrayList<Integer> store; // 단어저장

    static int answer;

    static HashMap<Integer, HashSet<Integer>> wordsLetter;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        words = new String[n];

        answer = 0;
        wordsLetter = new HashMap<>();

        for(int i = 0; i < n; i++)
        {
            words[i] = br.readLine();
        }

        // 글자를 5개 미만으로 선택하면 기본 단어들을 충족 못하므로 0을 리턴
        if(k < 5)
        {
            System.out.println(answer);
            return;
        }

        m = k - 5;

        store = new ArrayList<>();

        gibon = new ArrayList<>();
        gibon.add(0); // a
        gibon.add(2); // c
        gibon.add(8); // i
        gibon.add(13); // n
        gibon.add(19); // t

        for(int i = 0; i < words.length; i++)
        {
            letterCheck(i, words[i]);
        }

        select(0, 0);

        System.out.println(answer);

    }

    // 가르칠 문자를 고르는 함수
    public static void select(int stage, int start)
    {
        if(stage == m)
        {
            answerCheck();
            return;
        }

        for(int i = start; i < 26; i++)
        {
            // 기본에 포함되어 있으면 생략
            if(gibon.contains(i)) {
                continue;
            }
            store.add(i);
            select(stage+1, i+1);
            store.remove(store.size()-1);
        }
    }

    public static void answerCheck()
    {
        int count = 0;

        for(int i : wordsLetter.keySet())
        {
            HashSet<Integer> integers = wordsLetter.get(i);
            int flag = 0;
            for(Integer one : integers)
            {
                if(!store.contains(one))
                {
                    flag = 1;
                    break;
                }
            }
            if(flag == 0)
            {
                count += 1;
            }

        }

        answer = Math.max(answer, count);

    }

    // 문자열에서 어떤 문자가 쓰였는지 알아보는 함수
    public static void letterCheck(int index, String one)
    {
        HashSet<Integer> letters = new HashSet<>();
        for(int i = 0; i < one.length(); i++)
        {
            char c = one.charAt(i);
            int letter = c - 'a';

            if(!gibon.contains(letter))
            {
                letters.add(letter);
            }
        }
        wordsLetter.put(index, letters);

    }



}
