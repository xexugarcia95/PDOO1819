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
 */
public class PruebaQytetet {
       private static Qytetet juego = Qytetet.getInstance();
    
    public static void main(String[] args) {
       //PruebaQytetet prueba = new PruebaQytetet();
       juego.getNombreJugadores();
       /*System.out.println("Tablero\n");
       System.out.println(juego.getTablero().toString());
       System.out.println("Sorpresas\n");
       System.out.println(juego.getMazo().toString());
       
       System.out.println(prueba.metodo1());
       System.out.println(prueba.metodo2());
       
       TipoSorpresa[] t =  TipoSorpresa.values();
       for(TipoSorpresa tipo: t)
       {
          System.out.println(prueba.metodo3(tipo)); 
       }
       */
       System.out.println(juego.toString(
       
       ));
    }
    
    private ArrayList<Sorpresa> metodo1() //sorpresas cuyo valor es mayor a 0
    {
        ArrayList<Sorpresa> array = new ArrayList<>();
        for(Sorpresa s: juego.getMazo())
        {
            if(s.getValor()>0) array.add(s);
        }
        
        return array;
    }
    
    private ArrayList<Sorpresa> metodo2() //Sorpresas de TipoSorpresa IRACASILLA
    {
        ArrayList<Sorpresa> array = new ArrayList<>();
        for(Sorpresa s: juego.getMazo())
        {
            if(s.getTipo() == TipoSorpresa.IRACASILLA) array.add(s);
        }
        
        return array;
    }
    
    private ArrayList<Sorpresa> metodo3(TipoSorpresa tipo) //Sorpresas del TipoSorpresa indicado como argumento
    {
        ArrayList<Sorpresa> array = new ArrayList<>();
        for(Sorpresa s: juego.getMazo())
        {
            if(s.getTipo()==tipo) array.add(s);
        }
        
        return array;
    }
    
}
