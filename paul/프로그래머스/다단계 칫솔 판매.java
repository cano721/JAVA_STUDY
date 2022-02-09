import java.util.*;
class Solution {
    static class Node{
        String name;
        int money;
        Node parent;
        
        public Node(String n, Node p){
            name = n;
            parent = p;
            money = 0;
        }
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        Map<String, Node> map = makeTree(enroll, referral);
        
        for(int i =0; i< seller.length; i++) {
            getResult(map, seller[i], amount[i]*100);
        }
        
        for(int i =0; i<enroll.length; i++){
            answer[i] = map.get(enroll[i]).money;
        }
        
        return answer;
    }
    
    static Map<String, Node> makeTree(String[] enroll, String[] referral){
        Node centre = new Node("-", null);
        Map<String, Node> map = new HashMap<>();
        map.put("-", centre);
        for(int i =0; i< enroll.length; i++){
            String s = enroll[i];
            Node parent = map.get(referral[i]);
            Node node = new Node(s, parent);
            map.put(s, node);
        }
        return map;
    }
    
    static void getResult(Map<String, Node> map, String seller, int amount){
        
        Node node = map.get(seller);
        
        while(true){
            if(node.name.equals("-") || amount == 0) break;
            
            int money = amount/10;
            node.money += (amount- money);
        
            node = node.parent;
            amount = money;
        }
    }
}