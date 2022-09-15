import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board;
    static boolean[][] visited;
    static int n, k;
    static boolean check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[2][n];
        visited = new boolean[2][n];
        visited[0][0] = true;

        for (int i = 0; i < 2; i ++){
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        int time = 0;
        while (!q.isEmpty()){
            // 현재 들어있는 것들은 같은 시간에 행해져야 함
            for (int i = 0; i < q.size(); i++) {
                Point poll = q.poll();
                if (poll.y + 1 >= n || poll.y + k >= n){
                    check = true;
                    break;
                }

                if (poll.y + 1 < n && board[poll.x][poll.y + 1] == 1 && !visited[poll.x][poll.y + 1]){
                    visited[poll.x][poll.y + 1] = true;
                    q.add(new Point(poll.x, poll.y + 1));
                }

                if (poll.y - 1 >= 0 && poll.y - 1 > time && board[poll.x][poll.y - 1] == 1 && !visited[poll.x][poll.y - 1]){
                    visited[poll.x][poll.y - 1] = true;
                    q.add(new Point(poll.x, poll.y - 1));
                }

                if (poll.y + k < n && board[poll.x == 0 ? 1 : 0][poll.y + k] == 1 && !visited[poll.x == 0 ? 1 : 0][poll.y + k]){
                    visited[poll.x == 0 ? 1 : 0][poll.y + k] = true;
                    q.add(new Point(poll.x == 0 ? 1 : 0, poll.y + k));
                }
            }
            time += 1;
        }

        System.out.println(check ? 1:0);
    }

    public static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
