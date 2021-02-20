import java.util.List;

public class Order {

<<<<<<< HEAD
	public int numberOfPizza;
	public int numOfTeamOf2;
	public int numOfTeamOf3;
	public int numOfTeamOf4;
	public List<Pizza> pizza;
	public StringBuilder output = new StringBuilder();
=======
  public int numberOfPizza;
  public int numOfTeamOf2;
  public int numOfTeamOf3;
  public int numOfTeamOf4;
  public List<Pizza> pizza;
>>>>>>> 0290dc703ce96bf141ee42a48e7b699cfb78c348

  public Order(int numberOfPizza, int numOfTeamOf2, int numOfTeamOf3, int numOfTeamOf4, List<Pizza> pizza) {
    this.numberOfPizza = numberOfPizza;
    this.numOfTeamOf2 = numOfTeamOf2;
    this.numOfTeamOf3 = numOfTeamOf3;
    this.numOfTeamOf4 = numOfTeamOf4;
    this.pizza = pizza;
  }

  public Order(Order order) {
    this.numberOfPizza = order.numberOfPizza;
    this.numOfTeamOf2 = order.numOfTeamOf2;
    this.numOfTeamOf3 = order.numOfTeamOf3;
    this.numOfTeamOf4 = order.numOfTeamOf4;
    this.pizza = order.pizza;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(String.format(
        "#noOfPizza: %-10d #2Team: %-10d #3Team: %-10d #4Team: %-10d",
        numberOfPizza, numOfTeamOf2, numOfTeamOf3, numOfTeamOf4));
    for (Pizza pizza1 : pizza) {
      stringBuilder.append(String.format(
          "%n%s", pizza1
      ));
    }
    return stringBuilder.toString();
  }
}
