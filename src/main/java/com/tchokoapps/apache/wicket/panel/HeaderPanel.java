package com.tchokoapps.apache.wicket.panel;

import com.tchokoapps.apache.wicket.page.ArticlePage;
import com.tchokoapps.apache.wicket.page.CategoryPage;
import com.tchokoapps.apache.wicket.page.TablePage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class HeaderPanel extends Panel {
    public HeaderPanel(String id) {
        super(id);

        CategoryLink categoryLink = new CategoryLink("categories", CategoryPage.class);
        ArticleLink articleLink = new ArticleLink("articles", ArticlePage.class);
        TableLink tableLink = new TableLink("tables", TablePage.class);
        DashboardLink dashboardLink = new DashboardLink("dashboard", getWebApplication().getHomePage());

        add(dashboardLink);
        add(categoryLink);
        add(articleLink);
        add(tableLink);
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
