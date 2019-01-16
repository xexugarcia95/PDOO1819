/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author jesus
 */
public class Casilla {
    private int numeroCasilla;
    private int coste;
    private TipoCasilla tipo;
    private TituloPropiedad titulo;

    public Casilla(int numeroCasilla,TipoCasilla tipo) {
        this.numeroCasilla = numeroCasilla;
        this.coste = 0;
        this.tipo = tipo;
        this.titulo = null;
    }

    //Constructor para Casillas del TipoCasilla -> CALLE
    public Casilla(int numeroCasilla,TituloPropiedad titulo) {
        this.numeroCasilla = numeroCasilla;
        setTitulo(titulo);
        this.tipo = TipoCasilla.CALLE;
        this.coste = this.titulo.getPrecioCompra();
    }
    
    TituloPropiedad asignarPropietario(Jugador jugador)
    {
        titulo.setPropietario(jugador);
        
        return titulo;
    }
    
    int pagarAlquiler()
    {
        int costeAlquiler = titulo.pagarAlquiler();
        return costeAlquiler;
    }
    
    boolean PropietarioEncarcelado()
    {
        return titulo.propietarioEncarcelado();
    }
    
    boolean soyEdificable()
    {
        return (tipo == TipoCasilla.CALLE);
    }
    
    boolean tengoPropietario()
    {
        return titulo.tengoPropietario();
    }

    int getNumeroCasilla() {
        return numeroCasilla;
    }

    int getCoste() {
        return coste;
    }

    TipoCasilla getTipo() {
        return tipo;
    }

    TituloPropiedad getTitulo() {
        return titulo;
    }

    private void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Casilla{" + "numeroCasilla=" + numeroCasilla + ", coste=" + coste + ", tipo=" + tipo + ", titulo=" + titulo + '}' + "\n";
    }
    
    
    
    
    
}
