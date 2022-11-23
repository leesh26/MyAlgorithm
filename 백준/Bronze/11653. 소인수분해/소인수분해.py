num = int(input())

start = 2
while num != 1:
    if num % start == 0:
        num = num//start
        print(start)
    else:
        start += 1
