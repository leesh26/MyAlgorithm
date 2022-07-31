import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        int n;

        for (int i = 0; i < t; i++){
            n = Integer.parseInt(br.readLine());

            int[][] sticker = new int[2][n];

            for (int j = 0; j < 2; j++){
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k ++){
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[2][n];
            dp[0][0] = sticker[0][0]; dp[1][0] = sticker[1][0];

            if (n > 1) {
                dp[0][1] = dp[1][0] + sticker[0][1];
                dp[1][1] = dp[0][0] + sticker[1][1];

                for (int k = 2; k < n; k++) {
                    dp[0][k] = Math.max(dp[1][k - 1] + sticker[0][k], dp[1][k - 2] + sticker[0][k]);
                    dp[1][k] = Math.max(dp[0][k - 1] + sticker[1][k], dp[0][k - 2] + sticker[1][k]);
                }
            }

            System.out.println(Math.max(dp[0][n-1], dp[1][n-1]));
        }

    }
}
