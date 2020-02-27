package com.tchokoapps.apache.wicket.modalwindow;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class YesOrNoPanel extends Panel {

    public YesOrNoPanel(String id, ModalWindow modalWindow, ConfirmationAnswer answer) {
        super(id);

        Form form = new Form("yesOrNoForm");

        MultiLineLabel multiLineLabel = new MultiLineLabel("message", Model.of("Do you really want to perform this action?"));
        form.add(multiLineLabel);

        AjaxButton yesButton = new AjaxButton("yesButton") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                if(target != null) {
                    answer.setAnswered(true);
                    modalWindow.close(target);
                }
            }
        };

        AjaxButton noButton = new AjaxButton("noButton") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                if(target != null) {
                    answer.setAnswered(false);
                    modalWindow.close(target);
                }
            }
        };

        form.add(yesButton);
        form.add(noButton);

        this.add(form);
    }
}
