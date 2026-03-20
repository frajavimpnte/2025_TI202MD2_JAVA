package tema2arboles;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import net.datastructures.AdjacencyMapGraph;
import net.datastructures.ChainHashMap;
import net.datastructures.Edge;
import net.datastructures.Entry;
import net.datastructures.Graph;
import net.datastructures.GraphAlgorithms;
import static net.datastructures.GraphExamples.graphFromEdgelist;
import net.datastructures.Map;
import net.datastructures.SortedTableMap;
import net.datastructures.Vertex;

class MyVertex<V> implements Vertex {
    private V element;

    public MyVertex(V element) {
        this.element = element;
    }

    @Override
    public V getElement() {
        return element;
    }
}

class MyEdge<V> implements Edge {
    private V element;

    public MyEdge(V element) {
        this.element = element;
    }
   
    @Override
    public Object getElement() {
        return element;
    }
}

public class ArbolesGrafosTest {
    public static void main(String ...args) {
        //mapTest();
        //graphSample1();
        //graphSampleFromEdgeListDFS();
        graphSampleFromEdgeListDijkstra();
        //graphWeightedFromList();
        //graphSpanningTreeSusan10_7_1();
    }
    
    
    public static void graphSampleFromEdgeListDijkstra() {
        // CREAR UN GRAFO A PARTIR DE ARISTAS
        System.out.println("G====================================");
        String[][] edges = {
        {"A1","A2"},{"A2","A3"},{"A3","A4"}, {"A4","A5"},{"A5", "A6"},
        {"A1","B1"},{"A3","B3"},{"A6", "B6"},
        {"B1","C1"},{"B3","C3"},{"B6","C6"},
        {"C3","C4"},{"C4","C5"},{"C5","C6"},
        {"C1","D1"},{"C5","D5"},{"C6","D6"},
        {"D5","D6"},
        {"D1","E1"},{"D5","E5"}, {"D6","E6"},
        {"E1","E2"},{"E2","E3"},{"E3","E4"}, {"E4","E5"},{"E5", "E6"},
        };
        Graph<String, Integer> G = graphFromEdgelist(edges, false);
        System.out.println(G.toString());
           
        
        
        Iterator<Vertex<String>> iteratorVertex = G.vertices().iterator();
        Vertex<String> ori = null;
        while (iteratorVertex.hasNext()) {
             ori = iteratorVertex.next();
            if (ori.getElement() == "B3")
                break;
        }
        //Vertex<String> s = G.vertices().iterator().next();// A1
        Vertex<String> s = ori;
        System.out.println("start vertex:" + s.getElement());
        
        Map<Vertex<String>, Integer> res =  GraphAlgorithms.<String>shortestPathLengths(G,s);
        Iterable<Vertex<String>> iterableRes = res.keySet();
        Iterator<Vertex<String>> iteratorRes = iterableRes.iterator();
        
        while( iteratorRes.hasNext() ) {
             Vertex<String> key = iteratorRes.next();
             System.out.println(
                     "vertice: " + key.getElement() + 
                     " length: " + res.get(key) 
             );
        }
    }
    
    public static void mapTest() {
        Map<String,Integer> freq1 = new ChainHashMap<>();
        freq1.put("KEY1", 1);
        freq1.put("KEY2", 2);
        freq1.put("KEY3", 3);
        System.out.println(freq1.toString());
        
        Map<String,Integer> freq2 =  new SortedTableMap<>();
        freq2.put("KEY1", 1);
        freq2.put("KEY2", 2);
        freq2.put("KEY3", 3);
        System.out.println(freq2.toString());
        
        Map< MyVertex<String>,MyEdge<Integer>> forest =  new ChainHashMap<>();
        //Map< MyVertex<String>,MyEdge<Integer>> forest =  new SortedTableMap<>();
        forest.put(new MyVertex("Vertex1"), new MyEdge(1));
        forest.put(new MyVertex("Vertex2"), new MyEdge(2));
        forest.put(new MyVertex("Vertex3"), new MyEdge(3));
        System.out.println(forest.toString());
    }
    
    
    public static void graphSpanningTreeSusan10_7_1() {
    // CREAR EL GRAFO DE LA FIGURA 10.7.1 SUSAN minimun spanning tree
        System.out.println("G2====================================");
        String[][] edges2 = { 
            {"Minneapolis", "Chicago"}, {"Minneapolis", "Nashville"},
            {"Milwaukee", "Chicago"}, {"Milwaukee", "Louisville"}, 
            {"Detroit", "Cincinnati"}, {"Detroit", "Louisville"},
            {"Chicago", "St. Louis"}, {"Chicago", "Louisville"},
            {"Cincinnati", "Louisville"}, {"St. Louis", "Louisville"},
            {"Louisville", "Nashville"}            
        };
        Graph<String, Integer> G2 = graphFromEdgelist(edges2, false);
        System.out.println(G2.toString());
    }
    public static void graphWeightedFromList() {
         // CREAR EL GRAFO CON PESOS
        System.out.println("Graph weithed from list===============================");
        String[][] edges3 = {{"A", "B", "1"}, {"A","C","2"}};
        Graph<String, Integer> G3 = graphFromEdgelist(edges3, false);
        System.out.println(G3.toString());
    }
    public static void graphSampleFromEdgeListDFS() {
        // CREAR UN GRAFO A PARTIR DE ARISTAS
        System.out.println("G====================================");
        String[][] edges = {
        {"BOS","SFO"}, {"BOS","JFK"}, {"BOS","MIA"}, {"JFK","BOS"},
        {"JFK","DFW"}, {"JFK","MIA"}, {"JFK","SFO"}, {"ORD","DFW"},
        {"ORD","MIA"}, {"LAX","ORD"}, {"DFW","SFO"}, {"DFW","ORD"},
        {"DFW","LAX"}, {"MIA","DFW"}, {"MIA","LAX"}};
        Graph<String, Integer> G = graphFromEdgelist(edges, true);
        System.out.println(G.toString());
           
        System.out.println("DFS :::::::::::");
        Set<Vertex<String>> known = new HashSet<>();
        Map<Vertex<String>,Edge<Integer>> forest =  new ChainHashMap<Vertex<String>,Edge<Integer>>();
       
        Vertex<String> s = G.vertices().iterator().next();// BOS
        System.out.println("start vertex:" + s.getElement());
        
        GraphAlgorithms.<String, Integer>DFS(G, s, known, forest);
        System.out.println("recorrido: " +  known);
        System.out.println("forest: " +  forest);
        
        System.out.println("known:");
        Iterator<Vertex<String> > iterator = known.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next().getElement() + " ");
        }
        System.out.println("");
        
        System.out.println("forest:");
        
        Iterator< Entry<Vertex<String>,Edge<Integer>> > iteratorForest = forest.entrySet().iterator();
        while (iteratorForest.hasNext()) {
            Entry<Vertex<String>,Edge<Integer>> item = iteratorForest.next();
            System.out.print("(k:" + item.getKey().getElement() + ": v " +  item.getValue().getElement() + "),");
        }
        System.out.println("");
    }
    public static void graphSample1() {
        System.out.println("AdjacencyMapGraph====================================");
        AdjacencyMapGraph<String, Integer>  AMG = new  AdjacencyMapGraph<>(false);  
        
        System.out.println("AMG Empty: " + AMG.toString());
        Vertex<String>  a = AMG.insertVertex("A");
        Vertex<String>  b =  AMG.insertVertex("B");
        Vertex<String>  c = AMG.insertVertex("C");
        
        System.out.println("AMG With some vertex: ");
        System.out.println(AMG.toString());
        
        AMG.insertEdge(a, b, 1);
        Edge<Integer> e = AMG.insertEdge(b, c, 1);
        System.out.println("AMG With some eges:");
        System.out.println(AMG.toString());
        
        Map< Vertex<String>,Edge<Integer>> forest =  new SortedTableMap<Vertex<String>,Edge<Integer>>();
        forest.put(c, e);
        System.out.println("forest: " + forest);
    }
   
}
