import java.util.Scanner;

public class Main {
    static int n, ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        // 퀸을 설치할 보드
        boolean[][] board = new boolean[n][n];
        // 열에 이미 퀸이 있음을 체크할 배열
        boolean[] visited = new boolean[n];

        dfs(0, visited, board);
        System.out.println(ans);
    }

    public static boolean isQueen(boolean[][] board, int x, int y){
        int right_y = y;
        while (x != 0){
            x --;
            if (y > 0) {
                y--;
                if (board[x][y]) return true;
            }
            if (right_y < n - 1) {
                right_y++;
                if (board[x][right_y]) return true;
            }
        }
        return false;
    }

    public static void dfs(int count, boolean[] visited, boolean[][] board){
        if (count == n){
            ans += 1;
            return;
        }

        /* 퀸은 행, 열, 대각선으로 공격 가능
        각 행마다 퀸은 하나만 있어야 하고, 각 열마다도 퀸 하나여야 함!
        행을 순회하며 퀸을 놓을 수 있는 위치(열)을 찾자!
        대각선은 x좌표 - 1, y좌표 - 1해서 x좌표가 0일 때까지 검사
        퀸이 있으면 : 이 칸에 놓을 수 없음 / 퀸이 없으면 : 방문처리 & 재귀!*/
        for (int i = 0; i < n; i ++){
            if (!visited[i] && !isQueen(board, count, i)){
                board[count][i] = true;
                visited[i] = true;
                dfs(count + 1, visited, board);
                board[count][i] = false;
                visited[i] = false;
            }
        }
    }
}
