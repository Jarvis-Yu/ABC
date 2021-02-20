import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GreedyAlgorithm {

  public static final String t = """
				5 1 2 1\s
				3 onion pepper olive
				3 mushroom tomato basil
				3 chicken mushroom pepper
				3 tomato mushroom basil
				2 chicken basil""";

  public static void main(String[] args) {

    var o = Parser.parse(t);
    o.pizza.sort(Pizza::compareTo);

    while(o.numOfTeamOf4 > 0 && o.pizza.size() > 4) {
      o.numOfTeamOf4 -= 1;
      Pizza originalPizza = new Pizza(-1, Set.of());

      List<Pizza> answerPizzas = new ArrayList<>();
      for (int i = 0; i < 4; i += 1) {
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
        originalPizza.merge(bestPizza);
      }

      System.out.print(4 + " ");
      for (Pizza pizzaUsed : answerPizzas) {
        System.out.print(pizzaUsed.getNo() + " ");
      }
      System.out.println();

    }
  }

}
