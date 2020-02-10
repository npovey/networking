package top_n;

//public class TopN {
//
//	
//}

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TopN {
   public static  List<List<Integer>> top_n(List<List<Integer>> list) {
//      // SortedMap is an interface
//      // Range based queries
//      List<List<Integer>> list  = new ArrayList<>();
//      
//      List<Integer> coordinate = new ArrayList<>();
//      coordinate.add(1);
//      coordinate.add(1);
//      list.add(coordinate);
//      
//      List<Integer> coordinate2 = new ArrayList<>();
//      coordinate2.add(2);
//      coordinate2.add(2);
//      list.add(coordinate2);
//      
//      List<Integer> coordinate3 = new ArrayList<>();
//      coordinate3.add(3);
//      coordinate3.add(3);
//      list.add(coordinate3);
//   
//      List<Integer> coordinate4 = new ArrayList<>();
//      coordinate4.add(4);
//      coordinate4.add(1);
//      list.add(coordinate4);

	  SortedMap<Double, List<Integer>> map = new TreeMap<>();

      for (int i = 0; i < list.size(); i++) {
        List<Integer> l = list.get(i);
        double distance = Math.sqrt((int)l.get(0) * (int)l.get(0) + (int)l.get(1) * (int)l.get(1));
         map.put(distance, l);
      }
      
      int n = 0;
      List<List<Integer>> list2  = new ArrayList<>();
      for (Map.Entry<Double, List<Integer>> entry : map.entrySet()) {
    	 list2.add(entry.getValue());
         System.out.println(entry.getKey() + "/" + entry.getValue());
         n++;
         if (n == 3){
            return list2;
         }
      }
      return list2;
   }
}
