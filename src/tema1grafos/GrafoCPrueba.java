/*
 * ITESS-TICS-MATEMATICAS DISCRETAS II
 * PERIODO: ENERO JUNIO 2025
 * GRUPO: TI-202
 * DOCENTE: FRANCISCO JAVIER MONTECILLO PUENTE
 * DESPCRICION: CLASE GAFO REPRESENTACION EN CONJUNTOS, PRUEBAS
 * PROGRRAMADOR: FRANCISCO JAVIER MONTECILLO PUENTE
 * FECHA 17/FEBRERO/2025
 */
package tema1grafos;

public class GrafoCPrueba {
    public static void main(String[] args) {
        System.out.println("Crear un grafo sin vertices ni aristas::::::::::");
        GrafoC g1 = new GrafoC();
        System.out.println(g1.toString());
        
        System.out.println("Crear un grafo con vertices y aristas:::::::::::");
        int[] v = {1, 2, 3};
        int[][] e = {{1,2}, {2,3}, {1,3}};
        GrafoC g2 = new GrafoC(v, e);
        System.out.println(g2.toString());
        
        System.out.println("Agregar vertices y aristas:::::::::::::::::::::::");
        g1.agregarVertice(1);
        g1.agregarVertice(2);
        g1.agregarArista(1, 2);
        System.out.println(g1.toString());
        
        System.out.println("Probar grado de un vertice y gradoTotal :::::::::::");
        GrafoC g3 = new GrafoC();
        
        g3.agregarVertice(1);
        g3.agregarVertice(2);
        g3.agregarVertice(3);
        
        g3.agregarArista(1, 1);
        g3.agregarArista(1, 2);
        
        System.out.println(g3.toString());
        System.out.println("grado(1)=" + g3.grado(1));
        System.out.println("grado(2)=" + g3.grado(2));
        System.out.println("grado(3)=" + g3.grado(3));
        System.out.println("grado(4)=" + g3.grado(4));
        
        System.out.println("gradoTotal de g3 = " + g3.gradoTotal());
        
        
    }
}
