import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken()) - 1;

        int[][] distance = new int[n][n];
        // 초기값 설정
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int dist = Integer.parseInt(st.nextToken());
            distance[start][end] = dist;
        }

        int answer = 0;
        int[] go = new int[n];
        for (int i = 0; i < n; i++) {
            PriorityQueue<Node> heap = new PriorityQueue<>();
            int[] allDist = new int[n];
            Arrays.fill(allDist, Integer.MAX_VALUE);
            heap.add(new Node(0, i)); // 현재 지점은 거리 0으로 넣기
            allDist[i] = 0;

            while (!heap.isEmpty()){
                Node now = heap.poll();
                if (now.dist > allDist[now.num]) continue;

                for (int j = 0; j < n; j++) {
                    if (distance[now.num][j] != Integer.MAX_VALUE){
                        int cost = now.dist + distance[now.num][j];
                        if (allDist[j] > cost) {
                            allDist[j] = cost;
                            heap.add(new Node(cost, j));
                        }
                    }
                }
            }


            if (i == x){
                for (int j = 0; j < n; j++) {
                    go[j] += allDist[j];
                }
            }
            else{
                go[i] += allDist[x];
            }
        }
        System.out.println(Arrays.stream(go).max().getAsInt());
    }

    static class Node implements Comparable<Node>{
        int dist; //거리
        int num; //노드 번호

        Node(int dist, int num) {
            this.dist = dist;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            return dist - o.dist;
        }
    }
}
