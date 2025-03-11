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
import java.util.List;

public class GrafoC {
    // modificador de acceso : tipo de dato : nombre
    private List<Integer> v;
    private List<   List<Integer>  > e;

    public GrafoC() {
        v = new ArrayList<>();
        e = new ArrayList<>();
    }
    
    public GrafoC(int[] v, int[][]e) {
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
    
    public void agregarVertice(int v) {
        this.v.add(v);
    }
    
    public void agregarArista(int u, int v) {
        ArrayList<Integer> arista = new ArrayList<>();
        arista.add(u);
        arista.add(v);
        
        this.e.add(arista);
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
    
    
    @Override
    public String toString() {
        return "GrafoC{" + "v=" + v.toString() + ", e=" + e.toString() + '}';
    }  
}
