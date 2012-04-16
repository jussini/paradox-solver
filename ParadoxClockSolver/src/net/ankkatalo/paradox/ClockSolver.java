package net.ankkatalo.paradox;

import java.util.ArrayList;
import java.util.List;

public class ClockSolver {

	public static List<SolutionItem> solution = new ArrayList<SolutionItem> ();

	public static boolean solve(List<Slot> slots, int index, int depth) {
		// if this slot has already been checked
		Slot slot = slots.get(index);
		if (!slot.available()) {
			return false;
		}
		
		// if we have reached the maximum recursion depth, and this slot is still available,
		// then we've found a solution path
		if (depth == slots.size()) {
			solution.add(0, new SolutionItem(index, slot.value()));
			return true;			
		}
	
		
		// first try doing clockwise
		int ip = index + slots.get(index).value();
		if (ip >= slots.size()) {
			ip = ip - slots.size();
		}
		List<Slot> cloneSlots = cloneList(slots);
		cloneSlots.get(index).setAvailable(false);
		if (solve(cloneSlots, ip, depth + 1)) {
			solution.add(0, new SolutionItem(index, slots.get(index).value()));
			return true;
		}
				
		
		// if that didn't help, go counter clockwise
		int im = index - slots.get(index).value();
		if (im < 0) {
			im = slots.size() + im;
		}
		cloneSlots = cloneList(slots);
		cloneSlots.get(index).setAvailable(false);
		if (solve(cloneSlots, im, depth + 1)) {
			solution.add(0, new SolutionItem(index, slots.get(index).value()));
			return true;
		}
		
		// there was no solution from this branch. too bad...
		return false;
		
	}
	
	public static List<Slot> cloneList(List<Slot> list) {
		List<Slot> clone = new ArrayList<Slot>(list.size());
		for (Slot slot: list) clone.add(new Slot(slot));
		return clone;		
	}
}
