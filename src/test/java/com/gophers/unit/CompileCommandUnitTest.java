package com.gophers.unit;

import com.gophers.interfaces.CompileCommand;
import com.gophers.services.helpers.JavaCompileCommand;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompileCommandUnitTest {
    @Test
    public void testCompileWithValidJavaFile() {
        String filePath = "src/main/resources/studentSubmissions/Brandon_Chandoo_816034693_A1";
        CompileCommand compiler = new JavaCompileCommand(filePath);
        boolean result = compiler.compile();
        assertTrue("Compilation failed", result);
    }

    @Test
    public void testCompileWithInvalidJavaFile() {
        String filePath = "src/main/resources/studentSubmissions/John_Doe_816123456_A1";
        CompileCommand compiler = new JavaCompileCommand(filePath);
        boolean result = compiler.compile();
        assertFalse("Compilation succeeded", result);
    }

    @Test
    public void testCompileWithNonExistentDirectory() {
        CompileCommand compiler = new JavaCompileCommand("nonExistentDirectory");
        boolean result = compiler.compile();
        assertFalse("Compilation succeeded", result);
    }
}
