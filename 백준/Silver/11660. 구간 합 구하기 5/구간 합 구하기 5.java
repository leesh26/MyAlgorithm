import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 첫번째줄부터 dp하기 위해서 가로 세로 한칸 더 크게 만들어줌
        int[][] board = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + board[i][j];
            }
        }

        int x1,y1,x2,y2;
        int ans;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            ans = dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1];
            sb.append(ans +"\n");
        }

        System.out.println(sb);
    }
}
