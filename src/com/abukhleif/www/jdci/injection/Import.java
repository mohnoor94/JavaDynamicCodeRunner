package com.abukhleif.www.jdci.injection;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a group of import statements in the actual runnable
 * class that we want to execute at the run time...
 *
 * @author AbuKhleif
 */
public class Import {
    private List<Library> libraries;

    public Import() {
        libraries = new ArrayList<>(getDefault());
    }

    public Import(List<Library> libraries) {
        this();
        if (libraries != null) this.libraries.addAll(libraries);
    }

    //TODO Add amy default imports you need all the times here!
    private List<Library> getDefault() {
        return List.of(
                new Library("com.abukhleif.www.jdci.injection.Parameter"),
                new Library("com.abukhleif.www.jdci.node.NodeEngine"),
                new Library("com.abukhleif.www.jdci.node.NodeProperties")
        );
    }

    public List<Library> getLibraries() {
        return new ArrayList<>(libraries);
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    public void addLibrary(String libraryPath) {
        libraries.add(new Library(libraryPath));
    }
}