import java.util.Scanner;

public class QueuesModule
{
    private Scanner sc;

    // static circular queue - fixed size (as per docs: Circular Array)
    private static final int MAX = 10;
    private int[] queue;
    private int front;
    private int rear;

    private class Node
    {
        int data;
        Node next;

        Node(int d)
        {
            data = d;
            next = null;
        }
    }

    private Node qFront;
    private Node qRear;
    private int linkedSize;

    public QueuesModule(Scanner sc)
    {
        this.sc = sc;
        queue = new int[MAX];
        front = -1;
        rear = -1;
        qFront = null;
        qRear = null;
        linkedSize = 0;
    }

    public void run()
    {
        boolean back = false;

        while (back == false)
        {
            System.out.println("\n--- QUEUES MODULE ---");
            System.out.println("Per Docs: Circular Array & Linked (Santiago - Module 3)");
            System.out.println("Array Queue: capacity = " + MAX + " (circular, shows overflow & wraparound)");
            System.out.println("[Circular Array Queue - Static FIFO]");
            System.out.println("1. Enqueue (Array)");
            System.out.println("2. Dequeue (Array)");
            System.out.println("3. Front and Rear (Array)");
            System.out.println("4. Display Array Queue");
            System.out.println("[Linked Queue - Dynamic]");
            System.out.println("5. Enqueue (Linked)");
            System.out.println("6. Dequeue (Linked)");
            System.out.println("7. Front and Rear (Linked)");
            System.out.println("8. Display Linked Queue");
            System.out.println("9. Status");
            System.out.println("0. Back to Main");

            int choice = ConsoleInput.readInt(sc, "Choice: ", 0, 9);

            if (choice == 1)
            {
                enqueueArray();
            }
            else if (choice == 2)
            {
                dequeueArray();
            }
            else if (choice == 3)
            {
                frontRearArray();
            }
            else if (choice == 4)
            {
                displayArray();
            }
            else if (choice == 5)
            {
                enqueueLinked();
            }
            else if (choice == 6)
            {
                dequeueLinked();
            }
            else if (choice == 7)
            {
                frontRearLinked();
            }
            else if (choice == 8)
            {
                displayLinked();
            }
            else if (choice == 9)
            {
                status();
            }
            else if (choice == 0)
            {
                back = true;
            }
        }
    }

    // check if circular queue is full
    private boolean isFull()
    {
        if ((rear + 1) % MAX == front)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // add to queue (rear) - FIFO
    private void enqueueArray()
    {
        if (isFull() == true)
        {
            System.out.println("Overflow! Circular queue is full (MAX = " + MAX + ").");
            return;
        }

        int value = ConsoleInput.readAnyInt(sc, "Value to enqueue: ");

        if (front == -1)
        {
            front = 0;
            rear = 0;
        }
        else
        {
            rear = (rear + 1) % MAX;
        }

        queue[rear] = value;

        System.out.println("Enqueued " + value + " to array queue.");
    }

    // remove from queue (front)
    private void dequeueArray()
    {
        if (front == -1)
        {
            System.out.println("Underflow! Queue is empty.");
            return;
        }

        System.out.println("Dequeued: " + queue[front]);

        if (front == rear)
        {
            front = -1;
            rear = -1;
        }
        else
        {
            front = (front + 1) % MAX;
        }
    }

    private void frontRearArray()
    {
        if (front == -1)
        {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Front = " + queue[front] + " at index " + front);
        System.out.println("Rear = " + queue[rear] + " at index " + rear);
    }

    // show queue with wraparound
    private void displayArray()
    {
        if (front == -1)
        {
            System.out.println("Array Queue is empty.");
            return;
        }

        System.out.print("Array Queue (front -> rear): ");

        int i = front;

        while (true)
        {
            System.out.print(queue[i] + " ");

            if (i == rear)
            {
                break;
            }

            i = (i + 1) % MAX;
        }

        System.out.println("(circular wraparound shown)");
    }

    // linked queue - dynamic
    private void enqueueLinked()
    {
        int value = ConsoleInput.readAnyInt(sc, "Value to enqueue: ");

        Node newNode = new Node(value);

        if (qRear == null)
        {
            qFront = newNode;
            qRear = newNode;
        }
        else
        {
            qRear.next = newNode;
            qRear = newNode;
        }

        linkedSize = linkedSize + 1;

        System.out.println("Enqueued " + value + " to linked queue.");
    }

    private void dequeueLinked()
    {
        if (qFront == null)
        {
            System.out.println("Underflow! Linked queue is empty.");
            return;
        }

        System.out.println("Dequeued: " + qFront.data);

        qFront = qFront.next;

        if (qFront == null)
        {
            qRear = null;
        }

        linkedSize = linkedSize - 1;
    }

    private void frontRearLinked()
    {
        if (qFront == null)
        {
            System.out.println("Linked queue is empty.");
            return;
        }

        System.out.println("Front = " + qFront.data);
        System.out.println("Rear = " + qRear.data);
    }

    private void displayLinked()
    {
        if (qFront == null)
        {
            System.out.println("Linked queue is empty.");
            return;
        }

        System.out.print("Linked Queue: ");

        Node current = qFront;

        while (current != null)
        {
            System.out.print(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }

    private void status()
    {
        int arraySize = 0;

        if (front == -1)
        {
            arraySize = 0;
        }
        else if (rear >= front)
        {
            arraySize = rear - front + 1;
        }
        else
        {
            arraySize = MAX - front + rear + 1;
        }

        System.out.println("Array Queue size = " + arraySize + " / " + MAX);
        System.out.println("Array isFull = " + isFull());
        System.out.println("Array isEmpty = " + (front == -1));
        System.out.println("Array is Circular (wraparound demo).");
        System.out.println("Linked Queue size = " + linkedSize);
        System.out.println("Linked isEmpty = " + (qFront == null));
    }
}
