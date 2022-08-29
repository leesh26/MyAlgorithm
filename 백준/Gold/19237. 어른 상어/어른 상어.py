n, m, k = map(int, input().split())
shark = [[0, 0] for _ in range(m)] # 격자

for i in range(n):
    t = list(map(int, input().split()))
    for j in range(n):
        if t[j] > 0:
            shark[t[j] - 1] = [i, j]


direction = list(map(int, input().split()))

board = [[0] * n for _ in range(n)]
mx = [-1, 1, 0, 0]
my = [0, 0, -1, 1]

priority = []
for _ in range(m):
    temp_priority = [list(map(int, input().split())) for _ in range(4)]
    priority.append(temp_priority)

time = 0
while len(shark) - shark.count([-1, -1]) != 1:
    if time >= 1000:
        print(-1)
        exit(0)

    # 상어 냄새 남기기
    for i in range(len(shark)):
        if shark[i] != [-1, -1]:
            board[shark[i][0]][shark[i][1]] = [i, k]

    # 상어 이동
    for i in range(len(shark)):
        if shark[i] == [-1, -1]:
            continue
        no_blank = 0
        for j in range(4):
            d = priority[i][direction[i] - 1] # 여기 오류
            nx = shark[i][0] + mx[d[j] - 1]
            ny = shark[i][1] + my[d[j] - 1]

            # 빈칸이면 이동하고 냄새남기기
            if 0 <= nx < n and 0 <= ny < n and board[nx][ny] == 0:
                # 다른 상어가 있는지 확인
                if [nx, ny] in shark:
                    shark[i] = [-1, -1]
                    direction[i] = -1
                    break

                # 다른 상어가 없으면 이동
                shark[i] = [nx, ny]
                direction[i] = d[j]
                break
            else:
                no_blank += 1

        # 갈 수 있는 칸이 없다면
        if no_blank == 4:
            for j in range(4):
                d = priority[i][direction[i] - 1]
                nx = shark[i][0] + mx[d[j] - 1]
                ny = shark[i][1] + my[d[j] - 1]

                if 0 <= nx < n and 0 <= ny < n and board[nx][ny][0] == i:
                    shark[i] = [nx, ny]
                    direction[i] = d[j]
                    break

    # 냄새 지속시간 감소
    for i in range(n):
        for j in range(n):
            if board[i][j]:
                board[i][j][1] -= 1
                if board[i][j][1] == 0: board[i][j] = 0
                
    time += 1
print(time)