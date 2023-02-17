package org.example;

import Page.ButtonEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;



public class GUI extends JFrame{

    public static JLabel LnameForm = new JLabel("Название формы просмотра");
    public static JTextField TNameForm = new JTextField("Просмотр росписи расходов", 256);
    public static JLabel LXpath = new JLabel("Xpath до списка");

    public static JLabel Llogin = new JLabel("Логин");
    public static JTextField Tlogin = new JTextField("edo1", 100);
    public static JLabel Lpassword = new JLabel("Пароль");
    public static JTextField Tpassword = new JTextField("edo1edo1", 100);
    public static JRadioButton Joption = new JRadioButton("Настройки");
    public static JRadioButton Jfilters = new JRadioButton("Фильтры");
    public static JButton ConfirmButton = new JButton("Выполнить");

    public static JLabel LMainMenu = new JLabel("Режим");
    public static JTextField  TMainMenu = new JTextField("Классификатор", 100);
    public static JLabel LSubMenu = new JLabel("Подраздел");

    public static WebDriver driver1;
    public static WebDriverWait wait1;

    public GUI() {
        super("CheckDropRS");
        this.setBounds(200, 200, 750, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,3,3,3));
        container.add(LnameForm);
        container.add(Llogin);
        container.add(Lpassword);
        container.add(TNameForm);
        container.add(Tlogin);
        container.add(Tpassword);
        container.add(Joption);
        container.add(Jfilters);
        Joption.setSelected(true);
        container.add(ConfirmButton);
        ConfirmButton.addActionListener(new ButtonEvent());
    }

}
