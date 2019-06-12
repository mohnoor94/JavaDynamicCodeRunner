package com.abukhleif.www.jdci.injection;

/**
 * This class represent a library to import before executing the custom code
 *
 * @author AbuKheleif
 */
public class Library {
    private String libraryPath;

    public Library() {
    }

    public Library(String libraryPath) {
        this.libraryPath = libraryPath;
    }

    public String getLibraryPath() {
        return libraryPath;
    }

    public void setLibraryPath(String libraryPath) {
        this.libraryPath = libraryPath;
    }
}