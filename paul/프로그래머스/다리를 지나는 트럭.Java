import java.util.*;
import java.io.*;
class Solution {
    static class Truck{
        int weight, out_time;
        public Truck(int w, int ot){
            weight = w;
            out_time = ot;
        }
    }
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        LinkedList<Truck> bridge = new LinkedList<>();
        int ww = 0; // 전체무게.
        int ct = 1; //현재 시간.
        int idx = 0; // 지금 확인하는 트럭.
        while(idx != truck_weights.length){
            // 다리위의 트럭이 무조건 내려와야 하는 상황
            if(bridge.size() != 0 && 
               (bridge.size() == bridge_length || ww + truck_weights[idx] > weight || bridge.peek().out_time <= ct ))
            {
                Truck out_truck = bridge.poll();
                ww -= out_truck.weight;
                ct = out_truck.out_time;
                continue;
            }
            Truck in_truck = new Truck(truck_weights[idx], ct + bridge_length);
            bridge.add(in_truck);
            ww += in_truck.weight;
            ct++;
            idx++;
        }
        return bridge.peekLast().out_time;
    }
}