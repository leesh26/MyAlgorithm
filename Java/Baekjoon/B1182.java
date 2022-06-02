import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, s, len;
    static int[] nums;
    static int ans = 0;

    public static void permutation(int count, int sum){
        if (count == len){
            if (sum == s) ans += 1;
            return;
        }

        for (int i = count; i < n; i ++){
            permutation(i + 1, sum + nums[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); //기억하자

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++){
            len = i;
            permutation(0, 0);
        }

        System.out.println(ans);
    }
}
