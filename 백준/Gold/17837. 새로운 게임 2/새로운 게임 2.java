import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int x, y, d;
    static List<Integer>[][] position;
    static int[][] board, player;

    public static int findIndex(int i){
        int idx = 0;
        for (int j = 0; j < position[x][y].size(); j++){
            if (position[x][y].get(j) == i){
                idx = j;
            }
        }
        return idx;
    }

    public static int solution(){
        // 턴 시작
        int r = 1; // 턴 수를 셀 변수
        int[] mx = {0, 0, -1, 1}, my = {1, -1, 0, 0};
        int[] opp = {1, 0, 3, 2};

        while (r < 1000){

            // 말 이동하기
            for (int i = 0; i < k; i++){
                x = player[i][0]; y = player[i][1]; d = player[i][2];

                int idx = findIndex(i); //말의 인덱스
                int newX = x + mx[d]; // 말 이동
                int newY = y + my[d];

                //보드 색에 따라 이동
                if (newX < 0 || newY < 0 || newX >= n || newY >= n || board[newX][newY] == 2){
                    // 파란색인 경우 or 범위를 벗어난 경우 방향만 반대로 한칸 이동
                    player[i][2] = opp[d];
                    newX = x - mx[d];
                    newY = y - my[d];
                }
                if (newX < 0 || newY < 0 || newX >= n || newY >= n || board[newX][newY] == 2) continue;

                else if (board[newX][newY] == 0) {
                    // 흰색인 경우 말 옮기기
                    int count = position[x][y].size() - idx;
                    for (int j = 0; j < count; j++){
                        int rmv = position[x][y].remove(idx);
                        position[newX][newY].add(rmv);
                        player[rmv][0] = newX;
                        player[rmv][1] = newY;
                    }
                } else {
                    int count = position[x][y].size() - idx;
                    for (int j = 0; j < count; j++) {
                        int rmv = position[x][y].remove(position[x][y].size() - 1);
                        position[newX][newY].add(rmv);
                        player[rmv][0] = newX;
                        player[rmv][1] = newY;
                    }
                }
                if (position[newX][newY].size() >= 4) return r;
            }
            r++;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        position = new List[n][n];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                position[i][j] = new ArrayList<>();
            }
        }

        player = new int[k][3];
        for (int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;
            d = Integer.parseInt(st.nextToken()) - 1;
            position[x][y].add(i);
            player[i][0] = x; player[i][1] = y; player[i][2] = d;
        }
        System.out.println(solution());
    }
}
