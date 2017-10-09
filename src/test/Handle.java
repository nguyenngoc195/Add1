/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.HashMap;

/**
 *
 * @author Lan
 */
public class Handle {

    public HashMap<String, String> validateKilo(String kilo) {
        HashMap<String, String> errors = new HashMap<>();
        if (kilo.isEmpty()) {
            errors.put("txt.kilo", "Enter number");
        }

        return errors;
    }
}
