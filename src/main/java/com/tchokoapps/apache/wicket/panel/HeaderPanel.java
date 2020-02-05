package com.tchokoapps.apache.wicket.panel;

import com.tchokoapps.apache.wicket.page.ArticlePage;
import com.tchokoapps.apache.wicket.page.CategoryPage;
import com.tchokoapps.apache.wicket.page.HomePage;
import com.tchokoapps.apache.wicket.page.TablePage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

@Slf4j
public class HeaderPanel extends Panel {

    private final CategoryLink categoryLink;
    private final ArticleLink articleLink;
    private final TableLink tableLink;
    private final DashboardLink dashboardLink;

    public HeaderPanel(String id) {
        super(id);

        categoryLink = new CategoryLink("categories", CategoryPage.class);
        articleLink = new ArticleLink("articles", ArticlePage.class);
        tableLink = new TableLink("tables", TablePage.class);
        dashboardLink = new DashboardLink("dashboard", getWebApplication().getHomePage());

        add(dashboardLink);
        add(categoryLink);
        add(articleLink);
        add(tableLink);
//        add(new Image("brand",new LogoResourceReference()));
        add(new ContextImage("brand","sg-logo.png"));
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

    private class DashboardLink extends BookmarkablePageLink<Void> {
        public <C extends Page> DashboardLink(String id, Class<C> pageClass) {
            super(id, pageClass);
        }
    }

    private class CategoryLink extends BookmarkablePageLink<Void> {
        public <C extends Page> CategoryLink(String id, Class<C> pageClass) {
            super(id, pageClass);
        }
    }

    private class ArticleLink extends BookmarkablePageLink<Void> {
        public <C extends Page> ArticleLink(String id, Class<C> pageClass) {
            super(id, pageClass);
        }
    }

    private class TableLink extends BookmarkablePageLink<Void> {
        public <C extends Page> TableLink(String id, Class<C> pageClass) {
            super(id, pageClass);
        }
    }
}
