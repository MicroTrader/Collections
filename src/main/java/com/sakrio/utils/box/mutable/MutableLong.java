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
import com.sakrio.utils.box.immutable.ImmutableLong;
import sun.misc.Unsafe;

/**
 * Wrapper class
 *
 * @author sirinath
 */
@SuppressWarnings("serial")
public final class MutableLong extends Number
        implements BoxOnce<MutableLong> {
    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(MutableLong.class, "value");
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;
    /**
     * Value
     */
    private long value;

    /**
     * @param i Parameter
     */
    public MutableLong(final long i) {
        value = i;
    }

    public final static long pack(final byte... values) {
        long value = 0;

        switch (values.length) {
            default:
            case 8:
                value += values[7] << (Byte.SIZE * 7);
            case 7:
                value += values[6] << (Byte.SIZE * 6);
            case 6:
                value += values[5] << (Byte.SIZE * 5);
            case 5:
                value += values[4] << (Byte.SIZE * 4);
            case 4:
                value += values[3] << (Byte.SIZE * 3);
            case 3:
                value += values[2] << (Byte.SIZE * 2);
            case 2:
                value += values[1] << Byte.SIZE;
            case 1:
                value += values[0];
            case 0:
        }

        return value;
    }

    public final static void unpack(final byte[] result, final long value) {
        switch (result.length) {
            default:
            case 8:
                result[7] = (byte) ((value & 0xFF00000000000000L) >> (Byte.SIZE * 7));
            case 7:
                result[6] = (byte) ((value & 0x00FF000000000000L) >> (Byte.SIZE * 6));
            case 6:
                result[5] = (byte) ((value & 0x0000FF0000000000L) >> (Byte.SIZE * 5));
            case 5:
                result[4] = (byte) ((value & 0x000000FF00000000L) >> (Byte.SIZE * 4));
            case 4:
                result[3] = (byte) ((value & 0x00000000FF000000L) >> (Byte.SIZE * 3));
            case 3:
                result[2] = (byte) ((value & 0x0000000000FF0000L) >> (Byte.SIZE * 2));
            case 2:
                result[1] = (byte) ((value & 0x000000000000FF00L) >> Byte.SIZE);
            case 1:
                result[0] = (byte) (value & 0x00000000000000FFL);
            case 0:
        }
    }

    public final static long pack(final short... values) {
        int value = 0;

        switch (values.length) {
            default:
            case 4:
                value += values[3] << (Short.SIZE * 3);
            case 3:
                value += values[2] << (Short.SIZE * 2);
            case 2:
                value += values[1] << Short.SIZE;
            case 1:
                value += values[0];
            case 0:
        }

        return value;
    }

    public final static void unpack(final short[] result, final long value) {
        switch (result.length) {
            default:
            case 4:
                result[3] = (short) ((value & 0xFFFF000000000000L) >> (Short.SIZE * 3));
            case 3:
                result[2] = (short) ((value & 0x0000FFFF00000000L) >> (Short.SIZE * 2));
            case 2:
                result[1] = (short) ((value & 0x00000000FFFF0000L) >> Short.SIZE);
            case 1:
                result[0] = (short) (value & 0x000000000000FFFFL);
            case 0:
        }
    }

    public final static long pack(final int... values) {
        long value = 0;

        switch (values.length) {
            default:
            case 2:
                value += values[1] << Integer.SIZE;
            case 1:
                value += values[0];
            case 0:
        }

        return value;
    }

    public final static void unpack(final int[] result, final long value) {
        switch (result.length) {
            default:
            case 2:
                result[1] = (int) ((value & 0xFFFFFFFF00000000L) >> Integer.SIZE);
            case 1:
                result[0] = (int) (value & 0x00000000FFFFFFFFL);
            case 0:
        }
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    public final long getValue() {
        return value;
    }

    public final void setValue(final long value) {
        this.value = value;
    }

    public final long get() {
        return value;
    }

    public final long getValueVolatile() {
        return UNSAFE.getLongVolatile(this, valueFieldOffset);
    }

    public final void setValueVolatile(final long value) {
        UNSAFE.putLongVolatile(this, valueFieldOffset, value);
    }

    public final void set(final long value) {
        this.value = value;
    }

    public final void setValueOrdered(
            final long value) {
        UNSAFE.putOrderedLong(this, valueFieldOffset, (value));
    }

    public final boolean compareAndSwapValue(final long expected,
                                             final long value) {
        return UNSAFE.compareAndSwapLong(this,
                valueFieldOffset,
                (expected), (value));
    }

    public final long getAndSetValue(
            final long value) {
        return UNSAFE.getAndSetLong(this,
                valueFieldOffset,
                value);
    }

    @Override
    public final boolean equals(Object other) {
        if (other instanceof MutableLong)
            return value == ((MutableLong) other).getValue();
        else if (other instanceof ImmutableLong)
            return value == ((ImmutableLong) other).getValue();
        else if (other instanceof Long)
            return ((Long) other).longValue() == value;
        else
            return false;
    }

    @Override
    public final int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public final int compareTo(final MutableLong other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    public final int compareTo(final ImmutableLong other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    // Others

    @Override
    public final int intValue() {
        return (int) value;
    }

    @Override
    public final long longValue() {
        return value;
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