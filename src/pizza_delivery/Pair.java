package pizza_delivery;

public class Pair<U, T> {

  public final U first;
  public final T second;

  Pair(U first, T second) {
    this.first = first;
    this.second = second;
  }

  public static <U, T> Pair pair(U first, T second) {
    return new Pair(first, second);
  }

  @Override
  public String toString() {
    return String.format("(%s, %s)", first, second);
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Pair)) {
      return false;
    } else if (object == this) {
      return true;
    }
    Pair<U, T> other = (Pair<U, T>) object;
    return first.equals(other.first) && second.equals(other.second);
  }

  @Override
  public int hashCode() {
    return first.hashCode() + second.hashCode();
  }
}
