package com.tchokoapps.apache.wicket.page;

import com.tchokoapps.apache.wicket.entities.Category;
import com.tchokoapps.apache.wicket.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.UrlValidator;

@Slf4j
public class AddCategoryPage extends BaseWebPage {

    private CategoryForm categoryForm;
    private CategoryLink categoryLink;

    @SpringBean
    CategoryRepository categoryRepository;

    public AddCategoryPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        categoryLink = new CategoryLink("newCategory");
        add(categoryLink);

        Category category = new Category();

        categoryForm= new CategoryForm("form");
        categoryForm.setDefaultModel(new Model<Category>(category));
        categoryForm.add(new TextField<String>("name", new PropertyModel<>(category,"name")).setRequired(true));
        categoryForm.add(new TextField<String>("imgUrl", new PropertyModel<>(category,"imgUrl")).setRequired(true).add(new UrlValidator()));
        categoryForm.add(new TextField<String>("description", new PropertyModel<>(category,"description")).setRequired(true));
        add(categoryForm);

        categoryForm.setVisible(false);

        add(new FeedbackPanel("feedback"));
    }

    private class CategoryForm extends Form<Category> {
        public CategoryForm(String id) {
            super(id);
        }

        @Override
        protected void onSubmit() {
            super.onSubmit();
            Category category = (Category) this.getDefaultModelObject();
            categoryRepository.save(category);
            categoryForm.iterator().forEachRemaining(component -> component.setDefaultModelObject(""));
        }
    }

    private class CategoryLink extends Link<Void> {

        public CategoryLink(String id) {
            super(id);
        }

        @Override
        public void onClick() {
            AddCategoryPage.this.categoryForm.setVisible(true);
        }
    }
}
