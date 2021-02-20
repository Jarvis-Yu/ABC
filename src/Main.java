public class Main {

	public static void main(String[] args) {
		String t = """
				5 1 2 1\s
				3 onion pepper olive
				3 mushroom tomato basil
				3 chicken mushroom pepper
				3 tomato mushroom basil
				2 chicken basil""";

		var o = Parser.parse(t);
		System.out.println(o);

	}
}
