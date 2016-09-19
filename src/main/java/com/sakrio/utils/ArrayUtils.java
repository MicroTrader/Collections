/*
 * _______________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and
 *     Collections Project Contributors
 *
 * Collections, is a collection of works and / or artifacts submitted
 * and / or contributed by multiple authors ("Project Contributors"),
 * collectively creating a larger work(s) and / or artifact(s) (the / this
 * "Project"). This project is licensed under the terms of either:
 *  - the Apache License, Version 2.0 (ASL 2.0), or
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
 *     Collections Project Contributors
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
 *     Collections Project Contributors
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
 *     Collections Project Contributors
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

package com.sakrio.utils;

import sun.misc.Unsafe;

import static sun.misc.Unsafe.getUnsafe;

/**
 * Created by sirinath on 19/09/2016.
 */
public class ArrayUtils {
    public static final int ARRAY_BOOLEAN_INDEX_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Unsafe.ARRAY_BOOLEAN_INDEX_SCALE) - 1;
    public static final int ARRAY_BYTE_INDEX_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Unsafe.ARRAY_BYTE_INDEX_SCALE) - 1;
    public static final int ARRAY_SHORT_INDEX_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Unsafe.ARRAY_SHORT_INDEX_SCALE) - 1;
    public static final int ARRAY_CHAR_INDEX_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Unsafe.ARRAY_CHAR_INDEX_SCALE) - 1;
    public static final int ARRAY_INT_INDEX_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Unsafe.ARRAY_INT_INDEX_SCALE) - 1;
    public static final int ARRAY_LONG_INDEX_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Unsafe.ARRAY_LONG_INDEX_SCALE) - 1;
    public static final int ARRAY_FLOAT_INDEX_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Unsafe.ARRAY_FLOAT_INDEX_SCALE) - 1;
    public static final int ARRAY_DOUBLE_INDEX_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Unsafe.ARRAY_DOUBLE_INDEX_SCALE) - 1;
    public static final int ARRAY_OBJECT_INDEX_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Unsafe.ARRAY_OBJECT_INDEX_SCALE) - 1;
    public static final int ARRAY_BOOLEAN_BASE_OFFSET = Unsafe.ARRAY_BOOLEAN_BASE_OFFSET;
    public static final int ARRAY_BYTE_BASE_OFFSET = Unsafe.ARRAY_BYTE_BASE_OFFSET;
    public static final int ARRAY_SHORT_BASE_OFFSET = Unsafe.ARRAY_SHORT_BASE_OFFSET;
    public static final int ARRAY_CHAR_BASE_OFFSET = Unsafe.ARRAY_CHAR_BASE_OFFSET;
    public static final int ARRAY_INT_BASE_OFFSET = Unsafe.ARRAY_INT_BASE_OFFSET;
    public static final int ARRAY_LONG_BASE_OFFSET = Unsafe.ARRAY_LONG_BASE_OFFSET;
    public static final int ARRAY_FLOAT_BASE_OFFSET = Unsafe.ARRAY_FLOAT_BASE_OFFSET;
    public static final int ARRAY_DOUBLE_BASE_OFFSET = Unsafe.ARRAY_DOUBLE_BASE_OFFSET;
    public static final int ARRAY_OBJECT_BASE_OFFSET = Unsafe.ARRAY_OBJECT_BASE_OFFSET;
    public static final int ARRAY_BOOLEAN_INDEX_SCALE = Unsafe.ARRAY_BOOLEAN_INDEX_SCALE;
    public static final int ARRAY_BYTE_INDEX_SCALE = Unsafe.ARRAY_BYTE_INDEX_SCALE;
    public static final int ARRAY_SHORT_INDEX_SCALE = Unsafe.ARRAY_SHORT_INDEX_SCALE;
    public static final int ARRAY_CHAR_INDEX_SCALE = Unsafe.ARRAY_CHAR_INDEX_SCALE;
    public static final int ARRAY_INT_INDEX_SCALE = Unsafe.ARRAY_INT_INDEX_SCALE;
    public static final int ARRAY_LONG_INDEX_SCALE = Unsafe.ARRAY_LONG_INDEX_SCALE;
    public static final int ARRAY_FLOAT_INDEX_SCALE = Unsafe.ARRAY_FLOAT_INDEX_SCALE;
    public static final int ARRAY_DOUBLE_INDEX_SCALE = Unsafe.ARRAY_DOUBLE_INDEX_SCALE;
    public static final int ARRAY_OBJECT_INDEX_SCALE = Unsafe.ARRAY_OBJECT_INDEX_SCALE;
    private static final Unsafe UNSAFE = getUnsafe();

    public static void copy(final boolean[] srcBase, final boolean[] destBase, final int pos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, pos, destBase, pos, length);
    }

    public static void copy(final byte[] srcBase, final byte[] destBase, final int pos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, pos, destBase, pos, length);
    }

    public static void copy(final short[] srcBase, final short[] destBase, final int pos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, pos, destBase, pos, length);
    }

    public static void copy(final char[] srcBase, final char[] destBase, final int pos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, pos, destBase, pos, length);
    }

    public static void copy(final int[] srcBase, final int[] destBase, final int pos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, pos, destBase, pos, length);
    }

    public static void copy(final float[] srcBase, final float[] destBase, final int pos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, pos, destBase, pos, length);
    }

    public static void copy(final long[] srcBase, final long[] destBase, final int pos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, pos, destBase, pos, length);
    }

    public static void copy(final double[] srcBase, final double[] destBase, final int pos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, pos, destBase, pos, length);
    }

    public static <T extends U, U> void copy(final T[] srcBase, final U[] destBase, final int pos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, pos, destBase, pos, length);
    }

    public static void copy(final boolean[] srcBase, final boolean[] destBase, final int length) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, length);
    }

    public static void copy(final byte[] srcBase, final byte[] destBase, final int length) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, length);
    }

    public static void copy(final short[] srcBase, final short[] destBase, final int length) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, length);
    }

    public static void copy(final char[] srcBase, final char[] destBase, final int length) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, length);
    }

    public static void copy(final int[] srcBase, final int[] destBase, final int length) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, length);
    }

    public static void copy(final float[] srcBase, final float[] destBase, final int length) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, length);
    }

    public static void copy(final long[] srcBase, final long[] destBase, final int length) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, length);
    }

    public static void copy(final double[] srcBase, final double[] destBase, final int length) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, length);
    }

    public static <T extends U, U> void copy(final T[] srcBase, final U[] destBase, final int length) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, length);
    }

    public static void copy(final boolean[] srcBase, final boolean[] destBase) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, Math.min(srcBase.length, destBase.length));
    }

    public static void copy(final byte[] srcBase, final byte[] destBase) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, Math.min(srcBase.length, destBase.length));
    }

    public static void copy(final short[] srcBase, final short[] destBase) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, Math.min(srcBase.length, destBase.length));
    }

    public static void copy(final char[] srcBase, final char[] destBase) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, Math.min(srcBase.length, destBase.length));
    }

    public static void copy(final int[] srcBase, final int[] destBase) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, Math.min(srcBase.length, destBase.length));
    }

    public static void copy(final float[] srcBase, final float[] destBase) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, Math.min(srcBase.length, destBase.length));
    }

    public static void copy(final long[] srcBase, final long[] destBase) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, Math.min(srcBase.length, destBase.length));
    }

    public static void copy(final double[] srcBase, final double[] destBase) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, Math.min(srcBase.length, destBase.length));
    }

    public static <T extends U, U> void copy(final T[] srcBase, final U[] destBase) {
        UnsafeUtils.arrayCopy(srcBase, 0, destBase, 0, Math.min(srcBase.length, destBase.length));
    }

    public static void copy(final boolean[] srcBase, final int srcPos, final boolean[] destBase, final int destPos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final byte[] srcBase, final int srcPos, final byte[] destBase, final int destPos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final short[] srcBase, final int srcPos, final short[] destBase, final int destPos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final char[] srcBase, final int srcPos, final char[] destBase, final int destPos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final int[] srcBase, final int srcPos, final int[] destBase, final int destPos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final float[] srcBase, final int srcPos, final float[] destBase, final int destPos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final long[] srcBase, final int srcPos, final long[] destBase, final int destPos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final double[] srcBase, final int srcPos, final double[] destBase, final int destPos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static <T extends U, U> void copy(final T[] srcBase, final int srcPos, final U[] destBase, final int destPos, final int length) {
        UnsafeUtils.arrayCopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static int lengthInBytes(final boolean[] array) {
        return ARRAY_BOOLEAN_BASE_OFFSET + array.length << ARRAY_BOOLEAN_INDEX_SHIFT;
    }

    public static int lengthInBytes(final byte[] array) {
        return ARRAY_BYTE_BASE_OFFSET + array.length << ARRAY_BYTE_INDEX_SHIFT;
    }

    public static int lengthInBytes(final short[] array) {
        return ARRAY_SHORT_BASE_OFFSET + array.length << ARRAY_SHORT_INDEX_SHIFT;
    }

    public static int lengthInBytes(final char[] array) {
        return ARRAY_CHAR_BASE_OFFSET + array.length << ARRAY_CHAR_INDEX_SHIFT;
    }

    public static int lengthInBytes(final int[] array) {
        return ARRAY_INT_BASE_OFFSET + array.length << ARRAY_INT_INDEX_SHIFT;
    }

    public static int lengthInBytes(final long[] array) {
        return ARRAY_LONG_BASE_OFFSET + array.length << ARRAY_LONG_INDEX_SHIFT;
    }

    public static int lengthInBytes(final float[] array) {
        return ARRAY_FLOAT_BASE_OFFSET + array.length << ARRAY_FLOAT_INDEX_SHIFT;
    }

    public static int lengthInBytes(final double[] array) {
        return ARRAY_DOUBLE_BASE_OFFSET + array.length << ARRAY_DOUBLE_INDEX_SHIFT;
    }

    public static <T> int lengthInBytes(final T[] array) {
        return ARRAY_OBJECT_BASE_OFFSET + array.length << ARRAY_OBJECT_INDEX_SHIFT;
    }

    public static int indexInBytes(final boolean[] array, final int index) {
        return index << ARRAY_BOOLEAN_INDEX_SHIFT;
    }

    public static int indexInBytes(final byte[] array, final int index) {
        return index << ARRAY_BYTE_INDEX_SHIFT;
    }

    public static int indexInBytes(final short[] array, final int index) {
        return index << ARRAY_SHORT_INDEX_SHIFT;
    }

    public static int indexInBytes(final char[] array, final int index) {
        return index << ARRAY_CHAR_INDEX_SHIFT;
    }

    public static int indexInBytes(final int[] array, final int index) {
        return index << ARRAY_INT_INDEX_SHIFT;
    }

    public static int indexInBytes(final long[] array, final int index) {
        return index << ARRAY_LONG_INDEX_SHIFT;
    }

    public static int indexInBytes(final float[] array, final int index) {
        return index << ARRAY_FLOAT_INDEX_SHIFT;
    }

    public static int indexInBytes(final double[] array, final int index) {
        return index << ARRAY_DOUBLE_INDEX_SHIFT;
    }

    public static <T> int indexInBytes(final T[] array, final int index) {
        return index << ARRAY_OBJECT_INDEX_SHIFT;
    }

    public static int getIndexOffset(final boolean[] array, final int index) {
        return ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT;
    }

    public static int getIndexOffset(final byte[] array, final int index) {
        return ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT;
    }

    public static int getIndexOffset(final short[] array, final int index) {
        return ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT;
    }

    public static int getIndexOffset(final char[] array, final int index) {
        return ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT;
    }

    public static int getIndexOffset(final int[] array, final int index) {
        return ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT;
    }

    public static int getIndexOffset(final long[] array, final int index) {
        return ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT;
    }

    public static int getIndexOffset(final float[] array, final int index) {
        return ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT;
    }

    public static int getIndexOffset(final double[] array, final int index) {
        return ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT;
    }

    public static <T> int getIndexOffset(final T[] array, final int index) {
        return ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT;
    }

    public static void put(final boolean[] array, final int index, final boolean value) {
        UnsafeUtils.putBoolean(array, getIndexOffset(array, index), value);
    }

    public static void put(final byte[] array, final int index, final byte value) {
        UnsafeUtils.putByte(array, getIndexOffset(array, index), value);
    }

    public static void put(final short[] array, final int index, final short value) {
        UnsafeUtils.putShort(array, getIndexOffset(array, index), value);
    }

    public static void put(final char[] array, final int index, final char value) {
        UnsafeUtils.putChar(array, getIndexOffset(array, index), value);
    }

    public static void put(final int[] array, final int index, final int value) {
        UnsafeUtils.putInt(array, getIndexOffset(array, index), value);
    }

    public static void put(final float[] array, final int index, final float value) {
        UnsafeUtils.putFloat(array, getIndexOffset(array, index), value);
    }

    public static void put(final long[] array, final int index, final long value) {
        UnsafeUtils.putLong(array, getIndexOffset(array, index), value);
    }

    public static void put(final double[] array, final int index, final double value) {
        UnsafeUtils.putDouble(array, getIndexOffset(array, index), value);
    }

    public static <T, U extends T> void put(final T[] array, final int index, final U value) {
        UnsafeUtils.putObject(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final boolean[] array, final int index, final boolean value) {
        UnsafeUtils.putBooleanVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final byte[] array, final int index, final byte value) {
        UnsafeUtils.putByteVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final short[] array, final int index, final short value) {
        UnsafeUtils.putShortVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final char[] array, final int index, final char value) {
        UnsafeUtils.putCharVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final int[] array, final int index, final int value) {
        UnsafeUtils.putIntVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final float[] array, final int index, final float value) {
        UnsafeUtils.putFloatVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final long[] array, final int index, final long value) {
        UnsafeUtils.putLongVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final double[] array, final int index, final double value) {
        UnsafeUtils.putDoubleVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putOrdered(final int[] array, final int index, final int value) {
        UnsafeUtils.putOrderedInt(array, getIndexOffset(array, index), value);
    }

    public static void putOrdered(final float[] array, final int index, final float value) {
        UnsafeUtils.putOrderedInt(array, getIndexOffset(array, index), Float.floatToRawIntBits(value));
    }

    public static void putOrdered(final long[] array, final int index, final long value) {
        UnsafeUtils.putOrderedLong(array, getIndexOffset(array, index), value);
    }

    public static void putOrdered(final double[] array, final int index, final double value) {
        UnsafeUtils.putOrderedLong(array, getIndexOffset(array, index), Double.doubleToRawLongBits(value));
    }

    public static <T, U extends T> void putOrdered(final T[] array, final int index, final U value) {
        UnsafeUtils.putOrderedObject(array, getIndexOffset(array, index), value);
    }

    public static boolean getBoolean(final boolean[] array, final int index) {
        return UnsafeUtils.getBoolean(array, (long) getIndexOffset(array, index));
    }

    public static byte getByte(final byte[] array, final int index) {
        return UnsafeUtils.getByte(array, (long) getIndexOffset(array, index));
    }

    public static short getShort(final short[] array, final int index) {
        return UnsafeUtils.getShort(array, (long) getIndexOffset(array, index));
    }

    public static char getChar(final char[] array, final int index) {
        return UnsafeUtils.getChar(array, (long) getIndexOffset(array, index));
    }

    public static int getInt(final int[] array, final int index) {
        return UnsafeUtils.getInt(array, (long) getIndexOffset(array, index));
    }

    public static float getFloat(final float[] array, final int index) {
        return UnsafeUtils.getFloat(array, (long) getIndexOffset(array, index));
    }

    public static long getLong(final long[] array, final int index) {
        return UnsafeUtils.getLong(array, (long) getIndexOffset(array, index));
    }

    public static double getDouble(final double[] array, final int index) {
        return UnsafeUtils.getDouble(array, (long) getIndexOffset(array, index));
    }

    public static <T, U extends T> U getObject(final T[] array, final int index) {
        return (U) UnsafeUtils.getObject(array, (long) getIndexOffset(array, index));
    }

    public static boolean get(final boolean[] array, final int index) {
        return UnsafeUtils.getBoolean(array, getIndexOffset(array, index));
    }

    public static byte get(final byte[] array, final int index) {
        return UnsafeUtils.getByte(array, getIndexOffset(array, index));
    }

    public static short get(final short[] array, final int index) {
        return UnsafeUtils.getShort(array, getIndexOffset(array, index));
    }

    public static char get(final char[] array, final int index) {
        return UnsafeUtils.getChar(array, getIndexOffset(array, index));
    }

    public static int get(final int[] array, final int index) {
        return UnsafeUtils.getInt(array, getIndexOffset(array, index));
    }

    public static float get(final float[] array, final int index) {
        return UnsafeUtils.getFloat(array, getIndexOffset(array, index));
    }

    public static long get(final long[] array, final int index) {
        return UnsafeUtils.getLong(array, getIndexOffset(array, index));
    }

    public static double get(final double[] array, final int index) {
        return UnsafeUtils.getDouble(array, getIndexOffset(array, index));
    }

    public static <T, U extends T> U get(final T[] array, final int index) {
        return (U) UnsafeUtils.getObject(array, getIndexOffset(array, index));
    }

    public static boolean getVolatile(final boolean[] array, final int index) {
        return UnsafeUtils.getBooleanVolatile(array, getIndexOffset(array, index));
    }

    public static byte getVolatile(final byte[] array, final int index) {
        return UnsafeUtils.getByteVolatile(array, getIndexOffset(array, index));
    }

    public static short getVolatile(final short[] array, final int index) {
        return UnsafeUtils.getShortVolatile(array, getIndexOffset(array, index));
    }

    public static char getVolatile(final char[] array, final int index) {
        return UnsafeUtils.getCharVolatile(array, getIndexOffset(array, index));
    }

    public static int getVolatile(final int[] array, final int index) {
        return UnsafeUtils.getIntVolatile(array, getIndexOffset(array, index));
    }

    public static float getVolatile(final float[] array, final int index) {
        return UnsafeUtils.getFloatVolatile(array, getIndexOffset(array, index));
    }

    public static long getVolatile(final long[] array, final int index) {
        return UnsafeUtils.getLongVolatile(array, getIndexOffset(array, index));
    }

    public static double getVolatile(final double[] array, final int index) {
        return UnsafeUtils.getDoubleVolatile(array, getIndexOffset(array, index));
    }

    public static <T, U extends T> U getVolatile(final T[] array, final int index) {
        return (U) UnsafeUtils.getObjectVolatile(array, (long) getIndexOffset(array, index));
    }

    public static boolean CAS(final int[] array, final long index, final int expected, final int value) {
        return UnsafeUtils.compareAndSwapInt(array, index, expected, value);
    }

    public static boolean CAS(final float[] array, final long index, final float expected, final float value) {
        return UnsafeUtils.compareAndSwapFloat(array, index, expected, value);
    }

    public static boolean CAS(final long[] array, final long index, final long expected, final long value) {
        return UnsafeUtils.compareAndSwapLong(array, index, expected, value);
    }

    public static boolean CAS(final double[] array, final long index, final double expected, final double value) {
        return UnsafeUtils.compareAndSwapDouble(array, index, expected, value);
    }

    public static <T, U extends T, V extends T> boolean CAS(final T[] array, final long index, final U expected, final V value) {
        return UnsafeUtils.compareAndSwapObject(array, index, expected, value);
    }

    public static int getAndSet(final int[] array, final long index, final int value) {
        return UnsafeUtils.getAndSetInt(array, index, value);
    }

    public static long getAndSet(final long[] array, final long index, final long value) {
        return UnsafeUtils.getAndSetLong(array, index, value);
    }

    public static <S, T extends S, U extends S> T getAndSet(final S[] array, final long index, final U value) {
        return (T) UnsafeUtils.getAndSetObject(array, index, value);
    }

    public static float getAndSet(final float[] array, final long index, final float value) {
        return UnsafeUtils.getAndSetFloat(array, index, value);
    }

    public static double getAndSet(final double[] array, final long index, final double value) {
        return UnsafeUtils.getAndSetDouble(array, index, value);
    }

    public static int getAndAdd(final int[] array, final long index, final int value) {
        return UnsafeUtils.getAndAddInt(array, index, value);
    }

    public static long getAndAdd(final long[] array, final long index, final long value) {
        return UnsafeUtils.getAndAddLong(array, index, value);
    }
}
