package motoresdebusqueda.demoblaze.pageobject;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

public class DemoblazeHome extends PageObject {

    @Steps DemoblazeCart cart;
    @Steps DemoblazePlaceOrder order;

    @FindBy(css = "#nameofuser")
    WebElementFacade welcomeMessage;

    public static final String ProductName = "//div[@class='card-block']//a",
                               ProductPrice = "//div[@class='card-block']//h5";

    public void visualizarMensajeDeBienvenida(String mensaje) {
        waitFor(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#nameofuser")));
        assertThat(mensaje, Matchers.comparesEqualTo(welcomeMessage.getText()));
    }

    public List<String> ListOfNames(){
        return findAll(ProductName)
                .stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    public List<Integer> ListOfPrices(){
        return findAll(ProductPrice)
                .stream()
                .map(element -> Integer.parseInt(element.getText().substring(1)))
                .collect(Collectors.toList());
    }

    public void addToCart(List<String> ListOfNames){
        for (String productName: ListOfNames) {
            if (findBy("//a[contains(text(),'" + productName + "')]").isClickable()) {
                findBy("//a[contains(text(),'" + productName + "')]").click();
                cart.agregarAlCarrito();
                waitFor(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Home')]")));
                backToHome();
            }
        }

        /*ListOfPrices.forEach(element ->
                (findBy("//a[contains(text(),'" + element + "')]").isClickable())
                ? findBy("//a[contains(text(),'" + element + "')]").click()
                : findBy("//a[contains(text(),'Home')]").click()
        );*/
    }

    private void backToHome(){
        findBy("//a[contains(text(),'Home')]").click();
    }


}
