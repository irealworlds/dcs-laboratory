package lab1.exercise1.serialising;

import java.io.Serializable;

public class Person implements Serializable {
//    String nume;
    transient String nume;
    int varsta;

    Person(String n, int v){
        nume = n; varsta = v;
    }

    public String toString(){
        return "Persoana: "+nume+" vasrta: "+varsta;
    }
}
