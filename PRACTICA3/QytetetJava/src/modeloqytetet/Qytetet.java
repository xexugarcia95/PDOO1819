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
        boolean deboPagar = jugadorActual.deboPagarAlquiler();
        if(deboPagar)
        {
            jugadorActual.pagarAlquiler();
            if(jugadorActual.getSaldo()<0)
            {
                setEstado(estado.ALGUNJUGADORENBANCARROTA);
            }
        }
        Casilla casilla = obtenerCasillaJugadorActual();
        boolean tengoPropietario = casilla.tengoPropietario();
        
        if(estado==EstadoJuego.ALGUNJUGADORENBANCARROTA)
        {
            if(tengoPropietario)
            {
                setEstado(EstadoJuego.JA_PUEDEGESTIONAR);
            }else
            {
                setEstado(EstadoJuego.JA_PUEDECOMPRAROGESTIONAR);
            }
        }
    }
    
    void actuarSiEnCasillaNoEdificable()
    {
        setEstado(EstadoJuego.JA_PUEDEGESTIONAR);
        Casilla casillaActual = jugadorActual.getCasillaActual();
        if(casillaActual.getTipo()==TipoCasilla.IMPUESTO)
        {
            jugadorActual.pagarImpuesto();
        }else if(casillaActual.getTipo()==TipoCasilla.JUEZ)
        {
            encarcelarJugador();
        }else if(casillaActual.getTipo()==TipoCasilla.SORPRESA)
        {
            mazo.remove(cartaActual);
            setEstado(EstadoJuego.JA_CONSORPRESA);
        }
    }
    
    public void aplicarSorpresa()
    {
        setEstado(EstadoJuego.JA_PUEDEGESTIONAR);
        if(cartaActual.getTipo()==TipoSorpresa.SALIRCARCEL)
        {
            jugadorActual.setCartaLibertad(cartaActual);
        }else
        {
            mazo.add(cartaActual); //Incluir al final
            if(cartaActual.getTipo()==TipoSorpresa.PAGARCOBRAR)
            {
                jugadorActual.modificarSaldo(cartaActual.getValor());
                if(jugadorActual.getSaldo()<0)
                {
                    setEstado(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                }
            }else if(cartaActual.getTipo()==TipoSorpresa.IRACASILLA)
                {
                    int valor = cartaActual.getValor();
                    boolean casillaCarcel = tablero.esCasillaCarcel(valor);
                    if(casillaCarcel)
                    {
                        encarcelarJugador();
                    }else
                    {
                        mover(valor);
                    }
                }else if(cartaActual.getTipo()==TipoSorpresa.PORCASAHOTEL)
                {
                    int cantidad = cartaActual.getValor();
                    int numeroTotal = jugadorActual.cuantasCasasHotelesTengo();
                    jugadorActual.modificarSaldo(cantidad*numeroTotal);
                    
                    if(jugadorActual.getSaldo()<0)
                    {
                        setEstado(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                    }
                }else if(cartaActual.getTipo()==TipoSorpresa.PORJUGADOR)
                {
                    for(int i=0;i<MAX_JUGADORES-1;i++)
                    {
                        Jugador jugador = jugadores.get(i);
                        if(jugador!=jugadorActual)
                        {
                            jugadores.get(i).modificarSaldo(cartaActual.getValor());
                        }
                        if(jugador.getSaldo()<0)
                        {
                            setEstado(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                        }
                        
                        jugadorActual.modificarSaldo(-cartaActual.getValor());
                        if(jugadorActual.getSaldo()<0)
                        {
                            setEstado(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                        }
                    }
                }
            
        }
    }
    
    public boolean cancelarHipoteca(int numeroCasilla)
    {
        Casilla casilla = tablero.getCasillas().get(numeroCasilla);
        TituloPropiedad titulo = casilla.getTitulo();
        boolean cancelar = jugadorActual.cancelarHipoteca(titulo);
        setEstado(EstadoJuego.JA_PUEDEGESTIONAR);
        return cancelar;
    }
    
    public boolean comprarTituloPropiedad() 
    {
        boolean comprado = jugadorActual.comprarTituloPropiedad();
        if(comprado) setEstado(EstadoJuego.JA_PUEDEGESTIONAR);
        
        return comprado;
    }
    
    public boolean edificarCasa(int numeroCasilla)
    {
       Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
       TituloPropiedad titulo = casilla.getTitulo();
       boolean edificada = jugadorActual.edificarCasa(titulo);
       return edificada;
    }
    
    public boolean edificarHotel(int numeroCasilla)
    {
        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
       TituloPropiedad titulo = casilla.getTitulo();
       boolean edificada = jugadorActual.edificarHotel(titulo);
       return edificada;
    }
    
    private void encarcelarJugador()
    {
        if(!jugadorActual.tengoCartaLibertad())
        {
            Casilla casillaCarcel = tablero.getCarcel();
            jugadorActual.irACarcel(casillaCarcel);
            setEstado(EstadoJuego.JA_ENCARCELADO);
        }else
        {
            Sorpresa carta = jugadorActual.devolverCartaLibertad();
            mazo.add(carta); //incluir al final
            setEstado(EstadoJuego.JA_PUEDEGESTIONAR);
        }
    }

    public int getValorDado()
    {
        return dado.getValor();
    }
    
    public void hipotecarPropiedad(int numeroCasilla)
    {
        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        TituloPropiedad titulo = casilla.getTitulo();
        jugadorActual.hipotecarPropiedad(titulo);
        setEstado(EstadoJuego.JA_PUEDEGESTIONAR);
    }
    
    public void inicializarJuego(ArrayList<String> nombres)
    {
        inicializarJugadores(nombres);
        inicializarCartasSorpresa();
//        salidaJugadores();
        
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
        if(metodo==MetodoSalirCarcel.TIRANDODADO)
        {
            int resultado = tirarDado();
            if(resultado>=5) jugadorActual.setEncarcelado(false);            
        }else if(metodo==MetodoSalirCarcel.PAGANDOLIBERTAD)
        {
            jugadorActual.pagarLibertad(PRECIO_LIBERTAD);
            
        }
        
        boolean libre = jugadorActual.getEncarcelado();
        
        if(libre)
        {
            setEstado(EstadoJuego.JA_ENCARCELADO);
        }else
        {
            setEstado(EstadoJuego.JA_PREPARADO);
        }
        
        return libre;
    }
    
    public void jugar()
    {
        int i =tirarDado();
        Casilla casillaActual= tablero.obtenerCasillaFinal(jugadorActual.getCasillaActual(), i);
        mover(casillaActual.getNumeroCasilla());
    }
    
    void mover(int numCasillaDestino)
    {
        Casilla casillaInicial = jugadorActual.getCasillaActual();
        Casilla casillaFinal = tablero.obtenerCasillaNumero(numCasillaDestino);
        jugadorActual.setCasillaActual(casillaFinal);
        
        if(numCasillaDestino<casillaInicial.getNumeroCasilla())
        {
            jugadorActual.modificarSaldo(SALDO_SALIDA);
        }
        
        if(casillaFinal.soyEdificable())
        {
            actuarSiEnCasillaEdificable();
        }else
        {
            actuarSiEnCasillaNoEdificable();
        }
    }
    
    public Casilla obtenerCasillaJugadorActual()
    {
        return jugadorActual.getCasillaActual();
    }
    
    public ArrayList<Casilla> obtenerCasillasTablero()
    {
        return tablero.getCasillas();
    }
    
    public ArrayList<Integer> obtenerPropiedadesJugador()
    {
        ArrayList<Integer> numPropiedades = new ArrayList<>();
        for(int i=0;i<jugadorActual.getPropiedades().size();i++)
        {
            boolean encontrado = false;
            for(int j=0;j<tablero.getCasillas().size() && !encontrado;j++)
            {
                if(jugadorActual.getPropiedades().get(i)==tablero.getCasillas().get(j).getTitulo())
                {
                    numPropiedades.add(tablero.getCasillas().get(j).getNumeroCasilla());
                    encontrado = true;
                }
            }
                
        }
        return numPropiedades;
    }
    
    public ArrayList<Integer> obtenerPropiedadesJugadorSegunEstadoHipoteca(boolean estadoHipoteca)
    {
        ArrayList<Integer> numPropiedades = new ArrayList<>();
        for(int i=0;i<jugadorActual.getPropiedades().size();i++)
        {
            boolean encontrado = false;
            for(int j=0;j<tablero.getCasillas().size() && !encontrado;j++)
            {
                if(jugadorActual.getPropiedades().get(i)==tablero.getCasillas().get(j).getTitulo() 
                        && jugadorActual.getPropiedades().get(j).isHipotecada() == estadoHipoteca)
                {
                    numPropiedades.add(tablero.getCasillas().get(j).getNumeroCasilla());
                    encontrado = true;
                }
            }
                
        }
        return numPropiedades;
    }
    
    public void obtenerRanking()
    {
        ArrayList<Jugador> posiciones = jugadores;
        for(int i=0;i<posiciones.size();i++)
        {
            for(int j=i+1;j<posiciones.size()-1;j++)
            {
                if(posiciones.get(i).compareTo(posiciones.get(j))<=0)
                {
                    Jugador aux = posiciones.get(j);
                    posiciones.set(j,posiciones.get(i));
                    posiciones.set(i,aux);
                }
            }
        }
        
        for(int i=0;i<posiciones.size();i++)
        {
            System.out.println("Posicion " + i+1 + ": " + posiciones.get(i));
        }
    }
    
    public int obtenerSaldoJugadorActual()
    {
        return jugadorActual.getSaldo();
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
    
    public Jugador siguiente()
    {
        int mod = 0;
        boolean encontrado = false;
        for(int i=0;i<jugadores.size() && !encontrado;i++)
        {
            if(jugadores.get(i)==jugadorActual) mod = (i+1)%jugadores.size(); encontrado = true;
        }
        
        return jugadores.get(mod);
    }
    
    int tirarDado()
    {
        return dado.tirar();
    }
    
    public boolean venderPropiedad(int numeroCasilla)
    {
        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        boolean puedeVender = jugadorActual.venderPropiedad(casilla);
        if(puedeVender) setEstado(EstadoJuego.JA_PUEDEGESTIONAR);
        return puedeVender;
        
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

    private Jugador getJugadorActual() {
        return jugadorActual;
    }
    
    public String getNombreJugadorActual()
    {
        return jugadorActual.getNombre();
    }

    public ArrayList<Jugador> getJugadores() {
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

    public EstadoJuego getEstado() {
        return estado;
    }

    public void setEstado(EstadoJuego estado) {
        this.estado = estado;
    }
    
    

    @Override
    public String toString() {
        return "Qytetet{" + "mazo=" + mazo + ", tablero=" + tablero + ", MAX_JUGADORES=" + MAX_JUGADORES + ", NUM_SORPRESAS=" + NUM_SORPRESAS + ", NUM_CASILLAS=" + NUM_CASILLAS + ", PRECIO_LIBERTAD=" + PRECIO_LIBERTAD + ", SALDO_SALIDA=" + SALDO_SALIDA + ", cartaActual=" + cartaActual + ", jugadorActual=" + jugadorActual + ", jugadores=" + jugadores + ", dado=" + dado + '}';
    }


     
     
    
}
