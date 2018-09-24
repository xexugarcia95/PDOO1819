/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modeloqytetet;

/**
 *
 * @author Jesus Manuel Garcia Palma
 * @correo xexugarcia95@gmail.com / jesusluisbcrespo@correo.ugr.es
 */
public class Sorpresa {
    private String texto;
    private TipoSorpresa tipo;
    int valor;

    public Sorpresa(String texto, TipoSorpresa tipo, int valor) {
        this.texto = texto;
        this.tipo = tipo;
        this.valor = valor;
    }

    private String getTexto() {
        return texto;
    }

    private TipoSorpresa getTipo() {
        return tipo;
    }

    private int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Sorpresa{" + "texto=" + texto + ", tipo=" + tipo + ", valor=" + Integer.toString(valor) + '}';
    }
    
    
}
