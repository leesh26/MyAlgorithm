from collections import deque

n, k = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

chess = [[deque() for _ in range(n)] for _ in range(n)]
c = [[] for _ in range(k)]

for i in range(k):
    x, y, d = map(int, input().split())
    chess[x-1][y-1].append([i, d-1])
    c[i] = (x-1, y-1)

mx = [0, 0, -1, 1]
my = [1, -1, 0, 0]
opp = [1, 0, 3, 2]
time = 1

def check(chess):
    for i in range(n):
        for j in range(n):
            if len(chess[i][j]) >= 4:
                return True
    return False

while True:
    # 시간이 1000초보다 크면 게임 종료
    if time > 1000:
        print(-1)
        break

    # 체스 이동
    for i in range(k):
        x, y = c[i][0], c[i][1]

        # 인덱스 찾기
        for j in range(len(chess[x][y])):
            if chess[x][y][j][0] == i:
                index = j
                break

        d = chess[x][y][index][1]
        nx, ny = x + mx[d], y + my[d]

        # 범위를 벗어나거나 파란색이면 반대방향으로 이동
        move = True
        if 0 > nx or nx >= n or 0 > ny or ny >= n or board[nx][ny] == 2:
            d = opp[d]
            nx, ny = x + mx[d], y + my[d]

            # 방향을 바꾼 후 이동하려는 칸이 또 바깥이거나 파란색이면 이동 x
            if 0 > nx or nx >= n or 0 > ny or ny >= n or board[nx][ny] == 2:
                nx, ny = x, y
                move = False

            chess[x][y][index][1] = d

        if move:
            # 자신 위의 말들이 모두 이동
            temp = deque()
            for _ in range(len(chess[x][y])-index):
                temp.append(chess[x][y].pop())

            while temp:
                if board[nx][ny] == 1:
                    c[temp[0][0]] = (nx, ny)
                    chess[nx][ny].append(temp.popleft())
                else:
                    c[temp[-1][0]] = (nx, ny)
                    chess[nx][ny].append(temp.pop())

            # 체스가 4개 이상 쌓이면 게임 종료
            if check(chess):
                print(time)
                exit(0)

    time += 1