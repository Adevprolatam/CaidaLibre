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
    public Caida_Libre(double altura, double tiempo) {
        this.altura = altura;
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
    public double  Calcular(){
        double gravedad = 9.8;
        //return Math.sqrt(2*this.altura*gravedad);
        return gravedad*this.tiempo;
    }
       
}
