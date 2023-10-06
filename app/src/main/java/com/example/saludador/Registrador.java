package com.example.saludador;

public class Registrador {
    public static class Solicitud{
        public String nombre, apellido, contraseña, nickname, gmail;

        public Solicitud(String nombre, String apellido, String contraseña,String nickname, String gmail) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.contraseña = contraseña;
            this.nickname = nickname;
            this.gmail = gmail;
        }


    }
    public String registro(Solicitud solicitud){
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {}
        return "El usuario " + solicitud.nickname + " llamado " + solicitud.nombre + " " + solicitud.apellido + " a sido creado con el siguiente correo: " + solicitud.gmail + "\nCon una contraseña de " + solicitud.contraseña.length() + " digitos";
    }
}
