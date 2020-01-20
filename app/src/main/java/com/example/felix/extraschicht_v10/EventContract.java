package com.example.felix.extraschicht_v10;

import android.provider.BaseColumns;

public class EventContract {

    private EventContract() {}

    public static final class EventEntry implements BaseColumns {
        public static final String DB_TABLE ="Events_Table";
        public static final String ID = "ID";
        public static final String TITEL = "TITEL";
        public static final String FIRMA = "FIRMA";
        public static final String IMG = "IMG";

    }
}
