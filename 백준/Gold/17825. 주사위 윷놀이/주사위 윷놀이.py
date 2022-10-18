# 보드 만들기
from copy import deepcopy

board = [[0] * 20 for _ in range(4)]
board[0] = [2 * i for i in range(1, 21)]
board[1][:8] = [10, 13, 16, 19, 25, 30, 35, 40]
board[2][:7] = [20, 22, 24, 25, 30, 35, 40]
board[3][:8] = [30, 28, 27, 26, 25, 30, 35, 40]

checkli = [[[1, 4], [2, 3], [3, 4]], [[1, 5], [2, 4], [3, 5]], [[1, 6], [2, 5], [3, 6]], [[1, 7], [2, 6], [3, 7], [0, 19]]]

# 말의 위치
p = [[0, -1] for _ in range(4)]
blue, dupli = [10, 20], [25, 30, 35, 40]
dice = list(map(int, input().split()))

maxScore = -1

def dfs(num, s):
    global maxScore

    if num == 10:
        maxScore = max(maxScore, s)
        return

    for i in range(4):
        temp = p[i]
        # 이미 도착한 말이면 옮기지 않음
        if temp == [100, 100]: continue
        ny = temp[1] + dice[num]
        nx = temp[0]

        if [nx, ny] in p:
            if ny >= 20 or board[nx][ny] == 0:
                p[i] = [100, 100]
                dfs(num + 1, s)
                p[i] = temp

        else:
            if ny >= 20 or board[nx][ny] == 0:
                p[i] = [100, 100]
                dfs(num + 1, s)
                p[i] = temp

            else:
                # 파랑인 경우 : 30은 두 칸 있기 때문에 따로 정해줘야 함
                if board[nx][ny] in blue or (board[nx][ny] == 30 and nx == 0):
                    nx = board[nx][ny] // 10
                    ny = 0

                # 파랑이 아니면서 [25, 30, 35, 40] 이 칸들은 모든 경로에 존재하기 때문에 따로 확인
                else:
                    check = False
                    if board[nx][ny] in dupli:
                        idx = dupli.index(board[nx][ny])
                        for j in range(len(checkli[idx])):
                            if checkli[idx][j] in p:
                                check = True
                    if check: continue

                if [nx, ny] not in p:
                    p[i] = [nx, ny]
                    dfs(num + 1, s + board[nx][ny])
                    p[i] = temp

dfs(0, 0)
print(maxScore)