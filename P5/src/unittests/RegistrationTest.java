/**
 * Test unitario para la clase Registration
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import registrations.Registration;
import registrations.RegistrationKind;
import interfaces.IObserver;

public class RegistrationTest {

    @Test
    public void testPay() {
        Registration registration = new Registration("Dani", RegistrationKind.STUDENT);
        registration.pay(50.0);
        assertEquals(50.0, registration.getAmountPayed(), 0.0);
    }

    @Test
    public void testGetAmountPayed() {
        Registration registration = new Registration("Pedro", RegistrationKind.MEMBER);
        registration.pay(100.0);
        assertEquals(100.0, registration.getAmountPayed(), 0.0);
    }

    @Test
    public void testGetTotalAmount() {
        Registration registration = new Registration("Pablo", RegistrationKind.STUDENT);
        assertEquals(450.0, registration.getTotalAmount(), 0.0);
    }

    @Test
    public void testGetAffiliation() {
        Registration registration = new Registration("Juan", RegistrationKind.MEMBER);
        registration.setAffiliation("UAM");
        assertEquals("UAM", registration.getAffiliation());
    }

    @Test
    public void testSetAffiliation() {
        Registration registration = new Registration("Eva", RegistrationKind.STUDENT);
        registration.setAffiliation("UCM");
        assertEquals("UCM", registration.getAffiliation());
    }

    @Test
    public void testGetValidated() {
        Registration registration = new Registration("Carlos", RegistrationKind.MEMBER);
        registration.setValidated(true);
        assertTrue(registration.getValidated());
    }

    @Test
    public void testSetValidated() {
        Registration registration = new Registration("Sergio", RegistrationKind.STUDENT);
        registration.setValidated(false);
        assertFalse(registration.getValidated());
    }

    @Test
    public void testWithTracker() {
        Registration registration = new Registration("Pedro", RegistrationKind.MEMBER);
        IObserver<Registration> observer = new IObserver<Registration>() {
            @Override
            public void update(Registration registration) {
                return;
            }
        };
        Registration.withTracker(observer);
        assertTrue(registration.getObservers().contains(observer));
    }

    @Test
    public void testToString() {
        Registration registration = new Registration("Fran", RegistrationKind.STUDENT);
        assertEquals("Reg. of: Fran", registration.toString());
    }

    @Test
    public void testEquals() {
        Registration registration1 = new Registration("Sara", RegistrationKind.MEMBER);
        Registration registration2 = new Registration("Sara", RegistrationKind.MEMBER);
        assertTrue(registration1.equals(registration2));
    }

    @Test
    public void testHashCode() {
        Registration registration = new Registration("Stanciu", RegistrationKind.STUDENT);
        assertEquals("Stanciu".hashCode(), registration.hashCode());
    }
}