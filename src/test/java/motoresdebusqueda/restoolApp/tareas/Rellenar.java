package motoresdebusqueda.restoolApp.tareas;

import motoresdebusqueda.restoolApp.UI.PaginaPrincipal;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

public class Rellenar implements Task {

    private String nombre, razon, user;
    private int opc_posicion;

    public Rellenar(String nombre, int opc_posicion){
        this.nombre = nombre;
        this.opc_posicion = opc_posicion;
    }

    public Rellenar(String nombre, String razon){
        this.nombre = nombre;
        this.razon = razon;
    }

    public static Performable campos(String nombre, int opc_posicion){
        return Instrumented.instanceOf(Rellenar.class).withProperties(nombre, opc_posicion);
    }

    public static Performable campos(String nombre, String razon){
        return Instrumented.instanceOf(Rellenar.class).withProperties(nombre, razon);
    }

    @Override
    @Step("{0} decide rellenar los campos")
    public <T extends Actor> void performAs(T actor){
        if (this.opc_posicion != 0) {
            actor.attemptsTo(
                    SendKeys.of(this.nombre)
                            .into(PaginaPrincipal.CAMPO_NAME)
                            .then(Click.on(By.xpath("//select//option[position()='"+opc_posicion+"']")))
                            .then(Click.on(PaginaPrincipal.BOTON_SUBMIT))

            );
        } else {
            actor.attemptsTo(
                    SendKeys.of(this.nombre)
                            .into(PaginaPrincipal.CAMPO_NAME)
                            .then(SendKeys.of(this.razon)
                            .into(By.cssSelector("form:nth-child(1) div.form-row.row:nth-child(2) > input:nth-child(2)")))
                            .then(Click.on(PaginaPrincipal.BOTON_SUBMIT))

            );
        }
    }

}