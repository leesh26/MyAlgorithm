
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken()) - 1;
        long b = Long.parseLong(st.nextToken());

        // 규칙 1
        // dp[i] = dp[i-1] * 2 (-> 맨 비트가 0인 경우와 1인 경우 두 배가 됨)
        //          + (1L << i)(-> 맨 앞 비트가 1인 경우가 몇 가지인가) ex ) 1000 -> 100 (4가지)
        long[] dp = new long[55];
        dp[0] = 1;
        for (int i = 1; i < 55; i++) {
            dp[i] = 2 * dp[i - 1] + (1L << i);
        }

        // 규칙 2
        long oneA = a & 1;
        long oneB = b & 1;
        for (int i = 54; i > 0 ; i--) {
            if ((a & (1L << i)) > 0L){
                oneA += dp[i -1] + (a - (1L<< i) + 1);
                a -= (1L << i);
            }

            if ((b & (1L << i)) > 0L){
                oneB += dp[i -1] + (b - (1L<< i) + 1);
                b -= (1L << i);
            }
        }

        System.out.println(oneB - oneA);
    }
}