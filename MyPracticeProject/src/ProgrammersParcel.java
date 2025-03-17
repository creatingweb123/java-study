
public class ProgrammersParcel {
    public static void main(String[] args) {
        ProgrammersParcel parcel = new ProgrammersParcel();
        int n = 10;
        int w = 5;
        int num = 3;

        int result = parcel.solution(n, w, num);
        System.out.println("Result: " + result);
    }

    public int solution(int n, int w, int num) {

        int totalFloor = n / w + (n % w > 0 ? 1 : 0);
        int lastFloorIndex = (n % w > 0) ? n % w : w;

        int numFloor = num / w + (num % w > 0 ? 1 : 0);
        int numIndex = (num % w > 0) ? num % w : w;

        int startIndex, endIndex, adjustedNumIndex = numIndex;

        // 홀수 층일 경우 왼쪽부터 채워지고
        // 짝수 층일 경우 오른쪽부터 채워진다.
        if (totalFloor % 2 == 1) {
            startIndex = 1;
            endIndex = lastFloorIndex;
        } else {
            startIndex = w - lastFloorIndex + 1;
            endIndex = w;
        }
        // 상자 index 계산
        if (numFloor % 2 == 0)
            adjustedNumIndex = w - numIndex + 1;

        int answer = totalFloor - numFloor;
        // 가장 위층에 택배가 있는 경우
        if (startIndex <= adjustedNumIndex && adjustedNumIndex <= endIndex)
            answer += 1;
        return answer;
    }
}
