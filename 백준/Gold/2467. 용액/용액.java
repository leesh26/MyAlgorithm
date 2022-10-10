import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] nums = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = n - 1;
        long ansX = nums[left], ansY = nums[right];
        long sum = Long.MAX_VALUE;
        while (left < right){
            long temp = Math.abs(nums[left] + nums[right]);
            if (sum > temp){
                // 최소값 갱신
                sum = temp;
                ansX = nums[left];
                ansY = nums[right];
            }

            if (nums[left] + nums[right] < 0){
                left++;
            }
            else right--;
        }
        System.out.println(ansX + " " + ansY);
    }
}
