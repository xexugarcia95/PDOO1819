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
public class Jugador {
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
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean deboPagarAlquiler()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    Sorpresa devolverCartaLibertad()
    {
        throw new UnsupportedOperationException("Sin implementar");
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
        throw new UnsupportedOperationException("Sin implementar");
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
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int obtenerCapital()
    {
        throw new UnsupportedOperationException("Sin implementar");
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
        
    }
    
    void pagarLibertad(int cantidad)
    {
        
    }
    
    boolean tengoCartaLibertad()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private boolean tengoSaldo(int cantidad)
    {
        throw new UnsupportedOperationException("Sin implementar");
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
        return "Jugador{" + "encarcelado=" + encarcelado + ", nombre=" + nombre + ", saldo=" + saldo + ", cartaLibertad=" + cartaLibertad + ", casillaActual=" + casillaActual + ", propiedades=" + propiedades + '}';
    }

    
    
    
    
    
    
}
