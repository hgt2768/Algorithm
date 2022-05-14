import java.util.Scanner;

public class BasicTest {

    // 입력값 체크용
    public static int inputCheck() {
        Scanner sc = new Scanner(System.in);
        int input = 0;

        try {
            System.out.print("입력 : ");
            input = sc.nextInt();
        } catch (Exception e) {
            System.out.println("정수를 입력해주세요!(0~)");
        }

        return input;
    }

    public static void main(String[] args) {
        int select = 0;

        while (true) {
            System.out.println("*** 자료구조 기초 프로그램 ***");
            System.out.println("1. 큐(queue)");
            System.out.println("2. 스택(stack)");
            System.out.println("3. 링크드리스트(linkedList)");
            System.out.println("4. 해쉬(hash)");
            System.out.println("5. 트리(tree)");
            System.out.println("6. 힙(heap)");
            System.out.println("7. 종료");
            select = inputCheck();


            switch (select) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("프로그램 종료");
                    System.exit(0);
                    break;
                default:
                    System.out.println("올바른 값을 입력해주세요.");
            }
        }
    }
}


