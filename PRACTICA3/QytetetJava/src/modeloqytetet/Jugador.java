/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class Jugador implements Comparable {
    private boolean encarcelado;
    private String nombre;
    private int saldo;
    private Sorpresa cartaLibertad;
    private Casilla casillaActual;
    private ArrayList<TituloPropiedad> propiedades;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.encarcelado = false;
        this.saldo = 7500;
        
    }
    
    @Override
    public int compareTo(Object otroJugador)
    {
        int otroCapital = ((Jugador) otroJugador).obtenerCapital();
        return otroCapital - obtenerCapital();
    }
       
    
    boolean cancelarHipoteca(TituloPropiedad titulo)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean comprarTituloPropiedad()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int cuantasCasasHotelesTengo()
    {
        int numero = 0;
        for(int i=0;i<propiedades.size();i++)
        {
            numero+=propiedades.get(i).getNumCasas();
            numero+=propiedades.get(i).getNumHoteles();
        }
        
        return numero;
    }
    
    boolean deboPagarAlquiler()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    Sorpresa devolverCartaLibertad()
    {
        Sorpresa s = cartaLibertad;
        cartaLibertad = null;
        return s;
    }
    
    boolean edificarCasa(TituloPropiedad titulo)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean edificarHotel(TituloPropiedad titulo)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private void eliminarDeMisPropiedades(TituloPropiedad titulo)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private boolean esDeMiPropiedad(TituloPropiedad titulo)
    {
        boolean encontrado = false;
        for(int i=0;i<propiedades.size() && !encontrado;i++)
        {
            if(propiedades.get(i)==titulo) encontrado = true;
        }
        
        return encontrado;
    }
    
    boolean estoyEnCalleLibre()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean hipotecarPropiedad(TituloPropiedad titulo)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    void irACarcel(Casilla casilla)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int modificarSaldo(int cantidad)
    {
        int nuevo = saldo + cantidad;
        return nuevo;
    }
    
    int obtenerCapital()
    {
        int propiedadesTotal = 0;
        for(int i=0;i<propiedades.size();i++)
        {
            int prEd = propiedades.get(i).getPrecioEdificar();
            int numCH = propiedades.get(i).getNumCasas() + propiedades.get(i).getNumHoteles();
            propiedadesTotal+=propiedades.get(i).getPrecioCompra()+(numCH*prEd);
            if(propiedades.get(i).isHipotecada()) propiedadesTotal-= propiedades.get(i).getHipotecaBase();
        }
        
       propiedadesTotal+=saldo;
        return propiedadesTotal;
    }
    
    ArrayList<TituloPropiedad> obtenerPropiedades(boolean hipotecada)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    void pagarAlquiler()
    {
        
    }
    
    void pagarImpuesto()
    {
        saldo-=casillaActual.getCoste();
    }
    
    void pagarLibertad(int cantidad)
    {
        
    }
    
    boolean tengoCartaLibertad()
    {
        return (cartaLibertad!=null);
    }
    
    private boolean tengoSaldo(int cantidad)
    {
        return(saldo>cantidad);
    }
    
    boolean venderPropiedad(Casilla casilla)
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    
    //CONSULTODRES Y MODIFICADORES

    boolean isEncarcelado() {
        return encarcelado;
    }

    String getNombre() {
        return nombre;
    }

    public int getSaldo() {
        return saldo;
    }

    Sorpresa getCartaLibertad() {
        return cartaLibertad;
    }

    Casilla getCasillaActual() {
        return casillaActual;
    }

    ArrayList<TituloPropiedad> getPropiedades() {
        return propiedades;
    }

    void setEncarcelado(boolean encarcelado) {
        this.encarcelado = encarcelado;
    }

    void setCartaLibertad(Sorpresa cartaLibertad) {
        this.cartaLibertad = cartaLibertad;
    }

    void setCasillaActual(Casilla casillaActual) {
        this.casillaActual = casillaActual;
    }

    @Override
    public String toString() {
        return "Jugador{" + "encarcelado=" + encarcelado + ", nombre=" + nombre + ", saldo=" + saldo + ", capital=" + obtenerCapital() + ", cartaLibertad=" + cartaLibertad + ", casillaActual=" + casillaActual + ", propiedades=" + propiedades + '}';
    }

    
    
    
    
    
    
}
