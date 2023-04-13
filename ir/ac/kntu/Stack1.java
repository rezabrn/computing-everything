package ir.ac.kntu;

public class Stack1 {
    private String[] stack;
    private final int Maxsize = 20;
    private int top = -1;

    public Stack1() {
        stack = new String[Maxsize];
    }

    public String[] getStack() {
        return stack;
    }

    public void setStack(String[] stack) {
        this.stack = stack;
    }
    
    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getMaxsize() {
        return Maxsize;
    }
    
    public boolean isfull() {
        return top == Maxsize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(String input) {
        if (isfull()) {
            System.out.println("the stack is full");
        } else {
            stack[++top] = input;
        }
    }

    public String pop() {
        String output;
        if (isEmpty()) {
            output = "";
        } else {
            return output = stack[top--];
        }
        return output;
    }
}