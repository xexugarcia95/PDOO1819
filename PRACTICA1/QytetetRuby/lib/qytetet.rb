#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "sorpresa.rb"

module ModeloQytetet
  class Qytetet
    attr_reader :mazo, :tablero
    
    @mazo
    @tablero
    def inicializar_cartas_sorpresa
        @mazo = Array.new
        inicializar_tablero
        mazo << Sorpresa.new("El banco tira la casa por la ventana. Recibe 500",TipoSorpresa::PAGARCOBRAR,500)
        mazo << Sorpresa.new("Multa por bailar la conga. Paga 500",TipoSorpresa::PAGARCOBRAR,-500)
        mazo << Sorpresa.new("Corre como el viento perdigón!. Ve a la casilla de salida.",TipoSorpresa::IRACASILLA,0)
        mazo << Sorpresa.new("Te posee el espíritu de M.J y bailas un Moonwalk. Retrocede a la casilla 5.",TipoSorpresa::IRACASILLA,5)
        mazo << Sorpresa.new("Vas pegando brincos de alegría. Ve a la casilla 15.",TipoSorpresa::IRACASILLA,15)
        mazo << Sorpresa.new("El banco ha errado en la hipoteca. Recibe 50 por cada casa/hotel.",TipoSorpresa::PORCASAHOTEL,50)
        mazo << Sorpresa.new("Te se ha ido la olla hipotecando inmuebles. Paga 50 por cada casa/hotel",TipoSorpresa::PORCASAHOTEL,-50)
        mazo << Sorpresa.new("Es tu cumpleaños. Recibe 20 de cada jugador.",TipoSorpresa::PORJUGADOR,20)
        mazo << Sorpresa.new("Has perdido una apuesta. Paga 20 a cada jugador.",TipoSorpresa::PORJUGADOR,-20)
        mazo << Sorpresa.new("Has quedado libre de la cárcel.",TipoSorpresa::SALIRCARCEL,0)
    end
    
    private
    
    def inicializar_tablero
      @tablero = Tablero.new
    end
    
  end
end