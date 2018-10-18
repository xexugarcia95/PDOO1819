#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module ModeloQytetet
  class TituloPropiedad
    
    attr_reader :nombre,:precio_compra,:alquiler_base,:factor_revalorizacion,:hipoteca_base,:precio_edificar,:hipotecada,:num_casas,:num_hoteles
    attr_writer :hipotecada
    
    def initialize(nombre,precio_compra,alquiler_base,factor_revalorizacion,hipoteca_base,precio_edificar)
      @nombre = nombre
      @precio_compra = precio_compra
      @alquiler_base = alquiler_base
      @factor_revalorizacion = factor_revalorizacion
      @hipoteca_base = hipoteca_base
      @precio_edificar = precio_edificar
      @hipotecada = false
      @num_casas = 0
      @num_hoteles = 0
      @propietario = nil
    end
    
    def calcularCosteCancelar
    
        raise NotImplementedError
    end
    
    def calcularCosteHipotecar
    
        raise NotImplementedError
    end
    
    def calcularImporteAlquiler
    
       raise NotImplementedError 
    end
    
    def calcularPrecioVenta
    
        raise NotImplementedError
    end
    
    def cancelarHipoteca
    
    end    
    
    
     def cobrarAlquiler(coste)
    
     end
    
    
    def edificarCasa
    
    end   
    
    
    def edificarHotel
    
    end   
    
    
    def to_s
      "\nNombre: #{@nombre} \n Precio_compra: #{@precio_compra} \n Alquiler_base: #{@alquiler_base} \n Factor_revalorizacion: #{@factor_revalorizacion} \n Hipoteca_base: #{@hipoteca_base} \n Precio_edificar: #{@precio_edificar} \n Hipotecada: #{@hipotecada} \n Num_casas: #{@num_casas} \n Num_hoteles: #{@num_hoteles}"
    end
  end
end