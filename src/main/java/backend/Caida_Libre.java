/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;
/**
 *
 * @author dell
 */
public class Caida_Libre {
    
    private double altura;
    private double velocidadFinal;
    private double tiempo;
    private int indice;
    
    // CONSTRUCTOR
    public Caida_Libre(double altura, double velocidad, double tiempo) {
        this.altura = altura;
        this.velocidadFinal = velocidad;
        this.tiempo = tiempo;
    }
    
    // GETTERS 
    public double getAltura() {
        return altura;
    }

    public double getVelocidadFinal() {
        return velocidadFinal;
    }

    public double getTiempo() {
        return tiempo;
    }

    public int getIndice() {
        return indice;
    }
     
    // SETTERS 
    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setVelocidadFinal(double velocidadFinal) {
        this.velocidadFinal = velocidadFinal;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    
    // PROCESS
    public double  Calcular(String option){
        double gravedad = 9.8;
        switch(option){
            case "vf" -> {
                return Math.sqrt(2 * gravedad * this.altura);
            }
            case "d" -> {
                return 0.5 * gravedad * Math.pow(this.tiempo, 2);
            }
            case "t" ->{
                return velocidadFinal / gravedad;
            } 
        };
        return 0;
    }
       
}
