package com.abukhleif.www.jdci.injection;

public class CustomActionBuilder {
    private CustomAction customAction;

    public CustomActionBuilder newAction(String name) {
        customAction = new CustomAction();
        customAction.setName(name);
        return this;
    }

    public CustomActionBuilder withCode(String code) {
        customAction.setCode(code);
        return this;
    }

    public CustomActionBuilder withSuperClass(String superClass) {
        customAction.setSuperClass(superClass);
        return this;
    }

    public CustomActionBuilder importClass(String libraryPath) {
        customAction.getImportStatements().addLibrary(libraryPath);
        return this;
    }

    public CustomActionBuilder withParameter(Parameter parameter) {
        customAction.setParameter(parameter);
        return this;
    }

    public CustomAction build() {
        return customAction;
    }
}
