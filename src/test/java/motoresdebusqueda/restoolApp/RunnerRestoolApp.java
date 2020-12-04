package motoresdebusqueda.restoolApp;

import motoresdebusqueda.restoolApp.UI.*;
import motoresdebusqueda.restoolApp.tareas.*;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

@RunWith(SerenityRunner.class)
public class RunnerRestoolApp {

    Actor actor = Actor.named("Poseidón");

    @Managed WebDriver nvg;

    @Before public void elActorPuedeNavegarPorLaWeb(){
        actor.can(BrowseTheWeb.with(nvg));
    }

    @Test
    public void darDeAltaUnItem() {
        actor.has(Decidido.abrirElNavegador());

        actor.attemptsTo(
                IrHacia.seccion("Employees")
        );

        actor.attemptsTo(
                HacerClickEn.boton(PaginaPrincipal.BOTON_ADD_ITEM)
                        .then(Rellenar.campos("Daniel", 3))
        );

        try {
            actor.attemptsTo(
                    WaitUntil.the(PaginaPrincipal.ALERT_SUCCESS, isVisible()).forNoMoreThan(5).seconds(),
                    Ensure.that(PaginaPrincipal.ALERT_SUCCESS).hasText("Great Success!")
            );
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void darDeAltaUnItemEnDeads() {

        actor.has(Decidido.abrirElNavegador());

        actor.attemptsTo(
                IrHacia.seccion("Deads")
                        .then(HacerClickEn.boton(PaginaPrincipal.BOTON_ADD_ITEM))
                        .then(Rellenar.campos("Zeus", "Uknown"))
        );

        try {
            actor.attemptsTo(
                    WaitUntil.the(PaginaPrincipal.ALERT_SUCCESS, isVisible()).forNoMoreThan(5).seconds(),
                    Ensure.that(PaginaPrincipal.ALERT_SUCCESS).hasText("Great Success!")
            );
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
/* NO FUNCIONA, PRACTICANDO METODOS
    @Test
    public void realizarUnaBusqueda(){
        actor.has(Decidido.abrirElNavegador());

        actor.attemptsTo(
                IrHacia.seccion("Employees")
        );

        actor.attemptsTo(
                Realizar.busqueda("Daniel")
                        .then(WaitUntil.the(PaginaPrincipal.tablaDeEmployees, isPresent()).forNoMoreThan(5).seconds())
        );

        actor.attemptsTo(
                Comprobar.resultados("Daniel")
        );
    }
*/

}