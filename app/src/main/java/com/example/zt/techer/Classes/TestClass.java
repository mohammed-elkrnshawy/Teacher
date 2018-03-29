package com.example.zt.techer.Classes;

/**
 * Created by zt on 28/03/18.
 */

public class TestClass {
    private String TestName;
    private double TestDegree;

    public TestClass(String testName, double testDegree) {
        TestName = testName;
        TestDegree = testDegree;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public double getTestDegree() {
        return TestDegree;
    }

    public void setTestDegree(double testDegree) {
        TestDegree = testDegree;
    }
}

