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
        int costeCancelar = (int) (calcularCosteHipotecar() + (0.1*calcularCosteHipotecar()));
        return costeCancelar;
    }
    
    int calcularCosteHipotecar()
    {
        int costeHipoteca = (int) (hipotecaBase + numCasas * 0.5 * hipotecaBase +  numHoteles * hipotecaBase);
        return costeHipoteca;
    }
    
    int calcularImporteAlquiler()
    {
       int costeAlquiler = alquilerBase + (numCasas*5 + numHoteles*2);
       return costeAlquiler;
    }
    
    int calcularPrecioVenta()
    {
        int precioVenta = (int) (precioCompra + (numCasas + numHoteles) * precioEdificar * factorRevalorizacion);
        return precioVenta;
    }
    
    void cancelarHipoteca()
    {
        hipotecada = false;
    }
    
    void cobrarAlquiler(int coste)
    {
        
    }
    
    void edificarCasa()
    {
        numCasas+=1;
    }
    
    void edificarHotel()
    {
        numHoteles+=1;
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
        setHipotecada(true);
        int costeHipoteca = calcularCosteHipotecar();
        return costeHipoteca;
    }
    
    int pagarAlquiler()
    {
        int costeAlquiler = calcularImporteAlquiler();
        propietario.modificarSaldo(costeAlquiler);
        return costeAlquiler;
    }
    
    boolean propietarioEncarcelado()
    {
        return propietario.isEncarcelado();
    }
    
    boolean tengoPropietario()
    {
        return (propietario!=null);
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
