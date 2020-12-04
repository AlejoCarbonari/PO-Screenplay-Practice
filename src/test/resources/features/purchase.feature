Feature: Perform a purchase

  Background:
    Given nos encontramos en la pagina principal de Demoblaze
    When nos logueamos rellenando el modal con los siguientes datos: "chuckkznm9" "nissanGTR11"
    Then deberíamos visualizar el mensaje de bienvenida: "Welcome chuckkznm9"

  Scenario Outline:
    #Como un usuario corriente
    #Quiero loguearme y realizar una compra
    #Para poder efectuar la transacción de un pedido online,
    #ENTONCES
    When agregamos los productos al carrito
    Then deberíamos dirigirnos hacia la sección del carrito
    And hacemos click en el botón PlaceOrder
    And rellenamos el formulario con los siguientes datos: "<Name>" "<Country>" "<City>" "<CreditCard>" "<Month>" "<Year>"
    And hacemos click en el botón Purchase
    Then deberíamos visualizar un alert exitoso de compra que diga: "Thank you for your purchase!"
    And deberíamos comprobar los datos informados en el modal

    Examples:
      | Name  | Country   | City | CreditCard | Month | Year |
      | Alejo | Argentina | VCP  | 5512512321 | 07    | 1999 |
