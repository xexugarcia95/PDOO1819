#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "qytetet.rb"
require_relative "tablero.rb"

module ModeloQytetet
  class PruebaQytetet
    @@juego = Qytetet.instance
    
    def main
      nombres = Array.new
      nombres << "Jugador1"
      nombres << "Jugador2"
     # @@juego.inicializar_juego(nombres)
      puts "Tablero\n"
      puts(@@juego)
      #puts "Mazo\n"
      #puts(@@juego.mazo)
      #puts(metodo_1)
       #puts(metodo_2)
      #arr = Array.new(TipoSorpresa::constants)
      #arr.each do |tipos|
        #puts metodo_3(tipos)
       # puts metodo_3(TipoSorpresa.const_get(tipos))
      #end
      
    end
    
    def metodo_1
      array = Array.new
      puts "\nSorpresas con valor mayor que cero\n"
      @@juego.mazo.each do |maz|
        if maz.valor > 0
          array<< maz
        end
        
       end
       return array
    end
    
    def metodo_2
      array = Array.new
      puts "\nSorpresas de TipoSorpresa IRACASILLA"
      @@juego.mazo.each do |maz|
        if maz.tipo == TipoSorpresa::IRACASILLA
          array<< maz
        end
      end
      return array
    end
    
    def metodo_3(tip)
      puts "\nSorpresas del tipo especificado\n"
      array = Array.new
      @@juego.mazo.each do |maz|
        if maz.tipo == tip
          array<< maz
        end
      end
      return array
    end
    
  end
  
  prueba = PruebaQytetet.new
  prueba.main
end
