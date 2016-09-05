/*
 * _______________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and
 *     Project Contributors
 *
 * ${PROJECT_NAME} (the / this "Project") is available under
 * either the terms of
 *  - the Apache License, Version 2.0 (ASF 2.0), or
 *  - the Academic Free License, Version 3.0 (AFL 3.0), or
 *  - the MIT License (MIT), collectively referred as the "Project Licenses".
 * As a recipient of this Project, you may choose which license to receive
 * these artifacts under (except as noted in other explicit declarations
 * and / or notices of intellectual property rights). Some artifacts may not
 * be the intellectual property of Suminda Sirinath Salpitikorala Dharmasena
 * and Project Contributors. These are noted in explicit declarations and / or
 * notices of intellectual property rights.
 *
 * No external contributions are allowed under licenses which are
 * fundamentally incompatible with the Project Licenses under which this
 * Project is licenced under. By contributing or making a submission to
 * this Project you agree that:
 *  - the contributions and / or submissions will be licensed under the terms
 *    of all the Project Licenses whereas the recipients are free to choose
 *    under which license the contributions and submission is received under;
 *  - you own to intellectual property rights (patent rights if patented and
 *    copyrights) of the contribution and / or submission and have the
 *    authority and ability to make the contribution and / or submission
 *    without encumbrances and restrictions;
 *  - you comply and adhere to the adopted code of conduct, norms, etiquettes
 *    and protocols.
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
import com.sakrio.utils.box.immutable.ImmutableInt;
import sun.misc.Unsafe;

import java.io.UnsupportedEncodingException;

/**
 * Wrapper class
 *
 * @author sirinath
 */
@SuppressWarnings("serial")
public final class MutableInt extends Number
        implements BoxOnce<MutableInt> {
    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(MutableInt.class, "value");
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;
    /**
     * Value
     */
    private int value;

    /**
     * @param i Parameter
     */
    public MutableInt(final int i) {
        value = i;
    }

    public final static int pack(final String values, final String charsetName) {
        try {
            return pack(0, values.getBytes(charsetName));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Charset not supported", e);
        }
    }

    public final static int pack(final String values) {
        return pack(values, "US-ASCII");
    }

    public final static int pack(final int index, final byte... values) {
        int value = 0;

        final int remainder = Math.max(values.length - index, 0);

        switch (remainder) {
            default:
            case 4:
                value += values[index + 3] << (Byte.SIZE * 3);
            case 3:
                value += values[index + 2] << (Byte.SIZE * 2);
            case 2:
                value += values[index + 1] << Byte.SIZE;
            case 1:
                value += values[index];
            case 0:
        }

        return value;
    }

    public final static void unpack(final int index, final byte[] result, final int value) {
        final int remainder = Math.max(result.length - index, 0);

        switch (remainder) {
            default:
            case 4:
                result[index + 3] = (byte) ((value & 0xFF000000) >> (Byte.SIZE * 3));
            case 3:
                result[index + 2] = (byte) ((value & 0x00FF0000) >> (Byte.SIZE * 2));
            case 2:
                result[index + 1] = (byte) ((value & 0x0000FF00) >> Byte.SIZE);
            case 1:
                result[index] = (byte) (value & 0x000000FF);
            case 0:
        }
    }

    public final static int pack(final int index, final short... values) {
        int value = 0;

        final int remainder = Math.max(values.length - index, 0);

        switch (remainder) {
            default:
            case 2:
                value += values[index + 1] << Short.SIZE;
            case 1:
                value += values[index];
            case 0:
        }

        return value;
    }

    public final static void unpack(final int index, final short[] result, final int value) {
        final int remainder = Math.max(result.length - index, 0);

        switch (remainder) {
            default:
            case 2:
                result[index + 1] = (short) ((value & 0xFFFF0000) >> Short.SIZE);
            case 1:
                result[index] = (short) (value & 0x0000FFFF);
            case 0:
        }
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    public final int getValue() {
        return value;
    }

    public final void setValue(final int value) {
        this.value = value;
    }

    public final int get() {
        return value;
    }

    public final int getValueVolatile() {
        return UNSAFE.getIntVolatile(this, valueFieldOffset);
    }

    public final void setValueVolatile(final int value) {
        UNSAFE.putIntVolatile(this, valueFieldOffset, value);
    }

    public final void set(final int value) {
        this.value = value;
    }

    public final void setValueOrdered(
            final int value) {
        UNSAFE.putOrderedInt(this, valueFieldOffset, (value));
    }

    public final boolean compareAndSwapValue(final int expected,
                                             final int value) {
        return UNSAFE.compareAndSwapInt(this,
                valueFieldOffset,
                (expected), (value));
    }

    public final int getAndSetValue(
            final int value) {
        return UNSAFE.getAndSetInt(this,
                valueFieldOffset,
                value);
    }

    @Override
    public final boolean equals(Object other) {
        if (other instanceof MutableInt)
            return value == ((MutableInt) other).getValue();
        else if (other instanceof ImmutableInt)
            return value == ((ImmutableInt) other).getValue();
        else if (other instanceof Integer)
            return ((Integer) other).intValue() == value;
        else
            return false;
    }

    @Override
    public final int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public final int compareTo(final MutableInt other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    public final int compareTo(final ImmutableInt other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    // Others

    @Override
    public final int intValue() {
        return value;
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