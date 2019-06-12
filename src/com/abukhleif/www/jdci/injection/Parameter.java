package com.abukhleif.www.jdci.injection;


import com.abukhleif.www.jdci.node.NodeEngine;

/**
 * This class represent a parameter that always will be passed to the custom code
 *
 * @author AbUKhleif
 */
public class Parameter {
    private NodeEngine engine; // TODO: add any other needed classes here!

    public Parameter(NodeEngine engine) {
        this.engine = engine;
    }

    public NodeEngine getEngine() {
        return engine;
    }

    public void setEngine(NodeEngine engine) {
        this.engine = engine;
    }
}
