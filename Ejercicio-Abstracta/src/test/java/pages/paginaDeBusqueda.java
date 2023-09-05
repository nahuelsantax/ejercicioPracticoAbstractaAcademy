package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class paginaDeBusqueda {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public paginaDeBusqueda(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        PageFactory.initElements(driver, this);
    }

    public void seleccionarItem() {
        WebElement enlaceProducto = driver.findElement(By.xpath("//a[contains(text(),'iPhone')]"));
        enlaceProducto.click();
    }

    public void agregarAlCarrito() {
        WebElement botonAgregarCarrito = driver.findElement(By.id("button-cart"));
        botonAgregarCarrito.click();
    }

    public void verCarrito() {
        WebElement botonCarrito = driver.findElement(By.id("cart"));
        botonCarrito.click();
    }

    public void abrirCarrito() {
        WebElement botonVerCarrito = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("View Cart")));
        botonVerCarrito.click();
    }
}

