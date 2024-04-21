package com.example.multidiciplinario.Models;

import com.example.multidiciplinario.Controllers.LoginController;

public class Login extends LoginController {
    private String usuario;
    private String contrasena;
    public Login(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }


    public boolean validarIniciarSesion () {
        return usuario.equals("Alonso Macias") && contrasena.equals("1234");
    }


}
