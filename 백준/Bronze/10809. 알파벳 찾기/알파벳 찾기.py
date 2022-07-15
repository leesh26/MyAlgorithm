words = input()
answer = [-1]*123
for i in range(len(words)):
    if answer[ord(words[i])] == -1:
        answer[ord(words[i])] = i #현재 위치로 변경

answer = answer[97:]
for a in answer:
    print(a, end=" ")