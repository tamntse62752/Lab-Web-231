/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.utils;

/**
 *
 * @author SIMON
 */
public class RamdomOrderID {

    public static int randomInt(int min, int max) {
        int random_int = (int) (Math.random() * (max - min + 1) + min);
        return random_int;
    }

}
