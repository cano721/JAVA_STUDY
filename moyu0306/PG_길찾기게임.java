import java.util.*;

class Solution {
    int[] preorder;
    int[] postorder;
    int preorderidx = 0;
    int postorderidx = 0;
    public int[][] solution(int[][] nodeinfo) {
        ArrayList<Node> nodes  = new ArrayList<>();
        int len = nodeinfo.length;
        preorder = new int[len];
        postorder = new int[len];
        int count = 1;

        for(int[] info : nodeinfo){
            nodes.add(new Node(count++,info));
        }

        Collections.sort(nodes,(x,y)->{
            return (x.info[1] != y.info[1]) ? y.info[1] - x.info[1]
                    : x.info[0] - y.info[0];
        });
        for(int i=1; i<nodes.size(); i++){
            insert(nodes.get(i),nodes.get(0));
        }

        setPreorder(nodes.get(0));
        setPostorder(nodes.get(0));

        int[][] answer = new int[2][len];
        answer[0] = preorder;
        answer[1] = postorder;

        return answer;
    }

    public void insert(Node child,Node parent){
        int childX = child.info[0];
        int parentX = parent.info[0];
        if(childX<parentX){
            if(parent.left == null) parent.left = child;
            else insert(child,parent.left);
        }else{
            if(parent.right == null) parent.right = child;
            else insert(child,parent.right);
        }

    }

    public void setPreorder(Node node){
        preorder[preorderidx++] = node.num;
        if(node.left != null) setPreorder(node.left);
        if(node.right != null)  setPreorder(node.right);
    }

    public void setPostorder(Node node){
        if(node.left != null) setPostorder(node.left);
        if(node.right != null) setPostorder(node.right);
        postorder[postorderidx++] = node.num;
    }

}
class Node{
    Node left;
    Node right;

    int num;
    int[] info;
    public Node(int num, int[] info){
        this.num = num;
        this.info = info;
    }
}