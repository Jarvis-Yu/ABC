import java.util.List;

public class Order {

	public int numberOfPizza;
	public int numOfTeamOf2;
	public int numOfTeamOf3;
	public int numOfTeamOf4;
	public List<Pizza> pizza;

	public Order(int numberOfPizza, int numOfTeamOf2, int numOfTeamOf3, int numOfTeamOf4, List<Pizza> pizza) {
		this.numberOfPizza = numberOfPizza;
		this.numOfTeamOf2 = numOfTeamOf2;
		this.numOfTeamOf3 = numOfTeamOf3;
		this.numOfTeamOf4 = numOfTeamOf4;
		this.pizza = pizza;
	}

}
