package motoresdebusqueda.restoolApp.navegacion;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;

public class Navegar implements Interaction {
    private final String url;

    public Navegar(String url){
        this.url = url;
    }

    @Override
    @Step("{0} navegará hacia una página")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(url));
    }

    public static Performable hacia(String url){
        return Instrumented.instanceOf(Navegar.class).withProperties(url);
    }
}
