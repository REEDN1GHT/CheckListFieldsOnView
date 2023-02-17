package Page;

import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import io.github.sukgu.*;


import static Page.SQL.*;
import static org.example.GUI.*;

public class ButtonEvent extends BD implements ActionListener {

    public static By ByLogin = By.xpath("//*[@id='input-vaadin-text-field-6']");
    public static By ByPassword = By.xpath("//*[@id='input-vaadin-password-field-7']");
    public static By ByConfirmButton = By.xpath("//vaadin-button[@role='button']");

    public static String staticNameForm;
    public static String staticLogin;
    public static String staticPassword;
    public static String URLUKT = "https://asbpek-test.aisa.ru/login";
    public static String ListS;
    public static String ListSS;
    public static List<String> ListWeb;
    public static List<String> ListAISBP;
    public static int Locator = 1;

    public SQL requestSQL = new SQL();

    public List<String> CheckListOptionsOLD() {
        List<String> myValuess = driver1.findElements(By.xpath("//vaadin-grid-cell-content[@draggable='true']"))
                .stream()
                .map(option -> option.getAttribute("innerText").trim().replaceAll("  ", " "))
                .collect(Collectors.toList());
        System.out.println("myValuess" + myValuess);
        return myValuess;
    }
    public List<String> CheckListOptionsNew() {
        List<String> ListView = new ArrayList<>();
        driver1.findElement(By.xpath("//*[@id='overlay']/flow-component-renderer/div/vaadin-vertical-layout/vaadin-vertical-layout[2]/vaadin-horizontal-layout[1]/vaadin-grid[1]/vaadin-grid-cell-content[1]/vaadin-grid-sorter")).click();
        ListAISBP = requestSQL.SqlFormFieldsLeft();
        int SizeAISBP = ListAISBP.size() - 1;
        String LastAISBP = ListAISBP.get(SizeAISBP);
        String LastWEBais = "";
            while (!Objects.equals(LastAISBP, LastWEBais)) {
                while (ListAISBP.size() != ListView.size()) {
                    //while (ListAISBP.size() != ListView.size()) {
                        JavascriptExecutor js = (JavascriptExecutor) driver1;
                        js.executeScript("document.querySelector('#overlay > flow-component-renderer > div > vaadin-vertical-layout > vaadin-vertical-layout:nth-child(3) > vaadin-horizontal-layout:nth-child(1) > vaadin-grid:nth-child(1)').shadowRoot.querySelector('#table').scrollBy(0,10)");
                    while (19 > Locator) {
                        WebElement SSS = driver1.findElement(By.xpath("//*[text()='Невыводимые поля']/parent::vaadin-grid-cell-content/following-sibling::vaadin-grid-cell-content[@draggable='true'][" + Locator + "]"));
                        ListS = SSS.getAttribute("innerText");
                        //System.out.println(ListS);
                        if (ListAISBP.size() == ListView.size()) {
                            Locator = Locator + ListView.size() + 10;
                        } else {
                            Locator = Locator + 1;
                        }
                        if (ListView.contains(ListS)) {// (Locator == 18) - old
                            //ListSS = ListS;
                        } else {
                            ListView.add(String.join(" ", ListS).trim());
                            Collections.sort(ListView);
                            //System.out.println(ListView);
                        }
                    }
                //}
                Locator = Locator - 17;
                int SizeListWeb = ListView.size() - 1;
                LastWEBais = ListView.get(SizeListWeb);
            }
                //Locator = Locator - ListView.size() - 10;
        }
        ListWeb = ListView;
        return ListWeb;
    }

    public void actionPerformed(ActionEvent e) {
        System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe");
        driver1 = new ChromeDriver();
        wait1 = new WebDriverWait(driver1, Duration.ofSeconds(5));
        driver1.manage().window().maximize();
        driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        staticLogin = Tlogin.getText();
        staticPassword = Tpassword.getText();
        staticNameForm = TNameForm.getText();
        driver1.navigate().to(URLUKT);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        driver1.findElement(ByLogin).sendKeys(staticLogin);
        driver1.findElement(ByPassword).sendKeys(staticPassword);
        driver1.findElement(ByConfirmButton).click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        driver1.navigate().to("https://asbpek-test.aisa.ru/lk/tabs?tabs=21%D0%AD1&selectedTab=21%D0%AD1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        WebElement shadow = driver1.findElement(By.xpath("//vaadin-horizontal-layout[@theme='spacing'][last()]/vaadin-menu-bar[@role='menubar']"));
        SearchContext shadowRoot = shadow.getShadowRoot();
        shadowRoot.findElement(By.cssSelector("div>vaadin-menu-bar-button[role='menuitem']:nth-child(2)")).click();
//        Shadow shadowSearch = new Shadow(driver1);
//        shadowSearch.findElement("div > vaadin-menu-bar-button:nth-child(1)").click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        requestSQL.SqlForm();
        System.out.println(CheckListOptionsNew());
        System.out.println(ListAISBP);
//        driver1.findElement(By.xpath("//*[@id='overlay']/flow-component-renderer/div/vaadin-vertical-layout/vaadin-vertical-layout[2]/vaadin-horizontal-layout[1]/vaadin-grid[1]/vaadin-grid-cell-content[1]/vaadin-grid-sorter")).click();
//        WebElement SSS = driver1.findElement(By.xpath("//*[text()='Невыводимые поля']/parent::vaadin-grid-cell-content/following-sibling::vaadin-grid-cell-content[@draggable='true'][" + 1 + "]"));
//        String ListS = SSS.getAttribute("innerText");
//        List<String> List = new ArrayList<>();
//        List.add(String.join(" ", ListS));
//        System.out.println("ИИИИИУУУ" + List);

//        JavascriptExecutor js = (JavascriptExecutor) driver1;
//        js.executeScript("document.querySelector('#overlay > flow-component-renderer > div > vaadin-vertical-layout > vaadin-vertical-layout:nth-child(3) > vaadin-horizontal-layout:nth-child(1) > vaadin-grid:nth-child(1)').shadowRoot.querySelector('#table').scrollBy(0,4000)");
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//            throw new RuntimeException(ex);
//        }
        //System.out.println(CheckListOptionsOLD());
        //System.out.println(CheckListOptionsOLD().size());
//        requestSQL.SqlForm();
//        System.out.println(FormID);
//        System.out.println(RequestID);
//        List<String> L = new ArrayList<>();
//        L = requestSQL.SqlFormFieldsLeft();
//        int LAst = 0;
//        System.out.println(LAst = L.size() - 1);
//        System.out.println(L);
//        System.out.println(L.get(LAst));
//        System.out.println(requestSQL.SqlFormFieldsRight());
//        System.out.println(requestSQL.SqlFormFilters());
        System.out.println(ListWeb.removeAll(ListAISBP));
        System.out.println(ListWeb);

        driver1.quit();
        String message = "keka";
        JOptionPane.showConfirmDialog(null, message.replaceAll(",", "\n"), "Result", JOptionPane.DEFAULT_OPTION);

    }
}
