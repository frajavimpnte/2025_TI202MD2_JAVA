/*
 * ITESS-TICS-MATEMATICAS DISCRETAS II
 * PERIODO: ENERO JUNIO 2025
 * GRUPO: TI-202
 * DOCENTE: FRANCISCO JAVIER MONTECILLO PUENTE
 * DESPCRICION: CLASE GAFO REPRESENTACION EN CONJUNTOS
 * PROGRRAMADOR: FRANCISCO JAVIER MONTECILLO PUENTE
 * FECHA 17/FEBRERO/2025
 */
package tema1grafos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class GrafoC {
    // modificador de acceso : tipo de dato : nombre
    private List<Integer> v;
    private List< List<Integer>  > e;
    private List<Integer> w;

    public GrafoC() {
        v = new ArrayList<>();
        e = new ArrayList<>();
        w = new ArrayList<>();
    }
    
    private void initVE(int[] v, int[][]e) {
         this.v = new ArrayList<>();
         this.e = new ArrayList<>();
        this.w = new ArrayList<>();
        for (int i = 0; i < v.length; i++)
            this.v.add(v[i]);
        
        for (int i = 0; i < e.length; i++) {
            ArrayList<Integer> arista = new ArrayList<>();
            
            arista.add(e[i][0]);
            arista.add(e[i][1]);
            
            this.e.add(arista);
        }
    }
    
    public GrafoC(int[] v, int[][]e) {
       initVE(v, e);
    }
    
    public GrafoC(int[] v, int[][]e, int[] w) {
       initVE(v, e);
       this.w = new ArrayList<>();
       for (int i = 0; i < w.length; i++)
            this.w.add(w[i]);
    }
    
    public void agregarVertice(int v) {
        this.v.add(v);
    }
    
    public void agregarArista(int u, int v) {
        ArrayList<Integer> arista = new ArrayList<>();
        arista.add(u);
        arista.add(v);
        
        this.e.add(arista);
    }
    
     public void agregarArista(int u, int v, int w) {
        ArrayList<Integer> arista = new ArrayList<>();
        arista.add(u);
        arista.add(v);
        
        this.e.add(arista);
        this.w.add(w);
    }

    public int grado(int v) {
        /*
        boolean vValid = false;
        for (Integer v1 : this.v) {
            if (v1 == v) {
                vValid = true;
                break;
            };
        }
        if (!vValid) return -1;
      */
       if (!this.v.contains(v)) 
           return -1;
       
       int grado = 0;
       for (List<Integer> arista: this.e) {
           if (arista.get(0) == v)
               grado++;
           if (arista.get(1) == v)
               grado++;
       }
       return grado;
    } 
    
    public int gradoTotal() {
        int gradoTotal = 0;
        for (Integer v: this.v) {
            gradoTotal += grado(v);
        }
        return gradoTotal;
    }
    
    public void dijkstra(int verticeInicial, int verticeFinal) {
        // inicializar
        // calcular infinito
        int infinito = 0;
        for (Integer w: this.w)
            infinito += w;
        infinito++;
        // vertice inicial a = 1
        Integer  a = verticeInicial;

        // vertice final   z = 6
        Integer z = verticeFinal;

        // hacer el paso 1. grafo T
        GrafoC T = new GrafoC();
        T.agregarVertice(a);

        // Hacer el paso 2. L, considerar indice 0 corresponde a
        // vertice 1
        List<Integer> L = new ArrayList<>();
        L.add(0);  // 0  -> v1,  1 -> v2, 2 -> v3
        for (int i = 1; i < this.v.size(); i++)
            L.add(infinito);

        // hacer el paso 3, inialice v = a, F sera {a}
        Integer v  =  a;

        HashSet<Integer> F = new HashSet<>();
        F.add(a);
        // paso 4. while ( z no pertenece a V(T) )
        // crear D, tiene tamaño de L o cantidad de vertices
        List<Integer> D = new ArrayList<>(this.v.size());
        for (int i = 0; i < this.v.size(); i++) 
            D.add(-1);

        int iter = 1;
        while (! T.v.contains(z) ) { 
            // 4a. F = (F - {v}) U { vertices adyacentes a v y no estan en V(T)}
            // F - {v}
            if (F.contains(v)) {
                F.remove(v);
            }
            
            // vertices adyacentes a v y no estan en V(T)
            List<Integer> adyacentes = new ArrayList();
            for (List<Integer> arista: e)  {
                if (arista.get(0) == v  && !T.v.contains(arista.get(1))) {
                     //System.out.print(arista.get(1) + " ");
                     adyacentes.add(arista.get(1));
                 }
                  if (arista.get(1) == v && !T.v.contains(arista.get(0))) {
                     //System.out.print(arista.get(0) + " ");
                     adyacentes.add(arista.get(0));
                 }
            }
            
            // (F - {v}) U {adyacencia}
            F.addAll(adyacentes);

            // 4b. para cada vertice u que es adyacente a v y no esta en V(T)
            for (Integer u:adyacentes) {
                if (T.v.contains(u)) continue;                   
                // IF L(v) + w(v,u) < L(u) then L(u) = L(v) + w(v,u); D(u) = v                 
                if (  L.get(v-1) +  getWeight(v, u) < L.get(u-1)) {
                    //System.out.println("    v:" + v + " u:" + u + " L(v):" + L.get(v-1) + " w(v,u): " + getWeight(v, u));
                    L.set(u-1, L.get(v-1) +  getWeight(v, u));
                    D.set(u-1, v);
                }
            }
            // 4c. Encuentre un vertice x en F con la etiqueta mas pequeña
            // Agregue el vertice x a V(T) y se agrega una arista {D(x), x} a E(T)
            int min = Integer.MAX_VALUE;
            int minIdx = -1; // este es x
            for (Integer u:F) {
                if (L.get(u-1) < min) {
                    min = L.get(u-1);
                    minIdx = u;
                }
            }
            T.agregarVertice(minIdx);
            T.agregarArista(D.get(minIdx-1), minIdx);

            v = minIdx;
            iter++;
        }        
        // resultado
        System.out.println("L:" + L.toString());
        System.out.println("T:" + T.toString());
    }
    
    public void dijkstraVerbose(int verticeInicial, int verticeFinal) {
        // inicializar
        System.out.println("Inicializar dijkstra:");
       // calcular infinito
       int infinito = 0;
       for (Integer w: this.w)
           infinito += w;
       infinito++;
       System.out.println("Infinito: " + infinito);
       // vertice inicial a = 1
       Integer  a = verticeInicial;

       // vertice final   z = 6
       Integer z = verticeFinal;

       // hacer el paso 1. grafo T
       System.out.println("Paso 1. Crear grafo T");
       GrafoC T = new GrafoC();
       T.agregarVertice(a);
       System.out.println("T:" + T.toString());

       // Hacer el paso 2. L, considerar indice 0 corresponde a
       // vertice 1
       System.out.println("Paso 2. Inicializar L");
       List<Integer> L = new ArrayList<>();
       L.add(0);  // 0  -> v1,  1 -> v2, 2 -> v3
       for (int i = 1; i < this.v.size(); i++)
           L.add(infinito);
       System.out.println("L: " + L.toString()); 

       // hacer el paso 3, inialice v = a, F sera {a}
       System.out.println("Paso 3. inialice v = a, F sera {a}");
       Integer v  =  a;

       HashSet<Integer> F = new HashSet<>();
       F.add(a);
       System.out.println("F:" + F.toString());

       // paso 4. while ( z no pertenece a V(T) )
       // crear D, tiene tamaño de L o cantidad de vertices
       List<Integer> D = new ArrayList<>(this.v.size());
       for (int i = 0; i < this.v.size(); i++) D.add(-1);
       System.out.println("D:" + D.toString());

       System.out.println("Paso 4. Iteraciones");
       int iter = 1;
       while (! T.v.contains(z) ) { 
           System.out.println(":::::::::::: Iteracion " + iter + " ::::::::::::::::::::::::::::");
           // 4a. F = (F - {v}) U { vertices adyacentes a v y no estan en V(T)}
           System.out.println("Paso 4a. F = (F - {v}) U { vertices adyacentes a v y no estan en V(T)");

           // F - {v}
           if (F.contains(v)) {
               F.remove(v);
           }
           System.out.println("     F - {v}:" + F.toString());
           // vertices adyacentes a v y no estan en V(T)
           List<Integer> adyacentes = new ArrayList();
           for (List<Integer> arista: e)  {
               if (arista.get(0) == v  && !T.v.contains(arista.get(1))) {
                    //System.out.print(arista.get(1) + " ");
                    adyacentes.add(arista.get(1));
                }
                 if (arista.get(1) == v && !T.v.contains(arista.get(0))) {
                    //System.out.print(arista.get(0) + " ");
                    adyacentes.add(arista.get(0));
                }
           }
           System.out.println("     adyacentes: " + adyacentes);

           // (F - {v}) U {adyacencia}
           F.addAll(adyacentes);
           System.out.println("     (F - {v}) U {adyacencia}:" + F.toString());

           // 4b. para cada vertice u que es adyacente a v y no esta en V(T)
           System.out.println("     update L con los u e adyacentes:" + adyacentes.toString());
           for (Integer u:adyacentes) {
               if (T.v.contains(u)) continue;                   
               // IF L(v) + w(v,u) < L(u) then L(u) = L(v) + w(v,u); D(u) = v                 
               if (  L.get(v-1) +  getWeight(v, u) < L.get(u-1)) {
                   //System.out.println("    v:" + v + " u:" + u + " L(v):" + L.get(v-1) + " w(v,u): " + getWeight(v, u));
                   L.set(u-1, L.get(v-1) +  getWeight(v, u));
                   D.set(u-1, v);
               }
               System.out.println("         update L:" + L.toString() );
           }
           System.out.println("     L:" + L.toString());
           System.out.println("     D:" + D.toString());

           // 4c. Encuentre un vertice x en F con la etiqueta mas pequeña
           // Agregue el vertice x a V(T) y se agrega una arista {D(x), x} a E(T)
           int min = Integer.MAX_VALUE;
           int minIdx = -1; // este es x
           for (Integer u:F) {
               if (L.get(u-1) < min) {
                   min = L.get(u-1);
                   minIdx = u;
               }
           }
           System.out.println("     min:" + min);
           System.out.println("  minIdx:" + minIdx);

           T.agregarVertice(minIdx);
           T.agregarArista(D.get(minIdx-1), minIdx);

           System.out.println("     T:" +T.toString());

           v = minIdx;
           iter++;
       }        
       // resultado
    }
    private int getWeight(int v, int u) {
         for (int i = 0; i < e.size(); i++) {
             List<Integer> arista = e.get(i);
             if ( (arista.get(0) == v && arista.get(1) == u) ||  
                    (arista.get(0) == u && arista.get(1) == v)) {
                 return w.get(i);
             }
         }
         return -1;
    }
    private void verticesAdyacentes(int v) {
        // recorrer todas las aristas
        for (List<Integer> arista: e)  {
            //System.out.print(arista.toString() + " ");
            // checar si v es para de la arista
            if (arista.get(0) == v) {
                 System.out.print(arista.get(1) + " ");
            }
            if (arista.get(1) == v) {
                 System.out.print(arista.get(0) + " ");
            }
            //    si es parte el otro vertice es adyacente
        }
        System.out.println("");
    }
    
    @Override
    public String toString() {
        return "GrafoC{" + "v=" + v.toString() + ", e=" + e.toString() + 
                ", w=" + w.toString() + "}";
    }  
}
