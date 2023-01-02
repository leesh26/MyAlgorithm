import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] mx = new int[]{-1, 1, 0, 0};
    static int[] my = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        // 빈칸 0, 벽 1, 레이저로 연결할 칸 2
        int[][] board = new int[h][w];
        List<Point> source = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            String line = br.readLine();
            for (int j = 0; j < w; j++) {
                char c = line.charAt(j);
                if (c == '.') board[i][j] = 0;
                else if (c == '*') board[i][j] = 1;
                else {
                    source.add(new Point(i, j, 0, 0));
                    board[i][j] = 2;
                }
            }
        }

        // bfs
        Point start = source.get(0);
        Point end = source.get(1);

        // 시작점 방문 표시
        int[][] visited = new int[h][w];
        for (int i = 0; i < h; i++) {
            Arrays.fill(visited[i], -1);
        }

        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            queue.add(new Point(start.x, start.y, i, 0)); //사방을 탐색하기 위해 4가지 넣어줌
        }
        visited[start.x][start.y] = 0;

        while (!queue.isEmpty()){
            Point poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = mx[i] + poll.x;
                int ny = my[i] + poll.y;

                if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
                if (board[nx][ny] == 1) continue;

                // 이전과 진행방향이 같으면 거울 0개 추가
                int tempMirror = poll.mirror;
                if (!poll.equals(start) && i != poll.direction) tempMirror++;
                if (visited[nx][ny] != -1 && visited[nx][ny] < tempMirror) continue;
                queue.add(new Point(nx, ny, i, tempMirror));
                visited[nx][ny] = tempMirror;
            }
        }

        System.out.println(visited[end.x][end.y]);

    }

    private static class Point {
        int x;
        int y;
        int direction;
        int mirror;

        public Point(int x, int y, int direction, int mirror) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.mirror = mirror;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
