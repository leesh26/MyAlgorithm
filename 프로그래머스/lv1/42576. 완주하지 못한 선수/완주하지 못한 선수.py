def solution(participant, completion):
    answer = ''
    p_name, c_name = {}, {}

    # 참가자 이름 : 명수로 해시 테이블 생성
    for p in participant:
        if p in p_name:
            p_name[p] += 1
        else :
            p_name[p] = 1
    
    for c in completion:
        p_name[c] -= 1
    
    re_name = {v:k for k,v in p_name.items()}
    answer = str(re_name[1]) 
    
    
    return answer