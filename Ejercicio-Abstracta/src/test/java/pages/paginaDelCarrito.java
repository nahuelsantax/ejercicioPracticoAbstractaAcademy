package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class paginaDelCarrito {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public paginaDelCarrito(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera implícita con un tiempo de espera de 10 segundos.
        PageFactory.initElements(driver, this);
    }

    // Agrega un método para validar si un producto se encuentra en el carrito.
    public boolean validarProductoEnCarrito(String producto) {
        // Utiliza un selector adecuado para encontrar el contenedor del carrito.
        String xpathSelector = "//body/div[@id='checkout-cart']/div[2]/div[1]/form[1]/div[1]";

        // Espera hasta que el contenedor del carrito sea visible.
        WebElement carritoContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSelector)));

        // Verifica si el texto del carrito contiene el nombre del producto.
        String carritoText = carritoContainer.getText();
        return carritoText.contains(producto);
    }

    // Agrega un método para remover un producto del carrito.
    public void removerProductoDelCarrito() {
        // Utiliza un selector adecuado para encontrar el botón o enlace "Remove" del producto en el carrito.
        // Esto dependerá de cómo esté diseñado tu carrito de compras.
        String xpathSelector = "//button[@class='btn btn-danger']";

        // Espera hasta que el botón o enlace "Remove" sea visible.
        WebElement botonRemove = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSelector)));

        // Haz clic en el botón o enlace "Remove" para eliminar el producto del carrito.
        botonRemove.click();
    }

    // Agrega un método para validar que un producto no se encuentra en el carrito.
    public boolean validarProductoNoEnCarrito(String producto) {
        // Utiliza un selector adecuado para verificar que el producto no se encuentra en el carrito.
        // Esto depende de la estructura específica de tu carrito de compras.
        String xpathSelector = "//div[@id='content']//p[contains(text(),'Your shopping cart is empty!')]";

        // Espera hasta que el mensaje aparezca en caso de que el carrito esté vacío.
        WebElement mensajeCarritoVacio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSelector)));

        // Verifica si el mensaje indica que el carrito está vacío.
        return mensajeCarritoVacio.isDisplayed();
    }
}
