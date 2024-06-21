/**
 * Patron observer
 * Interfaz que implementan los observadores de un subject
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package interfaces;

public interface IObserver<T> {
    /**
     * Metodo caracteristico del patrón observer, se invoca cuando
     * recibe una notificación del concretesubject
     * 
     * @param t Objeto que ha sido actualizado 
     */
    void update(T t);
}