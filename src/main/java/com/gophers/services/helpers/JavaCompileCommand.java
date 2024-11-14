package com.gophers.services.helpers;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import com.gophers.interfaces.CompileCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaCompileCommand implements CompileCommand {
    private final String submissionDirectory;

    public JavaCompileCommand(String submissionDirectory) {
        this.submissionDirectory = submissionDirectory;
    }

    @Override
    public boolean compile() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null)
            return false;

        File directory = new File(submissionDirectory);
        List<String> javaFiles = new ArrayList<>();

        if (!directory.exists() || !directory.isDirectory())
            return false;
        for (File file : directory.listFiles())
            if (file.isFile() && file.getName().endsWith(".java"))
                javaFiles.add(file.getPath());

        if (javaFiles.isEmpty()) {
            return false;
        }

        List<String> compilerArgs = new ArrayList<String>();
        compilerArgs.add("-classpath");
        compilerArgs.add(submissionDirectory);
        compilerArgs.addAll(javaFiles);
        String[] argsArray = compilerArgs.toArray(new String[0]);
        int compilationResult = compiler.run(null, null, null, argsArray);
        return compilationResult == 0;
    }
}
