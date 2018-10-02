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
public class Tablero {
    private ArrayList<Casilla> casillas;
    private Casilla carcel;

    public Tablero() {
        inicializar();
    }
    
    private void inicializar()
    {
        //...Rellenar de los tipos de casillas
        casillas = new ArrayList<>();
        casillas.add(new Casilla(0,0,TipoCasilla.SALIDA));
        casillas.add(new Casilla(1,new TituloPropiedad("Calle Monte",100,0,0,0,0)));
        casillas.add(new Casilla(2,new TituloPropiedad("Calle Falsa123",100,0,0,0,0)));
        casillas.add(new Casilla(3));
        casillas.add(new Casilla(4,new TituloPropiedad("Calle se",100,0,0,0,0)));
        casillas.add(new Casilla(5,new TituloPropiedad("Calle se",100,0,0,0,0)));
        casillas.add(new Casilla(6));
        casillas.add(new Casilla(7,new TituloPropiedad("Calle se",100,0,0,0,0)));
        casillas.add(new Casilla(8,new TituloPropiedad("Calle se",100,0,0,0,0)));
        casillas.add(new Casilla(9));
        casillas.add(new Casilla(10,new TituloPropiedad("Calle se",100,0,0,0,0)));
        casillas.add(new Casilla(11,new TituloPropiedad("Calle se",100,0,0,0,0)));
        casillas.add(new Casilla(12));
        casillas.add(new Casilla(13,new TituloPropiedad("Calle se",100,0,0,0,0)));
        casillas.add(new Casilla(14,new TituloPropiedad("Calle se",100,0,0,0,0)));
        casillas.add(new Casilla(15));
        casillas.add(new Casilla(16,new TituloPropiedad("Calle se",100,0,0,0,0)));
        casillas.add(new Casilla(17,new TituloPropiedad("Calle se",100,0,0,0,0)));
        casillas.add(new Casilla(18));
        casillas.add(new Casilla(19));
    }
    
    
}
