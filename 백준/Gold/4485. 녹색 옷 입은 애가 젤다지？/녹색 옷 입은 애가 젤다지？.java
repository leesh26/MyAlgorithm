import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int[] mx = new int[]{-1, 1, 0, 0};
    static int[] my = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNum = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            StringTokenizer st;
            int[][] cave = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // bfs 진행
            // 시작위치는 0, 0
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{0, 0});
            int[][] distance = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(distance[i], Integer.MAX_VALUE);
            }
            distance[0][0] = cave[0][0];

            while (!q.isEmpty()){
                // 꺼내서 이 칸을 기준으로 갈 수 있는 모든 칸의 값 갱신
                int[] now = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = now[0] + mx[i];
                    int ny = now[1] + my[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    // 이전 칸을 거쳐 오는 것이 현재 값보다 작으면 갱신하고 넣어줌
                    if (distance[now[0]][now[1]] + cave[nx][ny] < distance[nx][ny]){
                        distance[nx][ny] = distance[now[0]][now[1]] + cave[nx][ny];
                        q.add(new int[]{nx, ny});
                    }
                }
            }

            System.out.println("Problem " + caseNum++ + ": " + distance[n-1][n-1]);
        }
    }
}