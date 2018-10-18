#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require "singleton"

module ModeloQytetet
  
  class Dado
    include Singleton
    
    @@instance = Dado.new
    
    def initialize(valor)
      @valor = valor
    end
    
    def tirar
      raise NotImplementedError
    end
    
    def self.instance
      return @@instance
    end
    
    
  end
end
