n = int(input())
dp = [[0]*10 for _ in range(n+1)]
dp[1] = [1]*10
dp[1][0] = 0

for i in range(2, n+1):
    for j in range(10):
        # 끝자리가 0이면 붙을 수 있는건 1뿐이고 끝자리가 9이면 붙을 수 있는건 8뿐
        if j == 0:
            dp[i][0] = dp[i-1][1] # 이전에서 1로 끝나는 것의 개수(가지수)와 같음
        elif j == 9:
            dp[i][9] = dp[i-1][8] # 이전에서 8로 끝나는 것의 개수(가지수)와 같음
        else:
            dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1]

print(sum(dp[n])%1000000000)
