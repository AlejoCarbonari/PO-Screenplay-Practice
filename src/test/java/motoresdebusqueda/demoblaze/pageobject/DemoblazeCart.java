package motoresdebusqueda.demoblaze.pageobject;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DemoblazeCart extends PageObject {

    public void agregarAlCarrito(){
        findBy("//a[contains(text(),'Add to cart')]").click();
        waitFor(ExpectedConditions.alertIsPresent());
        getDriver().switchTo().alert().accept();
    }

    public void irAlaSeccionCart(){
        findBy("//a[contains(text(),'Cart')]").click();
    }

    public void confirmarCompra(){
        findBy("//button[contains(text(),'Place Order')]").click();
    }

}
