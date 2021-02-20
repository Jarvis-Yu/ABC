import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Pizza implements Comparator<Pizza>{

  private final int no;
  private final Set<String> ingres;

  public Pizza(int no, Set<String> ingres) {
    this.no = no;
    this.ingres = ingres;
  }

  public int getNo() {
    return no;
  }

  public Set<String> getIngres() {
    return ingres;
  }

  public int getSize() {
    return ingres.size();
  }

  // 返回两个Pizza总共的不同ingres种类数
  public int diffIngresWith(Pizza other) {
    Set<String> all = new HashSet<>(ingres);
    all.addAll(other.ingres);
    return all.size();
  }

  @Override
  public String toString() {
    return this.ingres.toString();
  }

  @Override
  public int compare(Pizza o1, Pizza o2) {
    return o1.getSize() - o2.getSize();
  }
}
