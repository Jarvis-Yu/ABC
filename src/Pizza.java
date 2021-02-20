import java.util.HashSet;
import java.util.Set;

public class Pizza {

  private final Set<String> ingres;

  public Pizza(Set<String> ingres) {
    this.ingres = ingres;
  }

  public Set<String> getIngres() {
    return ingres;
  }

  // 返回两个Pizza总共的不同ingres种类数
  public int diffIngresWith(Pizza other) {
    Set<String> diff = new HashSet<>(ingres);
    diff.removeAll(other.ingres);
    return diff.size() + other.ingres.size();
  }
}
