package com.tchokoapps.apache.wicket.page;

import com.tchokoapps.apache.wicket.entities.Article;
import com.tchokoapps.apache.wicket.entities.Category;
import com.tchokoapps.apache.wicket.repositories.ArticleRepository;
import com.tchokoapps.apache.wicket.repositories.CategoryRepository;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


public class ArticlePage extends BaseWebPage {

    @SpringBean
    ArticleRepository articleRepository;

    @SpringBean
    CategoryRepository categoryRepository;

    private final ArticleDataProvider articleDataProvider;
    private final DataView<Article> articleDataView;
    private final ArticleForm articleForm;
    private final ArticleLink articleLink;

    public ArticlePage(PageParameters parameters) {
        super(parameters);

        articleForm = new ArticleForm("form");
        articleDataProvider = new ArticleDataProvider();
        articleDataView = new ArticleDataView("articles", articleDataProvider);
        articleLink = new ArticleLink("newArticle");
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        articleDataView.setItemsPerPage(5);
        final PagingNavigator navigator = new PagingNavigator("navigator", articleDataView);
        add(navigator);
        add(articleDataView);
        add(articleLink);
        add(new FeedbackPanel("feedback"));
        initializeArticleForm();
        add(articleForm);
    }

    private void initializeArticleForm() {
        Article article = new Article();
        articleForm.setVisible(true);
        articleForm.setDefaultModel(Model.of(article));
        articleForm.add(new TextField<Article>("name", new PropertyModel<>(article, "name")).setRequired(true));
        articleForm.add(new TextArea<Article>("description", new PropertyModel<>(article, "description")).setRequired(true));
        articleForm.add(new TextField<Article>("price", new PropertyModel<>(article, "price")).setRequired(true).add(new RangeValidator<BigDecimal>(BigDecimal.ZERO, BigDecimal.valueOf(100))));
        articleForm.add(new TextField<Article>("imageUrl", new PropertyModel<>(article, "imgUrl")).setRequired(true));
        List<Category> categories = categoryRepository.findAll();
        DropDownChoice<Category> categoryDropDownChoice = new DropDownChoice<>("category", categories, new ChoiceRenderer<>("name", "id"));
        articleForm.add(categoryDropDownChoice);
    }

    private class ArticleForm extends Form<Article> {
        public ArticleForm(String id) {
            super(id);
        }

        @Override
        protected void onSubmit() {
            super.onSubmit();
            Article article = this.getModelObject();
            Optional<Category> categoryOptional = categoryRepository.findCategoryByName(article.getCategory().getName());
            if (categoryOptional.isPresent()) {
                Category category = categoryOptional.get();
                article.setCategory(category);
                articleRepository.save(article);
            } else {
                throw new RuntimeException("Category with name " + article.getCategory().getName() + " doesn´t exist!");
            }

            this.iterator().forEachRemaining(component -> component.setDefaultModelObject(null));
            this.setDefaultModelObject(new Article());
        }
    }

    private class ArticleDataProvider extends SortableDataProvider<Article, Void> {
        @Override
        public Iterator<? extends Article> iterator(long l, long l1) {
            return articleRepository.findAll().iterator();
        }

        @Override
        public long size() {
            return articleRepository.count();
        }

        @Override
        public IModel<Article> model(Article article) {
            return new Model<Article>(article);
        }
    }

    private class ArticleDataView extends DataView<Article> {

        protected ArticleDataView(String id, IDataProvider<Article> dataProvider) {
            super(id, dataProvider);
        }

        @Override
        protected void populateItem(Item<Article> item) {
            final Article article = item.getModelObject();
            item.add(new Label("name", Model.of(article.getName())));
            item.add(new Label("description", Model.of(article.getDescription())));
            item.add(new Label("price", Model.of(article.getPrice())));
            item.add(new Label("createdAt", Model.of(article.getCreatedAt())));
            final AttributeAppender imgAttributeAppender = new AttributeAppender("src", Model.of(article.getImgUrl()));
            item.add(new WebMarkupContainer("image").add(imgAttributeAppender));
            item.add(new Label("catName", Model.of(article.getCategory().getName())));
        }
    }

    private class ArticleLink extends Link<Article> {

        public ArticleLink(String id) {
            super(id);
        }

        @Override
        public void onClick() {
            ArticlePage.this.articleForm.setVisible(true);
        }
    }
}
