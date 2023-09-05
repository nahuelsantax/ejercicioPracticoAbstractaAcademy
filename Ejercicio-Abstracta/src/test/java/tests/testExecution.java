package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import pages.paginaInicio;
import pages.paginaDeBusqueda;
import pages.paginaDelCarrito;
import java.io.File;
import java.io.IOException;

public class testExecution {
    String url = "http://opencart.abstracta.us/";
    WebDriver driver;

    @BeforeSuite
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test
    public void escribirPalabra() {
        paginaInicio inicio = new paginaInicio(driver);
        inicio.escribirPalabra("iPhone");
        takeScreenshot("Paso1_EscribirPalabra");
    }

    @Test(dependsOnMethods = "escribirPalabra")
    public void hacerBusqueda() {
        paginaInicio inicio = new paginaInicio(driver);
        inicio.hacerBusqueda();
        takeScreenshot("Paso2_HacerBusqueda");
    }

    @Test(dependsOnMethods = "hacerBusqueda")
    public void seleccionarItem() {
        paginaDeBusqueda buscar = new paginaDeBusqueda(driver);
        buscar.seleccionarItem();
        buscar.agregarAlCarrito();
        buscar.verCarrito();
        buscar.abrirCarrito();
        takeScreenshot("Paso3_SeleccionarItemYAgregarAlCarrito");
    }

    @Test(dependsOnMethods = "seleccionarItem")
    public void validarProductoEnCarrito() {
        paginaDelCarrito carrito = new paginaDelCarrito(driver);

        boolean productoEnCarrito = carrito.validarProductoEnCarrito("iPhone");
        Assert.assertTrue(productoEnCarrito, "El producto iPhone no se encuentra en el carrito.");
        takeScreenshot("Paso4_ValidarCarrito");
    }
    
    @Test(dependsOnMethods = "validarProductoEnCarrito")
    public void removerProductoDelCarrito() {
        paginaDelCarrito carrito = new paginaDelCarrito(driver);

        // Remueve el iPhone del carrito.
        carrito.removerProductoDelCarrito();
        takeScreenshot("Paso5_RemoverProductoDelCarrito");
    }

    @Test(dependsOnMethods = "removerProductoDelCarrito")
    public void validarProductoNoEnCarrito() {
        paginaDelCarrito carrito = new paginaDelCarrito(driver);

        boolean productoNoEnCarrito = carrito.validarProductoNoEnCarrito("iPhone");
        Assert.assertTrue(productoNoEnCarrito, "El producto iPhone todavía se encuentra en el carrito.");
        takeScreenshot("Paso6_ValidarProductoNoEnCarrito");
    }

    @AfterSuite
    public void tearDown() {
        driver.close();
    }

    public void takeScreenshot(String screenshotName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(screenshotName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

