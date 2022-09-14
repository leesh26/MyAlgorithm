import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static boolean able(int maxValue, int[] nums, int group){
        int count = 0;

        int max = -1; int min = 10000000;
        for (int i = 0; i < nums.length; i++){
            max = Math.max(nums[i], max);
            min = Math.min(nums[i], min);
            // 최대값과 최소값의 차이가 maxValue를 넘어서면 count 증가 -> 최대값, 최소값 초기화
            if (max - min > maxValue) {
                count ++;
                max = nums[i]; min = nums[i];
            }
        }
        return (count + 1) <= group;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 최대값을 탐색
        int left = 0;
        int right = Arrays.stream(nums).max().getAsInt() - Arrays.stream(nums).min().getAsInt();
        while (left <= right){
            int mid = (left + right) / 2;
            // 가능하다면 내리기
            if (able(mid, nums, m)){
                right = mid - 1;
            }
            else left = mid + 1;
        }
        System.out.println(left);
    }
}
