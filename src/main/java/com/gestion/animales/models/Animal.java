package com.gestion.animales.models;

public class Animal {
    private Integer id;
    private String nombre;
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

    /*@Override
    public String toString() {
        return "id: " + id + ", nombre: " + nombre + ", vida media: " + vidaMedia + ", extinto: " + (extinto ? "Si" : "No");
    }*/

}
