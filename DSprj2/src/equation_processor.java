import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class equation_processor<T> {

	cursor<String> equation_c;
	int index;

	public int getIndex() {
		return equation_c.stack_index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public cursor<String> getEquationCursor() {
		return equation_c;
	}

	public void setEquationCursor(cursor<String> equationCursor) {
		this.equation_c = equationCursor;
	}

	public equation_processor() {
		equation_c = new cursor<>();
	}

	public void load(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			boolean inside = false;

			while ((line = br.readLine()) != null) {
				line = line.trim();

				if (line.startsWith("<section>")) {
					inside = true;
					equation_c.new_stack();
				} else if (line.startsWith("</section>")) {
					inside = false;
				} else if (inside && line.startsWith("<equation>")) {

					String equation = line.substring("<equation>".length(), line.indexOf("</equation>")).trim();
					equation_c.getCurrStack().push(equation);
				}
			}
			getequation();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void next() {
		equation_c.nextStack();
		int index = getIndex();

		if (index >= 0 && index < equation_c.size()) {
			getequation();
		} else {
			System.out.println("No more sections available.");
		}
	}

	public void prev() {
		stack_linkedlist<String> curr_sec = equation_c.getCurrStack();

		if (!curr_sec.isEmpty()) {
			String curr_equation = curr_sec.peek();
			System.out.println("Equation: " + curr_equation + "\nEvaluation: " + infix_to_postfix(curr_equation));
		} else {
			System.out.println("Current section is empty");
		}
	}

	public String infix_to_postfix(String exp) {
		stack_linkedlist<Integer> operand = new stack_linkedlist<>();
		stack_linkedlist<Character> operator = new stack_linkedlist<>();

		String[] parts = exp.split("\\s+");

		for (String part : parts) {
			if (Character.isDigit(part.charAt(0))) {
				operand.push(Integer.parseInt(part));
			} else {
				char c = part.charAt(0);
				if (c == '(') {
					operator.push(c);
				} else if (c == ')') {
					while (!operator.isEmpty() && operator.peek() != '(') {
						applyOperator(operand, operator.pop().getData());
					}
					if (!operator.isEmpty() && operator.peek() == '(') {
						operator.pop();
					}
				} else {
					while (!operator.isEmpty() && p(c) <= p(operator.peek())) {
						applyOperator(operand, operator.pop().getData());
					}
					operator.push(c);
				}
			}
		}

		while (!operator.isEmpty()) {
			applyOperator(operand, operator.pop().getData());
		}

		return operand.pop().toString();
	}

	public String postfixToPrefixAndEvaluate(String expression) {
		stack_linkedlist<String> prefix = new stack_linkedlist<>();
		stack_linkedlist<Integer> operand = new stack_linkedlist<>();

		String[] parts = expression.split("\\s+");

		for (String part : parts) {
			if (Character.isDigit(part.charAt(0))) {
				prefix.push(part);
				operand.push(Integer.parseInt(part));
			} else {
				String o = part;
				Node<String> n1 = prefix.pop();
				Node<String> n2 = prefix.pop();
				String o1 = n1.getData();
				String o2 = n2.getData();
				String result = o + " " + o1 + " " + o2;
				prefix.push(result);

				Node<Integer> int_n1 = operand.pop();
				Node<Integer> intn2 = operand.pop();
				int int_o1 = int_n1.getData();
				int into2 = intn2.getData();

				int intResult = preform(o.charAt(0), int_o1, into2);
				operand.push(intResult);
			}
		}

		return prefix.pop().getData();
	}

	private int preform(char o, int o1, int o2) {
		switch (o) {
		case '+':
			return o1 + o2;
		case '-':
			return o1 - o2;
		case '*':
			return o1 * o2;
		case '/':
			return o1 / o2;
		case '^':
			return (int) Math.pow(o1, o2);
		default:
			throw new IllegalArgumentException("Unsupported operator: " + o);
		}

	}

	public void applyOperator(stack_linkedlist<Integer> operand, char o) {

		Node<Integer> n1 = operand.pop();
		Node<Integer> n2 = operand.pop();
		int o1 = n1.getData();
		int o2 = n2.getData();
		int result = preform(o, o1, o2);
		operand.push(result);
	}

	public String getequation() {
		stack_linkedlist<String> curr_section = equation_c.getCurrStack();
		return curr_section.peek();
	}

	private int p(char operator) {
		switch (operator) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		default:
			return 0;
		}
	}
}
