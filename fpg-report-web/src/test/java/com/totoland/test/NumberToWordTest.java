/*
 * The MIT License
 *
 * Copyright 2016 totoland.
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
package com.totoland.test;

import com.totoland.web.utils.NumberToWord;

/**
 *
 * @author totoland
 */
public class NumberToWordTest {

    public static void main(String... args) {

        NumberToWord.AbstractProcessor processor = new NumberToWord.DefaultProcessor();

        long[] values = new long[]{
            0,
            4,
            10,
            12,
            100,
            108,
            299,
            1000,
            1003,
            2040,
            45213,
            100000,
            100005,
            100010,
            202020,
            202022,
            999999,
            1000000,
            1000001,
            10000000,
            10000007,
            99999999,
            Long.MAX_VALUE,
            Long.MIN_VALUE
        };

        String[] strValues = new String[]{
            "0001.2",
            "3.141592"
        };

        for (long val : values) {
            System.out.println(val + " = " + processor.getName(val));
        }

        for (String strVal : strValues) {
            System.out.println(strVal + " = " + processor.getName(strVal));
        }

        // generate a very big number...
        StringBuilder bigNumber = new StringBuilder();
        for (int d = 0; d < 66; d++) {
            bigNumber.append((char) ((Math.random() * 10) + '0'));
        }
        bigNumber.append(".");
        for (int d = 0; d < 26; d++) {
            bigNumber.append((char) ((Math.random() * 10) + '0'));
        }

        System.out.println(bigNumber.toString() + " = " + processor.getName(bigNumber.toString()));

    }
}
