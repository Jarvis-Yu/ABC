package pizza_delivery;

import java.util.ArrayList;
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
      return baseSituation;
    }

    Situation newSituation = baseSituation.copyOf();
    List<Integer> newOrder = new ArrayList<>();
    Pizza combinedPizza = new Pizza(-1, Set.of());
    Pizza theOther = new Pizza(-1, Set.of());

    for (int i = 0; i < groupNo; i++) {
      final int upperBound = min(newSituation.pizza.size(), 5);
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

    return newSituation;
  }

  private static Situation forLooping(Order order, Situation[][][] d) {
    Situation nothing = new Situation(0, order);
    Situation maxSoFar = new Situation(0, order);

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

    return maxSoFar;
  }

  private static Situation queueLooping(Order order, Situation[][][] d) {
    List<TriPair<Integer, Integer, Integer>> combination = new ArrayList<>();
    combination.add(TriPair.pair(0, 0, 0));

    Situation nothing = new Situation(0, order);
    Situation maxSoFar = new Situation(0, order);

    int index = 0;
    while (combination.size() > index) {
      TriPair<Integer, Integer, Integer> c = combination.get(index);
      int i = c.first;
      int j = c.second;
      int k = c.third;
      d[i][j][k] = max(
          i > 0 ? addBaseOn(d[i - 1][j][k], 2) : nothing,
          j > 0 ? addBaseOn(d[i][j - 1][k], 3) : nothing,
          k > 0 ? addBaseOn(d[i][j][k - 1], 4) : nothing
      );
      maxSoFar = max(maxSoFar, d[i][j][k]);
      if (
          (i == 0 || d[i][j][k].getScore() > d[i - 1][j][k].getScore())
          && (j == 0 || d[i][j][k].getScore() > d[i][j - 1][k].getScore())
          && (k == 0 || d[i][j][k].getScore() > d[i][j][k - 1].getScore())
      ) {
        if (i < order.numOfTeamOf2) combination.add(TriPair.pair(i + 1, j, k));
        if (j < order.numOfTeamOf3) combination.add(TriPair.pair(i, j + 1, k));
        if (k < order.numOfTeamOf4) combination.add(TriPair.pair(i, j, k + 1));
      }
      index++;
    }
    return maxSoFar;
  }

  public static Situation theLoop(Order order) {
    Situation[][][] d = new Situation[order.numOfTeamOf2 + 1][order.numOfTeamOf3 + 1][order.numOfTeamOf4 + 1];
    d[0][0][0] = new Situation(0, order);

    return forLooping(order, d);
  }

  public static void dynamicProgramming(String givenOrder) {
    Order order = Parser.parse(givenOrder);
    order.pizza.sort(Pizza::compareTo);

    Situation maximum = theLoop(order);

    System.out.printf("%s%n%n", maximum);
    System.out.println(maximum.getScore());
  }

  public static void dynamicProgrammingTimer(String givenOrder) {
    long startTime = System.currentTimeMillis();
    dynamicProgramming(givenOrder);
    long duration = System.currentTimeMillis() - startTime;
    System.out.printf("Duration(s): %s", duration / 1000.0);
  }

  public static void main(String[] args) {
    dynamicProgrammingTimer(Data.tb);
  }
}

class Situation extends Order {

  private int score = 0;
  private List<Pair<Integer, List<Integer>>> orders = new ArrayList<>();

  public Situation(
      int score, int numberOfPizza, int numOfTeamOf2, int numOfTeamOf3, int numOfTeamOf4, List<Pizza> pizza) {
    super(numberOfPizza, numOfTeamOf2, numOfTeamOf3, numOfTeamOf4, pizza);
    this.score = score;
  }

  public Situation(
      int score,
      List<Pair<Integer, List<Integer>>> orders,
      int numberOfPizza, int numOfTeamOf2, int numOfTeamOf3, int numOfTeamOf4, List<Pizza> pizza) {
    super(numberOfPizza, numOfTeamOf2, numOfTeamOf3, numOfTeamOf4, pizza);
    this.score = score;
    this.orders = orders;
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
        score,
        new ArrayList<>(orders),
        super.numberOfPizza,
        super.numOfTeamOf2,
        super.numOfTeamOf3,
        super.numOfTeamOf4,
        new ArrayList<>(super.pizza));
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