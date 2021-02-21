package pizza_delivery;

public class Greedy {

	public static final int DEPTH = 128;

	public static boolean runOnce(Order order, boolean fu, boolean mi, boolean yo) {
		if (order.pizza.isEmpty()) {
			return false;
		}

		var originalPizza = order.pizza.get(0);
		var fstPizza = order.pizza.get(0);
		var no = fstPizza.getNo();
		Pizza sndPizza = fstPizza;
		Pizza trdPizza = fstPizza;
		Pizza fthPizza = fstPizza;
		int[] used = {fstPizza.getNo(), fstPizza.getNo(), fstPizza.getNo()};
		int futatsu = 0;
		int mittsu = 0;
		int yottsu = 0;
		var maxSize = fstPizza.getSize();
		for (int i = 1; i <= Math.min(DEPTH, order.pizza.size() - 1); i++) {
			var otherPizza = order.pizza.get(i);
			var otherSize = fstPizza.diffIngresWith(otherPizza);
			if (otherSize >= maxSize) {
				maxSize = otherSize;
				sndPizza = otherPizza;
				used[0] = otherPizza.getNo();
			}
		}
		futatsu = maxSize;

		fstPizza = fstPizza.merge(sndPizza);
		maxSize = fstPizza.getSize();

		for (int i = 1; i <= Math.min(DEPTH, order.pizza.size() - 1); i++) {
			var otherPizza = order.pizza.get(i);
			var otherNo = otherPizza.getNo();
			if (otherNo == used[0]) {
				continue;
			}
			var otherSize = fstPizza.diffIngresWith(otherPizza);
			if (otherSize >= maxSize) {
				maxSize = otherSize;
				trdPizza = otherPizza;
				used[1] = otherPizza.getNo();
			}
		}
		mittsu = maxSize;

		fstPizza = fstPizza.merge(trdPizza);
		maxSize = fstPizza.getSize();

		for (int i = 1; i <= Math.min(DEPTH, order.pizza.size() - 1); i++) {
			var otherPizza = order.pizza.get(i);
			var otherNo = otherPizza.getNo();
			if (otherNo == used[0] || otherNo == used[1]) {
				continue;
			}
			var otherSize = fstPizza.diffIngresWith(otherPizza);
			if (otherSize >= maxSize) {
				maxSize = otherSize;
				fthPizza = otherPizza;
				used[2] = otherPizza.getNo();
			}
		}
		yottsu = maxSize;

		if (yottsu > mittsu && yo && fthPizza != originalPizza) {
			order.numOfTeamOf4--;
			order.pizza.remove(originalPizza);
			order.pizza.remove(sndPizza);
			order.pizza.remove(trdPizza);
			order.pizza.remove(fthPizza);
			order.output.append("4 ").append(no).append(" ").append(used[0]).append(" ").append(used[1]).append(" ").append(used[2]).append("\n");
			order.score += yottsu * yottsu;
			return true;
		} else if (mittsu > futatsu && mi && trdPizza != originalPizza) {
			order.numOfTeamOf3--;
			order.pizza.remove(originalPizza);
			order.pizza.remove(sndPizza);
			order.pizza.remove(trdPizza);
			order.output.append("3 ").append(no).append(" ").append(used[0]).append(" ").append(used[1]).append("\n");
			order.score += mittsu * mittsu;
			return true;
		} else if (fu && sndPizza != originalPizza) {
			order.numOfTeamOf2--;
			order.pizza.remove(originalPizza);
			order.pizza.remove(sndPizza);
			order.output.append("2 ").append(no).append(" ").append(used[0]).append("\n");
			order.score += futatsu * futatsu;
			return true;
		}

		return false;
	}
}
