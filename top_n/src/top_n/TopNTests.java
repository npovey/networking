// command + shift + F -> outoformat.
package top_n;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

class TopNTests {

	@Test
	void test() {
		// SortedMap is an interface
		// Range based queries
		List<List<Integer>> list = new ArrayList<>();

		List<Integer> coordinate = new ArrayList<>();
		coordinate.add(1);
		coordinate.add(1);
		list.add(coordinate);

		List<Integer> coordinate2 = new ArrayList<>();
		coordinate2.add(2);
		coordinate2.add(2);
		list.add(coordinate2);

		List<Integer> coordinate3 = new ArrayList<>();
		coordinate3.add(3);
		coordinate3.add(3);
		list.add(coordinate3);

		List<Integer> coordinate4 = new ArrayList<>();
		coordinate4.add(4);
		coordinate4.add(1);
		list.add(coordinate4);

		// fail("Not yet implemented");
		List<List<Integer>> list2 = new ArrayList<>();
		coordinate = new ArrayList<>();
		coordinate.add(1);
		coordinate.add(1);
		list2.add(coordinate);

		coordinate2 = new ArrayList<>();
		coordinate2.add(2);
		coordinate2.add(2);
		list2.add(coordinate2);

		coordinate4 = new ArrayList<>();
		coordinate4.add(4);
		coordinate4.add(1);
		list2.add(coordinate4);

		assertEquals(list2, TopN.top_n(list));
	}

	@Test
	// testing is the list is shorter than n
	void test2() {
		// SortedMap is an interface
		// Range based queries
		List<List<Integer>> list = new ArrayList<>();

		List<Integer> coordinate = new ArrayList<>();
		coordinate.add(1);
		coordinate.add(1);
		list.add(coordinate);

		List<Integer> coordinate2 = new ArrayList<>();
		coordinate2.add(2);
		coordinate2.add(2);
		list.add(coordinate2);

		

		List<List<Integer>> list2 = new ArrayList<>();
		coordinate = new ArrayList<>();
		coordinate.add(1);
		coordinate.add(1);
		list2.add(coordinate);

		coordinate2 = new ArrayList<>();
		coordinate2.add(2);
		coordinate2.add(2);
		list2.add(coordinate2);

		
		assertEquals(list2, TopN.top_n(list));
	}

}
