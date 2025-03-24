public class ProgrammersSecretMagic {

    public static void main(String[] args) {
        long n = 7388;
        String[] bans = { "gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc" };

        ProgrammersSecretMagic p = new ProgrammersSecretMagic();
        String answer = p.solution(n, bans);
    }

    public String solution(long n, String[] bans) {
        String answer = "";
        long tmpN = n;
        long tmpMod;
        char[] targetN = new char[12];
        boolean check = false;
        for (int i = 1; i < 12; i++) {
            tmpMod = tmpN % 26;
            tmpMod = tmpMod == 0 ? 26 : tmpMod;
            tmpN /= 26;
            if (tmpN == 0 || (tmpN == 1 && tmpMod == 26))
                check = true;
            targetN[i] = numberToChar((int) tmpMod);
            if (check)
                break;
        }

        return answer;
    }

    public char numberToChar(int number) {
        if (number < 1 || number > 26) {
            throw new IllegalArgumentException("Number must be between 1 and 26");
        }
        return (char) ('a' + number - 1);
    }

}
