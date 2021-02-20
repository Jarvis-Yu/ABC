import java.util.HashSet;
import java.util.Set;

public class Pizza implements Comparable<Pizza>{

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

  // 返回一个新的pizza，是2~4个pizza的ingres的总和，序号为-1
  public Pizza merge(Pizza other) {
    Set<String> all = new HashSet<>(ingres);
    all.addAll(other.ingres);
    return new Pizza(-1, all);
  }

  public Pizza merge(Pizza other1, Pizza other2) {
    return this.merge(other1).merge(other2);
  }

  public Pizza merge(Pizza other1, Pizza other2, Pizza other3) {
    return this.merge(other1, other2).merge(other3);
  }

  @Override
  public String toString() {
    return this.ingres.toString();
  }

  @Override
  public int compareTo(Pizza o) {
    return o.getSize() - getSize();
  }
}
