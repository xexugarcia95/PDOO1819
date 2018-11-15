/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modeloqytetet;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jesus Manuel Garcia Palma
 * @correo xexugarcia95@gmail.com / jesusluisbcrespo@correo.ugr.es
 */
public class Qytetet {
    private ArrayList<Sorpresa> mazo = new ArrayList<>();
    private Tablero tablero;
    private static final Qytetet instance = new Qytetet();
    public int MAX_JUGADORES = 4;
    int NUM_SORPRESAS = 10;
    public int NUM_CASILLAS = 20;
    int PRECIO_LIBERTAD = 200;
    int SALDO_SALIDA = 1000;
    private Sorpresa cartaActual;
    private Jugador jugadorActual;
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private Dado dado;
    private EstadoJuego estado;
    private static final Scanner in= new Scanner(System.in);
    
    private Qytetet()
    {
       ArrayList<String> nombres = new ArrayList();
       nombres.add("Jugador 1");
       nombres.add("Jugador 2");
       inicializarJuego(nombres);
    }
    
    public static Qytetet getInstance()
    {
        return instance;
    }
    
   
    private void inicializarTablero()
    {
        tablero = new Tablero();
    }
    
    void actuarSiEnCasillaEdificable()
    {
        
    }
    
    void actuarSiEnCasillaNoEdificable()
    {
        
    }
    
    public void aplicarSorpresa()
    {
        
    }
    
    public boolean cancelarHipoteca(int numeroCasilla)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public boolean comprarTituloPropiedad() 
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public boolean edificarCasa(int numeroCasilla)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public boolean edificarHotel(int numeroCasilla)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private void encarcelarJugador()
    {
        
    }

    public int getValorDado()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public void hipotecarPropiedad(int numeroCasilla)
    {
        
    }
    
    public void inicializarJuego(ArrayList<String> nombres)
    {
        inicializarJugadores(nombres);
        inicializarCartasSorpresa();
    }
    
    private void inicializarJugadores(ArrayList<String> nombres)
    {
        for(int i=0;i<nombres.size();i++)
        { 
            Jugador j = new Jugador(nombres.get(i));
            jugadores.add(j);
        }
    }
    
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public void jugar()
    {
        
    }
    
    void mover(int numCasillaDestino)
    {
        
    }
    
    public Casilla obtenerCasillaJugadorActual()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public ArrayList<Casilla> obtenerCasillasTablero()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public ArrayList<Integer> obtenerPropiedadesJugador()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public ArrayList<Integer> obtenerPropiedadesJugadorSegunEstadoHipoteca(boolean estadoHipoteca)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public void obtenerRanking()
    {
        
    }
    
    public int obtenerSaldoJugadorActual()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private void salidaJugadores()
    {
        for(int i=0;i<jugadores.size();i++) jugadores.get(i).setCasillaActual(tablero.obtenerCasillaNumero(0));
        Random r = new Random();
        int numero = r.nextInt(1);
        jugadorActual = jugadores.get(numero);
        estado = EstadoJuego.JA_PREPARADO;
    }
    
    public void siguienteJugador()
    {
        int mod = 0;
        boolean encontrado = false;
        for(int i=0;i<jugadores.size() && !encontrado;i++)
        {
            if(jugadores.get(i)==jugadorActual) mod = (i+1)%jugadores.size(); encontrado = true;
        }
        
        jugadorActual = jugadores.get(mod);
        if(jugadorActual.isEncarcelado()) estado = EstadoJuego.JA_ENCARCELADOCONOPCIONDELIBERTAD;
        else estado = EstadoJuego.JA_PREPARADO;
    }
    
    int tirarDado()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public boolean venderPropiedad(int numeroCasilla)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    
    
    
    void inicializarCartasSorpresa()
    {
        inicializarTablero();
        mazo.add(new Sorpresa("El banco tira la casa por la ventana. Recibe 500",TipoSorpresa.PAGARCOBRAR,500));
        mazo.add(new Sorpresa("Multa por bailar la conga. Paga 500",TipoSorpresa.PAGARCOBRAR,-500));
        mazo.add(new Sorpresa("Corre como el viento perdigón!. Ve a la casilla de salida.",TipoSorpresa.IRACASILLA,0));
        mazo.add(new Sorpresa("Te posee el espíritu de M.J y bailas un Moonwalk. Retrocede a la casilla 5.",TipoSorpresa.IRACASILLA,5));
        mazo.add(new Sorpresa("Te ha pillado la pasma. Vas a la carcel",TipoSorpresa.IRACASILLA,tablero.getCarcel().getNumeroCasilla()));
        mazo.add(new Sorpresa("El banco ha errado en la hipoteca. Recibe 50 por cada casa/hotel.",TipoSorpresa.PORCASAHOTEL,50));
        mazo.add(new Sorpresa("Te se ha ido la olla hipotecando inmuebles. Paga 50 por cada casa/hotel",TipoSorpresa.PORCASAHOTEL,-50));
        mazo.add(new Sorpresa("Es tu cumpleaños. Recibe 20 de cada jugador.",TipoSorpresa.PORJUGADOR,20));
        mazo.add(new Sorpresa("Has perdido una apuesta. Paga 20 a cada jugador.",TipoSorpresa.PORJUGADOR,-20));
        mazo.add(new Sorpresa("Has quedado libre de la cárcel.",TipoSorpresa.SALIRCARCEL,0));
    }
    
    
    public ArrayList<String> getNombreJugadores()
    {
        
        ArrayList<String> array = new ArrayList<>();
        for(Jugador j: jugadores)
        {
            System.out.println(j.getNombre());
            String s = in.nextLine();
            System.out.println(s);
            array.add(s);
        }
        
        return array;
    }

    public Tablero getTablero() {
        return tablero;
    }
    
    public Sorpresa getCartaActual() {
        return cartaActual;
    }

    Jugador getJugadorActual() {
        return jugadorActual;
    }

    ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    Dado getDado() {
        return dado;
    }
    
     ArrayList<Sorpresa> getMazo() {
        return mazo;
    }

    private void setCartaActual(Sorpresa cartaActual) {
        this.cartaActual = cartaActual;
    }

    @Override
    public String toString() {
        return "Qytetet{" + "mazo=" + mazo + ", tablero=" + tablero + ", MAX_JUGADORES=" + MAX_JUGADORES + ", NUM_SORPRESAS=" + NUM_SORPRESAS + ", NUM_CASILLAS=" + NUM_CASILLAS + ", PRECIO_LIBERTAD=" + PRECIO_LIBERTAD + ", SALDO_SALIDA=" + SALDO_SALIDA + ", cartaActual=" + cartaActual + ", jugadorActual=" + jugadorActual + ", jugadores=" + jugadores + ", dado=" + dado + '}';
    }


     
     
    
}
