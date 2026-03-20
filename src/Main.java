
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
  
    
    public static void main (String [] args) throws IOException{
        
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      int num = Integer.parseInt(in.readLine()); // lee una linea de texto numero
      int suma=0;
      for (int i=0; i<num; i++){
          if(i%2==1){
              suma+=i;
          }
      }
      System.out.print(suma);
    }
}
