package com.felipecarranza.application2023_05_23.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Entries {
    private int id;
    private String title;
    private String content;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getShortContent() {
        if (content != null && !content.isEmpty()) {
            // Dividir el contenido en líneas
            String[] lines = content.split("\n");
            if (lines.length > 0) {
                // Obtener la primera línea
                String firstLine = lines[0].trim();
                // Limitar la longitud a 40 caracteres
                if (firstLine.length() > 40) {
                    firstLine = firstLine.substring(0, 40) + "...";
                }
                return firstLine;
            }
        }
        return ""; // Si el campo content está vacío o no hay contenido
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
