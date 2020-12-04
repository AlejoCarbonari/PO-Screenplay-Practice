package motoresdebusqueda.restoolApp.tareas;

import motoresdebusqueda.restoolApp.UI.PaginaPrincipal;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.Wait;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collection;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Comprobar implements Task {

    private String name;

    public Comprobar(String name){
        this.name = name;
    }

    public static Performable resultados(String name){
        return Instrumented.instanceOf(Comprobar.class).withProperties(name);
    }

    private Question<Collection<String>> listaNombres(Actor actor){
        return Question.about("names")
                .answeredBy(actor1 -> Text.of(PaginaPrincipal.infoTablaDeEmployees)
                                      .viewedBy(actor)
                                      .asList()
                           );
    }

    private String[] itemsCount(Actor actor){
        return PaginaPrincipal.showingResults.resolveFor(actor).getText().split(" ");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
                actor.attemptsTo(
                         SendKeys.of(itemsCount(actor)[5]).into(PaginaPrincipal.CAMPO_LIMIT)
                        .then(Ensure.thatTheAnswersTo(listaNombres(actor)).contains("Daniel"))
                );
    }
}
