package com.gestion.animales.models;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Animal {
    private Integer id;
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 3, max = 15, message = "El nombre debe tener entre 3 y 15 caracteres.")
    private String nombre;
    @Min(value = 0, message = "La vida media debe ser como mínimo 0.")
    @Max(value = 600, message = "La vida media no puede superar los 600.")
    private int vidaMedia;
    private boolean extinto;

    //Constructor con los cuatro atributos
    public Animal(Integer id, String nombre, int vidaMedia, boolean extinto) {
        setId(id);
        setNombre(nombre);
        setVidaMedia(vidaMedia);
        setExtinto(extinto);
    }

    //Generar getters y setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVidaMedia() {
        return vidaMedia;
    }
    public void setVidaMedia(int vidaMedia) {
        this.vidaMedia = vidaMedia;
    }

    public boolean isExtinto() {
        return extinto;
    }
    public void setExtinto(boolean extinto) {
        this.extinto = extinto;
    }

    public Animal() {

    }
}


