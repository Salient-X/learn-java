class CountDigit {
    public int findNthDigit(int n) {
        
        int count = 1;
        int num = 1;
        while (true) {
            List<Integer> lstnum = new ArrayList<>();
            int num1 = num;
            while (num1 != 0) {
                lstnum.add(num1%10);
                num1 /= 10;
            }
            
            for (int i = lstnum.size() - 1; i >= 0; i--) {
                
                
                if (count == n) {
                    return lstnum.get(i);
                } 
                
                count++;
                
            }
            
            num++;
            
        } 

    }
}