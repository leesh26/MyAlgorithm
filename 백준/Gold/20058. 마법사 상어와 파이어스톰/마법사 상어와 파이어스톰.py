# 입력받기
from collections import deque

n, q = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(2**n)]
stage = list(map(int, input().split()))
size = 2**n

mx = [-1, 1, 0, 0]
my = [0, 0, -1, 1]

for s in stage:
    l = 2**s

    #격자 회전
    for i in range(0, size, l):
        for j in range(0, size, l):
            temp_board = [[0] * l for _ in range(l)]
            for a in range(l):
                for b in range(l):
                    temp_board[b][l - 1 - a] = board[i + a][j + b]

            for a in range(l):
                for b in range(l):
                    board[i + a][j + b] = temp_board[a][b]

    #얼음 녹이기
    melting = []
    for i in range(size):
        for j in range(size):
            if board[i][j]:
                count = 0
                for d in range(4):
                    nx = i + mx[d]
                    ny = j + my[d]

                    if 0 <= nx < size and 0 <= ny < size and board[nx][ny]:
                        count += 1

                if count < 3:
                    melting.append([i, j])

    for x, y in melting:
        board[x][y] -= 1

    #bfs
    visited = [[0] * size for _ in range(2**n)]
    ice = [[0] * size for _ in range(2**n)]
    num = 0
    for i in range(size):
        for j in range(size):
            if visited[i][j] == 0:
                visited[i][j] = 1
                if board[i][j] == 0: continue #얼음이 없는 경우 방문처리만

                num += 1
                q = deque()
                q.append([i, j])
                ice[i][j] = num

                while q:
                    x, y = q.popleft()
                    for d in range(4):
                        nx = x + mx[d]
                        ny = y + my[d]

                        if 0 <= nx < size and 0 <= ny < size:
                            if not visited[nx][ny]:
                                visited[nx][ny] = 1

                                # 얼음이면 큐에 넣기
                                if board[nx][ny]:
                                    ice[nx][ny] = num
                                    q.append([nx, ny])

# 얼음덩어리 리스트 만들기 [개수, 총합]
dung = [0] * (size * size + 1)
sum_ = 0
for i in range(size):
    for j in range(size):
        if ice[i][j]:
            dung[ice[i][j]] += 1
            sum_ += board[i][j]

dung.sort(reverse=True)
print(sum_) # 얼음 합
print(dung[0]) # 최대 덩어리가 차지하는 칸수