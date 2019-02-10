/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfazUsuarioQytetet;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Scanner;
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
    
    private static Qytetet modelo = Qytetet.getInstance();

    
    public ArrayList<String> obtenerNombreJugadores()
    {        
        ArrayList<String> array = modelo.getNombreJugadores();        
        return array;
    }
    
    public String leerValorCorrecto(ArrayList<String> valoresCorrectos)
    {  
        boolean encontrado = false;
        String v = new String();
        OpcionMenu opcion;
        int num = 0;
        for(int i=0;i<OpcionMenu.values().length && !encontrado;i++)
        {
            String value = OpcionMenu.values()[i].toString();

            for(int j=0;j<valoresCorrectos.size();j++)
            {
                if(value.equals(valoresCorrectos.get(j)))
                    num = OpcionMenu.values()[i].ordinal();
                    v = String.valueOf(num);
                    encontrado = true;
            }
        }
        
        return v;
    }
    
    public void realizarOperacion(int OpcionElegida,int CasillaElegida)
    {
        boolean libre;
        Scanner scanner = new Scanner(System.in);
        switch(OpcionElegida)
        {
            case 0:  System.out.println("Iniciando el juego...\n");
                String v;
                ArrayList<String> array = new ArrayList<>();
                System.out.println("Jugador 1...\n");
                v = scanner.nextLine();
                array.add(v);
                System.out.println("Jugador 2...\n"); 
                v = scanner.nextLine();
                array.add(v);
                modelo.inicializarJuego(array); break;
            case 1: System.out.println("A jugar!!!\n");
                modelo.jugar();    break;
            case 2: System.out.println("Aplicando sorpresa...");
                modelo.aplicarSorpresa();
            break;
            case 3: System.out.println("Intentando salir de la carcel pagando por mi libertad...\n");
                libre = modelo.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);
                if(libre) System.out.println("Has sido liberado!!!");
                else System.out.println("No has podido pagar tu libertad. Dinero insuficiente\n");    
            break;
            case 4: System.out.println("Intentando salir de la carcel mediente tirada de dado...\n"); 
                libre = modelo.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
                System.out.println("Valor dado" + modelo.getValorDado());
                if(libre) System.out.println("Has sido liberado!!!");
                else System.out.println("Mala tirada, sigues en la cárcel\n");  
            break;
            case 5: System.out.println("Comprando titulo de propiedad...\n");    
                libre = modelo.comprarTituloPropiedad();
                if(libre) System.out.println("Compra hecha con exito\n");
                else System.out.println("Compra fallida\n");
            break;
            case 6: System.out.println("Hipotecando propiedad...");    
                modelo.hipotecarPropiedad(CasillaElegida);
            break;
            case 7: System.out.println("Cancelando hipoteca...");
                libre = modelo.cancelarHipoteca(CasillaElegida);
                if(libre) System.out.println("La cancelación ha sido todo un exito");
                else System.out.println("Cancelación de la hipoteca fallida");
            break;
            case 8: System.out.println("Edificando casa...");    
                libre = modelo.edificarCasa(CasillaElegida);
                if(libre) System.out.println("Casa edificada");
                else System.out.println("No se ha podido edificar la casa");
            break;
            case 9: System.out.println("Edificando hotel...");    
                libre = modelo.edificarHotel(CasillaElegida);
                if(libre) System.out.println("Hotel edificado");
                else System.out.println("No se ha podido edificar el hotel");
            break;
            case 10: System.out.println("Vendiendo propiedad...");  
                libre = modelo.venderPropiedad(CasillaElegida);
                if(libre) System.out.println("Propiedad vendida");
                else System.out.println("No se ha podido vender esta propiedad");
            break;
            case 11: System.out.println("Pasas el turno al siguiente jugador");   
                modelo.siguienteJugador();
            break;
            case 12: System.out.println("Ranking de jugadores");    
                modelo.obtenerRanking();
            break;
            case 13: System.out.println("Finalizando juego...");    
                System.out.println("Puntuacion final:");
                modelo.obtenerRanking();
                exit();
            break;
            case 14: System.out.println("Mostrando jugador actual...");
                System.out.println(modelo.getNombreJugadorActual());
            break;
            case 15: System.out.println("Mostrando los jugadores actuales de la partida...");    
                System.out.println(modelo.getJugadores());
            break;
            case 16: System.out.println("Mostrando tablero...");    
                modelo.getTablero().toString();
            break;
            default:
                System.out.println("Ha ocurrido un error...Saliendo."); exit();
            break;
        }
    }
    
    public ArrayList<String> obtenerOperacionesJuegoValidas()
    {
        ArrayList<String> array = new ArrayList<>();
        if(modelo.getJugadores().isEmpty()) //Juego no iniciado
        {
            array.add(OpcionMenu.INICIARJUEGO.toString());
        }else
        {
            switch(modelo.getEstado())
            {
                case JA_CONSORPRESA: 
                    array.add(OpcionMenu.APLICARSORPRESA.toString());
                    break;
                case ALGUNJUGADORENBANCARROTA: 
                    array.add(OpcionMenu.OBTENERRANKING.toString());
                    break;
                case JA_PUEDECOMPRAROGESTIONAR: 
                    array.add(OpcionMenu.COMPRARTITULOPROPIEDAD.toString());
                    array.add(OpcionMenu.VENDERPROPIEDAD.toString());
                    array.add(OpcionMenu.HIPOTECARPROPIEDAD.toString());
                    array.add(OpcionMenu.CANCELARHIPOTECA.toString());
                    array.add(OpcionMenu.EDIFICARCASA.toString());
                    array.add(OpcionMenu.EDIFICARHOTEL.toString());
                    break;
                case JA_PUEDEGESTIONAR: 
                    array.add(OpcionMenu.VENDERPROPIEDAD.toString());
                    array.add(OpcionMenu.HIPOTECARPROPIEDAD.toString());
                    array.add(OpcionMenu.CANCELARHIPOTECA.toString());
                    array.add(OpcionMenu.EDIFICARCASA.toString());
                    array.add(OpcionMenu.EDIFICARHOTEL.toString());
                    array.add(OpcionMenu.SIGUIENTEJUGADOR.toString());
                    break;
                case JA_PREPARADO: 
                    array.add(OpcionMenu.JUGAR.toString());
                    
                    break;
                case JA_ENCARCELADO:                    
                    break;
                case JA_ENCARCELADOCONOPCIONDELIBERTAD:
                    array.add(OpcionMenu.INTENTARSALIRCARCELPAGANDOLIBERTAD.toString());
                    array.add(OpcionMenu.INTENTARSALIRCARCELTIRANDODADO.toString());
                    break;
            }
        }
        
        return array;
    }
    
    public int obtenerOpcionMenu(ArrayList<String> operacionesJuegoValidas)
    {
        System.out.println("Opciones disponibles:");
        for (String operacionesJuegoValida : operacionesJuegoValidas) {
            for (OpcionMenu value : OpcionMenu.values()) {
                if(operacionesJuegoValida.equals(value.toString())) 
                    System.out.println(value.ordinal() + " : " + operacionesJuegoValida);
            }
        }       
        String s = leerValorCorrecto(operacionesJuegoValidas);
        int valor = Integer.parseInt(s);
        return valor;
    }
    
    public int elegirOperacion()
    {   
        ArrayList<String> array = obtenerOperacionesJuegoValidas();
        int valor = obtenerOpcionMenu(array);
        return valor;
    }
    
    public ArrayList<Integer> obtenerCasillasValidas(int opcionMenu)
    {
        OpcionMenu opcion = OpcionMenu.values()[opcionMenu];
        ArrayList<Integer> array = modelo.obtenerPropiedadesJugador();
        ArrayList<Integer> array2 = modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(true);
        ArrayList<Integer> array3 = modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(false);
        ArrayList<Integer> fin = new ArrayList<>();
        switch(opcion)
        {
            case HIPOTECARPROPIEDAD:
                for(int i=0;i<array2.size();i++) fin.add(array2.get(i));
            break;
            case CANCELARHIPOTECA:
                for(int i=0;i<array3.size();i++) fin.add(array3.get(i));
            break;
            case EDIFICARCASA:
            for(int i=0;i<array.size();i++) fin.add(array.get(i));
                break;
            case EDIFICARHOTEL:
                for(int i=0;i<array.size();i++) fin.add(array.get(i));
            break;
            case VENDERPROPIEDAD:
                for(int i=0;i<array.size();i++) fin.add(array.get(i));
            break;
        }
        
        return fin;
    }
    
    public boolean necesitaElegirCasilla(int opcionMenu)
    {
        boolean ordinal = false;
  
        if(OpcionMenu.values()[opcionMenu] == OpcionMenu.CANCELARHIPOTECA || OpcionMenu.values()[opcionMenu] == OpcionMenu.COMPRARTITULOPROPIEDAD
                || OpcionMenu.values()[opcionMenu] == OpcionMenu.EDIFICARCASA || OpcionMenu.values()[opcionMenu] == OpcionMenu.EDIFICARHOTEL
                || OpcionMenu.values()[opcionMenu] == OpcionMenu.HIPOTECARPROPIEDAD) ordinal = true;        
        
        return ordinal;
    }
    
    public int elegirCasilla(int opcionMenu)
    {
        ArrayList<Integer> array = obtenerCasillasValidas(opcionMenu);
        ArrayList<String> ar = new ArrayList<>();
        if(array.isEmpty())
        {
            return -1;
        }else
        {

            for (Integer array1 : array) {
                System.out.println(array1);
                ar.add(array1.toString());
            }
        }
          

        String s = leerValorCorrecto(ar);
        int val = Integer.parseInt(s);
        return val;
    }
    
    public static void main(String[] args)
    {
        
        InterfazUsuarioQytetet ui = new InterfazUsuarioQytetet();
        int operacionElegida, casillaElegida = 0;
        boolean necesitaElegirCasilla;
        
        do
        {
           operacionElegida = ui.elegirOperacion();
           necesitaElegirCasilla = ui.necesitaElegirCasilla(operacionElegida);
           if (necesitaElegirCasilla)
                casillaElegida = ui.elegirCasilla(operacionElegida);
           if (!necesitaElegirCasilla || casillaElegida >= 0)
                 ui.realizarOperacion(operacionElegida, casillaElegida);
        }while(1==1);
    }
}
