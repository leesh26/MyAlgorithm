import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[] mx = {-1, 1, 0, 0};
    public static int[] my = {0, 0, -1, 1};

    public static class Point{
        int x; int y;
        boolean isBroken;
        int distance;

        public Point(int x, int y, boolean isBroken, int distance) {
            this.x = x;
            this.y = y;
            this.isBroken = isBroken;
            this.distance = distance;
        }
    }

    public static int changeInt(boolean bool){
        return bool?1:0;
    }

    public static void main(String[] args) throws IOException {
        // 0. 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String temp = br.readLine();
            for (int j = 0; j < m; j++){
                map[i][j] = temp.charAt(j) - '0';
            }
        }

        // 1. 필요한 것들 만들기
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, false, 1));
        boolean[][][] visited = new boolean[n][m][2];
        visited[0][0][0] = true;
        int ans = 0;

        // 2. BFS 탐색
        while (!queue.isEmpty()){
            Point now = queue.poll();
            if (now.x == n - 1 && now.y == m - 1) {
                ans = now.distance;
                break;
            }

            // 2-1. 사방을 확인
            for (int i = 0; i < 4; i++){
                int nextX = now.x + mx[i];
                int nextY = now.y + my[i];

                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;
                if (visited[nextX][nextY][changeInt(now.isBroken)]) continue;

                // 2-2. 벽이라면
                if (map[nextX][nextY] == 1){
                    // 2-2-1. 벽을 한번 없앴다면 다른 벽을 부술 수 없음 -> 부수고 이동 불가

                    // 2-2-2. 벽을 한번도 부수지 않았다면 이 벽을 부숨
                    if (!now.isBroken) {
                        visited[nextX][nextY][changeInt(now.isBroken)] = true;
                        queue.add(new Point(nextX, nextY, true, now.distance + 1));
                    }
                }
                else {
                    visited[nextX][nextY][changeInt(now.isBroken)] = true;
                    queue.add(new Point(nextX, nextY, now.isBroken, now.distance + 1));
                }

            }
        }
        if (ans == 0) System.out.println(-1);
        else System.out.println(ans);
    }
}