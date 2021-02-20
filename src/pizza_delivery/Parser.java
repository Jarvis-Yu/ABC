package pizza_delivery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Parser {

	public static Order parse(String str) {
		var pizzas = new ArrayList<Pizza>();
		var lines = str.split("\n");
		var line1 = lines[0].split(" ");
		for (int i = 1; i < lines.length; i++) {
			var lineN = lines[i].split(" ");
			pizzas.add(new Pizza(i - 1, new HashSet<String>(Arrays.stream(lineN).skip(1).collect(Collectors.toList()))));
		}

		Collections.sort(pizzas);

		return new Order(Integer.parseInt(line1[0]), Integer.parseInt(line1[1]), Integer.parseInt(line1[2]), Integer.parseInt(line1[3]), pizzas);
	}
}
