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
        int calcularCosteCancelar = titulo.calcularCosteCancelar();
        boolean cancelar = (saldo > calcularCosteCancelar);
        if(cancelar)
        {
            modificarSaldo(-calcularCosteCancelar);
            titulo.setHipotecada(false);
        }
        
        return cancelar;
    }
    
    boolean comprarTituloPropiedad()
    {
        int costeCompra = casillaActual.getCoste();
        boolean comprado= false;
        if(costeCompra<saldo)
        {
            TituloPropiedad titulo = casillaActual.asignarPropietario(this);
            propiedades.add(titulo);
            modificarSaldo(-costeCompra);
            comprado = true;
        }
        return comprado;
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
        TituloPropiedad titulo = casillaActual.getTitulo();
        boolean esDeMiPropiedad = esDeMiPropiedad(titulo);
        boolean hipotecada = false;
        if(!esDeMiPropiedad)
        {            
            boolean tienePropietario = titulo.tengoPropietario();
            if(tienePropietario)
            {
                encarcelado = titulo.propietarioEncarcelado();
                if(!encarcelado)
                {
                    hipotecada = titulo.isHipotecada();
                }
            }
        }
        
        return hipotecada;
    }
    
    Sorpresa devolverCartaLibertad()
    {
        Sorpresa s = cartaLibertad;
        cartaLibertad = null;
        return s;
    }
    
    boolean edificarCasa(TituloPropiedad titulo)
    {
        int numCasas = titulo.getNumCasas();
        boolean edificada = false;
        if(numCasas<4)
        {
            int costeEdificarCasa = titulo.getPrecioEdificar();
            boolean tengoSaldo = tengoSaldo(costeEdificarCasa);
            if(tengoSaldo)
            {
                titulo.edificarCasa();
                modificarSaldo(-costeEdificarCasa);
                edificada = true;
            }
        }
        return edificada;
    }
    
    boolean edificarHotel(TituloPropiedad titulo)
    {
        int numCasas = titulo.getNumCasas();
        boolean edificada = false;
        if(numCasas==4)
        {
            int costeEdificarHotel = titulo.getPrecioEdificar();
            boolean tengoSaldo = tengoSaldo(costeEdificarHotel);
            if(tengoSaldo)
            {
                titulo.edificarHotel();
                modificarSaldo(-costeEdificarHotel);
                edificada = true;
            }
        }
        return edificada;
    }
    
    private void eliminarDeMisPropiedades(TituloPropiedad titulo)
    {
        propiedades.remove(titulo);
        titulo.setPropietario(null);
        int precioVenta = titulo.calcularPrecioVenta();
        modificarSaldo(precioVenta);
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
        int costeHipoteca = titulo.hipotecar();
        modificarSaldo(costeHipoteca);
        return titulo.isHipotecada();
    }
    
    void irACarcel(Casilla casilla)
    {
        setCasillaActual(casilla);
        setEncarcelado(true);
    }
    
    int modificarSaldo(int cantidad)
    {
        int nuevo = saldo + cantidad;
        return nuevo;
    }
    
    int obtenerCapital()
    {
        int propiedadesTotal = 0;
        int prEd;
        int numCH;
        for(int i=0;i<propiedades.size();i++) {
             prEd = propiedades.get(i).getPrecioEdificar();
             numCH = propiedades.get(i).getNumCasas() + propiedades.get(i).getNumHoteles();
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
        int costeAlquiler = casillaActual.pagarAlquiler();
        modificarSaldo(-costeAlquiler);
    }
    
    void pagarImpuesto()
    {
        saldo-=casillaActual.getCoste();
    }
    
    void pagarLibertad(int cantidad)
    {
        boolean tengoSaldo = tengoSaldo(cantidad);
        if(tengoSaldo)
        {
            setEncarcelado(false);
            modificarSaldo(-cantidad);
        }
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
        TituloPropiedad titulo = casilla.getTitulo();
        eliminarDeMisPropiedades(titulo);
        boolean esNoEs = esDeMiPropiedad(titulo);
        return esNoEs;
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
    
    boolean getEncarcelado()
    {
        return encarcelado;
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
