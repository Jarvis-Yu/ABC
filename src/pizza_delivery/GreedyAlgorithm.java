package pizza_delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GreedyAlgorithm {

  public static int score = 0;

  public static String t = """
				5 1 2 1\s
				3 onion pepper olive
				3 mushroom tomato basil
				3 chicken mushroom pepper
				3 tomato mushroom basil
				2 chicken basil""";

  public static void solve(Order o ,int numberPerTeam, int teamNumber) {

    t = System.in.toString();

    while(teamNumber > 0 && o.pizza.size() > numberPerTeam) {
      teamNumber -= 1;

      Pizza originalPizza = new Pizza(-1, Set.of());
      List<Pizza> answerPizzas = new ArrayList<>();

      for (int i = 0; i < numberPerTeam; i += 1) {
        int bestPizzaIndex = -1;
        int bestIngredientNumber = -1;
        for (int pizzaIndex = 0; pizzaIndex < 256 && pizzaIndex < o.pizza.size(); pizzaIndex ++ ) {
          if (originalPizza.diffIngresWith(o.pizza.get(pizzaIndex)) > bestIngredientNumber) {
            bestPizzaIndex = pizzaIndex;
            bestIngredientNumber = originalPizza.diffIngresWith(o.pizza.get(pizzaIndex));
          }
        }
        Pizza bestPizza = o.pizza.remove(bestPizzaIndex);
        answerPizzas.add(bestPizza);
        originalPizza = originalPizza.merge(bestPizza);
      }

      System.out.print(numberPerTeam + " ");
      for (Pizza pizzaUsed : answerPizzas) {
        System.out.print(pizzaUsed.getNo() + " ");
      }
      System.out.println();
      score += Math.pow(originalPizza.getSize(), 2);

    }
  }

  public static void main(String[] args) {

    var o = Parser.parse(t);
    o.pizza.sort(Pizza::compareTo);

    int nof4 = o.numberOfPizza/4;
    int nof3 = (o.numberOfPizza - nof4 * 4) / 3;
    int nof2 = (o.numberOfPizza - nof4 * 4 - nof3 * 3) / 2;
    System.out.println(nof4 + nof3 + nof2);

    solve(o, 4, o.numOfTeamOf4);
    solve(o, 3, o.numOfTeamOf3);
    solve(o, 2, o.numOfTeamOf2);

    System.out.print(score);


  }

}
