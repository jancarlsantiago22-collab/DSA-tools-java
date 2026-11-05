import java.util.Scanner;

public class StacksModule
{
    private Scanner sc;

    // static array stack - fixed size for demo of overflow (as per docs)
    private static final int MAX = 10;
    private int[] arrayStack;
    private int top;

    // node for linked stack - dynamic
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

    private Node linkedTop;
    private int linkedSize;

    public StacksModule(Scanner sc)
    {
        this.sc = sc;
        arrayStack = new int[MAX];
        top = -1;
        linkedTop = null;
        linkedSize = 0;
    }

    public void run()
    {
        boolean back = false;

        while (back == false)
        {
            System.out.println("\n--- STACKS MODULE ---");
            System.out.println("Per Docs: Array & Linked List (Ortiz - Module 2)");
            System.out.println("Array Stack: capacity = " + MAX + " (fixed, shows overflow)");
            System.out.println("[Array - Static]");
            System.out.println("1. Push (Array)");
            System.out.println("2. Pop (Array)");
            System.out.println("3. Peek (Array)");
            System.out.println("4. Display Array Stack");
            System.out.println("[Linked List - Dynamic]");
            System.out.println("5. Push (Linked)");
            System.out.println("6. Pop (Linked)");
            System.out.println("7. Peek (Linked)");
            System.out.println("8. Display Linked Stack");
            System.out.println("9. Status (Empty/Full/Size)");
            System.out.println("0. Back to Main");

            int choice = ConsoleInput.readInt(sc, "Choice: ", 0, 9);

            if (choice == 1)
            {
                pushArray();
            }
            else if (choice == 2)
            {
                popArray();
            }
            else if (choice == 3)
            {
                peekArray();
            }
            else if (choice == 4)
            {
                displayArray();
            }
            else if (choice == 5)
            {
                pushLinked();
            }
            else if (choice == 6)
            {
                popLinked();
            }
            else if (choice == 7)
            {
                peekLinked();
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

    // push to array - with overflow check
    private void pushArray()
    {
        if (top == MAX - 1)
        {
            System.out.println("Overflow! Array stack is full (MAX = " + MAX + ").");
            return;
        }

        int value = ConsoleInput.readAnyInt(sc, "Value to push: ");
        top = top + 1;
        arrayStack[top] = value;

        System.out.println("Pushed " + value + " to array stack.");
    }

    // pop from array - with underflow check
    private void popArray()
    {
        if (top == -1)
        {
            System.out.println("Underflow! Array stack is empty.");
            return;
        }

        int value = arrayStack[top];
        top = top - 1;

        System.out.println("Popped: " + value);
    }

    // see top value
    private void peekArray()
    {
        if (top == -1)
        {
            System.out.println("Stack empty.");
            return;
        }

        System.out.println("Top value: " + arrayStack[top]);
    }

    // show all
    private void displayArray()
    {
        if (top == -1)
        {
            System.out.println("Array Stack is empty.");
            return;
        }

        System.out.print("Array Stack (top -> bottom): ");

        for (int i = top; i >= 0; i--)
        {
            System.out.print(arrayStack[i] + " ");
        }

        System.out.println();
    }

    // push to linked - dynamic
    private void pushLinked()
    {
        int value = ConsoleInput.readAnyInt(sc, "Value to push: ");

        Node newNode = new Node(value);
        newNode.next = linkedTop;
        linkedTop = newNode;
        linkedSize = linkedSize + 1;

        System.out.println("Pushed " + value + " to linked stack.");
    }

    private void popLinked()
    {
        if (linkedTop == null)
        {
            System.out.println("Underflow! Linked stack is empty.");
            return;
        }

        System.out.println("Popped: " + linkedTop.data);
        linkedTop = linkedTop.next;
        linkedSize = linkedSize - 1;
    }

    private void peekLinked()
    {
        if (linkedTop == null)
        {
            System.out.println("Linked stack empty.");
            return;
        }

        System.out.println("Top value: " + linkedTop.data);
    }

    private void displayLinked()
    {
        if (linkedTop == null)
        {
            System.out.println("Linked Stack is empty.");
            return;
        }

        System.out.print("Linked Stack (top -> bottom): ");

        Node current = linkedTop;

        while (current != null)
        {
            System.out.print(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }

    // show if empty or full
    private void status()
    {
        System.out.println("Array Stack size = " + (top + 1) + " / " + MAX);
        System.out.println("Array isEmpty = " + (top == -1));
        System.out.println("Array isFull = " + (top == MAX - 1));
        System.out.println("Linked Stack size = " + linkedSize);
        System.out.println("Linked isEmpty = " + (linkedTop == null));
        System.out.println("Linked is always dynamic (no limit).");
    }
}
