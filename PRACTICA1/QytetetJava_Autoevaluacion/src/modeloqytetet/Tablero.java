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
    private TipoTablero tipo;

    public Tablero(TipoTablero tipo) {
        this.tipo = tipo;
        inicializar();
    }
    
    private void inicializar()
    {
        casillas = new ArrayList<>();   
        casillas.add(new Casilla(0,TipoCasilla.SALIDA));
        casillas.add(new Casilla(1,new TituloPropiedad("Calle Monte",100,20,(float)0.5,10,50)));
        casillas.add(new Casilla(2,new TituloPropiedad("Calle Falsa123",150,20,(float)0.5,15,75)));
        casillas.add(new Casilla(3,TipoCasilla.SORPRESA));
        casillas.add(new Casilla(4,new TituloPropiedad("Calle Paca",200,30,(float)0.5,20,100)));
        casillas.add(new Casilla(5,new TituloPropiedad("Calle Mingo",250,30,(float)0.5,25,125)));
        casillas.add(new Casilla(6,TipoCasilla.JUEZ));
        casillas.add(new Casilla(7,new TituloPropiedad("Calle Tiriti Trump",300,40,(float)0.5,30,150)));
        casillas.add(new Casilla(8,new TituloPropiedad("Calle Lepanto",350,40,(float)0.5,35,175)));
        casillas.add(new Casilla(9,TipoCasilla.SORPRESA));
        casillas.add(new Casilla(10,new TituloPropiedad("Calle Ambrosio",400,50,(float)0.5,40,200)));
        casillas.add(new Casilla(11,new TituloPropiedad("Calle Tapas",450,50,(float)0.5,45,225)));
        casillas.add(new Casilla(12,TipoCasilla.IMPUESTO));
        casillas.add(new Casilla(13,new TituloPropiedad("Calle Hulio",500,60,(float)0.5,50,250)));
        casillas.add(new Casilla(14,new TituloPropiedad("Calle Domingo",550,60,(float)0.5,55,275)));
        casillas.add(new Casilla(15,TipoCasilla.PARKING));
        casillas.add(new Casilla(16,new TituloPropiedad("Calle Pio",600,70,(float)0.5,60,300)));
        casillas.add(new Casilla(17,new TituloPropiedad("Calle Fifi",650,70,(float)0.5,65,375)));
        carcel = new Casilla(18,TipoCasilla.CARCEL);
        casillas.add(carcel);
        casillas.add(new Casilla(19,TipoCasilla.SORPRESA));
    }

    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    public Casilla getCarcel() {
        return carcel;
    }

    public TipoTablero getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Tablero{" + "casillas=" + casillas + ", carcel=" + carcel + ", tipo=" + tipo + '}';
    }
    
    
    
    
    
    
}
