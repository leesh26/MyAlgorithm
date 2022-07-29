package com.codestates.coplit; 
import java.util.*;

public class Solution { 
	public int robotPath2(int[][] room, int[] src, int sDir, int[] dst, int dDir) {
		// TODO:
		// 방향전환 및 이동을 위한 변수 선언
		int[] mx = {-1, 0, 1, 0};
		int[] my = {0, 1, 0, -1};

		// 초기 설정
		int n = room.length, m = room[0].length;
		int[][] visited = new int[n][m];
		for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++) visited[i][j] = -1;

		Queue<int[]> q = new LinkedList<>();

		// 1. 시작 점 방문 처리
		// 2. 시작 점 큐에 넣기
		visited[src[0]][src[1]] = 0;
		int[] temp = {src[0], src[1], sDir - 1};
		q.add(temp);

		int count = 0;
		int nx = 0, ny = 0;
		int ans = 0;

		while (!q.isEmpty()){
				temp = q.poll();
				int d = temp[2];
				int x = temp[0], y = temp[1];
				System.out.println(Arrays.toString(temp));

				if (x == dst[0] && y == dst[1]) {
						dDir -= 1;
						if (d == dDir) {
								ans = visited[x][y];
						}
						else {
								if (((d - 1) % 4 == dDir) || ((d + 1) % 4 == dDir)) ans = visited[x][y] + 1;
								else ans = visited[x][y] + 2;
						}
						break;
				}

				for (int i = 0; i < 4; i++){
						int tempX = x, tempY = y;
						while (true) {
								nx = tempX + mx[d];
								ny = tempY + my[d];
								if (nx >= n || ny >= m || nx < 0 || ny < 0 || room[nx][ny] == 1) break;
								if (visited[nx][ny] != -1) break;

								if (i == 1 || i == 3) visited[nx][ny] = visited[x][y] + 2;
								else visited[nx][ny] = visited[x][y] + i + 1;

								q.add(new int[]{nx, ny, d});
								tempX = nx; tempY = ny;
						}
						d = (d + 1) % 4;
				}
		}
		return ans;
  }
}
