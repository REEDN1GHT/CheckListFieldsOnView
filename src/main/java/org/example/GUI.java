package org.example;

import Page.ButtonEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;



public class GUI extends JFrame{

    public static JLabel LnameForm = new JLabel("Название формы просмотра");
    public static JTextField TNameForm = new JTextField("Просмотр поручений", 256);
    public static JLabel URLforView = new JLabel("URL просмотра");
    public static JTextField  TextURLforView = new JTextField("https://asbpek-test.aisa.ru/lk/tabs?tabs=51%D0%AD1&selectedTab=51%D0%AD1");
    public static JLabel Llogin = new JLabel("Логин");
    public static JTextField Tlogin = new JTextField("UKT_RiabtsevWeb", 100);
    public static JLabel Lpassword = new JLabel("Пароль");
    public static JTextField Tpassword = new JTextField("UKT_RiabtsevWeb1", 100);
    public static JRadioButton Joption = new JRadioButton("Настройки");
    public static JRadioButton Jfilters = new JRadioButton("Фильтры");
    public static JButton ConfirmButton = new JButton("Выполнить");

    public static JLabel LMainMenu = new JLabel("Режим");
    public static JTextField  TMainMenu = new JTextField("Классификатор", 100);
    public static JLabel LSubMenu = new JLabel("Подраздел");
    public static JTextField Delay = new JTextField("400", 100);


    public static WebDriver driver1;
    public static WebDriverWait wait1;

    public GUI() {
        super("CheckListsWEB");
        this.setBounds(200, 200, 400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(5,2,3,3));
        container.add(LnameForm);
        container.add(TNameForm);
        container.add(Llogin);
        container.add(Tlogin);
        container.add(Lpassword);
        container.add(Tpassword);
        container.add(URLforView);
        container.add(TextURLforView);
        container.add(ConfirmButton);
        container.add(Delay);
        ConfirmButton.addActionListener(new ButtonEvent());
    }

}
