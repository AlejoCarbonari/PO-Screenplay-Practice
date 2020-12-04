package motoresdebusqueda.demoblaze.steps;

import motoresdebusqueda.demoblaze.pageobject.DemoblazeCart;
import motoresdebusqueda.demoblaze.pageobject.DemoblazeHome;
import motoresdebusqueda.demoblaze.pageobject.DemoblazePaginaPrincipal;
import motoresdebusqueda.demoblaze.pageobject.DemoblazePlaceOrder;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.thucydides.core.annotations.Steps;

public class UsuarioDemoblaze {

    @Steps DemoblazePaginaPrincipal paginaPrincipal;
    @Steps DemoblazeHome demoblazeHome;
    @Steps DemoblazeCart cart;
    @Steps DemoblazePlaceOrder order;

    private int totalPrice;

    @Given("nos encontramos en la pagina principal de Demoblaze")
    public void ingresaADemoblaze() {
        paginaPrincipal.setDefaultBaseUrl("https://www.demoblaze.com");
        paginaPrincipal.open();
    }

    @When("nos logueamos rellenando el modal con los siguientes datos: {string} {string}")
    public void ingresaUsuarioYPassword(String usuario, String password) {
        paginaPrincipal.hacerClickEnElBotonLogIn();
        paginaPrincipal.ingresarUsuario(usuario);
        paginaPrincipal.ingresarPassword(password);
    }

    @Then("deberíamos visualizar el mensaje de bienvenida: {string}")
    public void deberiaVisualizarElMensajeDeBienvenida(String mensaje) {
        demoblazeHome.visualizarMensajeDeBienvenida(mensaje);
    }

    @When("agregamos los productos al carrito")
    public void seleccionaUnProducto(){
        demoblazeHome.addToCart(demoblazeHome.ListOfNames());
        this.totalPrice = demoblazeHome.ListOfPrices()
                        .stream()
                        .mapToInt(price -> price)
                        .sum();
    }

    @Then("^deberíamos dirigirnos hacia la sección del carrito$")
    public void vamosHaciaLaSeccionDelCarrito(){
        cart.irAlaSeccionCart();
    }

    @And("^hacemos click en el botón PlaceOrder$")
    public void hacemosClickEnElBotonPlaceOrder(){
        cart.confirmarCompra();
    }

    @And("rellenamos el formulario con los siguientes datos: {string} {string} {string} {string} {string} {string}")
    public void rellenarFormularioDeCompra(String name, String country, String city, String creditCard, String month, String year){
        order.addInformationToBuy(name, country, city, creditCard, month, year);
    }

    @Then("hacemos click en el botón Purchase")
    public void hacemosClickEnComprar(){
        order.hacerClickEnPurchase();
    }

    @And("deberíamos visualizar un alert exitoso de compra que diga: {string}")
    public void visualizarAlertExitosoDeCompra(String msg){
        order.visualizaAlertExitosoDeCompra(msg);
    }

    @And("deberíamos comprobar los datos informados en el modal")
    public void aceptarAlertaDeCompra(){
        /* console.log($('div.sweet-alert p.text-muted').text().substr(19, 3)); */
        order.validarMontoTotal(totalPrice, String.valueOf(totalPrice).length());
    }

}
