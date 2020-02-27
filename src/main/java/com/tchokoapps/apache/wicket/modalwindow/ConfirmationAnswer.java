package com.tchokoapps.apache.wicket.modalwindow;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ConfirmationAnswer implements Serializable {

    private boolean answered;

    public ConfirmationAnswer(boolean answered) {
        this.answered = answered;
    }
}
