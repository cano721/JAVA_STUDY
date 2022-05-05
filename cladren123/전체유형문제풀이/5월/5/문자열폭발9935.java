package studyGroup.may.may5;


import java.util.*;
import java.io.*;

public class 문자열폭발9935 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String bomb = br.readLine();


        Stack<Character> store = new Stack<>();
        char trigger = bomb.charAt(bomb.length()-1);

        for(int i = 0; i < s.length(); i++)
        {
            char one = s.charAt(i);
            store.add(one);

            if(store.size() >= bomb.length() && one == trigger)
            {
                Stack<Character> temp = new Stack<>();
                for(int j = bomb.length()-1; j >= 0; j--)
                {
                    char check = store.pop();
                    temp.add(check);
                    if(check != bomb.charAt(j))
                    {
                        while(!temp.isEmpty()) {
                            store.add(temp.pop());
                        }
                        break;
                    }

                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Character c : store)
        {
            sb.append(c);
        }

        System.out.println(sb.length()==0 ? "FRULA" : sb.toString());


    }



}
