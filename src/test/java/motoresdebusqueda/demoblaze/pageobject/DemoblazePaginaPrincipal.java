package motoresdebusqueda.demoblaze.pageobject;

import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;


@DefaultUrl("https://www.demoblaze.com")
public class DemoblazePaginaPrincipal extends PageObject {

    @FindBy(css = "#login2")
    WebElementFacade botonLogInAbreModal;

    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    WebElementFacade botonIngresar;

    @FindBy(css = "#loginusername")
    WebElementFacade inputUsuario;

    @FindBy(css = "#loginpassword")
    WebElementFacade inputPassword;


    public void hacerClickEnElBotonLogIn(){
        botonLogInAbreModal.click();
    }

    public void ingresarUsuario(String usuario) {
        inputUsuario.type(usuario);
    }

    public void ingresarPassword(String password) {
        inputPassword.type(password);
        botonIngresar.click();
    }


}
