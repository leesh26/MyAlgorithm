import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 자기보다 큰 값이 나올 때까지 pop한다.
        // 자기보다 큰 값이 나오면 그 값의 층을 추가한다.
        StringBuilder sb = new StringBuilder();
        Stack<Building> stack = new Stack<>();
        int[] l = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty()){
                Building peek = stack.peek();
                if (peek.height > nums[i]) {
                    l[i] = peek.index;
                    break;
                }
                else stack.pop();
            }
            stack.add(new Building(nums[i], i + 1));
        }

        for (int i = 0; i < n; i++) {
            sb.append(l[i] + " ");
        }

        System.out.println(sb.toString());
    }

    private static class Building {
        int height;
        int index;

        public Building(int height, int index) {
            this.height = height;
            this.index = index;
        }
    }
}