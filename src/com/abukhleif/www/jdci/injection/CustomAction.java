package com.abukhleif.www.jdci.injection;

import com.abukhleif.www.jdci.util.Utils;
import com.abukhleif.www.jdci.compiler.MethodInvocationUtils;
import com.abukhleif.www.jdci.compiler.RuntimeCompiler;

import java.util.stream.Collectors;

/**
 * This class is used to inject a custom code at the runtime...
 *
 * @author AbuKhleif
 */

public class CustomAction {
    private String name;
    private String code;
    private String superClass;
    private Import importStatements;
    private Parameter parameter;

    private RuntimeCompiler compiler = new RuntimeCompiler();

    private String className;
    private static final String CLASS_NAME_PREFIX = "CustomCode";
    private static final String METHOD_NAME = "execute";

    public CustomAction() {
        importStatements = new Import();
        setClassName();
    }

    public CustomAction(String name, String code, String superClass, Import importStatements, Parameter parameter) {
        this.name = name;
        this.code = code;
        this.superClass = superClass;
        this.importStatements = importStatements == null ? new Import() : importStatements;
        this.parameter = parameter;
        setClassName();
    }

    public void execute() {
        try {
            compiler.addClass(className, buildClass());
            compiler.compile();
            invokeMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String buildClass() {
        StringBuilder sb = new StringBuilder(getImportsAsString());
        sb.append("\npublic class ");
        sb.append(className);
        sb.append(" ");
        sb.append(getSuperClassAsString());
        sb.append(String.format(" {\n\tpublic static void %s(Parameter parameters){\t", METHOD_NAME));
        sb.append(getCode());
        sb.append("\t}\n}");
        return sb.toString();
    }

    private String getSuperClassAsString() {
        if (getSuperClass() == null) {
            return "extends Object"; // extend any default class you want if needed!
        }
        return "extends " + getSuperClass();
    }

    private String getImportsAsString() {
        return getImportStatements().getLibraries().stream()
                .map(lib -> String.format("import %s;", lib.getLibraryPath()))
                .collect(Collectors.joining("\n"));
    }

    private void invokeMethod() {
        MethodInvocationUtils.invokeStaticMethod(compiler.getCompiledClass(className), METHOD_NAME, parameter);
    }

    public String getCode() {
        if (code == null) {
            code = "";
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public String getName() {
        if (name == null) {
            name = "CustomAction Code";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public Import getImportStatements() {
        return importStatements;
    }

    private void setClassName() {
        className = String.format("%s_%s", CLASS_NAME_PREFIX, Utils.getSerial());
    }
}