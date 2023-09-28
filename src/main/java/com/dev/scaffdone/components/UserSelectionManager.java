package com.dev.scaffdone.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.List;

public class UserSelectionManager extends VerticalLayout {

    public UserSelectionManager() {
        TextField customUser = new TextField("Custom User");
        customUser.addClassName("home-view-text-field-1");
        ComboBox<String> lastUsers = new ComboBox<>("Last Users");
        lastUsers.addClassName("home-view-combo-box-1");
        Button setUser = userButton("SET USER");

        setUser.addClickListener(e -> {
            //Dodaj zapisywanie do listy obiektu ilosc x modul
            Notification.show("User added!");
        });
        setUsers(lastUsers);
        add(lastUsers,customUser,setUser);
    }

    private static void setUsers(ComboBox<String> comboBox) {
        List<String> items = new ArrayList<>();
        //Dopisac uzytkownikow ktorzy istnieja w bazie danych
        items.add("");
        items.add("My Data");
        comboBox.setAllowCustomValue(true);
        comboBox.setItems(items);
    }

    private static Button userButton(String name) {
        Button setUser = new Button(name);
        setUser.setWidth("190px");
        setUser.getStyle().set("background-color", "#4e8752");
        setUser.getStyle().set("color", "white");
        return setUser;
    }
}