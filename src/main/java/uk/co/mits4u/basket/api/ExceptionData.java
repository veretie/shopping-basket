package uk.co.mits4u.basket.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionData {

        private String errorMessage;

        public ExceptionData(@JsonProperty("errorMessage") String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

    }