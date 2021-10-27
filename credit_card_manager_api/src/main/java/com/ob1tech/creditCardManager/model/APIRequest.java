package com.ob1tech.creditCardManager.model;

import lombok.Getter;
import lombok.Setter;

public abstract class APIRequest {

    @Getter
    @Setter
    private String authToken;
    @Getter
    @Setter
    private String type;
    @Getter
    @Setter
    private int user_id;
    @Getter
    @Setter
    private int card_id;

}
