package com.androidavanzado.prueba.model.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notas")
public class NotaEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String titulo;
    public String contenido;
    public boolean favorita;
    public String color;

    public NotaEntity(String titulo, String contenido, boolean favorita, String color) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.favorita = favorita;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean isFavorita() {
        return favorita;
    }

    public void setFavorita(boolean favorita) {
        this.favorita = favorita;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
