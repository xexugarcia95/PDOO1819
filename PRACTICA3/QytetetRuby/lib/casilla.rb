#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "tipo_casilla.rb"
require_relative "titulo_propiedad.rb"

module ModeloQytetet
  class Casilla
    
    attr_reader :numero_casilla,:tipo,:titulo,:coste
    attr_writer :titulo
    
    def initialize(numero_casilla,tipo,titulo)
      @numero_casilla = numero_casilla
      if tipo == TipoCasilla::CALLE
        @tipo = TipoCasilla::CALLE
        @titulo = titulo
        @coste = titulo.precio_compra
      else
        @tipo = tipo
        @titulo = 0
        @coste = 0
        
      end
    end
    
    def asignar_propietario(jugador)
    
        @titulo.propietario = jugador
        
        return @titulo
    end
    
    def pagar_alquiler
    
        coste_alquiler = @titulo.pagar_alquiler
        return coste_alquiler
    end
    
    def propietario_encarcelado
    
        return titulo.propietario_encarcelado
    end
    
    def soy_edificable
    
        return (@tipo == TipoCasilla::CALLE)
    end
    
    def tengo_propietario
    
        return @titulo.tengo_propietario
    end

    
    def to_s
      "N_casilla: #{@numero_casilla} \n Tipo: #{@tipo} \n Titulo: #{@titulo} \n Coste: #{@coste} \n\n"
    end
  end
end