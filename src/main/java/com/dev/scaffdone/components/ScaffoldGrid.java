package com.dev.scaffdone.components;

import com.dev.scaffdone.core.scaffolding.ScaffoldingService;
import com.dev.scaffdone.core.scaffolding.model.Colors;
import com.dev.scaffdone.core.scaffolding.model.Scaffolding;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.provider.SortDirection;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;


public class ScaffoldGrid extends Grid<Scaffolding> {

    private final ScaffoldingService scaffoldingService;

    public ScaffoldGrid(ScaffoldingService scaffoldingService) {
        this.scaffoldingService = scaffoldingService;
        initColumns();
        addComponentColumn(this::createDoneButton).setHeader("Done");
        addComponentColumn(ScaffoldGrid::createMoreButton).setHeader("More Info");
        addComponentColumn(item -> createDeleteButton());
    }

    private static Button createDeleteButton() {
        Button delete = new Button(VaadinIcon.TRASH.create());
        buttonRedStyle(delete);
        delete.setWidth("80px");
        delete.addClickListener(e -> {
            //Dodaj potwierdzenie TAK/NIE USUN Z BAZY
            Notification.show("Deleted!");
        });
        return delete;
    }

    private Button createDoneButton(Scaffolding item) {
        Button doneButton = new Button("Done");
        doneButtonHandler(item, doneButton);
        doneButton.addClickListener(click -> {
            isDoneHandler(item, doneButton);
        });
        return doneButton;
    }

    private static Button createMoreButton(Scaffolding item) {
        Button moreButton = new Button("MORE");
        buttonGreenStyle(moreButton);
        moreButtonHandler(item, moreButton);
        return moreButton;
    }

    private static void buttonRedStyle(Button delete) {
        delete.addClassName("home-view-button-1");
        delete.getStyle().set("background-color", "#ff6464");
        delete.getStyle().set("color", "white");
    }

    private static void moreButtonHandler(Scaffolding item, Button moreButton) {
        moreButton.addClickListener(click -> {
            VerticalLayout dialogLayout = createLayoutWithTextArea(item);
            Dialog moreInfoDialog = createDialog(dialogLayout);
            Button closeDialog = new Button("Close", e -> {
                moreInfoDialog.close();
            });
            moreInfoDialog.getFooter().add(closeDialog);
            moreInfoDialog.open();
        });
    }

    private static Dialog createDialog(VerticalLayout dialogLayout) {
        Dialog moreInfoDialog = new Dialog(dialogLayout);
        dialogStyle(moreInfoDialog);
        return moreInfoDialog;
    }

    private static VerticalLayout createLayoutWithTextArea(Scaffolding item) {
        TextArea dialogTextArea = new TextArea("More");
        TextArea modules = new TextArea("Modules List");
        modules.setReadOnly(true);
        modules.setValue(item.getModules().toString());
        dialogTextArea.setReadOnly(true);
        dialogTextArea.setValue(item.getOtherInformation());
        VerticalLayout layout = new VerticalLayout(dialogTextArea, modules);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        return layout;
    }

    private static void dialogStyle(Dialog moreInfoDialog) {
        moreInfoDialog.setHeaderTitle("Other Information");
        moreInfoDialog.setWidth("600px");
        moreInfoDialog.setHeight("400px");
    }

    private static void buttonGreenStyle(Button moreButton) {
        moreButton.getStyle().set("background-color", Colors.GREEN_COLOR.getHexCode());
        moreButton.getStyle().set("color", "white");
    }

    private static void doneButtonHandler(Scaffolding item, Button button) {
        if (item.isDone()) {
            button.setText("YES");
            buttonGreenStyle(button);
        } else {
            button.setText("NO");
            buttonRedStyle(button);
        }
    }

    private void isDoneHandler(Scaffolding item, Button button) {
        Scaffolding scaffolding = scaffoldingService.getScaffold(item.getId()).orElseThrow(
                () -> new NoSuchElementException("Calculation not found"));
        System.out.println(scaffolding);
        if (scaffolding.isDone()) {
            scaffolding.setDone(false);
            button.setText("NO");
            buttonRedStyle(button);
        } else {
            button.setText("YES");
            scaffolding.setDone(true);
            buttonGreenStyle(button);
        }
        scaffoldingService.updateDone(scaffolding);
        System.out.println("UPDATED" + scaffolding);
    }

    private void initColumns() {
        addColumn(Scaffolding::getId).setHeader("Id").setSortable(true).setWidth("50px");
        addColumn(Scaffolding::getDate).setHeader("Date").setSortable(true).setWidth("150px");
        addColumn(Scaffolding::getUsername).setHeader("User").setWidth("50px");
        addColumn(Scaffolding::getModules).setHeader("Modules").setWidth("350px");
        addColumn(Scaffolding::getHeight).setHeader("Height").setWidth("50px");
        addColumn(Scaffolding::getTotalLength).setHeader("Total Length").setSortable(true).setWidth("50px");
        addColumn(Scaffolding::getResultSquareMeters).setHeader("Square Meters").setSortable(true).setWidth("50px");
    }
}
