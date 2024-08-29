package demo;

import org.springframework.stereotype.Component;

public class Doctor implements Staff {

    @Override
    public void assist() {
        System.out.println("Doctor is assisting");
    }
}