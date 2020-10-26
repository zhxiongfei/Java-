package com.zxf.util;

import junit.framework.TestCase;
import org.junit.Assert;

public class StringsTest extends TestCase {

    public void testUnderlineClass() {
        Assert.assertTrue(Strings.underlineClass("MyFirstAge").equals("my_first_age"));
        Assert.assertTrue(Strings.underlineClass("myFirstAge").equals("my_first_age"));
    }
}