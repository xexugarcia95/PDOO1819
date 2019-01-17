/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfazUsuarioQytetet;

import static java.lang.System.in;
import java.util.ArrayList;
import static javafx.application.Platform.exit;
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
    
    public String leerValorCorrecto(String valoresCorrectos)
    {   boolean encontrado = false;
        String v = new String();
        for(int i=0;i<16 && !encontrado;i++)
        {
            OpcionMenu value = OpcionMenu.values()[i];
            int valor = value.ordinal();
            v = Integer.toString(valor);
            if(valoresCorrectos==v) encontrado = true;
        }
        
        return v;
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
            case 7: System.out.println("Cancelando hipoteca...");
                libre = Qytetet.getInstance().cancelarHipoteca(CasillaElegida);
                if(libre) System.out.println("La cancelación ha sido todo un exito");
                else System.out.println("Cancelación de la hipoteca fallida");
            break;
            case 8: System.out.println("Edificando casa...");    
                libre = Qytetet.getInstance().edificarCasa(CasillaElegida);
                if(libre) System.out.println("Casa edificada");
                else System.out.println("No se ha podido edificar la casa");
            break;
            case 9: System.out.println("Edificando hotel...");    
                libre = Qytetet.getInstance().edificarHotel(CasillaElegida);
                if(libre) System.out.println("Hotel edificado");
                else System.out.println("No se ha podido edificar el hotel");
            break;
            case 10: System.out.println("Vendiendo propiedad...");  
                libre = Qytetet.getInstance().venderPropiedad(CasillaElegida);
                if(libre) System.out.println("Propiedad vendida");
                else System.out.println("No se ha podido vender esta propiedad");
            break;
            case 11: System.out.println("Pasas el turno al siguiente jugador");   
                Qytetet.getInstance().siguienteJugador();
            break;
            case 12: System.out.println("Ranking de jugadores");    
                Qytetet.getInstance().obtenerRanking();
            break;
            case 13: System.out.println("Finalizando juego...");    
                System.out.println("Puntuacion final:");
                Qytetet.getInstance().obtenerRanking();
                exit();
            break;
            case 14: System.out.println("Mostrando jugador actual...");
                System.out.println(Qytetet.getInstance().getNombreJugadorActual());
            break;
            case 15: System.out.println("Mostrando los jugadores actuales de la partida...");    
                System.out.println(Qytetet.getInstance().getJugadores());
            break;
            case 16: System.out.println("Mostrando tablero...");    
                Qytetet.getInstance().getTablero().toString();
            break;
                          
        }
    }
    
    public int obtenerOpcionMenu(ArrayList<String> operacionesJuegoValidad)
    {
        
    }
    
    public static void main(String[] args)
    {
        InterfazUsuarioQytetet ui = new InterfazUsuarioQytetet();
        int operacionElegida, casillaElegida = 0;
        boolean necesitaElegirCasilla;
        
        do
        {
            
        }while(1==1);
    }
}
