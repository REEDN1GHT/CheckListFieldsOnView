package Page;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


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
    public static int staticDelay;
    public static List<String> ListOptionsWeb;
    public static List<String> ListOptionsAISBP;
    public static List<String> ListFiltersWeb = new ArrayList<>();

    public static String staticURLforView;
    public static int Locator = 1;
    public static By Left = By.xpath("//*[text()='Невыводимые поля']/parent::vaadin-grid-cell-content/following-sibling::vaadin-grid-cell-content[@draggable='true']");
    public static By Right = By.xpath("//*[text()='Выводимые поля']/parent::vaadin-grid-cell-content/following-sibling::vaadin-grid-cell-content[@draggable='true']");


    public SQL requestSQL = new SQL();

    public void OpenOptions() {
        WebElement shadow = driver1.findElement(By.xpath("//vaadin-horizontal-layout[@theme='spacing'][last()]/vaadin-menu-bar[@role='menubar']"));
        SearchContext shadowRoot = shadow.getShadowRoot();
        shadowRoot.findElement(By.cssSelector("div>vaadin-menu-bar-button[role='menuitem']:nth-child(2)")).click();
    }
    public void OpenFilters() {
        driver1.findElement(By.xpath("//*[@id='overlay']/flow-component-renderer/div/vaadin-vertical-layout/vaadin-tabs/vaadin-tab[1]")).click();
        WebElement shadow = driver1.findElement(By.xpath("//vaadin-horizontal-layout/vaadin-combo-box"));
        SearchContext shadowRoot = shadow.getShadowRoot();
        shadowRoot.findElement(By.cssSelector("#toggleButton")).click();
    }

    public void ReSizeAttribute(WebElement element)  {
        JavascriptExecutor js = (JavascriptExecutor) driver1;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",element, "style", "height: 1000%");
    }
    public void ReSizeAttributeSR()  {
        WebElement shadow = driver1.findElement(By.xpath("/html/body/vaadin-combo-box-overlay"));
        WebElement element1 = driver1.findElement(By.xpath("//*[@role='listbox']"));
        JavascriptExecutor js = (JavascriptExecutor) driver1;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",shadow,"style", "height: 2200000px;");
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",element1, "style","height: 65000px;");
    }
    public void ReSizeOptions()
    {
        WebElement LeftColum = driver1.findElement(By.cssSelector("vaadin-horizontal-layout[theme='spacing']>vaadin-grid[suppress-template-warning][multi-sort-priority='prepend']:nth-child(1)"));
        WebElement RightColum = driver1.findElement(By.cssSelector("vaadin-horizontal-layout[theme='spacing']>vaadin-grid[suppress-template-warning][multi-sort-priority='prepend']:nth-child(2)"));
        ReSizeAttribute(LeftColum);
        ReSizeAttribute(RightColum);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        CheckListOptionsNew();

    }

    public void ReSizeFilters() {
        ReSizeAttributeSR();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        CheckListFiltersNEW();
        ListFiltersWeb.addAll(PreventiveList);
//        PreventiveList.clear();
//        int set = 0;
//        while (set < 90) {
//            driver1.findElement(By.xpath("//input[@role='combobox']")).sendKeys(Keys.DOWN);
//            set = set+1;
//        }
//            CheckListFiltersNEW();
//            ListFiltersWeb.removeAll(PreventiveList);
//        ListFiltersWeb.addAll(PreventiveList);
        Collections.sort(ListFiltersWeb);
        System.out.println(ListFiltersWeb);
    }

    public void ListFiilters() {
        int numElement = 0;
        int numAttribute = 0;
        int size = ListFiltersWeb.size();
        driver1.findElement(By.xpath("//input[@role='combobox']")).sendKeys(Keys.DOWN);
        while (numElement == numAttribute) {
            String AttributeElement = driver1.findElement(By.xpath("//input[@role='combobox']")).getAttribute("aria-activedescendant");
            numAttribute = Integer.parseInt(AttributeElement.replace("vaadin-combo-box-item-", ""));
            numElement = numElement + 1;
            String InnerText = driver1.findElement(By.xpath("//*[@id= \'" + AttributeElement + "\']")).getAttribute("innerText");
            ListFiltersWeb.add(InnerText);
            size = ListFiltersWeb.size();
            driver1.findElement(By.xpath("//input[@role='combobox']")).sendKeys(Keys.DOWN);
            AttributeElement = driver1.findElement(By.xpath("//input[@role='combobox']")).getAttribute("aria-activedescendant");
            numAttribute = Integer.parseInt(AttributeElement.replace("vaadin-combo-box-item-", ""));
        }
        Collections.sort(ListFiltersWeb);
        System.out.println("web " + ListFiltersWeb);

    }

    public List<String> CheckListOptionsNew() {
         ListOptionsWeb = driver1.findElements(By.xpath("//vaadin-grid-cell-content[@draggable='true']"))
                .stream()
                .map(option -> option.getAttribute("innerText").trim().replaceAll("  ", " "))
                .collect(Collectors.toList());
         Collections.sort(ListOptionsWeb);
        return ListOptionsWeb;
    }
    public List<String> CheckListFilters() {
        ListFiltersWeb = driver1.findElements(By.xpath("//*[@role='listbox']/vaadin-combo-box-item"))
                .stream()
                .map(option -> option.getAttribute("innerText").trim().replaceAll("  ", " "))
                .collect(Collectors.toList());
         Collections.sort(ListFiltersWeb);
        return ListFiltersWeb;
    }
    public static List<String> PreventiveList;
    public List<String> CheckListFiltersNEW() {
        PreventiveList = driver1.findElements(By.xpath("//*[@role='listbox']/vaadin-combo-box-item"))
                .stream()
                .map(option -> option.getAttribute("innerText").trim().replaceAll("  ", " "))
                .collect(Collectors.toList());
         Collections.sort(PreventiveList);
        return PreventiveList;
    }


    public List<String> CheckListOptionsOLD() {
        List<String> ListView = new ArrayList<>();
        driver1.findElement(By.xpath("//*[@id='overlay']/flow-component-renderer/div/vaadin-vertical-layout/vaadin-vertical-layout[2]/vaadin-horizontal-layout[1]/vaadin-grid[1]/vaadin-grid-cell-content[1]/vaadin-grid-sorter")).click();
        ListOptionsAISBP = requestSQL.SqlFormFields();
        int SizeAISBP = ListOptionsAISBP.size() - 1;
        String LastAISBP = ListOptionsAISBP.get(SizeAISBP);
        String LastWEBais = "";
            while (!Objects.equals(LastAISBP, LastWEBais)) {
                while (ListOptionsAISBP.size() != ListView.size()) {
                    List<String> myValuess = new ArrayList<>();
                    myValuess.clear();
                    myValuess = driver1.findElements(By.xpath("//*[text()='Невыводимые поля']/parent::vaadin-grid-cell-content/following-sibling::vaadin-grid-cell-content[@draggable='true']"))
                            .stream()
                            .map(option -> option.getAttribute("innerText").trim().replaceAll("  ", " "))
                            .collect(Collectors.toList());
                    //while (ListAISBP.size() != ListView.size()) {
                    if (ListView.containsAll(myValuess)) {
                        JavascriptExecutor js = (JavascriptExecutor) driver1;
                        js.executeScript("document.querySelector('#overlay > flow-component-renderer > div > vaadin-vertical-layout > vaadin-vertical-layout:nth-child(3) > vaadin-horizontal-layout:nth-child(1) > vaadin-grid:nth-child(1)').shadowRoot.querySelector('#table').scrollBy(0,20)");
                    }
                    while (19 > Locator) {
                        WebElement SSS = driver1.findElement(By.xpath("//*[text()='Невыводимые поля']/parent::vaadin-grid-cell-content/following-sibling::vaadin-grid-cell-content[@draggable='true'][" + Locator + "]"));
                        ListS = SSS.getAttribute("innerText");
                        //System.out.println(ListS);
                        if (ListOptionsAISBP.size() == ListView.size()) {
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
                Locator = Locator - 18;
                int SizeListWeb = ListView.size() - 1;
                LastWEBais = ListView.get(SizeListWeb);
            }
                //Locator = Locator - ListView.size() - 10;
        }
        ListOptionsWeb = ListView;
        return ListOptionsWeb;
    }

    static void saveToFile(String text) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Results\\Result.txt"));
            writer.write(text.replaceAll(",","\n"));
            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void waitContentPage()
    {
        var newWait = new WebDriverWait(driver1, Duration.ofSeconds(staticDelay));
        newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ROOT-2521314']/vaadin-app-layout/vaadin-vertical-layout/div/div/vaadin-vertical-layout/vaadin-vertical-layout/vaadin-grid/vaadin-grid-cell-content[1]/vaadin-grid-sorter")));
    }
    public void waitContentOptions()
    {
        var newWait = new WebDriverWait(driver1, Duration.ofSeconds(staticDelay));
        newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='overlay']/h2")));
    }

    public void actionPerformed(ActionEvent e) {
        System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe");
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        driver1 = new ChromeDriver(ops);
        wait1 = new WebDriverWait(driver1, Duration.ofSeconds(5));
        driver1.manage().window().maximize();
        driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        staticLogin = Tlogin.getText();
        staticPassword = Tpassword.getText();
        staticNameForm = TNameForm.getText();
        staticURLforView = TextURLforView.getText();
        staticDelay = Integer.parseInt(Delay.getText());
        driver1.navigate().to(URLUKT);
        requestSQL.SqlForm();
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
        driver1.navigate().to(staticURLforView);
        waitContentPage();
        OpenOptions();
        waitContentOptions();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        ReSizeOptions();
        OpenFilters();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        ListFiilters();
        List<String> ResultOptionsWeb = ListOptionsWeb;
        List<String> ResultOptionsAIS = requestSQL.SqlFormFields();
        List<String> ResultFiltersWeb = ListFiltersWeb;
        List<String> ResultFiltersAIS = requestSQL.SqlFormFilters();
        System.out.println("AIS " + ResultFiltersAIS);
        List<String> duplicatesFilters = ResultFiltersWeb.stream()
                .collect(Collectors.groupingBy(Function.identity()))
                .entrySet()
                .stream()
                .filter(ee -> ee.getValue().size() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        List<String> duplicatesOptions = ResultOptionsWeb.stream()
                .collect(Collectors.groupingBy(Function.identity()))
                .entrySet()
                .stream()
                .filter(ee -> ee.getValue().size() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("duplicates " + duplicatesFilters);
        int SizeOptionsWEB = ResultOptionsWeb.size();
        int SizeOptionsSQL = ResultOptionsAIS.size();
        int SizeFiltersWEB = ResultFiltersWeb.size();
        int SizeFiltersSQL = ResultFiltersAIS.size();
        boolean IsEqualOptions = (ResultOptionsWeb.equals(ResultOptionsAIS));
        boolean IsEqualFilters = (ResultFiltersWeb.equals(ResultFiltersAIS));
        driver1.quit();
        String messageOptions = "";
        if (IsEqualOptions) {
            messageOptions += "Списки настроек идентичны " + "Веб АИС БП " + SizeOptionsWEB + " Десктоп АИС БП " + SizeOptionsSQL +"\n";
            messageOptions += "------------------------\n";
        } else {
            if (SizeOptionsWEB>SizeOptionsSQL) {
                ResultOptionsWeb.removeAll(ResultOptionsAIS);
                messageOptions += "Списки настроек различаются " + "Веб АИС БП " + SizeOptionsWEB + " Десктоп АИС БП " + SizeOptionsSQL + "\n";
                messageOptions += "Отсутствующие значения в десктоп АИС БП " + ResultOptionsWeb.size() + "\n";
                messageOptions += "Список значений" + "\n";
                messageOptions += ResultOptionsWeb + "\n";
                messageOptions += "Список дубликатов" + "\n";
                messageOptions += duplicatesOptions + "\n";
                messageOptions += "------------------------" + "\n";
            } else {
                ResultOptionsAIS.removeAll(ResultOptionsWeb);
                messageOptions += "Списки настроек различаются " + "Веб АИС БП " + SizeOptionsWEB + " Десктоп АИС БП " + SizeOptionsSQL + "\n";
                messageOptions += "Отсутствующие значения в Веб АИС БП  " + ResultOptionsAIS.size() + "\n";
                messageOptions += "Список значений" + "\n";
                messageOptions += ResultOptionsAIS + "\n";
                messageOptions += "Список дубликатов" + "\n";
                messageOptions += duplicatesOptions + "\n";
                messageOptions += "------------------------" + "\n";
            }
        }
        if (IsEqualFilters) {
            messageOptions += "Списки фильтров идентичны " + "Веб АИС БП " + SizeFiltersWEB + " Десктоп АИС БП " + SizeFiltersSQL;
        } else {
            if (SizeFiltersWEB>SizeFiltersSQL) {
                ResultFiltersWeb.removeAll(ResultFiltersAIS);
                messageOptions += "Списки фильтров различаются " + "Веб АИС БП " + SizeFiltersWEB + " Десктоп АИС БП " + SizeFiltersSQL + "\n";
                messageOptions += "Отсутствующие значения в десктоп АИС БП " + ResultFiltersWeb.size() + "\n";
                messageOptions += "Список значений" + "\n";
                messageOptions += ResultFiltersWeb + "\n";
                messageOptions += "Список дубликатов" + "\n";
                messageOptions += duplicatesFilters + "\n";
            } else {
                ResultFiltersAIS.removeAll(ResultFiltersWeb);
                messageOptions += "Списки фильтров различаются " + "Веб АИС БП " + SizeFiltersWEB + " Десктоп АИС БП " + SizeFiltersSQL + "\n";
                messageOptions += "Отсутствующие значения в Веб АИС БП " + ResultFiltersAIS.size() + "\n";
                messageOptions += "Список значений" + "\n";
                messageOptions += ResultFiltersAIS + "\n";
                messageOptions += "Список дубликатов" + "\n";
                messageOptions += duplicatesFilters + "\n";
            }
        }
        try {
            saveToFile(messageOptions);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        JOptionPane.showConfirmDialog(null, messageOptions.replaceAll(",", "\n"), "Result", JOptionPane.DEFAULT_OPTION);
        SizeFiltersSQL = 0;
        SizeFiltersWEB = 0;
        SizeOptionsSQL = 0;
        SizeFiltersWEB = 0;
        ResultFiltersWeb.clear();
        ResultFiltersAIS.clear();
        ResultOptionsWeb.clear();
        ResultOptionsAIS.clear();
    }
}
