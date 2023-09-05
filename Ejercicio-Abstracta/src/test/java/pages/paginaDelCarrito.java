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

    public paginaDelCarrito(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        PageFactory.initElements(driver, this);
    }

    public boolean validarProductoEnCarrito(String producto) {
        String xpathSelector = "//body/div[@id='checkout-cart']/div[2]/div[1]/form[1]/div[1]";

        WebElement carritoContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSelector)));

        String carritoText = carritoContainer.getText();
        return carritoText.contains(producto);
    }

    public void removerProductoDelCarrito() {
        String xpathSelector = "//button[@class='btn btn-danger']";
        WebElement botonRemove = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSelector)));
        botonRemove.click();
    }

    public boolean validarProductoNoEnCarrito(String producto) {
        String xpathSelector = "//div[@id='content']//p[contains(text(),'Your shopping cart is empty!')]";
        WebElement mensajeCarritoVacio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSelector)));

        return mensajeCarritoVacio.isDisplayed();
    }
}
