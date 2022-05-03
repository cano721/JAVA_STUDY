package studyGroup.may.may2;

import java.util.*;

public class 길찾기게임 {

    public static void main(String[] args) {

        int[][] nodeinf = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};

        System.out.println(Arrays.deepToString(solution(nodeinf)));


    }

    static int[][] answer;
    static int index;

    public static int[][] solution(int[][] nodeinfo) {


        int n = nodeinfo.length;
        ArrayList<node> nodeList = new ArrayList<>();

        for(int i = 0; i < n; i++)
        {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];

            nodeList.add(new node(y,x,i+1,null,null));
        }

        // 이중 정렬 : y 내림차순, x 오름차순
        Collections.sort(nodeList, new Comparator<node>() {

            @Override
            public int compare(node o1, node o2)
            {
                if(o1.y == o2.y)
                {
                    return o1.x - o2.x;
                }

                return o2.y - o1.y;
            }
        });

        node parentNode = nodeList.get(0);

        for(int i = 1; i < n; i++)
        {
            insertNode(parentNode, nodeList.get(i));
        }


        answer = new int[2][n];

        index = 0;
        preorder(parentNode);

        index = 0;
        postorder(parentNode);

        return answer;
    }


    // 전위순회
    public static void preorder(node rootNode)
    {
        answer[0][index++ ] = rootNode.index;


        if(rootNode.leftNode != null)
        {
            preorder(rootNode.leftNode);
        }
        if(rootNode.rightNode != null)
        {
            preorder(rootNode.rightNode);
        }
    }

    // 후위 순회
    public static void postorder(node node)
    {
        if(node != null)
        {
            postorder(node.leftNode);
            postorder(node.rightNode);
            answer[1][index++] = node.index;
        }

    }

    // 노드의 맞는 위치 찾기
    public static void insertNode(node parentNode, node childNode)
    {
        if(parentNode.x < childNode.x)
        {
            if(parentNode.rightNode == null)
            {
                parentNode.rightNode = childNode;
                return;
            }
            else
            {
                insertNode(parentNode.rightNode, childNode);
            }
        }
        else
        {
            if(parentNode.leftNode == null)
            {
                parentNode.leftNode = childNode;
                return;
            }
            else
            {
                insertNode(parentNode.leftNode, childNode);
            }
        }


    }


    public static class node
    {
        int y;
        int x;
        int index;
        node leftNode;
        node rightNode;

        node(int y, int x, int index, node leftNode, node rightNode)
        {
            this.y = y;
            this.x = x;
            this.index = index;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }


}
