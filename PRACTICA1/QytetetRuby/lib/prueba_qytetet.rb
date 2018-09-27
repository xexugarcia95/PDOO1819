#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "qytetet.rb"

module ModeloQytetet
  class PruebaQytetet
    @@juego = Qytetet.new
    
    def main
      
      @@juego.inicializar_cartas_sorpresa
      puts(@@juego.mazo)
    end
    
  end
  
  prueba = PruebaQytetet.new
  prueba.main
end
