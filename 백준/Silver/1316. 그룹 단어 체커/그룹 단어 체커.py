def group_word(word):
    past = " "
    words = []
    for w in word:
        if past != w and w in words:
            return False

        if past == w:
            pass
        words.append(w)
        past = w
    return True

n = int(input())
count = 0
for _ in range(n):
    word = input()
    if group_word(word):
        count += 1

print(count)