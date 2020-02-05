package com.tchokoapps.apache.wicket.page;

import com.tchokoapps.apache.wicket.entities.Article;
import com.tchokoapps.apache.wicket.entities.Category;
import com.tchokoapps.apache.wicket.repositories.ArticleRepository;
import com.tchokoapps.apache.wicket.repositories.CategoryRepository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class CategoryPage extends BaseWebPage {

    @SpringBean
    CategoryRepository categoryRepository;

    @SpringBean
    ArticleRepository articleRepository;

    public CategoryPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final RepeatingView categoryRepeatingView = new RepeatingView("categoryRepeatingView");
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            categoryRepeatingView.add(new Label(categoryRepeatingView.newChildId(), category.getName()));
        }
        add(categoryRepeatingView);

        ListView<Category> categoryListView = new ListView<Category>("categoryListView", categories) {
            @Override
            protected void populateItem(ListItem<Category> listItem) {
                listItem.add(new Label("nameListView",listItem.getModelObject().getName()));
                listItem.add(new Label("descriptionListView",listItem.getModelObject().getDescription()));
            }
        };
        add(categoryListView);

        ListDataProvider<Category> categoryListDataProvider = new ListDataProvider<>(categories);
        DataView<Category> categoryDataView = new DataView<Category>("categoryDataView", categoryListDataProvider) {
            @Override
            protected void populateItem(Item<Category> item) {
                item.add(new Label("nameDataView",item.getModelObject().getName()));
                item.add(new Label("descriptionDataView",item.getModelObject().getDescription()));
            }
        };

        categoryDataView.setItemsPerPage(3);
        final PagingNavigator navigator = new PagingNavigator("navigator",categoryDataView);
        add(categoryDataView);
        add(navigator);
    }
}
