package com.ob1tech.creditCardManager.model;

import lombok.Getter;
import lombok.Setter;

public abstract class APIRequest {

    @Getter
    @Setter
    private String authToken;

}
