/*
 * The MIT License
 *
 * Copyright 2018 Intuit Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.intuit.karate.web;

import com.intuit.karate.web.chrome.Chrome;
import com.intuit.karate.web.chrome.ChromeDriver;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pthomas3
 */
public class DriverFactory {
    
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    
    private DriverFactory() {
        // only static methods
    }
    
    public static Driver construct(Map<String, Object> options) {
        String type = (String) options.get("type");
        if (type == null) {
            logger.warn("type was null, defaulting to 'chrome'");
            type = "chrome";
        }
        if (type.equals("chrome")) {
            return Chrome.start(options);
        } else if (type.equals("chromedriver")) {
            return new ChromeDriver(options);
        } else {
            logger.warn("unknown driver type: {}, defaulting to 'chrome'", type);
            return Chrome.start(options);
        }
    }
    
}