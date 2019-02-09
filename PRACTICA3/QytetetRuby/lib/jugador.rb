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
    
        num_casas = titulo.num_casas
        edificada = false
        if(num_casas==4)
        
            coste_edificar_hotel = titulo.precio_edificar
            tengo_saldo = tengo_saldo(coste_edificar_hotel)
            if(tengo_saldo)
            
                titulo.edificar_hotel
                modificar_saldo(-coste_edificar_hotel)
                edificada = true
            end
        end
        return edificada
    end
    
    def eliminar_de_mis_propiedades(titulo)
    
        propiedades.delete(titulo)
        titulo.propietario = null
        precio_venta = titulo.calcular_precio_venta
        modificar_saldo(precio_venta)
    end
    
    def es_de_mi_propiedad(titulo)
    
        encontrado = false
        for i in 0..@propiedades.size && !encontrado
        
            if(@propiedades[i]==titulo) 
              encontrado = true;
            end
        end
        return encontrado
    end
    
    def estoy_en_calle_libre
    
        raise NotImplementedError
    end
    
    def hipotecar_propiedad(titulo)
    
        coste_hipoteca = titulo.hipotecar
        modificar_saldo(coste_hipoteca)
        
        return titulo.is_hipotecada
    end
    
    def ir_a_carcel(casilla)
    
        @casilla_actual = casilla
        @encarcelado = true
    end
    
    def modificar_saldo(cantidad)
    
        nuevo = @saldo + cantidad;
        return nuevo
    end
    
    def obtener_capital
    
        capital = @saldo
        @propiedades.each do |propiedad|
        
            capital+=propiedad.precio_compra + (propiedad.num_casas+propiedad.num_hoteles)*propiedad.precio_edificar
            if(propiedad.is_hipotecada) 
              capital-=propiedad.hipoteca_base
            end
        end
        return capital
    end
    
    def obtener_propiedades(hipotecada)
    
        raise NotImplementedError
    end
    
    def pagar_alquiler
       coste_alquiler = @casilla_actual.pagar_alquiler
       modificar_saldo(-coste_alquiler)      
    end
    
    def pagar_impuesto
       @saldo-=@casilla_actual.coste       
    end
    
    def pagar_libertad(cantidad)
       tengo_saldo = tengo_saldo(cantidad)
        if(tengo_saldo)
        
            @encarcelado = false
            modificar_saldo(-cantidad)
        end  
    end
    
    def tengo_carta_libertad
    
         return (@carta_libertad!=null)
    end
    
    def tengo_saldo(cantidad)
    
        return(@saldo>cantidad)
    end
    
    def vender_propiedad(casilla)
    
        titulo = casilla.titulo
        eliminar_de_mis_propiedades(titulo)
        es_no_es = es_de_mi_propiedad(titulo)
        return es_no_es
    end
    
    def to_s
      puts "Nombre: #{@nombre} \n Encarcelado: #{@encarcelado} \n Saldo: #{@saldo} \n CartaLib: #{@cartaLibertad} \n CasillaAct: #{@casillaActual} \n Propiedades: #{@propiedades} \n"

    end
    
  end
end
