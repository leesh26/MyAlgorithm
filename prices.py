# 주식 가격
# https://programmers.co.kr/learn/courses/30/lessons/42584

def solution(prices):
    answer = []

    for k in range (len(prices)):
        # lower_day의 초기값 지정 (총 길이 - 현재값 + 1)
        # 아래 for문에서 하락하지 않으면 초기값이 lower 리스트에 저장되도록 함
        lower_day=len(prices)-(k+1)

        for i in range (k , len(prices)):
            # 자신보다 작은값이 나오면 lower_day의 값을 바꿔줌
            if prices[k]>prices[i]:
                lower_day = i-k
        answer.append(lower_day)

    return answer
