package basic.queue;

import java.util.Scanner;

public class RingBufferQueue {
    private int max;    // 큐의 용량
    private int front;  // 첫 번째 요소 커서
    private int rear;   // 마지막 요소 커서
    private int num;    // 현재 데이터 수
    private int[] que;  // 큐 본체

    // 실행 시 예외 : 큐가 비어있을 경우
    public class EmptyRingBufferQueueException extends RuntimeException {
        public EmptyRingBufferQueueException() {
        }
    }

    // 실행 시 예외 : 큐가 가득참
    public class OverflowRingBufferQueueException extends RuntimeException {
        public OverflowRingBufferQueueException() {
        }
    }

    // 생성자
    public RingBufferQueue(int capacity) {
        num = front = rear = 0;
        max = capacity;
        try {
            que = new int[max];
        } catch (OutOfMemoryError e) {
            max = 0;
        }
    }

    // 큐에 데이터를 인큐
    public int enQueue(int x) throws OverflowRingBufferQueueException {
        if (num >= max)
            throw new OverflowRingBufferQueueException(); // 큐 가득참
        que[rear++] = x;
        num++;
        if (rear == max)
            rear = 0;
        return x;
    }

    // 큐에 데이터를 디큐
    public int deque() throws EmptyRingBufferQueueException {
        if (num <= 0)
            throw new EmptyRingBufferQueueException();
        int x = que[front++];
        num--;
        if (front == max)
            front = 0;
        return x;
    }

    // 큐에서 데이터를 피크(프런트 데이터를 들여다 봄)
    public int peek() throws EmptyRingBufferQueueException {
        if (num <= 0)
            throw new EmptyRingBufferQueueException();
        return que[front];
    }

    // 큐에서 x를 검색하여 인덱스(찾지 못하면-1) 반환
    public int indexOf(int x) {
        for (int i = 0; i < num; i++) {
            int idx = (i + front) % max;
            if (que[idx] == x)
                return idx;
        }
        return -1;
    }

    // 큐를 비움
    public void clear() {
        num = front = rear = 0;
    }

    // 큐의 용량을 반환
    public int capacity() {
        return max;
    }

    // 큐에 쌓여 있는 데이터 수를 반환
    public int size() {
        return num;
    }

    // 큐가 비어있는지 체크
    public boolean isEmpty() {
        return num <= 0;
    }

    // 큐가 가득찼는지 체크
    public boolean isFull() {
        return num >= max;
    }

    // 큐 안의 모든 데이터를 프런트 > 리어 순으로 출력
    public void dump() {
        if (num <= 0)
            System.out.println("큐가 비어 있습니다.");
        else {
            for (int i = 0; i<num; i++)
                System.out.println(que[(i + front) % max] + " ");
            System.out.println();
        }
    }

    // 큐 확인 프로그램
    public void start(RingBufferQueue q) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("현재 데이터 수 : " + q.size() + " / " + q.capacity());
            System.out.println("1. 인큐");
            System.out.println("2. 디큐");
            System.out.println("3. 피크");
            System.out.println("4. 덤프");
            System.out.println("5. 종료");

            System.out.print("입력 : ");
            int menu = sc.nextInt();
            if (menu == 5) {
                break;
            }

            int x;
            switch (menu) {
                default:
                    System.out.println("올바른 값을 입력하세요.");
                    break;
                case 1:
                    System.out.print("데이터 : ");
                    x = sc.nextInt();
                    try {
                        q.enQueue(x);
                    } catch (RingBufferQueue.OverflowRingBufferQueueException e) {
                        System.out.println("큐가 가득찼습니다.");
                    }
                    break;

                case 2:
                    try {
                        x = q.deque();
                        System.out.println("디큐한 데이터는 " + x + "입니다.");
                    } catch (RingBufferQueue.EmptyRingBufferQueueException e) {
                        System.out.println("큐가 비어있습니다.");
                    }
                    break;

                case 3:
                    try {
                        x = q.peek();
                        System.out.println("피크한 데이터는 " + x + "입니다.");
                    } catch (RingBufferQueue.EmptyRingBufferQueueException e) {
                        System.out.println("큐가 비어 있습니다.");
                    }
                    break;

                case 4:
                    q.dump();
                    break;
            }
        }
    }
}
