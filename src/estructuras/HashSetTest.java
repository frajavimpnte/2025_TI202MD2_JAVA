package estructuras;

import java.util.HashSet;

public class HashSetTest {
    public static void main(String[] args) {
        System.out.println("Crear conjunto a:");
        HashSet<Integer> a  = new HashSet<Integer>();
        a.add(1);
        a.add(2);
        a.add(3);        
        System.out.println(a);
        
        System.out.println("Crear conjunto b:");
        HashSet<Integer> b  = new HashSet<Integer>();
        b.add(3);
        b.add(4);        
        System.out.println(b);
        
        System.out.print("a U b: ");
        HashSet<Integer> aClone  = (HashSet)a.clone();
        aClone.addAll(b);
        System.out.println(aClone);
        System.out.println(a);
          
        System.out.print(" '3' e a: ");
        System.out.println(aClone.contains(3));
        
        System.out.println("a U b - {3}");
        aClone.remove(3);
        System.out.println(aClone);        
    }
}
