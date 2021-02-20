//import java.util.List;
//
//public class LY820 {
//
//  public static void main(String[] args) {
//    Order order = Parser.parse(Main.t);
//    System.out.println(order);
//
//    Situation[][][] d = new Situation[order.numOfTeamOf2][order.numOfTeamOf3][order.numOfTeamOf4];
//    d[0][0][0] = new Situation(0);
//  }
//}
//
//class Situation extends Order {
//
//  private final int score;
//
//  public Situation(
//      int score, int numberOfPizza, int numOfTeamOf2, int numOfTeamOf3, int numOfTeamOf4, List<Pizza> pizza) {
//    super(numberOfPizza, numOfTeamOf2, numOfTeamOf3, numOfTeamOf4, pizza);
//    this.score = score;
//  }
//
//  public Situation(int score, Order order) {
//    super(order);
//    this.score = score;
//  }
//
//  public int getScore() {
//    return score;
//  }
//}