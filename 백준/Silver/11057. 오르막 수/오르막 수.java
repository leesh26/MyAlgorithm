import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] dp = new int[n + 1][10];
        int sum = 0;
        Arrays.fill(dp[1], 1); // 한 자리는 모두 1로 채우기
        
        if (n > 1) {
            for (int i = 2; i <= n; i++) {
                for (int j = 0; j < 10; j++) {
                    // 작은 값까지 더함
                    sum = 0;
                    for (int k = 0; k <= j; k++) {
                        sum += dp[i - 1][k];
                    }
                    dp[i][j] = sum % 10007;
                }
            }
        }
        System.out.println(Arrays.stream(dp[n]).sum() % 10007);
    }
}
