package com.tchokoapps.apache.wicket.panel;

import com.tchokoapps.apache.wicket.page.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

@Slf4j
public class HeaderPanel extends Panel {

    private final BookmarkablePageLink<Void> categoryLink;
    private final BookmarkablePageLink<Void> articleLink;
    private final BookmarkablePageLink<Void> tableLink;
    private final BookmarkablePageLink<Void> dashboardLink;
    private final BookmarkablePageLink<Void> addCategoryLink;

    public HeaderPanel(String id) {
        super(id);

        categoryLink = new BookmarkablePageLink<Void>("categories", CategoryPage.class);
        articleLink = new BookmarkablePageLink<Void>("articles", ArticlePage.class);
        tableLink = new BookmarkablePageLink<Void>("tables", TablePage.class);
        dashboardLink = new BookmarkablePageLink<Void>("dashboard", getWebApplication().getHomePage());
        addCategoryLink = new BookmarkablePageLink<Void>("addCategory", AddCategoryPage.class);

        add(dashboardLink);
        add(categoryLink);
        add(articleLink);
        add(tableLink);
        add(addCategoryLink);

//        add(new Image("brand",new LogoResourceReference()));
        add(new ContextImage("brand", "sg-logo.png"));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        dashboardLink.setEnabled(!getPage().getClass().equals(HomePage.class));
        categoryLink.setEnabled(!getPage().getClass().equals(CategoryPage.class));
        articleLink.setEnabled(!getPage().getClass().equals(ArticlePage.class));
        tableLink.setEnabled(!getPage().getClass().equals(TablePage.class));

        log.info("dashboardLink = {}", ToStringBuilder.reflectionToString(dashboardLink, ToStringStyle.MULTI_LINE_STYLE));

    }
}
