public class ProgrammersParcel {
    public static void main(String[] args) {
        ProgrammersParcel tree = new ProgrammersParcel();
        int n = 22;
        int w = 6;
        int num = 8;

        int result = tree.solution(n, w, num);
        System.out.println("Result: " + result);
    }

    public int solution(int n, int w, int num) {
        int answer = 0;
        int x1 = n / w;
        int y1 = n % w;

        int x2 = num / w;
        int y2 = num % w;

        int totalFloor = (y1 != 0) ? x1 + 1 : x1;
        int totalIndex = (y1 != 0) ? y1 : w;

        int numFloor = (y2 != 0) ? x2 + 1 : x2;
        int numIndex = (y2 != 0) ? y2 : w;

        int startIndex = 1;
        int endIndex = w;

        int numRealIndex = 0;

        if (totalFloor % 2 != 0) {
            endIndex = totalIndex;
        } else {
            startIndex = w - totalIndex + 1;
        }

        if (numFloor % 2 == 0)
            numRealIndex = w - numIndex + 1;

        if (startIndex <= numRealIndex && numRealIndex <= endIndex) {
            answer = totalFloor - numFloor + 1;
        } else {
            answer = totalFloor - numFloor;
        }
        return answer;
    }

}
