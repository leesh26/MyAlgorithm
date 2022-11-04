import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int[] nums = new int[k];
            for (int j = 0; j < k; j++) {
                nums[j] = Integer.parseInt(st.nextToken());
            }

            int[] sum = new int[k];
            sum[0] = nums[0];
            for (int j = 1; j < k; j++) {
                sum[j] = sum[j - 1] + nums[j]; // 누적합 계산
            }

            int[][] dp = new int[k][k];
            int minValue;
            // 맨 밖의 포문은 길이
            // 중간 포문은 시작점과 끝점 지정..
            // 맨 안쪽 포문은 시작점과 끝점 사이의 분기점 지정
            dp[0][1] = nums[0] + nums[1];
            for (int j = 1; j <= k; j++) {
                for (int l = 0; l + j < k; l++) {
                    minValue = Integer.MAX_VALUE;
                    if (l == 0){
                        for (int m = l; m < l + j; m++) {
                            minValue = Math.min(minValue, dp[l][m] + dp[m + 1][l + j] + sum[l + j]);
                        }
                    }
                    else{
                        for (int m = l; m < l + j; m++) {
                            minValue = Math.min(minValue, dp[l][m] + dp[m + 1][l + j] + sum[l + j] - sum[l - 1]);
                        }
                    }

                    dp[l][l + j] = minValue;
                }
            }
            System.out.println(dp[0][k - 1]);
        }
    }
}