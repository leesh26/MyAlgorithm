import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] mx = {-1, 0, 1, 0};
    static int[] my = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        StringBuilder sb = new StringBuilder();

        int[][] board = new int[n][n];
        int x = n/2;
        int y = n/2;
        int i = 1;
        int direction = -1;
        board[x][y] = i++;

        while (true){
            if(x == 0 && y == 0) break;

            int temp_direction = (direction +1) % 4; // direction은 계속 바뀌어야 함
            // 만약 이미 방문했다면 다시 돌리고 이동
            int nx = x + mx[temp_direction];
            int ny = y + my[temp_direction];

            if (board[nx][ny] != 0){
                nx = x + mx[direction];
                ny = y + my[direction];
            }
            else direction = temp_direction;

            x = nx; y = ny;
            board[x][y] = i++;
        }

        int[] answer = {0, 0};
        for (int k = 0; k < n; k++){
            for (int l = 0; l < n; l++){
                if (board[k][l] == m) {
                    answer[0] = k + 1;
                    answer[1] = l + 1;
                }
                sb.append(board[k][l] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
        System.out.println(answer[0] + " " + answer[1]);
    }
}
