import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> left = new PriorityQueue<>(((o1, o2) -> o2- o1)); // 작은 값들
        PriorityQueue<Integer> right = new PriorityQueue<>(((o1, o2) -> o1 - o2)); // 큰 값들

        int n = Integer.parseInt(br.readLine());

        // 작은 값, 큰값
        for (int i = 0; i < n; i++) {
            int now = Integer.parseInt(br.readLine());
            if (i == 0) left.add(now);
            else if (i == 1) {
                if (left.peek() < now) right.add(now);
                else {
                    right.add(left.poll());
                    left.add(now);
                }
            } else {
                if (right.peek() < now) right.add(now);
                else left.add(now);
            }

            // 두 큐의 차가 2 이상 나면 큐를 재조정한다.
            if (left.size() - right.size() >= 2) {
                right.add(left.poll());
            } else if (right.size() - left.size() >= 2) {
                left.add(right.poll());
            }

            // 중간값은 left를 peek한 것.
            if (left.size() < right.size()) sb.append(right.peek() + "\n");
            else sb.append(left.peek() + "\n");
        }
        System.out.println(sb.toString());
    }
}