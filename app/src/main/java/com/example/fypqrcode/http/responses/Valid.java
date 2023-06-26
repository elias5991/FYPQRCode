package com.example.fypqrcode.http.responses;

public class Valid {
    Boolean isValid;

    public Valid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
