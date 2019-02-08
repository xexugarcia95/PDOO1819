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
   
    def calcular_coste_cancelar
    
        coste_cancelar = calcular_coste_hipotecar + (0.1*calcular_coste_hipotecar)
        return coste_cancelar
    end
    
    def calcular_coste_hipotecar
    
        coste_hipoteca = (@hipoteca_base + @num_casas *0.5*@hipoteca_base + @num_hoteles * @hipoteca_base)
        return coste_hipoteca
    end
    
    def calcular_importe_alquiler
    
        coste_alquiler = @alquiler_base + (@num_casas*5 + @num_hoteles*2)
        return coste_alquiler
    end
    
    def calcular_precio_venta
    
         precio_venta =  (@precio_compra + (@num_casas + @num_hoteles) * @precio_edificar * @factor_revalorizacion)
        return precio_venta
    end
    
    def cancelar_hipoteca
    
    end    
    
    
     def cobrar_alquiler(coste)
    
     end
    
    
    def edificar_casa
      @num_casas = @num_casas +1
    end   
    
    
    def edificar_hotel
      @num_hoteles = @num_hoteles + 1
    end   
    
    
    def to_s
      "\nNombre: #{@nombre} \n Precio_compra: #{@precio_compra} \n Alquiler_base: #{@alquiler_base} \n Factor_revalorizacion: #{@factor_revalorizacion} \n Hipoteca_base: #{@hipoteca_base} \n Precio_edificar: #{@precio_edificar} \n Hipotecada: #{@hipotecada} \n Num_casas: #{@num_casas} \n Num_hoteles: #{@num_hoteles}"
    end
  end
end