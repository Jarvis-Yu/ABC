public class LY820 {

  public static void main(String[] args) {
    Order order = Parser.parse(Main.t);
    System.out.println(order);
  }
}
