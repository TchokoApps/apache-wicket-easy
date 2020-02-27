package com.tchokoapps.apache.wicket.modalwindow;

import com.tchokoapps.apache.wicket.page.BaseWebPage;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ShowModalWindowPage extends BaseWebPage {

    public ShowModalWindowPage(PageParameters parameters) {
        super(parameters);
        Form<Void> formWithJavaScript = new Form<>("formWithJavaScript");
        Button buttonWithJavaScript = new Button("buttonWithJavaScript") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                System.out.println("Doing my job");
            }
        };

        buttonWithJavaScript.add(new AttributeModifier("onclick", "if(!confirm('Do you really want to perform this action?')) return false;"));
        formWithJavaScript.add(buttonWithJavaScript);
        this.add(formWithJavaScript);

        AreYouSurePanel yesOrNoPanel = new AreYouSurePanel("yesOrNoPanel");

        this.add(yesOrNoPanel);
    }
}
