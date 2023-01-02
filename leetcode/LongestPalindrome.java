class LongestPalindrome {
    public int longestPalindrome(String s) {
        
        String str[] = s.split("");
        int count = 0;
        int n = 0;
        Map<String, Integer> tm = new TreeMap<String, Integer>();
        
        for (int i = 0; i < str.length; i++) {
            if (tm.containsKey(str[i])) {
                tm.put(str[i], tm.get(str[i]) + 1);
            } else {
                tm.put(str[i], 1);
            }
        }
        
        System.out.println(tm);

        for (String e : tm.keySet()) {
            if (tm.get(e) % 2 == 0) {
                count = count + tm.get(e);
            } else {
                n++;
                count = count + tm.get(e)-1;
            }
        }
        
        if (n >= 1) {
            return count+1;
        }
        
        return count;
        
    }
}