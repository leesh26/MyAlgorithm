import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int left, right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        int ans = Integer.parseInt(br.readLine());
        int count = 0;

        left = 0;
        right = n - 1;
        while (left < right) {
            if (nums[left] + nums[right] > ans) {
                right--;
                continue;
            } else if (nums[left] + nums[right] == ans) count++;
            left++;
        }
        System.out.println(count);
    }
}
