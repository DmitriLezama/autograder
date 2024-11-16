package com.gophers.interfaces;

/**
 * The CompileCommand interface defines a contract for compiling student
 * submission code.
 */
public interface CompileCommand {
    /**
     * Compiles the student submission code and returns the result of the
     * compilation.
     *
     * @return {@code true} if the compilation was successful, {@code false}
     *         otherwise
     */
    public abstract boolean compile();
}
