import java.io.*;
import java.util.*;

class Solution {
    public String solution(String new_id) {
        new_id = new_id.toLowerCase();
        String id = "";
        for(int i =0; i < new_id.length(); i++){
            char ch = new_id.charAt(i);
            
            if(ch >= 'a' && ch <= 'z') id += ch;
            else if (ch >= '0' && ch <='9') id += ch;
            else if (ch == '.' || ch =='_' || ch == '-') id +=ch;
        }
        
        for(int i=0; i< id.length(); i++){
            if(id.charAt(i) == '.'){
                int j = i+1;
                String dot = ".";
                while(j != id.length() && id.charAt(j) =='.'){
                    dot +='.';
                    j++;
                }
                if(dot.length() > 1) id = id.replace(dot, ".");
            }
        }
        
        if(id.startsWith(".")) id = id.substring(1, id.length());
        else if(id.endsWith(".")) id = id.substring(0, id.length() -1); 
        
        if(id.length() == 0) id += "a";
        
        if(id.length() >= 16) id = id.substring(0,15);
        if(id.endsWith(".")) id = id.substring(0,id.length()-1);
        
        
        while(id.length() <= 2){
            id += id.charAt(id.length()-1);
        }
        
        return id;
    }
}