package de.xnonymous.warpsystem.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListHandler {

    public static List<String> getList(String... strings) {
        return new ArrayList<>(Arrays.asList(strings));
    }

}
