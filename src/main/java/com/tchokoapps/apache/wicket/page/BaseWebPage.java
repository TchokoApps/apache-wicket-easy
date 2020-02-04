package com.tchokoapps.apache.wicket.page;

import com.tchokoapps.apache.wicket.panel.FooterPanel;
import com.tchokoapps.apache.wicket.panel.HeaderPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class BaseWebPage extends WebPage {
    public BaseWebPage(PageParameters parameters) {
        super(parameters);
        add(new HeaderPanel("header"));
        add(new FooterPanel("footer"));
    }
}
