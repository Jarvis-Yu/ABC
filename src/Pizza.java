import java.util.HashSet;
import java.util.Set;

public class Pizza implements Comparable<Pizza>{

  private final int no;
  private final Set<String> ingres;
  public String output;

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
    return this.merge(other).getSize();
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

  @Override
  public int hashCode() {
    return no + ingres.hashCode();
  }

  @Override
  public boolean equals(Object object) {
      if (!(object instanceof Pizza)) {
        return false;
      } else if (object == this) {
        return true;
      }
      Pizza other = (Pizza) object;
      return no == other.no && ingres.equals(other.ingres);
  }
}
