/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author Jesus Manuel Garcia Palma
 */
public class Dado {
    
    private int valor;
    
    private static final Dado instance = new Dado();
    
    private Dado()
    {
    }
    
    int tirar()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public static Dado getInstance()
    {
        return instance;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Dado{" + "valor=" + valor + '}';
    }
    
    
}
