#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  class Jugador
    
    attr_accessor :nombre, :encarcelado, :saldo, :cartaLibertad, :casillaActual, :propiedades
    
    def initialize(nombre)
      @nombre = nombre;
      @encarcelado = false
      @saldo = 7500
      @cartaLibertad = nil
      @casillaActual = nil
      @propiedades = nil
    end
    
    def cancelarHipoteca(titulo)
    
        raise NotImplementedError
    end
    
    def comprarTituloPropiedad
    
        raise NotImplementedError
    end
    
    def cuantasCasasHotelesTengo
    
        raise NotImplementedError
    end
    
    def deboPagarAlquiler
    
        raise NotImplementedError
    end
    
    def devolverCartaLibertad
    
        raise NotImplementedError
    end
    
    def edificarCasa(titulo)
    
        raise NotImplementedError
    end
    
    def edificarHotel(titulo)
    
        raise NotImplementedError
    end
    
    def eliminarDeMisPropiedades(titulo)
    
        raise NotImplementedError
    end
    
    def esDeMiPropiedad(titulo)
    
        raise NotImplementedError
    end
    
    def estoyEnCalleLibre
    
        raise NotImplementedError
    end
    
    def hipotecarPropiedad(titulo)
    
        raise NotImplementedError
    end
    
    def irACarcel(casilla)
    
        raise NotImplementedError
    end
    
    def modificarSaldo(cantidad)
    
        raise NotImplementedError
    end
    
    def obtenerCapital
    
        raise NotImplementedError
    end
    
    def obtenerPropiedades(hipotecada)
    
        raise NotImplementedError
    end
    
    def pagarAlquiler
    
        
    end
    
    def pagarImpuesto
    
        
    end
    
    def pagarLibertad(cantidad)
    
        
    end
    
    def tengoCartaLibertad()
    
        raise NotImplementedError
    end
    
    def tengoSaldo(cantidad)
    
        raise NotImplementedError
    end
    
    def venderPropiedad(casilla)
    
        raise NotImplementedError
    end
    
  end
end
