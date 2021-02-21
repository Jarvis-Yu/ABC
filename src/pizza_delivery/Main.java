package pizza_delivery;

public class Main {

	public static void main(String[] args) {

		var o = Serialization.getE();

		while (Greedy.runOnce(o, o.numOfTeamOf2 > 0, o.numOfTeamOf3 > 0, o.numOfTeamOf4 > 0)) {
			o.delivers++;
		}
		var res = o.delivers + "\n" + o.output.toString();
		System.out.println(o.score);
		Serialization.writeResult("/Users/crait/Desktop/e.txt", res);

	}
}
