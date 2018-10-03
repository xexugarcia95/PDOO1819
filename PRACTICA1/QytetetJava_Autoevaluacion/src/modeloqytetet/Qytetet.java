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
public class Qytetet {
    private ArrayList<Sorpresa> mazo = new ArrayList<>();
    private Tablero tablero;
    
    public ArrayList<Sorpresa> getMazo() {
        return mazo;
    }
    
    private void inicializarTablero()
    {
        tablero = new Tablero(TipoTablero.PUEBLO);
    }
    
    public void inicializarCartasSorpresa()
    {
        inicializarTablero();
        mazo.add(new Sorpresa("El banco tira la casa por la ventana. Recibe 500",TipoSorpresa.PAGARCOBRAR,500));
        mazo.add(new Sorpresa("Multa por bailar la conga. Paga 500",TipoSorpresa.PAGARCOBRAR,-500));
        mazo.add(new Sorpresa("Corre como el viento perdigón!. Ve a la casilla de salida.",TipoSorpresa.IRACASILLA,0));
        mazo.add(new Sorpresa("Te posee el espíritu de M.J y bailas un Moonwalk. Retrocede a la casilla 5.",TipoSorpresa.IRACASILLA,5));
        mazo.add(new Sorpresa("Te ha pillado la pasma. Vas a la carcel",TipoSorpresa.IRACASILLA,tablero.getCarcel().getNumeroCasilla()));
        mazo.add(new Sorpresa("El banco ha errado en la hipoteca. Recibe 50 por cada casa/hotel.",TipoSorpresa.PORCASAHOTEL,50));
        mazo.add(new Sorpresa("Te se ha ido la olla hipotecando inmuebles. Paga 50 por cada casa/hotel",TipoSorpresa.PORCASAHOTEL,-50));
        mazo.add(new Sorpresa("Es tu cumpleaños. Recibe 20 de cada jugador.",TipoSorpresa.PORJUGADOR,20));
        mazo.add(new Sorpresa("Has perdido una apuesta. Paga 20 a cada jugador.",TipoSorpresa.PORJUGADOR,-20));
        mazo.add(new Sorpresa("Has quedado libre de la cárcel.",TipoSorpresa.SALIRCARCEL,0));
    }
    
    
    

    public Tablero getTablero() {
        return tablero;
    }
    
    
}
