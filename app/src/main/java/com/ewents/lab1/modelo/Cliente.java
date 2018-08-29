package com.ewents.lab1.modelo;

public class Cliente {
    private String name,cuil;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "name='" + name + '\'' +
                ", cuil='" + cuil + '\'' +
                '}';
    }
}
