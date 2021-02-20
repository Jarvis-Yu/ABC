public class Main {

	public static final String t = """
				5 1 2 1\s
				3 onion pepper olive
				3 mushroom tomato basil
				3 chicken mushroom pepper
				3 tomato mushroom basil
				2 chicken basil""";

	public static void main(String[] args) {

		var o = Parser.parse(t);

		while (Greedy.runOnce(o, o.numOfTeamOf2 > 0, o.numOfTeamOf3 > 0, o.numOfTeamOf4 > 0)) {
			System.out.println("Run...");
		}
		System.out.println(o.output.toString());

	}
}
