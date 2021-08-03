package com.mathinjection.authservice.util.validators;

import java.util.List;
import java.util.Map;

public interface CustomValidator<T> {
    public List<Map<String, String>> validate(T validationObject);
}
