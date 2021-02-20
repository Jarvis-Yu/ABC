package pizza_delivery;

public class Main {

	public static void main(String[] args) {

		var o = Parser.parse(Data.tb);

		while (Greedy.runOnce(o, o.numOfTeamOf2 > 0, o.numOfTeamOf3 > 0, o.numOfTeamOf4 > 0)) {
			// System.out.println("Run...");
		}
		System.out.println(o.output.toString());
		System.out.println(o.score);

	}
}
