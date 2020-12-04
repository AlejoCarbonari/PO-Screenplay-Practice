package motoresdebusqueda.demoblaze.pageobject;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import org.hamcrest.Matchers;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;

public class DemoblazePlaceOrder extends PageObject {

    @FindBy(xpath = "//button[contains(text(),'Purchase')]")
    WebElementFacade purchaseButton;

    @FindBy(css = "div.sweet-alert h2")
    WebElementFacade sweetAlertExitoso;

    public void addInformationToBuy(String ... datos){
        waitFor(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#orderModal")));
        List<String> elementsList = new ArrayList<String>();

        do{
            elementsList.add("//input[@id='name']");
            elementsList.add("//input[@id='country']");
            elementsList.add("//input[@id='city']");
            elementsList.add("//input[@id='card']");
            elementsList.add("//input[@id='month']");
            elementsList.add("//input[@id='year']");
        }while(false);

        AtomicInteger datosIterator = new AtomicInteger();
        elementsList.forEach(element -> findBy(element).type(datos[datosIterator.getAndIncrement()]));

    }

    public void hacerClickEnPurchase(){
        findBy("//button[contains(text(),'Purchase')]").click();
    }

    public void visualizaAlertExitosoDeCompra(String msg) {
        waitFor(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.sweet-alert")));
        assertThat(msg, Matchers.comparesEqualTo(sweetAlertExitoso.getText()));
    }

    public void validarMontoTotal(int totalPrice, int large){
        assertThat(totalPrice, Matchers.comparesEqualTo(getCurrentlyPrice(large)));
    }

    public int getCurrentlyPrice(int large){
        return Integer.parseInt(findBy("div.sweet-alert p.text-muted").getText().substring(20, 20+large));
    }

}
