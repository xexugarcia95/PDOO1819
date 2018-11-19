/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.Random;

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
        Random r = new Random();
        int val = r.nextInt(5)+1;
        return val;
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
