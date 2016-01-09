package com.mapcityapplication.domain;

import java.util.ArrayList;

/**
 * Created by Demis e Lucas on 21/06/2015.
 */
public class LocalCollection {

    private static ArrayList<Local> locals;
    public static ArrayList<Local> getLocals() {
        return locals;
    }

    public static void setLocals(ArrayList<Local> locals) {
        LocalCollection.locals = locals;
    }

}
