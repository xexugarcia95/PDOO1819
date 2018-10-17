/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modeloqytetet;

import java.util.ArrayList;

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
    private ArrayList<Jugador> jugadores;
    private Dado dado;
    
    private Qytetet()
    {}
    
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
        
    }
    
    private void inicializarJugadores(ArrayList<String> nombres)
    {
        
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
        
    }
    
    public void siguienteJugador()
    {
        
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
