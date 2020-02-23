package com.tchokoapps.apache.wicket.page;

import com.tchokoapps.apache.wicket.entities.Category;
import com.tchokoapps.apache.wicket.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.wicket.Component;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.UrlValidator;

import java.util.Optional;

@Slf4j
public class AddCategoryPage extends BaseWebPage {

    private CategoryForm categoryForm;
    private CategoryLink categoryLink;

    @SpringBean
    CategoryRepository categoryRepository;
    private FeedbackPanel feedback;

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
        categoryForm.add(new TextField<String>("name", new PropertyModel<>(category,"name")).add(new PropertyValidator<>()).setRequired(true).add(new UniqueCategoryNameValidator()));
        categoryForm.add(new TextField<String>("imgUrl", new PropertyModel<>(category,"imgUrl")).add(new PropertyValidator<>()).setRequired(true).add(new UrlValidator()));
        categoryForm.add(new TextField<String>("description", new PropertyModel<>(category,"description")).add(new PropertyValidator<>()).setRequired(true));
        add(categoryForm);

        categoryForm.setVisible(false);

        feedback = new FeedbackPanel("feedback");
        add(feedback);
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

    private class UniqueCategoryNameValidator implements org.apache.wicket.validation.IValidator<String> {
        @Override
        public void validate(IValidatable<String> validable) {
            String value = validable.getValue();
            Optional<Category> categoryByNameOpt = categoryRepository.findCategoryByName(value);
            if(categoryByNameOpt.isPresent()) {
                ValidationError validationError = new ValidationError(this);
                validationError.setVariable("suggestedCategory", "Weine");
                validable.error(validationError);

                Component feedbackul = feedback.get("feedbackul");
                feedbackul.add(new AttributeAppender("class", "alert alert-danger"));
                feedbackul.add(new AttributeAppender("role", "alert"));
            }
        }
    }
}
