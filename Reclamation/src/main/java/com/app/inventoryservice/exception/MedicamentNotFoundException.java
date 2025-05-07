package com.app.inventoryservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MedicamentNotFoundException extends RuntimeException {

    private final String msg;
}
