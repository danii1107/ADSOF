import java.util.*;

public class PlanificadorMenu {
    private List<Plato> platosDisponibles;
    private Map<ElementoNutricional, Double> restriccionesNutricionales = new EnumMap<>(ElementoNutricional.class);
    private Set<Alergeno> alergenosExcluidos = EnumSet.noneOf(Alergeno.class);

    public PlanificadorMenu(List<Plato> platos) {
        this.platosDisponibles = platos;
    }
    
    public PlanificadorMenu conMaximo(ElementoNutricional elemento, double maximo) {
        restriccionesNutricionales.put(elemento, maximo);
        return this;
    }
    
    public PlanificadorMenu sinAlergenos(Alergeno... alergenos) {
        Collections.addAll(alergenosExcluidos, alergenos);
        return this;
    }
    
    public Menu planificar(double caloriasMin, double caloriasMax) {
        Set<Plato> platosMenu = new HashSet<>();
        double caloriasTotales = 0.0;

        for (Plato plato : this.platosDisponibles) {
            boolean cumpleRestricciones = true;
            for (Map.Entry<ElementoNutricional, Double> restriccion : restriccionesNutricionales.entrySet()) {
                Double valorPlato = plato.getInfoNutricional().get(restriccion.getKey().ordinal());
                if (valorPlato > restriccion.getValue()) {
                    cumpleRestricciones = false;
                    break;
                }
            }

            boolean tieneAlergenosExcluidos = alergenosExcluidos.stream().anyMatch(alergeno -> plato.getAlergenosUnicos().contains(alergeno.name()));

            if (cumpleRestricciones && !tieneAlergenosExcluidos) {
                double caloriasPlato = plato.getInfoNutricional().get(ElementoNutricional.CALORIAS.ordinal());
                if (caloriasTotales + caloriasPlato <= caloriasMax) {
                    platosMenu.add(plato);
                    caloriasTotales += caloriasPlato;
                }
            }
            if (caloriasTotales >= caloriasMin) {
                break;
            }
        }

        if (platosMenu.isEmpty() || caloriasTotales < caloriasMin)
            return null;
        Plato[] platosArray = platosMenu.toArray(new Plato[0]);
        return new Menu(platosArray);
    }
}