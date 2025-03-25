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
           System.out.println("Infinito: " + infinito);
           // vertice inicial a = 1
           Integer  a = verticeInicial;

           // vertice final   z = 6
           Integer z = verticeFinal;
           
           // hacer el paso 1. grafo T
           GrafoC T = new GrafoC();
           T.agregarVertice(a);
           System.out.println("T:" + T.toString());
           
           // Hacer el paso 2. L, considerar indice 0 corresponde a
           // vertice 1
           List<Integer> L = new ArrayList<>();
           L.add(0);  // 0  -> v1,  1 -> v2, 2 -> v3
           for (int i = 1; i < this.v.size(); i++)
               L.add(infinito);
           System.out.println("L: " + L.toString()); 
           
           // hacer el paso 3, inialice v = a, F sera {a}
           Integer v  =  a;
           
           HashSet<Integer> F = new HashSet<>();
           F.add(a);
           System.out.println("F:" + F.toString());
          
           // paso 4. while ( z no pertenece a V(T) )
           //System.out.println("Vertices adjacentes:");
           //verticesAdyacentes(v);
           //while (! T.v.contains(z) ) {  
               // 4a. F = (F - {v}) U { vertices adyacentes a v y no estan en V(T)}
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
               System.out.println("adyacentes: " + adyacentes);
           //}        
           // resultado
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
        return "GrafoC{" + "v=" + v.toString() + ", e=" + e.toString() + '}';
    }  
}
