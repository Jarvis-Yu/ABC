package pizza_delivery;

public class TriPair<U, T, R> {

  public final U first;
  public final T second;
  public final R third;

  TriPair(U first, T second, R third) {
    this.first = first;
    this.second = second;
    this.third = third;
  }

  public static <U, T, R> TriPair pair(U first, T second, R third) {
    return new TriPair(first, second, third);
  }

  @Override
  public String toString() {
    return String.format("(%s, %s, %s)", first, second, third);
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof TriPair)) {
      return false;
    } else if (object == this) {
      return true;
    }
    TriPair<U, T, R> other = (TriPair<U, T, R>) object;
    return first.equals(other.first) && second.equals(other.second) && third.equals(other.third);
  }

  @Override
  public int hashCode() {
    return first.hashCode() + second.hashCode() + third.hashCode();
  }
}
