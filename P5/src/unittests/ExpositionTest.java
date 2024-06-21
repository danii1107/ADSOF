/**
 * Test unitario para la clase Exposition
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import expositions.Exposition;
import expositions.ExpositionKind;
import interfaces.IObserver;

public class ExpositionTest {

    @Test
    public void testEntraGente() {
        Exposition exposition = new Exposition("Expo1", ExpositionKind.ESCULTURAS);
        exposition.entraGente(15);
        assertEquals(5, exposition.getAforoDispo());
    }

    @Test
    public void testGetAforoMax() {
        Exposition exposition = new Exposition("Expo2", ExpositionKind.ESCULTURAS);
        assertEquals(20, exposition.getAforoMax());
    }

    @Test
    public void testGetAforoDispo() {
        Exposition exposition = new Exposition("Expo3", ExpositionKind.AUDIOVISUAL);
        assertEquals(50, exposition.getAforoDispo());
    }

    @Test
    public void testGetLleno() {
        Exposition exposition = new Exposition("Expo4", ExpositionKind.PINTURA);
        assertFalse(exposition.getLleno());
    }

    @Test
    public void testSetLleno() {
        Exposition exposition = new Exposition("Expo5", ExpositionKind.AUDIOVISUAL);
        exposition.setLleno(true);
        assertTrue(exposition.getLleno());
    }

    @Test
    public void testWithTracker() {
        Exposition exposition = new Exposition("Expo6", ExpositionKind.PINTURA);
        IObserver<Exposition> observer = new IObserver<Exposition>() {
            @Override
            public void update(Exposition exposition) {
                return;
            }
        };
        Exposition.withTracker(observer);
        assertTrue(exposition.getObservers().contains(observer));
    }

    @Test
    public void testToString() {
        Exposition exposition = new Exposition("Expo7", ExpositionKind.ESCULTURAS);
        assertEquals("Exposición: Expo7", exposition.toString());
    }

    @Test
    public void testEquals() {
        Exposition exposition1 = new Exposition("Expo8", ExpositionKind.AUDIOVISUAL);
        Exposition exposition2 = new Exposition("Expo8", ExpositionKind.AUDIOVISUAL);
        assertTrue(exposition1.equals(exposition2));
    }

    @Test
    public void testHashCode() {
        Exposition exposition = new Exposition("Expo9", ExpositionKind.PINTURA);
        assertEquals("Expo9".hashCode(), exposition.hashCode());
    }
}