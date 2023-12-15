package com.boveda.views.editarcredenciales;

import com.boveda.Boveda;
import com.boveda.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Editar Credenciales")
@Route(value = "Editar-Credenciales", layout = MainLayout.class)
@Uses(Icon.class)
public class EditarCredencialesView extends Composite<VerticalLayout> {

    private final Boveda boveda;

    public EditarCredencialesView() {
        this.boveda = Boveda.obtenerInstancia();

        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField plataformaTextField = new TextField();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        FormLayout formLayout2Col2 = new FormLayout();
        TextField nuevoUsuarioTextField = new TextField();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        FormLayout formLayout2Col3 = new FormLayout();
        TextField nuevaClaveTextField = new TextField();
        VerticalLayout layoutColumn5 = new VerticalLayout();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Button buttonPrimary2 = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Edita los datos de tu bóveda");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        Anchor enlace = new Anchor("https://www.security.org/how-secure-is-my-password/", "¡Comprueba la seguridad de nuestras contraseñas aquí!");

        plataformaTextField.setLabel("Ingresa la plataforma de los datos que quieres editar");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Buscar");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutColumn3.setWidth("100%");
        layoutColumn3.setMaxWidth("800px");
        layoutColumn3.setHeight("min-content");
        formLayout2Col2.setWidth("100%");
        nuevoUsuarioTextField.setLabel("Ingresa el nuevo usuario");
        layoutColumn4.setWidth("100%");
        layoutColumn4.setMaxWidth("800px");
        layoutColumn4.setHeight("min-content");
        formLayout2Col3.setWidth("100%");
        nuevaClaveTextField.setLabel("Ingrese la nueva contraseña");
        layoutColumn5.setWidth("100%");
        layoutColumn5.setMaxWidth("800px");
        layoutColumn5.setHeight("min-content");
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        buttonPrimary2.setText("Guardar");
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(enlace);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(plataformaTextField);
        layoutColumn2.add(layoutRow);
        layoutRow.add(buttonPrimary);
        layoutColumn2.add(layoutColumn3);
        layoutColumn3.add(formLayout2Col2);
        formLayout2Col2.add(nuevoUsuarioTextField);
        layoutColumn2.add(layoutColumn4);
        layoutColumn4.add(formLayout2Col3);
        formLayout2Col3.add(nuevaClaveTextField);
        layoutColumn4.add(layoutColumn5);
        layoutColumn5.add(layoutRow2);
        layoutRow2.add(buttonPrimary2);

        // Agregar la lógica al botón de búsqueda
        buttonPrimary.addClickListener(e -> buscarCredenciales(plataformaTextField, nuevoUsuarioTextField, nuevaClaveTextField));

        // Agregar la lógica al botón de guardar
        buttonPrimary2.addClickListener(e -> guardarCredenciales(plataformaTextField, nuevoUsuarioTextField, nuevaClaveTextField));
    }

    private void buscarCredenciales(TextField plataformaTextField, TextField nuevoUsuarioTextField, TextField nuevaClaveTextField) {
        String plataforma = plataformaTextField.getValue();
        if (boveda.existePlataforma(plataforma)) {
            nuevoUsuarioTextField.setValue(boveda.mostrarUsuario(plataforma));
            nuevaClaveTextField.setValue(boveda.mostrarClave(plataforma));
        } else {
            Notification.show("La plataforma no existe en la bóveda");
        }
    }

    private void guardarCredenciales(TextField plataformaTextField, TextField nuevoUsuarioTextField, TextField nuevaClaveTextField) {
        String plataforma = plataformaTextField.getValue();
        String nuevoUsuario = nuevoUsuarioTextField.getValue();
        String nuevaClave = nuevaClaveTextField.getValue();

        if (boveda.existePlataforma(plataforma)) {
            boveda.cambiarUsuario(plataforma, nuevoUsuario);
            boveda.cambiarClave(plataforma, nuevaClave);

            Notification.show("Credenciales actualizadas correctamente");
        } else {
            Notification.show("La plataforma no existe en la bóveda");
        }
    }
}