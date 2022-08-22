import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) nums[i] = i;

        st = new StringTokenizer(br.readLine());
        int truth = Integer.parseInt(st.nextToken());
        for (int i = 0; i < truth; i++){
            nums[Integer.parseInt(st.nextToken()) - 1] = -1; //진실을 아는 사람은 -1로 표시
        }

        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer>[] list = new ArrayList[m];
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            list[i] = new ArrayList<>();
            int people = Integer.parseInt(st.nextToken());
            int minValue = 51;
            for (int j = 0; j < people; j++){
                int num = Integer.parseInt(st.nextToken()) - 1;
                queue.add(num);
                list[i].add(num);

                // 이 친구의 부모와 같은 것들 모두 추가
                for (int k = 0; k < n; k++){
                    if (nums[k] == nums[num] && !queue.contains(k)) queue.add(k);
                }

                minValue = Math.min(nums[num], minValue);
            }

            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int temp = queue.poll();
                nums[temp] = minValue;
            }
        }

        int count = 0;
        boolean check;
        for (int i = 0; i < m; i++){
            check = true;
            for (Integer num : list[i]){
                if (nums[num] == -1) {
                    check = false;
                    break;
                }
            }
            if (check) count++;
        }
        System.out.print(count);
    }
}
