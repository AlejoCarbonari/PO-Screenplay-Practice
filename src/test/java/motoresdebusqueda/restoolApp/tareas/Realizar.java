package motoresdebusqueda.restoolApp.tareas;

import motoresdebusqueda.restoolApp.UI.PaginaPrincipal;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.Keys;

public class Realizar implements Task {

    private String user;

    public Realizar(String user){
        this.user = user;
    }

    private boolean getNumberPage(Actor actor, Target inputPage){
        return (inputPage.resolveFor(actor).getText().equalsIgnoreCase("1")) ? true : false;
    }

    public static Performable busqueda(String user){
        return Instrumented.instanceOf(Realizar.class).withProperties(user);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (!(getNumberPage(actor, PaginaPrincipal.CAMPO_PAGE))) {
            PaginaPrincipal.CAMPO_PAGE.resolveFor(actor).clear();
            actor.attemptsTo(
                    SendKeys.of("1")
                            .into(PaginaPrincipal.CAMPO_PAGE)
                            .then(SendKeys.of(this.user)
                                  .into(PaginaPrincipal.CAMPO_SEARCH)
                                  .thenHit(Keys.ENTER))

            );
        } else {
            actor.attemptsTo(
                    SendKeys.of(this.user)
                            .into(PaginaPrincipal.CAMPO_SEARCH)
                            .thenHit(Keys.ENTER)

            );
        }
    }
}
