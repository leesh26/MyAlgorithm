from collections import deque
# 아기상어
n = int(input())

graph = []
for i in range(n):
    graph.append(list(map(int, input().split())))
    for j in range(n):
        if graph[i][j] == 9:
            # 현재 아기 상어의 위치
            x, y = i, j
            graph[i][j] = 0

size = 2
mx = [-1, 1, 0, 0]
my = [0, 0, -1, 1]

fish = 0
def bfs():
    dis = [[-1]*n for _ in range(n)] # 최단 거리를 측정하기 위해 거리를 측정할 맵을 만들어줌!
    dis[x][y] = 0 #시작점은 0
    q = deque([(x, y)])
    while q :
        a, b = q.popleft()
        for i in range(4):
            nx = a + mx[i]
            ny = b + my[i]
            if 0<=nx<n and 0<=ny<n:
                if dis[nx][ny]== -1 and graph[nx][ny] <= size:
                    q.append((nx, ny))
                    dis[nx][ny] = dis[a][b] + 1
    return dis

def food(dis):
    min_dist = 10000
    x, y = 0, 0
    # 먹을 수 있는 먹이
    for i in range(n):
        for j in range(n):
            if dis[i][j] != -1 and 1<= graph[i][j] < size:
                if dis[i][j] < min_dist:
                    x, y = i, j
                    min_dist = dis[i][j]

    if min_dist == 10000:
        return None
    else:
        return x, y, min_dist

result = 0
eat = 0
while True:
    res = food(bfs())
    if res == None:
        print(result)
        break
    else:
        # 위치 갱신, 최단 거리 추가
        x, y = res[0], res[1]
        result += res[2]
        # 먹은 위치 없애기
        graph[x][y] = 0
        # 먹은 양
        eat += 1
        if eat % size == 0:
            size += 1
            eat = 0
