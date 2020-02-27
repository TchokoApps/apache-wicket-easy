package com.tchokoapps.apache.wicket.modalwindow;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

public class AreYouSurePanel extends Panel {

    private ConfirmationAnswer confirmationAnswer;

    public AreYouSurePanel(String id) {
        super(id);
        confirmationAnswer = new ConfirmationAnswer(false);

        ModalWindow modal = new ModalWindow("modal");
        modal.setCookieName(id);
        modal.setTitle("Ajax Action!m");
        modal.setInitialHeight(200);
        modal.setInitialWidth(350);
        modal.setContent(new YesOrNoPanel(modal.getContentId(), modal, confirmationAnswer));
        modal.setWindowClosedCallback((ModalWindow.WindowClosedCallback) ajaxRequestTarget -> {
            if (confirmationAnswer.isAnswered()) {
                onConfirm(ajaxRequestTarget);
            } else {
                onCancel(ajaxRequestTarget);
            }
        });

        Form<Void> form = new Form<>("confirmForm");
        this.add(form);

        form.add(modal);

        AjaxButton confirmButton = new AjaxButton("confirmButton") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                modal.show(target);
            }
        };

        form.add(confirmButton);
    }

    public void onConfirm(AjaxRequestTarget ajaxRequestTarget) {
        System.out.println("YES BUTTON HAS BEEN CLICKED");
    }

    public void onCancel(AjaxRequestTarget ajaxRequestTarget) {
        System.out.println("NO BUTTON HAS BEEN CLICKED");
    }
}
