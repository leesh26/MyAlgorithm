import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
피보나치 수를 나눈 나머지는 항상 주기를 가지며, 이를 피사노주기라고 한다.
나눌 수 k에 대해 k가 10^n이면, 피사노주기는 15*10^(n-1)이다.
 */

public class Main {
    static int pisano = 1500000;
    static long[] dp = new long[pisano];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long num = Long.parseLong(br.readLine());

        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < pisano; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
        }

        System.out.println(dp[(int) (num % pisano)]);
    }
}
