n = int(input())
dp = [0] * (n+1)
dp[0], dp[1] = 1, 1
fixed = [0] * (n+1)

vip = int(input())
for _ in range(vip):
    temp = int(input())
    fixed[temp] = 1


for i in range(2, n+1):
    # 고정석이 아니면 피보나치
    if not fixed[i] and not fixed[i-1]:
        dp[i] = dp[i-1] + dp[i-2]
    else:
        dp[i] = dp[i-1]

print(dp[n])