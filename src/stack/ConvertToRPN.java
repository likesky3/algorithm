package stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class ConvertToRPN {

	public static void main(String[] args) {
		ConvertToRPN obj = new ConvertToRPN();
		String s = "( a + b ) * c - d / e + ( f - ( g - h ) )";
		String[] array = s.split(" ");
		ArrayList<String> result = obj.convertToRPN(array);
		for (String item : result) {
			System.out.print(item + " ");
		}
		System.out.println();
	}
	
	
	public ArrayList<String> convertToRPN(String[] expression) {
        ArrayList<String> rpn = new ArrayList<>();
        if (expression == null || expression.length == 0) {
            return rpn;
        }
        
        Deque<String> opStack = new ArrayDeque<>();
        for (String elem : expression) {
            if (elem.equals("+") || elem.equals("-")) {
            	while (!opStack.isEmpty() && !opStack.peek().equals("(")) {
            		rpn.add(opStack.pop());
            	}
            	opStack.push(elem);
            } else if (elem.equals("*") || elem.equals("/")) {
            	while (!opStack.isEmpty() && (opStack.peek().equals("*") || opStack.peek().equals("/"))) {
            		rpn.add(opStack.pop());
            	}
            	opStack.push(elem);
            } else if (elem.equals("(")) {
            	opStack.push(elem);
            } else if (elem.equals(")")) {
            	while (!opStack.isEmpty() && !opStack.peek().equals("(")) {
            		rpn.add(opStack.pop());
            	}
            	opStack.pop();
            } else {
            	rpn.add(elem);
            }
        }
        while (!opStack.isEmpty()) {
        	rpn.add(opStack.pop());
        }
        return rpn;
    }

}
