package com.tchokoapps.apache.wicket.feedback;

import org.apache.wicket.feedback.ExactLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class ValidationSuccessFeedbackPanel extends FeedbackPanel {

    public ValidationSuccessFeedbackPanel(String id) {
        super(id, new ExactLevelFeedbackMessageFilter(FeedbackMessage.SUCCESS));
    }
}
