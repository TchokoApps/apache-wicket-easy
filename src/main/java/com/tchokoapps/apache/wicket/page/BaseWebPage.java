package com.tchokoapps.apache.wicket.page;

import com.tchokoapps.apache.wicket.panel.FooterPanel;
import com.tchokoapps.apache.wicket.panel.HeaderPanel;
import com.tchokoapps.apache.wicket.resources.BootstrapCssResourceReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.wicket.extensions.markup.html.form.palette.theme.DefaultTheme;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@Slf4j
public abstract class BaseWebPage extends WebPage {
    public BaseWebPage(PageParameters parameters) {
        super(parameters);
        add(new HeaderPanel("header"));
        add(new FooterPanel("footer"));
    }

    @Override
    protected void onAfterRender() {
        super.onAfterRender();
        log.info("Page = {}", ToStringBuilder.reflectionToString(this));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(BootstrapCssResourceReference.get()));
    }
}
