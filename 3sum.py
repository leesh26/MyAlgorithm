# 리트코드 : https://leetcode.com/problems/3sum/
# 투포인터 이용 

class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        results = []
        nums.sort()
	
        for i in range(len(nums)-2):
	#중복일 시 pass
            if i > 0 and nums[i] == nums[i-1] : 
                continue
	# left는 i 다음값, right는 마지막 값
            left, right = i+1, len(nums)-1
            
            while left<right:
                sum = nums[i]+nums[left]+nums[right]
             
		# sum이 0보다 작으면 left을 한칸 오른쪽으로, 0보다 크면 right를 한칸 왼쪽으로 
                if sum<0:
                    left += 1
                elif sum>0:
                    right -= 1
                else:
                    results.append((nums[i],nums[left],nums[right]))
                    
                    while left<right and nums[left]==nums[left+1]:
                        left += 1
                    while left<right and nums[right]==nums[right-1]:
                        right -= 1
                    left += 1 
                    right -= 1

         return results
      #
 
