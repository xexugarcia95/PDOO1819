#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "sorpresa.rb"
require_relative "tablero.rb"
require "singleton"

module ModeloQytetet
  class Qytetet
    include Singleton


    attr_reader :mazo, :tablero
    @@instance = Qytetet.new
    @mazo
    @tablero

    def self.instance
       
      return @@instance
    end

    def inicializar_cartas_sorpresa
        @mazo = Array.new
        inicializar_tablero
        mazo << Sorpresa.new("El banco tira la casa por la ventana. Recibe 500",TipoSorpresa::PAGARCOBRAR,500)
        mazo << Sorpresa.new("Multa por bailar la conga. Paga 500",TipoSorpresa::PAGARCOBRAR,-500)
        mazo << Sorpresa.new("Corre como el viento perdigón!. Ve a la casilla de salida.",TipoSorpresa::IRACASILLA,0)
        mazo << Sorpresa.new("Te posee el espíritu de M.J y bailas un Moonwalk. Retrocede a la casilla 5.",TipoSorpresa::IRACASILLA,5)
        mazo << Sorpresa.new("Vas pegando brincos de alegría. Ve a la casilla 15.",TipoSorpresa::IRACASILLA,15)
        mazo << Sorpresa.new("El banco ha errado en la hipoteca. Recibe 50 por cada casa/hotel.",TipoSorpresa::PORCASAHOTEL,50)
        mazo << Sorpresa.new("Te se ha ido la olla hipotecando inmuebles. Paga 50 por cada casa/hotel",TipoSorpresa::PORCASAHOTEL,-50)
        mazo << Sorpresa.new("Es tu cumpleaños. Recibe 20 de cada jugador.",TipoSorpresa::PORJUGADOR,20)
        mazo << Sorpresa.new("Has perdido una apuesta. Paga 20 a cada jugador.",TipoSorpresa::PORJUGADOR,-20)
        mazo << Sorpresa.new("Has quedado libre de la cárcel.",TipoSorpresa::SALIRCARCEL,0)
    end

    def actuarSiEnCasillaEdificable


    end

    def actuarSiEnCasillaNoEdificable

    end


    def void aplicarSorpresa

    end


    def cancelarHipoteca(numeroCasilla)

        raise NotImplementedError
    end

    def comprarTituloPropiedad

        raise NotImplementedError
    end

    def edificarCasa(numeroCasilla)

        raise NotImplementedError
    end

    def edificarHotel(numeroCasilla)

        raise NotImplementedError
    end



    def getValorDado

        raise NotImplementedError
    end

    def hipotecarPropiedad(numeroCasilla)

    end


    def inicializarJuego(nombres)
        inicializarCartasSorpresa
        inicializarTablero
        inicializarJugadores(nombres)
        
    end



    def entrarSalirCarcel(metodo)
    
        raise NotImplementedError
    end

    def jugar()


    end

    def mover(numCasillaDestino)
      

    end

    def obtenerCasillaJugadorActual

        raise NotImplementedError
    end

    def obtenerCasillasTablero

        raise NotImplementedError
    end

    def obtenerPropiedadesJugador

        raise NotImplementedError
    end

    def obtenerPropiedadesJugadorSegunEstadoHipoteca(estadoHipoteca)

        raise NotImplementedError
    end

    def obtenerRanking


    end

    def  obtenerSaldoJugadorActual

        raise NotImplementedError
    end



    def siguienteJugador


    end

    def tirarDado
    
        raise NotImplementedError
    end

    def venderPropiedad(numeroCasilla)

        raise NotImplementedError
    end
    
    def initialize
      nombres = Array.new
      nombres<<"Jugador 1"
      nombres<<"Jugador 2"
      inicializarJuego(nombres)

    end

    private

    

    def inicializar_tablero
      @tablero = Tablero.new
    end

    def encarcelarJugador


    end

    def inicializarJugadores(nombres)

        nombres.each do |n|

            j = Jugador.new(n)
            jugadores << j
        end

    end

    def salidaJugadores

    end
    
    public
    
    def to_s
      puts "Tablero: #{@tablero} \n Mazo: #{@mazo}"
    end
    
    private_class_method :new

  end
end
