import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int nx, ny;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(i -> Integer.parseInt(i)).toArray();

        ArrayList<String> board= new ArrayList<>();
        for (int i = 0; i < nm[0]; i++){
            board.add(br.readLine());
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        int[][] visited = new int[nm[0]][nm[1]];
        visited[0][0] = 1;

        while (!q.isEmpty()){
            int[] temp = q.poll();
            for (int i = 0; i < 4; i++){
                nx = mx[i] + temp[0];
                ny = my[i] + temp[1];
                // 범위 내에 있고 값이 방문하지 않았으며 보드 값이 1이면 큐에 추가, 방문처리
                if (nx < nm[0] && ny < nm[1] && nx >= 0 && ny >= 0 && visited[nx][ny] == 0){
                    if (board.get(nx).charAt(ny) == '1'){
                        visited[nx][ny] = visited[temp[0]][temp[1]] + 1;
                        q.add(new int[]{nx, ny});}
                }
            }
        }

        System.out.println(visited[nm[0]-1][nm[1]-1]);
    }
}
