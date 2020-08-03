package com.kael.hr.exception;

import com.kael.hr.responst.Statue;

public class HrException extends RuntimeException {
    private Statue statue;

    public HrException(Statue s) {
        super(s.getMessage());
        this.statue = s;
    }

    @Override
    public String getMessage() {
        return this.statue.getMessage();
    }

    public Statue getStatue() {
        return statue;
    }

    public void setStatue(Statue statue) {
        this.statue = statue;
    }
}
