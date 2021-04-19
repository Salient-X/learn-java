class Solution(object):
    def jump(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        
        if len(nums) <= 1:
            return 0
        
        if len(nums) == 2:
            return 1
        
        jumps = 0
        idx = -1
        minJumps = -1
        
        for i in range(len(nums)-1, -1, -1):
            
            if nums[i] + i >= len(nums) - 1:
                
                if i < len(nums) - 1:
                    jumps += 1
                
                start = i - 1
                
                print("Found = " , nums[i] , "  Jumps = " , jumps , "  Start = " , start)
                
                while True:
                    
                    for j in range(start, -1, -1):
                        if nums[j] + j > start:
                            
                            print("Found next position: ", j)
                            
                            idx = j
                    
                    if idx != -1:
                        jumps += 1
                        
                    print("jumps is ", jumps, "idx is ", idx)
                    
                    if idx < 0:
                        jumps = 0
                        break
                    
                    start = idx - 1
                    idx = -1
                    
                    print(" Start: " , start)
                    
                    if start < 0:
                        if minJumps == -1 or minJumps > jumps:
                            minJumps = jumps
                        break
                        
        return minJumps
     