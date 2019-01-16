/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfazUsuarioQytetet;

import static java.lang.System.in;
import java.util.ArrayList;
import modeloqytetet.Qytetet;
import modeloqytetet.Jugador;
import modeloqytetet.MetodoSalirCarcel;

/**
 *
 * @author Jesus Manuel Garcia Palma
 * @correo xexugarcia95@gmail.com / jesusluisbcrespo@correo.ugr.es
 */
public class InterfazUsuarioQytetet {
    public ArrayList<String> obtenerNombreJugadores()
    {        
        ArrayList<String> array = Qytetet.getInstance().getNombreJugadores();        
        return array;
    }
    
    public String leerValorCorrecto(ArrayList<String>valoresCorrectos)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public void realizarOperacion(int OpcionElegida,int CasillaElegida)
    {
        boolean libre;
        switch(OpcionElegida)
        {
            case 0:  System.out.println("Iniciando el juego...\n");
                Qytetet.getInstance().inicializarJuego(obtenerNombreJugadores()); break;
            case 1: System.out.println("A jugar!!!\n");
                Qytetet.getInstance().jugar();    break;
            case 2: System.out.println("Aplicando sorpresa...");
                Qytetet.getInstance().aplicarSorpresa();
            break;
            case 3: System.out.println("Intentando salir de la carcel pagando por mi libertad...\n");
                libre = Qytetet.getInstance().intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);
                if(libre) System.out.println("Has sido liberado!!!");
                else System.out.println("No has podido pagar tu libertad. Dinero insuficiente\n");    
            break;
            case 4: System.out.println("Intentando salir de la carcel mediente tirada de dado...\n"); 
                libre = Qytetet.getInstance().intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
                System.out.println("Valor dado" + Qytetet.getInstance().getValorDado());
                if(libre) System.out.println("Has sido liberado!!!");
                else System.out.println("Mala tirada, sigues en la cárcel\n");  
            break;
            case 5: System.out.println("Comprando titulo de propiedad...\n");    
                libre = Qytetet.getInstance().comprarTituloPropiedad();
                if(libre) System.out.println("Compra hecha con exito\n");
                else System.out.println("Compra fallida\n");
            break;
            case 6: System.out.println("Hipotecando propiedad...");    
                Qytetet.getInstance().hipotecarPropiedad(CasillaElegida);
            break;
            case 7: System.out.println("");    
            break;
            case 8: System.out.println("");    
            break;
            case 9: System.out.println("");    
            break;
            case 10: System.out.println("");  
            break;
            case 11: System.out.println("");    
            break;
            case 12: System.out.println("");    
            break;
            case 13: System.out.println("");    
            break;
            case 14: System.out.println("");   
            break;
            case 15: System.out.println("");    
            break;
            case 16: System.out.println("");    
            break;
                        
            
            
        }
    }
}
