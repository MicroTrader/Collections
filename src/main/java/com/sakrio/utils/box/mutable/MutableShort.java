/*
 * _______________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and
 *     Project Contributors
 *
 * ${PROJECT_NAME}, is a collection of works and / or artifacts submitted
 * and / or contributed by multiple authors ("Project Contributors"),
 * collectively creating a larger work and / or artifact (the / this
 * "Project"). This project is licensed under the terms of either:
 *  - the Apache License, Version 2.0 (ASF 2.0), or
 *  - the Academic Free License, Version 3.0 (AFL 3.0), or
 *  - the MIT License (MIT), collectively referred as the "Project Licenses".
 * As a recipient of this Project, you may choose which license to receive
 * the Project under (except as noted in other explicit declarations and / or
 * notices of intellectual property rights). Some artifacts and / or works may
 * not be the intellectual property of the Project Contributors. These are
 * noted in explicit declarations and / or notices of intellectual property
 * rights.
 *
 * This Project uses a shared copyright model. Each contributor maintains
 * copyright over their contributions and / or submissions to this Project.
 * Contributions and / or submissions are typically transformation,
 * modification or adaptation of existing artifacts or works (underlying work)
 * resulting in derivative works. Thus, the Project artifacts, in its entirety
 * is not the copyright of any single person or institution. Instead, it is the
 * collective copyright of all those who have made contributions and / or
 * submissions to the Project.
 *
 * No contributions and / or submissions are allowed under licenses which are
 * fundamentally incompatible with the Project Licenses under which this
 * Project is licenced under. By contributing or making a submission to this
 * Project you hereby understand and agree to the following:
 *  - your contributions and / or submissions are licensed under the terms of
 *    all the Project Licenses whereas the recipients are free to choose under
 *    which license the contributions and submission is received under;
 *  - you will only make contributions and / or submissions which you own the
 *    intellectual property right or have appropriate and adequate licenses,
 *    and in addition have the authority and ability to make the contributions
 *    and / or submissions, under the terms of the Project Licenses, without
 *    encumbrances, limitations and restrictions whatsoever;
 *  - you comply and adhere to the adopted: code of conduct, norms, etiquettes
 *    and protocols of the Project in all you dealing with the Project.
 *
 * _______________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and
 *     Project Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * _______________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and
 *     Project Contributors
 *
 * Licensed under the Academic Free License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/AFL-3.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * _______________________________________________________________________________
 *
 * The MIT License (MIT)
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and
 *     Project Contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 * _______________________________________________________________________________
 */


package com.sakrio.utils.box.mutable;

import com.sakrio.utils.UnsafeAccess;
import com.sakrio.utils.box.BoxOnce;
import com.sakrio.utils.box.immutable.ImmutableShort;
import sun.misc.Unsafe;

import java.io.UnsupportedEncodingException;

/**
 * Wrapper class
 *
 * @author sirinath
 */
@SuppressWarnings("serial")
public final class MutableShort extends Number
        implements BoxOnce<MutableShort> {
    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(MutableShort.class, "value");
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;
    /**
     * Value
     */
    private short value;

    /**
     * @param i Parameter
     */
    public MutableShort(final short i) {
        value = i;
    }

    public final static short pack(final int from, final int to, final String values, final String charsetName) {
        try {
            return pack(from, to, values.getBytes(charsetName));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Charset not supported", e);
        }
    }

    public final static short pack(final int from, final int to, final String values) {
        return pack(from, to, values, "US-ASCII");
    }

    public final static short pack(final int from, final int to, final byte... values) {
        short value = 0;

        final int last = Math.min(values.length, to);
        final int remainder = Math.max(last - from, 0);

        switch (remainder) {
            default:
            case 2:
                value += values[from + 1] << Byte.SIZE;
            case 1:
                value += values[from];
            case 0:
        }

        return value;
    }

    public final static void unpack(final int from, final int to, final byte[] result, final short value) {
        final int last = Math.min(result.length, to);
        final int remainder = Math.max(last - from, 0);

        switch (remainder) {
            default:
            case 2:
                result[from + 1] = (byte) ((value & 0xFF00) >> Byte.SIZE);
            case 1:
                result[from] = (byte) (value & 0x00FF);
            case 0:
        }
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    public final short getValue() {
        return value;
    }

    public final void setValue(final short value) {
        this.value = value;
    }

    public final short get() {
        return value;
    }

    public final short getValueVolatile() {
        return UNSAFE.getShortVolatile(this, valueFieldOffset);
    }

    public final void setValueVolatile(final short value) {
        UNSAFE.putShortVolatile(this, valueFieldOffset, value);
    }

    public final void set(final short value) {
        this.value = value;
    }

    @Override
    public final boolean equals(Object other) {
        if (other instanceof MutableShort)
            return value == ((MutableShort) other).getValue();
        else if (other instanceof ImmutableShort)
            return value == ((ImmutableShort) other).getValue();
        else if (other instanceof Short)
            return ((Short) other).shortValue() == value;
        else
            return false;
    }

    @Override
    public final int hashCode() {
        return Short.hashCode(value);
    }

    @Override
    public final int compareTo(final MutableShort other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    public final int compareTo(final ImmutableShort other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    // Others

    @Override
    public final int intValue() {
        return (int) value;
    }

    @Override
    public final long longValue() {
        return (long) value;
    }

    @Override
    public final float floatValue() {
        return (float) value;
    }

    @Override
    public final double doubleValue() {
        return (double) value;
    }
}