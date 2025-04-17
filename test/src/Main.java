import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    // {p=1, r=2, o=1, g=4, a=3, m=2, i=1, n=2, l=1, u=1, e=1}
    public static char secondHighestCharacter(String s) {
        Map<Character, Integer> hmap = new LinkedHashMap<>();
        for(char ch : s.toCharArray()) {
            if(ch == ' ') {
                continue;
            }
            hmap.put(ch, hmap.getOrDefault(ch, 0) + 1);
        }
        System.out.println(hmap);

        int max = 0;
        int secondMax = 0;
        char res = ' ';
        for(char ch : hmap.keySet()) {
            if(ch == ' ') {
                continue;
            }
            int val = hmap.get(ch);
            if(max < val) {
                secondMax = max;
                max = val;
            }

            if(val < max && secondMax < val) {
                secondMax = val;
                res = ch;
            }
            System.out.println("max - " + max + " secondMax - " + secondMax);

        }
        return res;
    }
    
    public static void main(String[] args) {
        String s = "programming language";
        char res = secondHighestCharacter(s);
        System.out.println(res);
    }
}