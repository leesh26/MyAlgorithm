import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] shark = new int[4];
        int[][] board = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    shark = new int[]{i, j, 2, 0}; // 상어의 상태 (x좌표, y좌표, 크기, 먹은 물고기수)
                    board[i][j] = 0;
                }
            }
        }

        int[][] visited;
        int count = 0;
        PriorityQueue<Fish> fish;
        while (true){
            fish = new PriorityQueue<>((o1, o2) -> {
                int d1 = o1.distance;
                int d2 = o2.distance;
                if (d1 == d2) {
                    if (o1.x == o2.x) return o1.y - o2.y;
                    else return o1.x - o2.x;
                }
                return d1 - d2;
            });

            visited = new int[n][n];
            for (int i = 0; i < visited.length; i++) Arrays.fill(visited[i], -1);

            Queue<Fish> queue = new LinkedList<>();
            queue.add(new Fish(0, shark[0], shark[1]));
            visited[shark[0]][shark[1]] = 0;
            while (!queue.isEmpty()){
                Fish now = queue.poll();

                for (int i = 0; i < 4; i++){
                    int nx = now.x + mx[i];
                    int ny = now.y + my[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    if (visited[nx][ny] != -1) continue;
                    if (board[nx][ny] > shark[2]) continue; //크기가 크면 못지나감

                    Fish newFish = new Fish(visited[now.x][now.y] + 1, nx, ny);
                    if (board[nx][ny] != 0 && board[nx][ny] < shark[2]) fish.add(newFish);
                    queue.add(newFish);
                    visited[nx][ny] = visited[now.x][now.y] + 1;
                }
            }

            if (fish.size() == 0) break;
            Fish meal = fish.poll();
            board[meal.x][meal.y] = 0;
            shark[0] = meal.x;
            shark[1] = meal.y;
            shark[3]++;

            if (shark[2] == shark[3]) {
                shark[2] += 1;
                shark[3] = 0;
            }

            count += meal.distance;
        }

        System.out.println(count);
    }

    private static class Fish {
        int distance;
        int x;
        int y;

        public Fish(int distance, int x, int y) {
            this.distance = distance;
            this.x = x;
            this.y = y;
        }
    }
}
