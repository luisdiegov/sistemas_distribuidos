/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectAalpha;

import java.io.Serializable;

/**
 *
 * @author sdist
 */
public class Credencial implements Serializable{
    String name;
    String password;

    @Override
    public String toString() {
        return "Credencial{" + "name=" + name + ", password=" + password + '}';
    }

    public Credencial(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
