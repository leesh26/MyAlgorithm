# 2022.09.08
from collections import deque

# 입력 받기
from copy import deepcopy

n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

# 준비
mx = [-1, 1, 0, 0]
my = [0, 0, -1, 1]
year = 0

while(1):
    year += 1
    zeroCount = 0
    
    # 0이 아니면 사방을 확인해서 0의 개수를 센다.
    tBoard = deepcopy(board)
    for i in range(n):
        for j in range(m):
            if not board[i][j]:
                zeroCount += 1
                continue

            for k in range(4):
                nx = mx[k] + i
                ny = my[k] + j

                if 0 > nx or 0 > ny or nx >= n or ny >= m : continue # 범위를 벗어난 경우
                if not tBoard[i][j]: break # 0이 된 경우 더 이상 탐색하지 않도록 break
                if not board[nx][ny]: tBoard[i][j] -= 1 # 주변이 0인 경우 1을 뺀다.

    if zeroCount == n * m:
        print(0)
        exit()

    board = deepcopy(tBoard)

    visited = [[0 for _ in range(m)] for _ in range(n)]
    token = 1
    for i in range(n):
        for j in range(m):
            # 만약 빙산이라면
            if board[i][j] and not visited[i][j]:
                # bfs로 빙산의 개수 확인
                q = deque()
                q.append([i, j])
                visited[i][j] = token

                while (len(q)):
                    x, y = q.popleft()

                    for k in range(4):
                        nx = mx[k] + x
                        ny = my[k] + y

                        if 0 > nx or 0 > ny or nx >= n or ny >= m: continue  # 범위를 벗어난 경우
                        if visited[nx][ny] or not board[nx][ny]: continue
                        visited[nx][ny] = token
                        q.append([nx, ny])

                token += 1

    if token > 2: break

print(year)