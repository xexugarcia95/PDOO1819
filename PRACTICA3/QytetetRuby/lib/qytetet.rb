#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'sorpresa.rb'
require_relative 'tablero.rb'
require_relative 'jugador.rb'
require_relative 'estado_juego.rb'


module ModeloQytetet
  require 'singleton'
  class Qytetet
    include Singleton


    attr_reader :mazo, :tablero
    #@@instance = Qytetet.instance
    @mazo = nil
    @tablero = nil
    @jugadores = nil
    @@max_jugadores = 4
    @@num_sorpresas = 10
    @@num_casillas = 20
    @@precio_libertad = 200
    @@saldo_salida = 1000
    @carta_actual
    @jugador_actual
    @estado
    @dado

    def inicializar_cartas_sorpresa
        @mazo = Array.new
        inicializar_tablero
        @mazo << Sorpresa.new("El banco tira la casa por la ventana. Recibe 500",TipoSorpresa::PAGARCOBRAR,500)
        @mazo << Sorpresa.new("Multa por bailar la conga. Paga 500",TipoSorpresa::PAGARCOBRAR,-500)
        @mazo << Sorpresa.new("Corre como el viento perdigón!. Ve a la casilla de salida.",TipoSorpresa::IRACASILLA,0)
        @mazo << Sorpresa.new("Te posee el espíritu de M.J y bailas un Moonwalk. Retrocede a la casilla 5.",TipoSorpresa::IRACASILLA,5)
        @mazo << Sorpresa.new("Vas pegando brincos de alegría. Ve a la casilla 15.",TipoSorpresa::IRACASILLA,15)
        @mazo << Sorpresa.new("El banco ha errado en la hipoteca. Recibe 50 por cada casa/hotel.",TipoSorpresa::PORCASAHOTEL,50)
        @mazo << Sorpresa.new("Te se ha ido la olla hipotecando inmuebles. Paga 50 por cada casa/hotel",TipoSorpresa::PORCASAHOTEL,-50)
        @mazo << Sorpresa.new("Es tu cumpleaños. Recibe 20 de cada jugador.",TipoSorpresa::PORJUGADOR,20)
        @mazo << Sorpresa.new("Has perdido una apuesta. Paga 20 a cada jugador.",TipoSorpresa::PORJUGADOR,-20)
        @mazo << Sorpresa.new("Has quedado libre de la cárcel.",TipoSorpresa::SALIRCARCEL,0)
    end

    def actuar_si_en_casilla_edificable
      debo_pagar = jugadorActual.debo_pagar_alquiler

        if(debo_pagar)
        
            jugadorActual.pagar_alquiler
            if(jugadorActual.saldo<0)
                @estado = EstadoJuego::ALGUNJUGADORENBANCARROTA              
            end
        end
        casilla = obtener_casilla_jugador_actual
        tengo_propietario = casilla.tengo_propietario
        
        if(@estado==EstadoJuego::ALGUNJUGADORENBANCARROTA)
        
            if(tengo_propietario)
                @estado = EstadoJuego::JA_PUEDEGESTIONAR
            else
                @estado = EstadoJuego::JA_PUEDECOMPRARGESTIONAR
            end
        end    
    end

    def actuar_si_en_casilla_no_edificable
      @estado=EstadoJuego::JA_PUEDEGESTIONAR
        @casilla_actual = @jugador_actual.casilla_actual
        if(@casilla_actual.tipo==TipoCasilla::IMPUESTO)
        
            jugadorActual.pagarImpuesto();
        elsif(@casilla_actual.tipo==TipoCasilla::JUEZ)
        
            encarcelar_jugador
        elsif(@casilla_actual.tipo==TipoCasilla::SORPRESA)
        
            @mazo.remove(@carta_actual);
            @estado = EstadoJuego::JA_CONSORPRESA
        end
    end


    def aplicar_sorpresa
     @estado=EstadoJuego::JA_PUEDEGESTIONAR
        if(@carta_actual.tipo==TipoSorpresa::SALIRCARCEL)
        
            @jugador_actual.carta_libertad = @carta_actual
        else
        
            @mazo << @carta_actual
            if(@carta_actual.tipo==TipoSorpresa::PAGARCOBRAR)
            
                jugador_actual.modificar_saldo(@carta_actual.valor)
                if(jugador_actual.saldo<0)
                  
                    @estado = EstadoJuego::ALGUNJUGADORENBANCARROTA
                end
            elsif(@carta_actual.tipo==TipoSorpresa::IRACASILLA)
                
                    valor = @carta_actual.valor
                    casilla_carcel = @tablero.es_casilla_carcel(valor)
                    if(casilla_carcel)
                    
                        encarcelar_jugador
                    else
                    
                        mover(valor)
                    end
            elsif(@carta_actual.tipo==TipoSorpresa::PORCASAHOTEL)
                
                    cantidad = @carta_actual.valor
                    numero_total = @jugador_actual.cuantas_casas_hoteles_tengo
                    @jugador_actual.modificar_saldo(cantidad*numero_total);
                    
                    if(@jugador_actual.saldo<0)
                    
                        @estado= EstadoJuego::ALGUNJUGADORENBANCARROTA
                    end
            elsif(@carta_actual.tipo==TipoSorpresa::PORJUGADOR)
                
                    for i in 0..@max_jugadores
                    
                        jugador = @jugadores[i]
                        if(jugador!=@jugador_actual)
                        
                            jugadores[i].modificar_saldo(@carta_actual.valor)
                        end
                        if(jugador.saldo<0)
                        
                            @estado = EstadoJuego::ALGUNJUGADORENBANCARROTA
                        end
                        
                        @jugador_actual.modificar_saldo(-@carta_actual.valor)
                        if(@jugador_actual.saldo<0)
                        
                            @estado= EstadoJuego::ALGUNJUGADORENBANCARROTA
                        end
                    end 
            end
        end   
    end


    def cancelar_hipoteca(numero_casilla)

        casilla = @tablero.casillas[numero_casilla]
        titulo = casilla.titulo
        cancelar = @jugador_actual.cancelar_hipoteca(titulo)
        @estado=EstadoJuego::JA_PUEDEGESTIONAR
        return cancelar
    end

    def comprar_titulo_propiedad

        comprado = @jugador_actual.comprar_titulo_propiedad
        if(comprado) 
          @estado = EstadoJuego::JA_PUEDEGESTIONAR
        end
        
        return comprado
    end

    def edificar_casa(numero_casilla)

        casilla = @tablero.obtener_casilla_numero(numero_casilla)
        titulo = casilla.titulo
        edificada = @jugador_actual.edificar_casa(titulo)
       return edificada
    end

    def edificar_hotel(numero_casilla)

       casilla = @tablero.obtener_casilla_numero(numero_casilla);
       titulo = casilla.titulo
       edificada = @jugador_actual.edificar_hotel(titulo)
       return edificada
    end



    def get_valor_dado

        return @dado.valor
    end

    def hipotecar_propiedad(numero_casilla)
        casilla = @tablero.obtener_casilla_numero(numero_casilla);
        titulo = casilla.titulo
        @jugador_actual.hipotecar_propiedad(titulo)
        @estado= EstadoJuego::JA_PUEDEGESTIONAR
    end


    def inicializar_juego(nombres)
        inicializar_cartas_sorpresa
        inicializar_jugadores(nombres)
        
    end



    def intentar_salir_carcel(metodo)
    
        if(metodo == MetodoSalirCarcel::TIRANDODADO)
        
            resultado = tirar_dado
            if(resultado>=5) 
              @jugador_actual.encarcelado = false
            end
        elsif(metodo == MetodoSalirCarcel::PAGANDOLIBERTAD)
        
            @jugador_actual.pagar_libertad(@@precio_libertad)          
        end
        
        libre = @jugador_actual.encarcelado
        
        if(libre)
        
            @estado = EstadoJuego.JA_ENCARCELADO
        else
        
            @estadi = EstadoJuego.JA_PREPARADO
        end
        
        return libre
    end

    def jugar
        i =  tirar_dado
        casilla_actual = @tablero.obtener_casilla_final(@jugadorActual.casilla_actual,i)
        mover(casilla_actual.numero_casilla)

    end

    def mover(num_casilla_destino)
        casilla_inicial = @jugador_actual.casilla_actual
        casilla_final = @tablero.obtener_casilla_numero(num_casilla_destino)
        @jugador_actual.casilla_actual = casilla_final
        
        if(num_casilla_destino<casilla_inicial.numero_casilla)
        
            @jugador_actual.modificar_saldo(@@saldo_salida)
        end
        
        if(casilla_final.soy_edificable)
        
            actuar_si_en_casilla_edificable
        else
        
            actuar_si_en_casilla_no_edificable
        end

    end

    def obtener_casilla_jugador_actual

        return @jugador_actual.casilla_actual
    end

    def obtener_casillas_tablero

        return @tablero.casillas
    end

    def obtener_propiedades_jugador

        num_propiedades = Array.new
        for i in 0..@jugador_actual.propiedades.size
        
            encontrado = false
            for j in 0..@tablero.casillas.size && !encontrado
            
                if(@jugador_actual.propiedades[i]==@tablero.casillas[i].titulo)
                
                    num_propiedades << @tablero.casillas[j].numero_casilla
                    encontrado = true;
                end
            end
                
        end
        return numPropiedades
    end

    def obtener_propiedades_jugador_segun_estado_hipoteca(estado_hipoteca)

        num_propiedades = Array.new
        for i in 0..@jugador_actual.propiedades.size
        
            encontrado = false
            for j in 0..@tablero.casillas.size && !encontrado
            
                if(@jugador_actual.propiedades[i]==@tablero.casillas[j].titulo && @jugador_actual.propiedades[j].is_hipotecada == estado_hipoteca)
                
                    num_propiedades << @tablero.casillas[j].numero_casilla
                    encontrado = true
                end
            end
                
        end
        return num_propiedades
    end

    def obtener_ranking
      posiciones = @jugadores
        for i in 0..posiciones.size
        
            for j in i+1..@posiciones.size-1
            
                if(posiciones[i].<=>(posiciones[j])<=0)
                
                    aux = posiciones[j]
                    posiciones[j] = posiciones[i]
                    posiciones[i] = aux
                end
            end
        end
        
        for i in 0..@posiciones.size
            puts ("Posicion " + i+1 + ": " + @posiciones[i])
        end

    end

    def  obtener_saldo_jugador_actual

        return @jugador_actual.saldo
    end



    def siguiente_jugador
        mod = 0
        encontrado = false
        for i in 0..@jugadores.size && !encontrado
       
            if(jugadores[i]==@jugador_actual) 
              mod = (i+1)%@jugadores.size 
              encontrado = true;
            end
        end
        
        @jugador_actual = @jugadores[mod]
        if(@jugador_actual.is_encarcelado)
          @stado = EstadoJuego::JA_ENCARCELADOCONOPCIONDELIBERTAD
        else 
          @estado = EstadoJuego::JA_PREPARADO
        end

    end
    
    def siguiente
        mod = 0;
        encontrado = false
        for i in 0..@jugadores.size && !encontrado
        
            if(@jugadores[i]==@jugador_actual) 
              mod = (i+1)%@jugadores.size 
              encontrado = true
            end
        end
        return @jugadores[mod]
    end

    def tirar_dado
    
        @dado.tirar
    end

    def vender_propiedad(numero_casilla)

        casilla = @tablero.obtener_casilla_numero(numero_casilla)
        puede_vender = @jugador_actual.vender_propiedad(casilla)
        if(puede_vender) 
          @estado = EstadoJuego::JA_PUEDEGESTIONAR
        end
        return puede_vender
    end
    
    def nombre_jugadores
      array = Array.new
        jugadores.each do |j|
        
            puts(j.nombre)
            array << j.nombre
            
        end
        
        return array
    end
    
    def initialize
      @jugadores = Array.new
      @estado = EstadoJuego.new
      nombres = Array.new
      nombres<<"Jugador 1"
      nombres<<"Jugador 2"
      inicializar_juego(nombres)

    end
    
    def to_s
      @jugadores.each do |ju|
        puts ju
      end
      
      puts "Tablero: #{@tablero} \n"
      @mazo.each do |m|
        puts m
      end
    end

    private

    

    def inicializar_tablero
      @tablero = Tablero.new
    end

    def encarcelar_jugador
      if(!@jugador_actual.tengo_carta_libertad)
        
            casilla_carcel = @tablero.carcel
            @jugador_actual.ir_a_carcel(casilla_carcel)
            @estado = EstadoJuego.JA_ENCARCELADO
       else
        
            carta = @jugador_actual.devolver_carta_libertad
            @mazo << carta 
            @estado= EstadoJuego.JA_PUEDEGESTIONAR
      end

    end

    def inicializar_jugadores(nombres)

        nombres.each do |n|

            j = Jugador.new(n)
            @jugadores << j
            
        end

    end

    def salida_jugadores
      for i in 0..@jugadores.size 
        @jugadores[i].casilla_actual = @tablero.obtener_casilla_numero(0)
      end
        r = Random.new
        numero = r.rand(0...1)
        @jugador_actual = @jugadores[numero]
        @estado = EstadoJuego::JA_PREPARADO
    end
    

  end
end
