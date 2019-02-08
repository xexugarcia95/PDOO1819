#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "casilla.rb"

module ModeloQytetet
  
  attr_reader :casillas,:carcel
  
  class Tablero
    @casillas
    @carcel
    @@num_casillas = 0
    def initialize
      inicializar
    end
    
    private
  
    def inicializar
      @casillas = Array.new
        @casillas << Casilla.new(0,TipoCasilla::SALIDA,0)
        @casillas << Casilla.new(1,TipoCasilla::CALLE,TituloPropiedad.new("Calle Monte",100,20,0.5,10,50))
        @casillas << Casilla.new(2,TipoCasilla::CALLE,TituloPropiedad.new("Calle Falsa123",150,20,0.5,15,75))
        @casillas << Casilla.new(3,TipoCasilla::SORPRESA,0)
        @casillas << Casilla.new(4,TipoCasilla::CALLE,TituloPropiedad.new("Calle Paca",200,30,0.5,20,100))
        @casillas << Casilla.new(5,TipoCasilla::CALLE,TituloPropiedad.new("Calle Mingo",250,30,0.5,25,125))
        @casillas << Casilla.new(6,TipoCasilla::JUEZ,0)
        @casillas << Casilla.new(7,TipoCasilla::CALLE,TituloPropiedad.new("Calle Tiriti Trump",300,40,0.5,30,150))
        @casillas << Casilla.new(8,TipoCasilla::CALLE,TituloPropiedad.new("Calle Lepanto",350,40,0.5,35,175))
        @casillas << Casilla.new(9,TipoCasilla::SORPRESA,0)
        @casillas << Casilla.new(10,TipoCasilla::CALLE,TituloPropiedad.new("Calle Ambrosio",400,50,0.5,40,200))
        @casillas << Casilla.new(11,TipoCasilla::CALLE,TituloPropiedad.new("Calle Tapas",450,50,0.5,45,225))
        @casillas << Casilla.new(12,TipoCasilla::IMPUESTO,0)
        @casillas << Casilla.new(13,TipoCasilla::CALLE,TituloPropiedad.new("Calle Hulio",500,60,0.5,50,250))
        @casillas << Casilla.new(14,TipoCasilla::CALLE,TituloPropiedad.new("Calle Domingo",550,60,0.5,55,275))
        @casillas << Casilla.new(15,TipoCasilla::PARKING,0)
        @casillas << Casilla.new(16,TipoCasilla::CALLE,TituloPropiedad.new("Calle Pio",600,70,0.5,60,300))
        @casillas << Casilla.new(17,TipoCasilla::CALLE,TituloPropiedad.new("Calle Fifi",650,70,0.5,65,375))
        @carcel = Casilla.new(18,TipoCasilla::CARCEL,0)
        @casillas << @carcel
        @casillas << Casilla.new(19,TipoCasilla::SORPRESA,0)
    
    end
    
     def es_casilla_carcel(numero_casilla)
    
        return (numero_casilla == carcel.numero_casilla);
     end
    
     def obtener_casilla_final(casilla,desplazamiento)
    
         encontrado = false
         valor = 0
        for cas in @casilla && !encontrado do
        
            if(cas==casilla) 
              encontrado = true
              valor = cas.numero_casilla
            end
            
        end
     
        
        valor = (valor+desplazamiento)%@casillas.size
        #return @casillas
     end
    
     def obtener_casilla_numero(numero_casilla)
     
        raise NotImplementedError
     end
    
    def to_s
      
      @casillas.each do |cas|
        puts cas
      end
      puts "#{@carcel} \n"
    end
  
  end
end