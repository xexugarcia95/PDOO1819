#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "tipo_sorpresa.rb"
module ModeloQytetet
  class Sorpresa
    attr_reader :texto, :tipo, :valor
  
    def initialize(texto,tipo,valor)
      @texto = texto
      @tipo = tipo
      @valor = valor
    end
    
    def to_s
      "Texto: #{@texto} \n Valor: #{@valor} \n Tipo: #{@tipo}"
    end
  end
end