n = int(input())
a = list(map(int, input().split()))
b = list(map(int, input().split()))

answer, s = [], 0
while a:
    t_a, t_b = min(a), max(b)
    s += (t_a * t_b)
    answer.append(t_a)
    del a[a.index(t_a)]
    del b[b.index(t_b)]

print(s)