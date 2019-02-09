#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'titulo_propiedad.rb'

module ModeloQytetet
  class Jugador
    
    attr_accessor :nombre, :encarcelado, :saldo, :cartaLibertad, :casillaActual, :propiedades
    
    def initialize(nombre)
      @nombre = nombre;
      @encarcelado = false
      @saldo = 7500
      @carta_libertad = nil
      @casilla_actual = nil
      @propiedades = nil
    end
    
    def <=>(otro_jugador)
      otro_capital = otro_jugador.obtener_capital
      mi_capital=obtener_capital
      if(otro_capital>mi_capital)
        return 1 
      end
      if(otro_capital<mi_capital)
        return -1
      end
      
      return 0
    end
    
    def cancelar_hipoteca(titulo)
    
        calcular_coste_cancelar = titulo.calcular_coste_cancelar
        cancelar = (@saldo > calcular_coste_cancelar)
        if(cancelar)
        
            modificar_saldo(-calcular_coste_cancelar);
            titulo.hipotecada = false
        end
        
        return cancelar
    end
    
    def comprar_titulo_propiedad
    
        coste_compra = @casilla_actual.coste
        comprado= false
        if(coste_compra<@saldo)
        
            titulo = @casilla_actual.asignar_propietario(this)
            @propiedades << titulo
            modificar_saldo(-coste_compra)
            comprado = true
        end
        return comprado
    end
    
    def cuantas_casas_hoteles_tengo
    
        numero = 0
        for i in 0..@propiedades.size
        
            numero+=@propiedades[i].num_casas
            numero+=@propiedades[i].num_hoteles
        end
        
        return numero
    end
    
    def debo_pagar_alquiler
    
        titulo = @casilla_actual.titulo
        es_de_mi_propiedad = es_de_mi_propiedad(titulo)
        hipotecada = false
        
        if(!es_de_mi_propiedad)
                    
            tiene_propietario = titulo.tengo_propietario
            if(tiene_propietario)
            
                @encarcelado = titulo.propietario_encarcelado
                if(!@encarcelado)
                
                    hipotecada = titulo.is_hipotecada
                end
            end
        end
        
        return hipotecada
    end
    
    def devolver_carta_libertad
    
        s = @carta_libertad
        @carta_libertad = null
        return s
    end
    
    def edificar_casa(titulo)
    
        num_casas = titulo.numero_casas
        edificada = false
        if(num_casas<4)
        
            coste_edificar_casa = titulo.precio_edificar
            tengo_saldo = tengo_saldo(coste_edificar_casa)
            if(tengo_saldo)
            
                titulo.edificar_casa
                modificar_saldo(-coste_edificar_casa);
                edificada = true
            end
        end
        return edificada
    end
    
    def edificar_hotel(titulo)
    
        raise NotImplementedError
    end
    
    def eliminar_de_mis_propiedades(titulo)
    
        raise NotImplementedError
    end
    
    def es_de_mi_propiedad(titulo)
    
        raise NotImplementedError
    end
    
    def estoy_en_calle_libre
    
        raise NotImplementedError
    end
    
    def hipotecar_propiedad(titulo)
    
        raise NotImplementedError
    end
    
    def ir_a_carcel(casilla)
    
        raise NotImplementedError
    end
    
    def modificar_saldo(cantidad)
    
        raise NotImplementedError
    end
    
    def obtener_capital
    
        raise NotImplementedError
    end
    
    def obtener_propiedades(hipotecada)
    
        raise NotImplementedError
    end
    
    def pagar_alquiler
    
        
    end
    
    def pagar_impuesto
    
        
    end
    
    def pagar_libertad(cantidad)
    
        
    end
    
    def tengo_carta_libertad
    
        raise NotImplementedError
    end
    
    def tengo_saldo(cantidad)
    
        raise NotImplementedError
    end
    
    def vender_propiedad(casilla)
    
        raise NotImplementedError
    end
    
    def to_s
      puts "Nombre: #{@nombre} \n Encarcelado: #{@encarcelado} \n Saldo: #{@saldo} \n CartaLib: #{@cartaLibertad} \n CasillaAct: #{@casillaActual} \n Propiedades: #{@propiedades} \n"

    end
    
  end
end
