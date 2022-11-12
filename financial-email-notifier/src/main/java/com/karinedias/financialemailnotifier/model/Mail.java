package com.karinedias.financialemailnotifier.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Mail {

    private final String FROM;
    private final String TO;
    private final String SUJECT;
    private final String CONTENT;
}
