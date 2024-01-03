package com.example.employee.model;

public enum Gender {
    M, F;

    public static Gender fromString(String gender){
        if(gender.equals("M")){
            return M;
        }else if(gender.equals("F")){
            return F;
        }

        return null;
    }
}
