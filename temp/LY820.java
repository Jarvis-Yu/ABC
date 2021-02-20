import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static java.lang.Math.min;

public class LY820 {

  public static Situation max(Situation s1, Situation s2) {
    return s1.getScore() > s2.getScore() ? s1 : s2;
  }

  public static Situation max(Situation s1, Situation s2, Situation s3) {
    return max(max(s1, s2), s3);
  }

  public static Situation addBaseOn(Situation baseSituation, int groupNo) {
    Pizza startPizza = baseSituation.pizza.get(0);
    final int size = baseSituation.pizza.size();
    Situation newSituation = baseSituation.copyOf();
    newSituation.pizza.remove(startPizza.getNo());
    List<Pizza> newOrder = new ArrayList<>(List.of(startPizza));

    final int upperBound = min(size, 10);
    final List<Pizza> backUps = baseSituation.pizza.subList(1, upperBound);


    backUps.sort(new Comparator<Pizza>() {
      @Override
      public int compare(Pizza p1, Pizza p2) {
        return startPizza.mutualIngresWith(p2) - startPizza.mutualIngresWith(p1);
      }
    });

    System.out.println(startPizza);
    System.out.println(backUps);

    return null;
  }

  public static void main(String[] args) {
    Order order = Parser.parse(Main.t);
    order.pizza.sort(Pizza::compareTo);
    System.out.println(order);

    Situation nothing = new Situation(0, order);
    Situation maxSoFar = new Situation(0, order);

    Situation[][][] d = new Situation[order.numOfTeamOf2][order.numOfTeamOf3][order.numOfTeamOf4];
    d[0][0][0] = new Situation(0, order);

    d[1][0][0] = addBaseOn(d[0][0][0], 2);
//    for (int i = 0; i < order.numOfTeamOf2; i++) {
//      for (int j = 0; j < order.numOfTeamOf3; j++) {
//        for (int k = 0; k < order.numOfTeamOf4; k++) {
//          d[i][j][k] = max(
//              i > 0 ? addBaseOn(d[i - 1][j][k], 2) : nothing,
//              j > 0 ? addBaseOn(d[i][j - 1][k], 3) : nothing,
//              k > 0 ? addBaseOn(d[i][j][k - 1], 4) : nothing
//              );
//          maxSoFar = max(maxSoFar, d[i][j][k]);
//        }
//      }
//    }

    System.out.println(maxSoFar);
  }
}

class Situation extends Order {

  private final int score;
  private final List<Pair<Integer, List<Integer>>> orders = new ArrayList<>();

  public Situation(
      int score, int numberOfPizza, int numOfTeamOf2, int numOfTeamOf3, int numOfTeamOf4, List<Pizza> pizza) {
    super(numberOfPizza, numOfTeamOf2, numOfTeamOf3, numOfTeamOf4, pizza);
    this.score = score;
  }

  public Situation(int score, Order order) {
    super(order);
    this.score = score;
  }

  public int getScore() {
    return score;
  }

  public void addOrder(int num, List<Integer> pizzas) {
    orders.add(Pair.pair(num, pizzas));
  }

  public Situation copyOf() {
    return new Situation(
        score, super.numberOfPizza, super.numOfTeamOf2, super.numOfTeamOf3, super.numOfTeamOf4, super.pizza);
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(orders.size());
    for (Pair<Integer, List<Integer>> order : orders) {
      stringBuilder.append(String.format("%n%d", order.first));
      for (Integer integer : order.second) {
        stringBuilder.append(String.format(" %d", integer));
      }
    }
    return stringBuilder.toString();
  }
}