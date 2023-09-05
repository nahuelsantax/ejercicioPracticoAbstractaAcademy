package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class paginaInicio {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public paginaInicio(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera implícita con un tiempo de espera de 10 segundos.
        PageFactory.initElements(driver, this);
    }

    // Agrega un método para ingresar una palabra en el campo de búsqueda.
    public void escribirPalabra(String palabra) {
        WebElement campoBusqueda = driver.findElement(By.name("search"));
        campoBusqueda.sendKeys(palabra);
    }

    // Agrega un método para realizar una búsqueda.
    public void hacerBusqueda() {
        WebElement botonBuscar = driver.findElement(By.cssSelector(".input-group-btn button"));
        botonBuscar.click();
    }
}

