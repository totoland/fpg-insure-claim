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
package com.totoland.db.enums;

/**
 *
 * @author totoland
 */
public enum DebitNoteType {

    PREVIEW(0),ORIGINAL(1), COPY(2);

    int value;

    DebitNoteType(int value) {
        this.value = value;
    }

    public static String valueOf(int value) {
        switch (value) {
            case 0: {
                return "SAMPLE";
            }
            case 1: {
                return "ORIGINAL";
            }
            case 2: {
                return "DEBIT NOTE COPY";
            }
            default: {
                return null;
            }
        }
    }

    public int getValue() {
        return value;
    }
    
    public String getName(){
        return valueOf(this.value);
    }
}
