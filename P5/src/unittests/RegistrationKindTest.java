/**
 * Test unitario de la enum RegistrationKind
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import registrations.RegistrationKind;

public class RegistrationKindTest {

    @Test
    public void testGetPrice() {
        assertEquals(1100, RegistrationKind.FULL.getPrice(), 0);
        assertEquals(900, RegistrationKind.MEMBER.getPrice(), 0);
        assertEquals(450, RegistrationKind.STUDENT.getPrice(), 0);
    }
}