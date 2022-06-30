import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] li;
    static int[] parent;

    public static void dfs(int num){
        for (int i = 0; i < li[num].size(); i++){
            if (parent[li[num].get(i)] == 0) {
                parent[li[num].get(i)] = num;
                dfs(li[num].get(i));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        li = new ArrayList[n+1];
        for (int i = 1; i <= n ; i++){
            li[i] = new ArrayList<>();
        }

        // 인접 리스트 만들기
        for (int i = 0; i < n - 1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            li[a].add(b);
            li[b].add(a);
        }
        
        parent = new int[1 + n];
        dfs(1); // dfs 실행

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++){
            sb.append(parent[i] + "\n");
        }
        System.out.println(sb.toString());
    }
}
