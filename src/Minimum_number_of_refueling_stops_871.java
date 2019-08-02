import java.util.PriorityQueue;

public class Minimum_number_of_refueling_stops_871 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    class Solution {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            int count = 0;
            int way = startFuel;
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int i = 0; i < stations.length; i++) {
                int dist = stations[i][0];
                int fuel = stations[i][1];

                while (way < dist && !queue.isEmpty()) {
                    way += -queue.poll();
                    count++;
                }

                if (way < dist) {
                    return -1;
                }

                queue.add(-fuel);
            }

            while (way < target && !queue.isEmpty()) {
                way += -queue.poll();
                count++;
            }

            if (way >= target) {
                return count;
            }

            return -1;
        }
    }}

