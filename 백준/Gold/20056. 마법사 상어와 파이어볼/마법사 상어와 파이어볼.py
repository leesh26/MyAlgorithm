from collections import deque

n, m, k = map(int, input().split())
stage = [[[] for _ in range(n)] for _ in range(n)]
fireBalls = deque()

mx = [-1, -1, 0, 1, 1, 1, 0, -1]
my = [0, 1, 1, 1, 0, -1, -1, -1]

for i in range(m):
    r, c, m, s, d = map(int, input().split())
    fireBalls.append((r-1, c-1, m, s, d))

for _ in range(k):
    # 모든 파이어볼 방향대로 이동
    while fireBalls:
        x, y, m, s, d = fireBalls.popleft()
        nx, ny = (x + s * mx[d]) % n, (y + s * my[d]) % n
        stage[nx][ny].append((m, s, d))

    for i in range(n):
        for j in range(n):
            # 2 이상인 경우
            count = len(stage[i][j])
            if count > 1:
                check = True
                m, v, d = 0, 0, stage[i][j][0][2] % 2
                for k in range(count):
                    mm, vv, dd = stage[i][j].pop(0)
                    m += mm
                    v += vv

                    if d != (dd % 2):
                        check = False

                if m//5:
                    direction_list = [0, 2, 4, 6] if check else [1, 3, 5, 7]
                    for direction in direction_list:
                        fireBalls.append([i, j, m//5, v//count, direction])

            elif count == 1:
                m, v, d = stage[i][j].pop(0)
                fireBalls.append([i, j, m, v, d])

ans = 0
for f in fireBalls:
    _, _, m, _, _ = f
    ans += m

print(ans)

