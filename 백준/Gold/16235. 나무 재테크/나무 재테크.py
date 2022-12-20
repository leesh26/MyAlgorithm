n, m, k = map(int, input().split())
food = [list(map(int, input().split())) for _ in range(n)]
farm = [[5] * n for _ in range(n)]
tree = [[[] for _ in range(n)] for _ in range(n)]

mx = [-1, -1, 0, 0, 1, 1, 1, -1]
my = [-1, 0, -1, 1, -1, 1, 0, 1]

for _ in range(m):
    x, y, age = map(int, input().split())
    tree[x-1][y-1].append(age)

for _ in range(k):
    summer = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if tree[i][j]:
                tree[i][j].sort()
                temp_tree = []
                for k in range(len(tree[i][j])):
                    if farm[i][j] >= tree[i][j][k]:
                        farm[i][j] -= tree[i][j][k]
                        temp_tree.append(tree[i][j][k]+1)
                    else:
                        summer[i][j] += (tree[i][j][k] // 2)
                tree[i][j] = temp_tree

    for i in range(n):
        for j in range(n):
            farm[i][j] += summer[i][j]
            for k in range(len(tree[i][j])):
                if tree[i][j][k] % 5 == 0:
                    for d in range(8):
                        if 0 <= i + mx[d] < n and 0 <= j + my[d] < n:
                            tree[i+mx[d]][j+my[d]].append(1)
            farm[i][j] += food[i][j]

count = 0
for i in range(n):
    for j in range(n):
        for k in range(len(tree[i][j])):
            count += 1
print(count)

