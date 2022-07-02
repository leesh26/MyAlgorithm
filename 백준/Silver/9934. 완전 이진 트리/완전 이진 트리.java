import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int treeSize = (int) (Math.pow(2, n) - 1);
        int[] nums = new int[treeSize];
        for (int i = 0; i < treeSize; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 트리(인덱스) 생성
        List<Integer>[] tree = new List[n];
        for (int i = 0; i < n; i++){
            tree[i] = new ArrayList<Integer>();
            treeSize /= 2;
            if (i == 0) {
                tree[i].add(treeSize);
                continue;
            }

            for (int j = 0 ; j < tree[i - 1].size(); j++){
                tree[i].add(tree[i - 1].get(j) - treeSize - 1);
                tree[i].add(tree[i-1].get(j) + treeSize + 1);
            }
        }

        // Ans 만들기
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < tree[i].size(); j++){
                sb.append(nums[tree[i].get(j)] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
