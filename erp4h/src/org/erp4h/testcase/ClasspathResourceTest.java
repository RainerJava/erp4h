package org.erp4h.testcase;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

/**
 * Test for the Service class.
 */
public class ClasspathResourceTest extends TestCase {

    /**
     * Tests whether the file /sample.txt with mime-type text/plain exists.
     * 
     * @throws Exception
     *             in case of an error
     */
    public void testSampleResource() throws Exception {
        final List list = ClasspathResource.getInstance()
                .listResourcesOfMimeType("text/plain");
        boolean found = false;
        final Iterator i = list.iterator();
        while (i.hasNext()) {
            final URL u = (URL) i.next();
            if (u.getPath().endsWith("sample.txt")) {
                found = true;
            }
        }
        assertTrue(found);
    }

    /**
     * Tests the mode where Service returns class names.
     * 
     * @throws Exception
     *             in case of an error
     */
    public void testNonexistingResource() throws Exception {
        final List list = ClasspathResource.getInstance()
                .listResourcesOfMimeType("nota/mime-type");
        assertTrue(list.isEmpty());
    }

}
