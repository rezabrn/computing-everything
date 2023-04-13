package ir.ac.kntu;

import java.util.Scanner;
import java.util.ArrayList;

public class Project1 {
    public static void main(String[] args) {
        System.out.println("enter the expression(example: 88 11 / 5 +):\n***you can only use 20 operands and operations***");
        Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split(" ");
        try {
            if (!Character.isDigit(input[0].charAt(0)) && !input[0].equals("(")) {
                System.out.println("result = " + preFix(input));
            } else if (!(Character.isDigit(input[input.length - 1].charAt(input[input.length - 1].length() - 1))) && !input[input.length - 1].equals(")")) {
                System.out.println("result = " + postFix(input));
            } else {
                System.out.println("result = " + postFix(inFix(input)));
            }
        } catch (NumberFormatException e) {
            System.out.println("the input expression is wrong!");
        }
    }

    public static int postFix(String[] input) {
        Stack1 stack = new Stack1();
        int x1 = 0, x2 = 0;
        for (int i = 0; i < input.length; i++) {
            if (isDig(input[i])) {
                stack.push(input[i]);
            } else {
                x1 = Integer.parseInt(stack.pop());
                x2 = Integer.parseInt(stack.pop());
                switch (input[i]) {
                    case "+":
                        stack.push(Integer.toString(x2 + x1));
                        break;
                    case "-":
                        stack.push(Integer.toString(x2 - x1));
                        break;
                    case "/":
                        stack.push(Integer.toString(x2 / x1));
                        break;
                    case "*":
                        stack.push(Integer.toString(x2 * x1));
                        break;
                    default:
                        System.out.println("the character is invalid");
                        System.exit(0);
                }
            }
        }
        return Integer.parseInt(stack.getStack()[0]);
    }

    public static int preFix(String[] input) {
        Stack1 stack = new Stack1();
        int x1 = 0, x2 = 0;
        for (int i = input.length - 1; i >= 0; i--) {
            if (isDig(input[i])) {
                stack.push(input[i]);
            } else {
                x1 = Integer.parseInt(stack.pop());
                x2 = Integer.parseInt(stack.pop());
                switch (input[i]) {
                    case "+":
                        stack.push(Integer.toString(x1 + x2));
                        break;
                    case "-":
                        stack.push(Integer.toString(x1 - x2));
                        break;
                    case "/":
                        stack.push(Integer.toString(x1 / x2));
                        break;
                    case "*":
                        stack.push(Integer.toString(x1 * x2));
                        break;
                    default:
                        System.out.println("the character is invalid");
                        System.exit(0);
                }
            }
        }
        return Integer.parseInt(stack.getStack()[0]);
    }

    public static boolean isDig(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String[] inFix(String[] input) {
        ArrayList<String> out = new ArrayList<>();
        boolean isParenthese = false;
        String parenthese;
        Stack1 stack = new Stack1();
        for (int i = 0; i < input.length; i++) {
            if (isDig(input[i])) {
                out.add(input[i]);
            } else if (input[i].equals("(")) {
                stack.push(input[i]);
            } else if (input[i].equals(")")) {
                for (int k = stack.getTop(); ; k--) {
                    if (stack.getStack()[k].equals("(")) {
                        parenthese = stack.pop();
                        break;
                    } else {
                        out.add(stack.pop());
                    }
                }
            } else {
                if (stack.isEmpty() || priority(input[i]) > priority(stack.getStack()[stack.getTop()])) {
                    stack.push(input[i]);
                } else {
                    String x;
                    while (priority(x = stack.pop()) >= priority(input[i]) && !x.equals("(")) {
                        out.add(x);
                    }
                    stack.push(x);
                    stack.push(input[i]);
                }
            }
        }
        for (int i = stack.getTop(); i >= 0; i--) {
            out.add(stack.getStack()[i]);
        }
        for (int i = 0; i < out.size(); i++) {
            if (out.get(i).equals("(") || out.get(i).equals(")") || out.get(i).equals("")) {
                out.remove(i);
            }
        }
        String[] output = new String[out.size()];
        for (int i = 0; i < out.size(); i++) {
            output[i] = out.get(i);
        }
        return output;
    }

    public static int priority(String input) {
        switch (input) {
            case ("+"):
                return 0;
            case ("-"):
                return 0;
            case ("*"):
                return 1;
            case ("/"):
                return 1;
            default:
                return -1;
        }
    }

    public static boolean include(String[] input, String x) {
        for (String e : input) {
            if (e.equals(x)) {
                return true;
            }
        }
        return false;
    }
}