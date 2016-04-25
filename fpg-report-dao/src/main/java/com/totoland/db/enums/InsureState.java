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
public enum InsureState {

    NEW(1, "New Entry"),
    DRAFT(2, "DRAFT"),
    SAVE(3, "SAVE"),
    DELETE(0, "DELETE");

    private int state = 0;
    private String name;

    InsureState(int state, String name) {
        this.state = state;
        this.name = name;
    }

    public int getState() {
        return this.state;
    }

    public String getName() {
        return this.name;
    }

    public static String toStateName(int status) {
        if (status == 1) {
            return NEW.name;
        } else if (status == 2) {
            return DRAFT.name;
        } else if (status == 3) {
            return SAVE.name;
        } else if (status == 0) {
            return DELETE.name;
        } else {
            return "UNKNOW";
        }
    }
}
