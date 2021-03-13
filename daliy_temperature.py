# 리트코드 739번 일일온도
#

class Solution(object):
    def dailyTemperatures(self, T):
        """
        :type T: List[int]
        :rtype: List[int]
        """
        #최근 들어온 값이 이전 값보다 크면 append(1)
        #최근 들어온 값이 이전 값보다 작으면 이전 값의 인덱스(위치)를 저장
        #다시 증가하면 append(1)한 후 저장된 인덱스의 값과 최근 들어온 값을 비교해 인덱스 차이 반환
        
        result = []
        stack = []
        
        i=1
        while (i<len(T)):
            result.append(0)
            
            if T[i]>T[i-1]:
                result[i-1]=1
                while (len(stack)>0):
                    if T[stack[-1]]<T[i]:
                        result[stack[-1]]=i-stack[-1]
                        del stack[-1]
                    else:
                        break
                i+=1
            
            else :
                stack.append(i-1)
                i+=1
                
        result.append(0)
        return result
