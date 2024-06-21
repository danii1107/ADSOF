/**
 * Tester que hereda de menustester para crear los menus e invoca los métodos
 * de ComparadorMenus e imprime los retornos para comprobar el funcionamiento de la clase
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.testers;

import src.informacion.*;
import src.comparador.*;
import src.menus.*;

import java.util.*;

public class ComparadorTester extends MenusTester {
    /**
     * Ejecuta el tester, invocando a los métodos de ordenación y printeando el retorno
     * @param comparador El comparador que contiene los menús y los métodos de ordenado
     */
    protected void ejecutar(ComparadorMenus comparador) {
        System.out.println("*********************** Menús ordenados para cada elemento nutricional ***********************");
        for (Map.Entry<ElementoNutricional, List<Menu>> entry : comparador.menusElementoNutricional().entrySet()) {
            System.out.println("**" + entry.getKey().toString().toUpperCase() + "**");
            entry.getValue().forEach(m -> System.out.println(m + "\n"));
        }

        System.out.println("\n*********************** Menus ordenados por calorías ***********************");
        int i = 1;
        for (Menu menu : comparador.ordenaCalorias()) {
            System.out.println("**" + i + "**");
            System.out.println(menu);
            i++;
        }

        System.out.println("\n*********************** Menús que contienen cada alergeno ***********************");
        for (Map.Entry<Alergeno, List<Menu>> entry : comparador.tieneAlergenos().entrySet()) {
            System.out.println("**" + entry.getKey().toString().toUpperCase() + "**\n");
            entry.getValue().forEach(m -> System.out.println(m + "\n"));
        }
    }

    public static void main(String[] args) {
        ComparadorTester tester = new ComparadorTester();
        List<Menu> menus = tester.crearMenus();

        ComparadorMenus comparador = new ComparadorMenus(menus.toArray(new Menu[0]));

        tester.ejecutar(comparador);
    }
}