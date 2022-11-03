import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r;
    static int c;
    static boolean[][] cave;
    static int[] mx = new int[]{-1, 1, 0, 0};
    static int[] my = new int[]{0, 0, -1, 1};

    public static boolean checkCluster(){
        // bfs
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[r][c];
        List<List<int[]>> clusters = new ArrayList<>();

        int clusterNum = 0;
        Set<Integer> ground = new HashSet<>(); // 바닥에 붙은 클러스터 체크
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!cave[i][j] || visited[i][j]) continue;
                // 방문하지 않았고 미네랄이라면
                clusterNum += 1;
                q.add(new int[]{i, j});
                visited[i][j] = true;

                List<int[]> cluster = new ArrayList<>();
                cluster.add(new int[]{i, j});

                while (!q.isEmpty()){
                    int[] now = q.poll();
                    for (int k = 0; k < 4; k++) {
                        int nx = now[0] + mx[k];
                        int ny = now[1] + my[k];

                        if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                        if (visited[nx][ny]) continue;
                        if (!cave[nx][ny]) continue;
                        if (nx == r - 1) ground.add(clusterNum - 1);
                        q.add(new int[]{nx, ny});
                        cluster.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }

                clusters.add(cluster);
            }
        }
        if (clusterNum >= 2) cave = dropCluster(clusters, ground);
        return true;
    }

    public static boolean[][] dropCluster(List<List<int[]>> clusters, Set<Integer> ground){
        // 땅에 닿지 않은 클러스터만 내리기
        for (int i = 0; i < clusters.size(); i++) {
            // ground에 포함되지 않는다면 땅에 닿지 않은 것
            if (!ground.contains(i)){
                // 모두 .으로 변경
                for (int[] points : clusters.get(i)){
                    cave[points[0]][points[1]] = false;
                }

                // 포함된 모든 칸을 x축으로 한칸씩 내림.
                // 불가능한지 체크
                boolean check = true;
                int dropHeight = 0; // 떨어질 높이
                while (true) {
                    for (int[] points : clusters.get(i)) {
                        int tempX = points[0] + dropHeight + 1;
                        // 이 지점에 이미 미네랄이 있다면 옮길 수 없음
                        if (tempX >= r || cave[tempX][points[1]]) check = false;
                    }

                    if (!check) break;
                    dropHeight ++;
                }

                // 변경된 칸으로 다시 채우기
                int j = 0;
                for (int[] points : clusters.get(i)) {
                    cave[points[0] + dropHeight][points[1]] = true;
                    clusters.get(i).set(j, new int[]{points[0] + dropHeight, points[1]});
                    j++;
                }
                break;
            }
        }
       return cave;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        cave = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                if (line.charAt(j) == 'x') cave[i][j] = true;
            }
        }

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        // 막대 던지기
        boolean direction = true; // true 왼쪽 false 오른쪽
        for (int i = 0; i < n; i++) {
            int height = r - Integer.parseInt(st.nextToken());
            int width = (direction ? 0 : c - 1);
            while (width >= 0 && width < c && !cave[height][width]){
                // x는 height, y는 0부터 증가
                if (direction) width += 1;
                else width -= 1;
            }
            // 부수기
            if (width >= 0 && width < c) cave[height][width] = false;

            // 체크
            checkCluster();
            direction = !direction;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (cave[i][j]) sb.append("x");
                else sb.append(".");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }
}
