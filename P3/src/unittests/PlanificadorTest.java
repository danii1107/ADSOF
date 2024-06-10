/**
 * Test unitario de la clase Planificador
 * 
 * @author Daniel Birsan daniel.birsan@estudiante.uam.es
 */
package src.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import static src.informacion.ElementoNutricional.*;
import src.informacion.ElementoNutricional;
import src.informacion.Alergeno;
import src.comida.Plato;
import src.testers.*;
import src.menus.*;

import java.util.*;

public class PlanificadorTest extends PlatosTester {

	@Test
    public void test_planificar() {
        Map<String, Plato> platos = this.crearPlatos();
        
        PlanificadorMenu planificador = new PlanificadorMenu(Arrays.asList(platos.values().toArray(new Plato[0])));
        
        Menu menu = planificador.planificar(700, 1500);
        
        assertNotNull(menu);
        assertTrue(menu.getInfoNutricional().getInfoNutricional().get(CALORIAS) >= 700 && menu.getInfoNutricional().getInfoNutricional().get(CALORIAS) <= 1500);
    }
    
    @Test
    public void test_planificarSinAlergenos() {
        Map<String, Plato> platos = this.crearPlatos();
        
        PlanificadorMenu planificador = new PlanificadorMenu(Arrays.asList(platos.values().toArray(new Plato[0])));
        
        planificador.sinAlergenos(Alergeno.FRUTOS_SECOS);
        
        Menu menu = planificador.planificar(700, 1500);
        
        assertNotNull(menu);
        assertTrue(menu.getInfoNutricional().getInfoNutricional().get(CALORIAS) >= 700 && menu.getInfoNutricional().getInfoNutricional().get(CALORIAS) <= 1500);
        for (Plato p : menu.getPlatos()) {
            assertFalse(p.getAlergenos().contains(Alergeno.FRUTOS_SECOS));
        }
    }

	@Test
    public void test_planificarCOnAlergenos() {
        Map<String, Plato> platos = this.crearPlatos();
        
        PlanificadorMenu planificador = new PlanificadorMenu(Arrays.asList(platos.values().toArray(new Plato[0])));
        
        planificador.sinAlergenos(Alergeno.LACTOSA, Alergeno.GLUTEN, Alergeno.HUEVO);
        
        Menu menu = planificador.planificar(700, 1500);
        
        assertNull(menu);
        for (Plato p : Arrays.asList(platos.values().toArray(new Plato[0]))) {
            assertTrue(p.getAlergenos().contains(Alergeno.LACTOSA) || p.getAlergenos().contains(Alergeno.GLUTEN) || p.getAlergenos().contains(Alergeno.HUEVO));
        }
    }

	@Test
    public void test_planificarMaximos() {
        Map<String, Plato> platos = this.crearPlatos();
        
        PlanificadorMenu planificador = new PlanificadorMenu(Arrays.asList(platos.values().toArray(new Plato[0])));
        
		planificador.conMaximo(ElementoNutricional.SODIO, 50.0)
                      .conMaximo(ElementoNutricional.AZUCARES, 30.0);
		
		Menu menu = planificador.planificar(700, 1500);
        
		assertNotNull(menu);
        assertTrue(menu.getInfoNutricional().getInfoNutricional().get(CALORIAS) >= 700 && menu.getInfoNutricional().getInfoNutricional().get(CALORIAS) <= 1500);
        for (Plato p : menu.getPlatos()) {
            assertTrue(p.getInfoNutricional().getInfoNutricional().get(ElementoNutricional.SODIO) <= 50.0);
            assertTrue(p.getInfoNutricional().getInfoNutricional().get(ElementoNutricional.AZUCARES) <= 30.0);
        }
    }

	@Test
    public void test_planificarMaximosRebasados() {
        Map<String, Plato> platos = this.crearPlatos();
        
        PlanificadorMenu planificador = new PlanificadorMenu(Arrays.asList(platos.values().toArray(new Plato[0])));
        
		planificador.conMaximo(ElementoNutricional.CARBOHIDRATOS, 1.0);
		
		Menu menu = planificador.planificar(700, 1500);
        
		assertNull(menu);
        for (Plato p : Arrays.asList(platos.values().toArray(new Plato[0]))) {
            assertTrue(p.getInfoNutricional().getInfoNutricional().get(ElementoNutricional.CARBOHIDRATOS) >= 1.0);
        }
    }
}
