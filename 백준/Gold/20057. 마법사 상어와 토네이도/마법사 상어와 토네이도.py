n = int(input())

graph = []
for _ in range(n):
    graph.append(list(map(int, input().split())))


mx = [0, 1, 0, -1]
my = [-1, 0, 1, 0]
left = [[0, 0, 2, 0, 0], [0, 10, 7, 1, 0], [5, 'a', 0, 0, 0], [0, 10, 7, 1, 0], [0, 0, 2, 0, 0]]
right = [[0, 0, 2, 0, 0], [0, 1, 7, 10, 0], [0, 0, 0, 'a', 5], [0, 1, 7, 10, 0], [0, 0, 2, 0, 0]]
up = [[0, 0, 5, 0, 0], [0, 10, 'a', 10, 0], [2, 7, 0, 7, 2], [0, 1, 0, 1, 0], [0, 0, 0, 0, 0]]
down = [[0, 0, 0, 0, 0], [0, 1, 0, 1, 0], [2, 7, 0, 7, 2], [0, 10, 'a', 10, 0], [0, 0, 5, 0, 0]]


visit = [[-1]*n for _ in range(n)]
step = 1
x, y = n//2, n//2
visit[x][y] = 0
direction = 0

def sandy(graph, ans, x, y, d):
    a, b = x - 2, y - 2
    temp = 0
    a_position = (0, 0)
    for i in range(5):
        for j in range(5):
            if d[i][j]!= 0 and d[i][j]!='a':
                if 0 <= i + a < n and 0 <= j + b < n:
                    graph[i + a][j + b] +=  graph[x][y] * d[i][j]//100
                else :
                    # 밖으로 나간 모래의 양 계산
                    ans += graph[x][y] * d[i][j]//100
                temp += graph[x][y] * d[i][j]//100
            elif d[i][j] == 'a':
                a_position = (i, j)

    if 0<= a_position[0] + a <n and 0<= a_position[1] + b <n:
        graph[a_position[0]+a][a_position[1]+b] += graph[x][y] - temp
    else:
        ans += graph[x][y] - temp
    graph[x][y] = 0
    return graph, ans

ans = 0
while True:
    if x==0 and y==0:
        break

    nx = x + mx[direction]
    ny = y + my[direction]
    if 0 <= nx < n and 0 <= ny < n :
        if visit[nx][ny] == -1:
            visit[nx][ny] = 0
            if graph[nx][ny] != 0:
                if direction == 0:
                    graph, ans = sandy(graph, ans, nx, ny, left)
                elif direction == 1:
                    graph, ans = sandy(graph, ans, nx, ny, down)
                elif direction == 2:
                    graph, ans = sandy(graph, ans, nx, ny, right)
                elif direction == 3:
                    graph, ans = sandy(graph, ans, nx, ny, up)
            direction = (direction + 1) % 4
            x, y = nx, ny
        else:
            direction = (direction - 1) % 4
print(ans)
