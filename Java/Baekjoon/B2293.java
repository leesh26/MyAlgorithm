import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] c = br.readLine().split(" ");
        int n = Integer.parseInt(c[0]);
        int k = Integer.parseInt(c[1]);

        int[] nums = new int[n];
        for (int i = 0; i < n; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(ocean(k, nums));
    }

    public static long ocean(int target, int[] type) {
        // TODO:
        // target + 1 x type.length + 1 크기의 2차원 dp 배열을 만든다
        int[][] dp = new int[type.length + 1][target + 1];

        // 초기값 설정
        Arrays.fill(dp[0],0);
        dp[0][0] = 1;

        // 점화식 구현
        for (int i = 1; i <= type.length; i++){
            for (int j = 0; j <= target; j ++){
                if (j < type[i - 1]) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j] + dp[i][j - type[i - 1]];
            }
        }

        return dp[type.length][target];
    }
}
