import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    public static int getParent(int i){
        if (parent[i] == i) return i;
        return parent[i] = getParent(parent[i]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] nodeTonode = new int[m][3]; //{weight, 시작점, 끝점}
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            nodeTonode[i][1] = Integer.parseInt(st.nextToken());
            nodeTonode[i][2] = Integer.parseInt(st.nextToken());
            nodeTonode[i][0] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nodeTonode, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                else return o1[0] - o2[0];
            }
        });

        // 크루스칼 알고리즘
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++){
            parent[i] = i;
        }

        int ans = 0;
        for (int i = 0; i < m; i++){
            int[] now = nodeTonode[i];

            if (getParent(now[1]) != getParent(now[2])){
                int p_a = getParent(now[1]);
                int p_b = getParent(now[2]);
                ans += now[0];

                if (p_a < p_b) parent[p_b] = p_a;
                else parent[p_a] = p_b;
            }
        }
        System.out.println(ans);
    }

}
