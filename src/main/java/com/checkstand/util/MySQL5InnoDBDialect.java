package com.checkstand.util;

import org.hibernate.dialect.MySQL5Dialect;

public class MySQL5InnoDBDialect extends MySQL5Dialect {
    public MySQL5InnoDBDialect() {
    }

    public boolean supportsCascadeDelete() {
        return true;
    }

    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }

    public boolean hasSelfReferentialForeignKeyBug() {
        return true;
    }
}