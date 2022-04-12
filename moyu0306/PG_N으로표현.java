import java.util.*;
class Solution {
    HashSet[] numSet = new HashSet[9];
    public int solution(int N, int number) {
      for(int i=1; i< 9; i++){
          HashSet<Integer> list = new HashSet<Integer>();
          String num ="";
          for(int j =0; j<i;j++) num+=N;
          list.add(Integer.parseInt(num));
          numSet[i] = list;
      }  
      for(int i = 1; i< 9; i++){
          for(int j =1 ; j< i; j++){
              HashSet<Integer> opSet1 = numSet[j];
              HashSet<Integer> opSet2 = numSet[i-j];
              Iterator iter1 = opSet1.iterator();
              while(iter1.hasNext()){
                  int op1 = (int)iter1.next();
                  Iterator iter2 = opSet2.iterator();
                  while(iter2.hasNext()){
                      int op2 = (int)iter2.next();
                      numSet[i].add(op1+op2);
                      numSet[i].add(op1-op2);
                      numSet[i].add(op1*op2);
                      if(op2 !=0) numSet[i].add(op1/op2);
                  }
              }
          }
      }        
          
          for(int k= 1; k< 9; k++){
              if(numSet[k].contains(number)) return k;
          }
          return -1;
      }
        
        
}