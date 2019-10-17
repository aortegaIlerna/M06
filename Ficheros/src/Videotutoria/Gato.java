package Videotutoria;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
@XStreamAlias("Gato")
public class Gato implements Serializable {
    private static final long serialVersionUID = 1L;
    @XStreamAlias("Años")
    private int edad;
    @XStreamAlias("Peso_kg")
    private double peso;
    @XStreamAlias("nombre")
    private String name;

    public Gato() {
        edad=18;
        peso=15.12;
        name="Gato";
    }

    public Gato(int edad, double peso, String name) {
        this.edad = edad;
        this.peso = peso;
        this.name = name;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Gato{" +
                "edad=" + edad +
                ", peso=" + peso +
                ", name='" + name + '\'' +
                '}';
    }
}
