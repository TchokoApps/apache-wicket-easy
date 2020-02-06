package com.tchokoapps.apache.wicket.page;

import com.tchokoapps.apache.wicket.entities.Category;
import com.tchokoapps.apache.wicket.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Iterator;

@Slf4j
public class AddCategoryPage extends BaseWebPage {

    private CategoryForm categoryForm;
    private CategoryLink categoryLink;
    private String n;
    private String d;

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

        categoryForm= new CategoryForm("form");
        categoryForm.add(new TextField<String>("name", new PropertyModel<>(this,"n")));
        categoryForm.add(new TextField<String>("description", new PropertyModel<>(this,"d")));
        add(categoryForm);

        categoryForm.setVisible(false);
    }

    private class CategoryForm extends Form<Void> {
        public CategoryForm(String id) {
            super(id);
        }

        @Override
        protected void onSubmit() {
            super.onSubmit();
            categoryRepository.save(new Category(n,d));
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
