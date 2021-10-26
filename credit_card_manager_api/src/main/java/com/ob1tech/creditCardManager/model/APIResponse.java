package com.ob1tech.creditCardManager.model;

import lombok.Getter;
import lombok.Setter;

public class APIResponse {

    @Getter @Setter private String errorMessage;
    @Getter @Setter private String message;
    @Getter @Setter private boolean ok;
}
