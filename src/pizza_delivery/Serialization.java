
package pizza_delivery;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialization {

  public static void writeResult(String filePath, String result) {
    try {
      File file = new File(filePath);
      if (file.createNewFile()) {
        FileWriter fw = new FileWriter(file);
        fw.write(result);
        fw.close();
      }
    } catch (Exception e) {
      System.out.println("写入文件出错");
      e.printStackTrace();
    }
  }

  public static Order parseOneFile(String filePath) {
    List<String> list = new ArrayList<>();
    try {
      File file = new File(filePath);
      if (file.isFile() && file.exists()) {
        InputStreamReader read = new InputStreamReader(new FileInputStream(file));
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        while ((lineTxt = bufferedReader.readLine()) != null) {
          list.add(lineTxt);
        }
        bufferedReader.close();
        read.close();
      }
    } catch (Exception e) {
      System.out.println("读取文件内容出错");
      e.printStackTrace();
    }
    String readed_string = String.join("\n", list);
    return Parser.parse(readed_string);
  }

    public static Order getA() {
      return parseOneFile("data/a_example");
    }

    public static Order getB() {
      return parseOneFile("data/b_little_bit_of_everything.in");
    }

    public static Order getC() {
      return parseOneFile("data/c_many_ingredients.in");
    }

    public static Order getD() {
      return parseOneFile("data/d_many_pizzas.in");
    }

    public static Order getE() {
      return parseOneFile("data/e_many_teams.in");
    }
}