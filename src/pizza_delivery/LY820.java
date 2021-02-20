package pizza_delivery;

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
    if (baseSituation.pizza.size() < groupNo) {
      return new Situation(
          0, new Order(0, 0, 0, 0, List.of()));
    }

    Pizza startPizza = baseSituation.pizza.get(0);
    final int size = baseSituation.pizza.size();
    Situation newSituation = baseSituation.copyOf();
    newSituation.pizza.remove(startPizza);
    List<Integer> newOrder = new ArrayList<>(List.of(startPizza.getNo()));

    Pizza combinedPizza = startPizza.copyOf();
    Pizza theOther = new Pizza(-1, Set.of());

    for (int i = 1; i < groupNo; i++) {
      final int upperBound = min(newSituation.pizza.size(), 10);
      final List<Pizza> backUps = newSituation.pizza.subList(0, upperBound);

      theOther = backUps.get(0);
      for (int j = 1; j < upperBound; j++) {
        if (combinedPizza.mutualIngresWith(backUps.get(j)) < combinedPizza.mutualIngresWith(theOther)) {
          theOther = backUps.get(j);
        }
      }

      newOrder.add(theOther.getNo());
      combinedPizza = combinedPizza.merge(theOther);

      newSituation.pizza.remove(theOther);
    }

    newSituation.addOrder(combinedPizza.getSize(), newOrder);

    System.out.printf("%n%nStart: %s", startPizza);
    System.out.printf("%nNewSituation: %d%n%s%n%s", newSituation.getScore(), newSituation, newSituation.pizza);

    return newSituation;
  }

  public static void main(String[] args) {
    Order order = Parser.parse(Main.ta);
    order.pizza.sort(Pizza::compareTo);
    System.out.println(order);

    Situation nothing = new Situation(0, order);
    Situation maxSoFar = new Situation(0, order);

    Situation[][][] d = new Situation[order.numOfTeamOf2 + 1][order.numOfTeamOf3 + 1][order.numOfTeamOf4 + 1];
    d[0][0][0] = new Situation(0, order);

    for (int i = 0; i <= order.numOfTeamOf2; i++) {
      for (int j = 0; j <= order.numOfTeamOf3; j++) {
        for (int k = 0; k <= order.numOfTeamOf4; k++) {
          d[i][j][k] = max(
              i > 0 ? addBaseOn(d[i - 1][j][k], 2) : nothing,
              j > 0 ? addBaseOn(d[i][j - 1][k], 3) : nothing,
              k > 0 ? addBaseOn(d[i][j][k - 1], 4) : nothing
              );
          maxSoFar = max(maxSoFar, d[i][j][k]);
        }
      }
    }

    for (int i = 0; i < order.numOfTeamOf2; i++) {
      for (int j = 0; j < order.numOfTeamOf3; j++) {
        for (int k = 0; k < order.numOfTeamOf4; k++) {
          if (d[i][j][k].getScore() > 0) {
            System.out.printf("%n%d %d %d: %s", i, j, k, d[i][j][k].getScore());
          }
        }
      }
    }

    System.out.printf("%n%nFinally: %s%n%s", maxSoFar.getScore(), maxSoFar);
  }
}

class Situation extends Order {

  private int score = 0;
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
    score = 0;
    for (Pair<Integer, List<Integer>> order : orders) {
      int temp = order.first;
      score += temp * temp;
    }
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
      stringBuilder.append(String.format("%n%d", order.second.size()));
      for (Integer integer : order.second) {
        stringBuilder.append(String.format(" %d", integer));
      }
    }
    return stringBuilder.toString();
  }
}