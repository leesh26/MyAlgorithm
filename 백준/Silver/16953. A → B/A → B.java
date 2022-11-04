import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long target;
    static long minValue;

    public static void dfs(long num, int count){
        if (num > target) return;
        if (num == target) {
            minValue = Math.min(count, minValue);
            return;
        }

        dfs(num * 2, count + 1);
        dfs(Long.parseLong(String.valueOf(num) + "1"), count + 1);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        target = Long.parseLong(st.nextToken());

        minValue = Long.MAX_VALUE;
        dfs(n, 0);
        if (minValue == Long.MAX_VALUE) System.out.println(-1);
        else System.out.println(minValue + 1);
    }
}