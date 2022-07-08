def solution(N, stages):
    length = len(stages)
    result = []
    answer = []
    
    for i in range(1, N+1):
        challenge = stages.count(i)
        
        if length == 0:
            fail = 0
        else:
            fail = challenge / length
        
        result.append((fail, i))
        length -= challenge # 도전했지만 실패한 사람 명수 빼주기
        
    result.sort(key=lambda x:(x[0], -x[1]),reverse = True)
    for a, b in result:
        answer.append(b)
        
    return answer