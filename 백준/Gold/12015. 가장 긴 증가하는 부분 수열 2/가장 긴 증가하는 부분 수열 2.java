import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] nums, memory;

    public static int binarySearch(int left, int right, int num){
        while (left < right){
            int mid = (left + right) / 2;
            if (memory[mid] < num){
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        return right;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        nums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        memory = new int[n + 1];
        int ans = 0;
        int idx = 0;
        for(int i = 0; i < n; i++){
            // 맨 끝값 보다 큰값이 나오면 길이 1증가
            if (nums[i] > memory[ans]) {
                ans += 1;
                memory[ans] = nums[i];
            }
            // 작은 경우 위치를 이분탐색으로 찾아서 넣어주기
            else{
                idx = binarySearch(0, ans, nums[i]);
                memory[idx] = nums[i];
            }
        }
        System.out.println(ans);
    }
}
