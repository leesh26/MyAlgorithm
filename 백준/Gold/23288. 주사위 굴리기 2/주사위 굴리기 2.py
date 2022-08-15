from copy import deepcopy

# 초기 상태 (바닥, 동, 서, 북, 동, 하늘)
n, m, k = map(int, input().split())
board = []
for _ in range(n):
    board.append(list(map(int, input().split())))

dice = [3, 4, 2, 5, 1, 6]

mx = [0, 0, -1, 1]
my = [1, -1, 0, 0]
opp = [1, 0, 3, 2] # 반대 면에 해당하는 면
count = 0
# 회전을 위한 리스트
c_rotation = [3, 2, 0, 1]
uc_rotation = [2, 3, 1, 0]

def dice_move(direction, dice):
    dice[5], dice[4], dice[direction], dice[opp[direction]] = \
        dice[direction], dice[opp[direction]], dice[4], dice[5]


def dfs(visited, pos_x, pos_y, num):
    global count

    for i in range(4):
        nx = pos_x + mx[i]
        ny = pos_y + my[i]

        if 0 <= nx < n and 0 <= ny < m:
            if visited[nx][ny] == 0 and board[nx][ny] == num:
                visited[nx][ny] = 1
                count += 1
                dfs(visited, nx, ny, num)

def solution():
    global count
    answer = 0
    x, y = 0, 0
    direction = 0  # 처음 direction 은 동쪽

    for _ in range(k):
        nx = x + mx[direction]
        ny = y + my[direction]

        # 범위 밖을 벗어난 경우 반대방향으로 이동
        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            direction = opp[direction]
            nx = x + mx[direction]
            ny = y + my[direction]

        # 점수 계산
        count = 1
        x, y = nx, ny
        visited = [[0] * m for _ in range(n)]
        visited[x][y] = 1
        dfs(visited, x, y, board[x][y])
        visited[x][y] = 0

        answer += (count * board[x][y])
        dice_move(direction, dice)

        # 방향 변경
        if dice[-1] > board[x][y]:
            direction = c_rotation[direction]
        elif dice[-1] < board[x][y]:
            direction = uc_rotation[direction]


    print(answer)

solution()
