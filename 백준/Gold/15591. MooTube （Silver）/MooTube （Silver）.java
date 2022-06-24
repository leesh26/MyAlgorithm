import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] distance = new int[n][n];

        //인접 리스트 만들기
        int p, q, r;
        List<int[]>[] list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i ++){
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            list[p].add(new int[]{q, r});
            list[q].add(new int[]{p, r});
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i ++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // bfs
            boolean[] visited = new boolean[n + 1];
            visited[v] = true; //시작점

            Queue<Integer> queue = new LinkedList<>();
            queue.add(v);

            int count = 0;
            while (!queue.isEmpty()) {
                int now = queue.poll();

                for (int j = 0; j < list[now].size(); j++){
                    int[] pp = list[now].get(j);
                    // k 이하이면 이 값이 최소가 됨 -> 탐색하지 않도록 큐에 넣지 않음
                    if(!visited[pp[0]] && pp[1] >= k){
                        queue.add(pp[0]);
                        visited[pp[0]] = true;
                        count ++;
                    }
                }
            }
            sb.append(count + "\n");
        }

        System.out.println(sb.toString());
    }
}
