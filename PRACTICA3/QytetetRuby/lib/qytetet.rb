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

        casilla = @jugador_actual.casilla_actual
        titulo = casilla.getTitulo();
        cancelar = jugadorActual.cancelarHipoteca(titulo);
        setEstado(EstadoJuego.JA_PUEDEGESTIONAR);
        return cancelar;
    end

    def comprar_titulo_propiedad

        raise NotImplementedError
    end

    def edificar_casa(numeroCasilla)

        raise NotImplementedError
    end

    def edificar_hotel(numeroCasilla)

        raise NotImplementedError
    end



    def get_valor_dado

        raise NotImplementedError
    end

    def hipotecar_propiedad(numeroCasilla)

    end


    def inicializar_juego(nombres)
        inicializar_cartas_sorpresa
        inicializar_jugadores(nombres)
        
    end



    def entrar_salir_carcel(metodo)
    
        raise NotImplementedError
    end

    def jugar


    end

    def mover(num_casilla_destino)
      

    end

    def obtener_casilla_jugador_actual

        raise NotImplementedError
    end

    def obtener_casillas_tablero

        raise NotImplementedError
    end

    def obtener_propiedades_jugador

        raise NotImplementedError
    end

    def obtener_propiedades_jugador_segun_estado_hipoteca(estado_hipoteca)

        raise NotImplementedError
    end

    def obtener_ranking


    end

    def  obtener_saldo_jugador_actual

        raise NotImplementedError
    end



    def siguiente_jugador


    end

    def tirar_dado
    
        raise NotImplementedError
    end

    def vender_propiedad(numero_casilla)

        raise NotImplementedError
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


    end

    def inicializar_jugadores(nombres)

        nombres.each do |n|

            j = Jugador.new(n)
            @jugadores << j
            
        end

    end

    def salida_jugadores

    end
    

  end
end
