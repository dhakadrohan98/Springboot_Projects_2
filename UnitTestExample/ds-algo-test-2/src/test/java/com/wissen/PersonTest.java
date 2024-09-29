package com.wissen;

import org.junit.Assert;
import org.junit.Test;

public class PersonTest {

    @Test
    public void testPersonality() {
        Person person1 = new Person("Bob", 26);
        Person person2 = new Person("bob", 25);
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
        Assert.assertEquals(person2, person1);
    }
}
