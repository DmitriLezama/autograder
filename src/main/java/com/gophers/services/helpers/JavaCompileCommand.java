package com.gophers.services.helpers;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import com.gophers.interfaces.CompileCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The JavaCompileCommand class handles compiling Java files in a specific
 * directory.
 */
public class JavaCompileCommand implements CompileCommand {
    private final String submissionDirectory;

    /**
     * Constructs a JavaCompileCommand with the given submission directory.
     *
     * @param submissionDirectory the directory containing Java files to compile
     */
    public JavaCompileCommand(String submissionDirectory) {
        this.submissionDirectory = submissionDirectory;
    }

    /**
     * Compiles all Java files in the submission directory.
     *
     * @return true if compilation was successful, false otherwise
     */
    @Override
    public boolean compile() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            return false;
        }

        File directory = new File(submissionDirectory);
        List<String> javaFiles = new ArrayList<>();

        if (!directory.exists() || !directory.isDirectory()) {
            return false;
        }

        for (File file : directory.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".java")) {
                javaFiles.add(file.getPath());
            }
        }

        if (javaFiles.isEmpty()) {
            return false;
        }

        List<String> compilerArgs = new ArrayList<>();
        compilerArgs.add("-classpath");
        compilerArgs.add(submissionDirectory);
        compilerArgs.addAll(javaFiles);
        String[] argsArray = compilerArgs.toArray(new String[0]);
        int compilationResult = compiler.run(null, null, null, argsArray);
        return compilationResult == 0;
    }
}
