import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] mx = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] my = {0, 0, -1, 1, 1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        int minValue = 100;
        for (int i = 0; i < n; i ++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j ++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                minValue = Math.min(minValue, board[i][j]);
            }
        }

        int count = 0;
        // 모든 점에서 bfs 실행
        // 자기보다 높은 지점이 있다면 count x
        // 자기보다 높은 지점이 없다면(낮은 지점만 있으면) 산봉우리 -> count += 1
        for (int i = 0; i < n; i ++){
            for (int j = 0; j < m; j++){
                if (visited[i][j]) continue;
                
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[]{i, j});
                visited[i][j] = true;
                boolean top = true;
                int height = board[i][j];

                int nx, ny;
                while (!q.isEmpty()){
                    int[] now = q.poll();

                    for (int d = 0; d < 8; d++){
                        nx = now[0] + mx[d];
                        ny = now[1] + my[d];

                        if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                        if (board[nx][ny] > height){
                            top = false;}
                        else if (!visited[nx][ny] && board[nx][ny] == height) {
                            q.add(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                }
                if (height > minValue && top) count += 1;
            }
        }
        System.out.println(count);
    }
}
