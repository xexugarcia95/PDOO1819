/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author jesus
 */
public class TituloPropiedad {
    private String nombre;
    private boolean hipotecada;
    private int precioCompra;
    private int alquilerBase;
    private float factorRevalorizacion;
    private int hipotecaBase;
    private int precioEdificar;
    private int numCasas;
    private int numHoteles;
    private Jugador propietario;

    public TituloPropiedad(String nombre, int precioCompra, int alquilerBase, float factorRevalorizacion, int hipotecaBase, int precioEdificar) {
        this.nombre = nombre;
        this.hipotecada = false;
        this.precioCompra = precioCompra;
        this.alquilerBase = alquilerBase;
        this.factorRevalorizacion = factorRevalorizacion;
        this.hipotecaBase = hipotecaBase;
        this.precioEdificar = precioEdificar;
        this.hipotecada = false;
        this.numCasas = 0;
        this.numHoteles = 0;
    }
    
    int calcularCosteCancelar()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int calcularCosteHipotecar()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int calcularImporteAlquiler()
    {
       throw new UnsupportedOperationException("Sin implementar"); 
    }
    
    int calcularPrecioVenta()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    void cancelarHipoteca()
    {
        
    }
    
    void cobrarAlquiler(int coste)
    {
        
    }
    
    void edificarCasa()
    {
        
    }
    
    void edificarHotel()
    {
        
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isHipotecada() {
        return hipotecada;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public int getAlquilerBase() {
        return alquilerBase;
    }

    public float getFactorRevalorizacion() {
        return factorRevalorizacion;
    }

    public int getHipotecaBase() {
        return hipotecaBase;
    }

    public int getPrecioEdificar() {
        return precioEdificar;
    }

    public int getNumCasas() {
        return numCasas;
    }

    public int getNumHoteles() {
        return numHoteles;
    }

    public void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }
    
    int hipotecar()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int pagarAlquiler()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean propietarioEncarcelado()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean tengoPropietario()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }

    Jugador getPropietario() {
        return propietario;
    }

    void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }
    
    

    @Override
    public String toString() {
        return "TituloPropiedad{" + "nombre=" + nombre + ", hipotecada=" + hipotecada + ", precioCompra=" + precioCompra + ", alquilerBase=" + alquilerBase + ", factorRevalorizacion=" + factorRevalorizacion + ", hipotecaBase=" + hipotecaBase + ", precioEdificar=" + precioEdificar + ", numCasas=" + numCasas + ", numHoteles=" + numHoteles + '}' + "\n";
    }
    
    
    
    
}
