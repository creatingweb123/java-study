public class ProgrammersServerCount {

    public static void main(String[] args) {
        ProgrammersServerCount parcel = new ProgrammersServerCount();
        int[] players = { 0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 };
        int m = 1;
        int k = 1;
        int result = parcel.solution(players, m, k);
        System.out.println("Result: " + result);
    }

    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] serverCount = new int[players.length + k];

        int requireServer;
        for (int index = 0; index < players.length; index++) {
            requireServer = players[index] / m - serverCount[index];
            if (requireServer > 0) {
                answer += requireServer;
                for (int i = 0; i < k; i++) {
                    serverCount[index + i] += requireServer;
                }
            }
        }

        return answer;
    }
}
