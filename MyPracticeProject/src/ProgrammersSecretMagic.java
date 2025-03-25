import java.util.TreeSet;

public class ProgrammersSecretMagic {

    public static void main(String[] args) {
        long n = 7388;
        String[] bans = { "gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len",
                "bhc" };
        // long n = 30;
        // String[] bans = { "d", "e", "bb", "aa", "ae" };

        ProgrammersSecretMagic p = new ProgrammersSecretMagic();
        String answer = p.solution(n, bans);
        System.out.println("answer: " + answer);
    }

    public String solution(long n, String[] bans) {
        TreeSet<Long> banValues = new TreeSet<>();

        for (String ban : bans) {
            long tmp = stringToNumber(ban);
            if (tmp <= n)
                n++;
            else {
                banValues.add(tmp);
            }
        }
        for (Long value : banValues) {
            if (value <= n)
                n += 1;
            else
                break;
        }
        String answer = numberToString(n);

        return answer;
    }

    public String numberToString(long n) {
        StringBuilder targetN = new StringBuilder();
        long tmpN = n;
        long tmpMod;

        while (tmpN > 0) {
            tmpMod = (tmpN - 1) % 26; // Adjust for 1-based indexing
            targetN.append(numberToChar((int) (tmpMod + 1))); // Convert 1-26 to a character
            tmpN = (tmpN - 1) / 26;
        }

        return targetN.reverse().toString(); // Reverse to maintain order
    }

    public char numberToChar(int number) {
        if (number < 1 || number > 26) {
            throw new IllegalArgumentException("Number must be between 1 and 26");
        }
        return (char) ('a' + number - 1);
    }

    public long stringToNumber(String target) {
        long answer = 0;
        int length = target.length();

        for (int i = 0; i < length; i++) {
            char c = target.charAt(i);
            answer = answer * 26 + (c - 'a' + 1);
        }

        return answer;
    }

}
