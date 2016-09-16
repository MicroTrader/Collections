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

import sun.misc.Cleaner;
import sun.misc.Unsafe;
import sun.nio.ch.DirectBuffer;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

import static java.lang.System.arraycopy;

/**
 * Created by sirin_000 on 03/10/2015.
 */
public class UnsafeUtils {
    public static final int CACHE_PADDING = 17;

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
    public static final int PAGE_SIZE = UNSAFE.pageSize();

    private static final Unsafe getUnsafe() {
        Unsafe unsafe = null;

        try {
            final PrivilegedExceptionAction<Unsafe> action = () -> {
                final Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);

                return (Unsafe) f.get(null);
            };

            unsafe = AccessController.doPrivileged(action);
        } catch (final Throwable t) {
            throw new RuntimeException("Exception accessing Unsafe", t);
        }

        return unsafe;
    }

    public static Cleaner cleaner(final Object object, final Runnable runnable) {
        return Cleaner.create(object, runnable);
    }

    public static long bufferAddress(final Buffer buffer) {
        return ((DirectBuffer) buffer).address();
    }

    public static void fullFence() {
        UNSAFE.fullFence();
    }

    public static void loadFence() {
        UNSAFE.loadFence();
    }

    public static void storeFence() {
        UNSAFE.storeFence();
    }

    public static long allocate(long bytes) {
        return allocateMemory(bytes);
    }

    public static long allocate(long address, long bytes) {
        return reallocate(address, bytes);
    }

    public static long allocateMemory(long bytes) {
        return UNSAFE.allocateMemory(bytes);
    }

    public static long reallocate(long address, long bytes) {
        return reallocateMemory(address, bytes);
    }

    public static long reallocateMemory(long address, long bytes) {
        return UNSAFE.reallocateMemory(address, bytes);
    }

    public static void freeMemory(long address) {
        free(address);
    }

    public static void deallocate(long address) {
        free(address);
    }

    public static void free(long address) {
        UNSAFE.freeMemory(address);
    }

    public static void rethrow(final Throwable throwable) {
        rethrowUnchecked(throwable);
    }

    private static <T extends Throwable> void rethrowUnchecked(final Throwable throwable) throws T {
        throw (T) throwable;
    }

    public static void rethrowUnsafe(final Throwable throwable) {
        UNSAFE.throwException(throwable);
    }

    public static long getObjectFieldOffset(final Field field) {
        return UNSAFE.objectFieldOffset(field);
    }

    public static long getObjectFieldOffset(final Class<?> cls, final String field) {
        try {
            return getObjectFieldOffset(cls.getField(field));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Could not access the field offset", e);
        }
    }

    public static long getStaticFieldOffset(final Field field) {
        return UNSAFE.staticFieldOffset(field);
    }

    public static long getStaticFieldOffset(final Class<?> cls, final String field) {
        try {
            return getStaticFieldOffset(cls.getField(field));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Could not access the field offset", e);
        }
    }

    public static Object getStaticFieldBase(final Field field) {
        return UNSAFE.staticFieldBase(field);
    }

    public static int arrayBaseOffset(final Class<?> arrayClass) {
        return UNSAFE.arrayBaseOffset(arrayClass);
    }

    public static int arrayIndexScale(final Class<?> arrayClass) {
        return UNSAFE.arrayIndexScale(arrayClass);
    }

    public static void putPointer(final long address, final long pointer) {
        UNSAFE.putAddress(address, pointer);
    }

    public static long getPointer(final long address) {
        return UNSAFE.getAddress(address);
    }

    public static void copyMemory(final long srcAddress, final long destAddress, final long bytes) {
        UNSAFE.copyMemory(srcAddress, destAddress, bytes);
    }

    public static void copyMemory(final Object srcBase, final long srcOffset, final Object destBase, final long destOffset, final long bytes) {
        UNSAFE.copyMemory(srcBase, srcOffset, destBase, destOffset, bytes);
    }

    public static void copy(final long srcAddress, final long destAddress, final long bytes) {
        copyMemory(srcAddress, destAddress, bytes);
    }

    public static void arrayCopy(final Object srcBase, final int srcPos, final Object destBase, final int destPos, final int length) {
        arraycopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final boolean[] srcBase, final int srcPos, final boolean[] destBase, final int destPos, final int length) {
        arraycopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final byte[] srcBase, final int srcPos, final byte[] destBase, final int destPos, final int length) {
        arraycopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final short[] srcBase, final int srcPos, final short[] destBase, final int destPos, final int length) {
        arraycopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final char[] srcBase, final int srcPos, final char[] destBase, final int destPos, final int length) {
        arraycopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final int[] srcBase, final int srcPos, final int[] destBase, final int destPos, final int length) {
        arraycopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final float[] srcBase, final int srcPos, final float[] destBase, final int destPos, final int length) {
        arraycopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final long[] srcBase, final int srcPos, final long[] destBase, final int destPos, final int length) {
        arraycopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static void copy(final double[] srcBase, final int srcPos, final double[] destBase, final int destPos, final int length) {
        arraycopy(srcBase, srcPos, destBase, destPos, length);
    }

    public static <T extends U, U> void copy(final T[] srcBase, final int srcPos, final U[] destBase, final int destPos, final int length) {
        arraycopy(srcBase, srcPos, destBase, destPos, length);
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
        putBoolean(array, getIndexOffset(array, index), value);
    }

    public static void put(final byte[] array, final int index, final byte value) {
        putByte(array, getIndexOffset(array, index), value);
    }

    public static void put(final short[] array, final int index, final short value) {
        putShort(array, getIndexOffset(array, index), value);
    }

    public static void put(final char[] array, final int index, final char value) {
        putChar(array, getIndexOffset(array, index), value);
    }

    public static void put(final int[] array, final int index, final int value) {
        putInt(array, getIndexOffset(array, index), value);
    }

    public static void put(final float[] array, final int index, final float value) {
        putFloat(array, getIndexOffset(array, index), value);
    }

    public static void put(final long[] array, final int index, final long value) {
        putLong(array, getIndexOffset(array, index), value);
    }

    public static void put(final double[] array, final int index, final double value) {
        putDouble(array, getIndexOffset(array, index), value);
    }

    public static <T, U extends T> void put(final T[] array, final int index, final U value) {
        putObject(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final boolean[] array, final int index, final boolean value) {
        putBooleanVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final byte[] array, final int index, final byte value) {
        putByteVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final short[] array, final int index, final short value) {
        putShortVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final char[] array, final int index, final char value) {
        putCharVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final int[] array, final int index, final int value) {
        putIntVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final float[] array, final int index, final float value) {
        putFloatVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final long[] array, final int index, final long value) {
        putLongVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putVolatile(final double[] array, final int index, final double value) {
        putDoubleVolatile(array, getIndexOffset(array, index), value);
    }

    public static void putOrdered(final int[] array, final int index, final int value) {
        putOrderedInt(array, getIndexOffset(array, index), value);
    }

    public static void putOrdered(final float[] array, final int index, final float value) {
        putOrderedInt(array, getIndexOffset(array, index), Float.floatToRawIntBits(value));
    }

    public static void putOrdered(final long[] array, final int index, final long value) {
        putOrderedLong(array, getIndexOffset(array, index), value);
    }

    public static void putOrdered(final double[] array, final int index, final double value) {
        putOrderedLong(array, getIndexOffset(array, index), Double.doubleToRawLongBits(value));
    }

    public static <T, U extends T> void putOrdered(final T[] array, final int index, final U value) {
        putOrderedObject(array, getIndexOffset(array, index), value);
    }

    public static <T> void putBoolean(final T object, final long fieldOffset, final boolean value) {
        UNSAFE.putBoolean(object, fieldOffset, value);
    }

    public static <T> void putByte(final T object, final long fieldOffset, final byte value) {
        UNSAFE.putByte(object, fieldOffset, value);
    }

    public static <T> void putShort(final T object, final long fieldOffset, final short value) {
        UNSAFE.putShort(object, fieldOffset, value);
    }

    public static <T> void putChar(final T object, final long fieldOffset, final char value) {
        UNSAFE.putChar(object, fieldOffset, value);
    }

    public static <T> void putInt(final T object, final long fieldOffset, final int value) {
        UNSAFE.putInt(object, fieldOffset, value);
    }

    public static <T> void putFloat(final T object, final long fieldOffset, final float value) {
        UNSAFE.putFloat(object, fieldOffset, value);
    }

    public static <T> void putLong(final T object, final long fieldOffset, final long value) {
        UNSAFE.putLong(object, fieldOffset, value);
    }

    public static <T> void putDouble(final T object, final long fieldOffset, final double value) {
        UNSAFE.putDouble(object, fieldOffset, value);
    }

    public static <T, U> void putObject(final T object, final long fieldOffset, final U value) {
        UNSAFE.putObject(object, fieldOffset, value);
    }

    public static <T> void put(final T object, final long fieldOffset, final boolean value) {
        putBoolean(object, fieldOffset, value);
    }

    public static <T> void put(final T object, final long fieldOffset, final byte value) {
        putByte(object, fieldOffset, value);
    }

    public static <T> void put(final T object, final long fieldOffset, final short value) {
        putShort(object, fieldOffset, value);
    }

    public static <T> void put(final T object, final long fieldOffset, final char value) {
        putChar(object, fieldOffset, value);
    }

    public static <T> void put(final T object, final long fieldOffset, final int value) {
        putInt(object, fieldOffset, value);
    }

    public static <T> void put(final T object, final long fieldOffset, final float value) {
        putFloat(object, fieldOffset, value);
    }

    public static <T> void put(final T object, final long fieldOffset, final long value) {
        putLong(object, fieldOffset, value);
    }

    public static <T> void put(final T object, final long fieldOffset, final double value) {
        putDouble(object, fieldOffset, value);
    }

    public static <T, U> void put(final T object, final long fieldOffset, final U value) {
        putObject(object, fieldOffset, value);
    }

    public static <T> void putBooleanVolatile(final T object, final long fieldOffset, final boolean value) {
        UNSAFE.putBooleanVolatile(object, fieldOffset, value);
    }

    public static <T> void putByteVolatile(final T object, final long fieldOffset, final byte value) {
        UNSAFE.putByteVolatile(object, fieldOffset, value);
    }

    public static <T> void putShortVolatile(final T object, final long fieldOffset, final short value) {
        UNSAFE.putShortVolatile(object, fieldOffset, value);
    }

    public static <T> void putCharVolatile(final T object, final long fieldOffset, final char value) {
        UNSAFE.putCharVolatile(object, fieldOffset, value);
    }

    public static <T> void putIntVolatile(final T object, final long fieldOffset, final int value) {
        UNSAFE.putIntVolatile(object, fieldOffset, value);
    }

    public static <T> void putFloatVolatile(final T object, final long fieldOffset, final float value) {
        UNSAFE.putFloatVolatile(object, fieldOffset, value);
    }

    public static <T> void putLongVolatile(final T object, final long fieldOffset, final long value) {
        UNSAFE.putLongVolatile(object, fieldOffset, value);
    }

    public static <T> void putDoubleVolatile(final T object, final long fieldOffset, final double value) {
        UNSAFE.putDoubleVolatile(object, fieldOffset, value);
    }

    public static <T, U> void putObjectVolatile(final T object, final long fieldOffset, final U value) {
        UNSAFE.putObjectVolatile(object, fieldOffset, value);
    }

    public static <T> void putVolatile(final T object, final long fieldOffset, final boolean value) {
        putBooleanVolatile(object, fieldOffset, value);
    }

    public static <T> void putVolatile(final T object, final long fieldOffset, final byte value) {
        putByteVolatile(object, fieldOffset, value);
    }

    public static <T> void putVolatile(final T object, final long fieldOffset, final short value) {
        putShortVolatile(object, fieldOffset, value);
    }

    public static <T> void putVolatile(final T object, final long fieldOffset, final char value) {
        putCharVolatile(object, fieldOffset, value);
    }

    public static <T> void putVolatile(final T object, final long fieldOffset, final int value) {
        putIntVolatile(object, fieldOffset, value);
    }

    public static <T> void putVolatile(final T object, final long fieldOffset, final float value) {
        putFloatVolatile(object, fieldOffset, value);
    }

    public static <T> void putVolatile(final T object, final long fieldOffset, final long value) {
        putLongVolatile(object, fieldOffset, value);
    }

    public static <T> void putVolatile(final T object, final long fieldOffset, final double value) {
        putDoubleVolatile(object, fieldOffset, value);
    }

    public static <T, U> void putVolatile(final T object, final long fieldOffset, final U value) {
        putObjectVolatile(object, fieldOffset, value);
    }

    public static <T> void putOrderedInt(final T object, final long fieldOffset, final int value) {
        UNSAFE.putOrderedInt(object, fieldOffset, value);
    }

    public static <T> void putOrderedLong(final T object, final long fieldOffset, final long value) {
        UNSAFE.putOrderedLong(object, fieldOffset, value);
    }

    public static <T, U extends T> void putOrderedObject(final T object, final long fieldOffset, final U value) {
        UNSAFE.putOrderedObject(object, fieldOffset, value);
    }

    public static <T> void putOrderedFloat(final T object, final long fieldOffset, final float value) {
        putOrderedInt(object, fieldOffset, Float.floatToRawIntBits(value));
    }

    public static <T> void putOrderedDouble(final T object, final long fieldOffset, final double value) {
        putOrderedLong(object, fieldOffset, Double.doubleToRawLongBits(value));
    }

    public static <T> void putOrdered(final T object, final long fieldOffset, final int value) {
        putOrderedInt(object, fieldOffset, value);
    }

    public static <T> void putOrdered(final T object, final long fieldOffset, final float value) {
        putOrderedInt(object, fieldOffset, Float.floatToRawIntBits(value));
    }

    public static <T> void putOrdered(final T object, final long fieldOffset, final long value) {
        putOrderedLong(object, fieldOffset, value);
    }

    public static <T> void putOrdered(final T object, final long fieldOffset, final double value) {
        putOrderedLong(object, fieldOffset, Double.doubleToRawLongBits(value));
    }

    public static <T, U extends T> void putOrdered(final T object, final long fieldOffset, final U value) {
        putOrderedObject(object, fieldOffset, value);
    }

    public static boolean getBoolean(final boolean[] array, final int index) {
        return UNSAFE.getBoolean(array, (long) getIndexOffset(array, index));
    }

    public static byte getByte(final byte[] array, final int index) {
        return UNSAFE.getByte(array, (long) getIndexOffset(array, index));
    }

    public static short getShort(final short[] array, final int index) {
        return UNSAFE.getShort(array, (long) getIndexOffset(array, index));
    }

    public static char getChar(final char[] array, final int index) {
        return UNSAFE.getChar(array, (long) getIndexOffset(array, index));
    }

    public static int getInt(final int[] array, final int index) {
        return UNSAFE.getInt(array, (long) getIndexOffset(array, index));
    }

    public static float getFloat(final float[] array, final int index) {
        return UNSAFE.getFloat(array, (long) getIndexOffset(array, index));
    }

    public static long getLong(final long[] array, final int index) {
        return UNSAFE.getLong(array, (long) getIndexOffset(array, index));
    }

    public static double getDouble(final double[] array, final int index) {
        return UNSAFE.getDouble(array, (long) getIndexOffset(array, index));
    }

    public static <T, U extends T> U getObject(final T[] array, final int index) {
        return (U) UNSAFE.getObject(array, (long) getIndexOffset(array, index));
    }

    public static boolean get(final boolean[] array, final int index) {
        return getBoolean(array, getIndexOffset(array, index));
    }

    public static byte get(final byte[] array, final int index) {
        return getByte(array, getIndexOffset(array, index));
    }

    public static short get(final short[] array, final int index) {
        return getShort(array, getIndexOffset(array, index));
    }

    public static char get(final char[] array, final int index) {
        return getChar(array, getIndexOffset(array, index));
    }

    public static int get(final int[] array, final int index) {
        return getInt(array, getIndexOffset(array, index));
    }

    public static float get(final float[] array, final int index) {
        return getFloat(array, getIndexOffset(array, index));
    }

    public static long get(final long[] array, final int index) {
        return getLong(array, getIndexOffset(array, index));
    }

    public static double get(final double[] array, final int index) {
        return getDouble(array, getIndexOffset(array, index));
    }

    public static <T, U extends T> U get(final T[] array, final int index) {
        return (U) getObject(array, getIndexOffset(array, index));
    }

    public static boolean getVolatile(final boolean[] array, final int index) {
        return getBooleanVolatile(array, getIndexOffset(array, index));
    }

    public static byte getVolatile(final byte[] array, final int index) {
        return getByteVolatile(array, getIndexOffset(array, index));
    }

    public static short getVolatile(final short[] array, final int index) {
        return getShortVolatile(array, getIndexOffset(array, index));
    }

    public static char getVolatile(final char[] array, final int index) {
        return getCharVolatile(array, getIndexOffset(array, index));
    }

    public static int getVolatile(final int[] array, final int index) {
        return getIntVolatile(array, getIndexOffset(array, index));
    }

    public static float getVolatile(final float[] array, final int index) {
        return getFloatVolatile(array, getIndexOffset(array, index));
    }

    public static long getVolatile(final long[] array, final int index) {
        return getLongVolatile(array, getIndexOffset(array, index));
    }

    public static double getVolatile(final double[] array, final int index) {
        return getDoubleVolatile(array, getIndexOffset(array, index));
    }

    public static <T, U extends T> U getVolatile(final T[] array, final int index) {
        return (U) getObjectVolatile(array, (long) getIndexOffset(array, index));
    }

    public static <T> boolean getBoolean(final T object, final long fieldOffset) {
        return UNSAFE.getBoolean(object, fieldOffset);
    }

    public static <T> byte getByte(final T object, final long fieldOffset) {
        return UNSAFE.getByte(object, fieldOffset);
    }

    public static <T> short getShort(final T object, final long fieldOffset) {
        return UNSAFE.getShort(object, fieldOffset);
    }

    public static <T> char getChar(final T object, final long fieldOffset) {
        return UNSAFE.getChar(object, fieldOffset);
    }

    public static <T> int getInt(final T object, final long fieldOffset) {
        return UNSAFE.getInt(object, fieldOffset);
    }

    public static <T> float getFloat(final T object, final long fieldOffset) {
        return UNSAFE.getFloat(object, fieldOffset);
    }

    public static <T> long getLong(final T object, final long fieldOffset) {
        return UNSAFE.getLong(object, fieldOffset);
    }

    public static <T> double getDouble(final T object, final long fieldOffset) {
        return UNSAFE.getDouble(object, fieldOffset);
    }

    public static <T, U> U getObject(final T object, final long fieldOffset) {
        return (U) UNSAFE.getObject(object, fieldOffset);
    }

    public static <T> boolean getBooleanVolatile(final T object, final long fieldOffset) {
        return UNSAFE.getBooleanVolatile(object, fieldOffset);
    }

    public static <T> byte getByteVolatile(final T object, final long fieldOffset) {
        return UNSAFE.getByteVolatile(object, fieldOffset);
    }

    public static <T> short getShortVolatile(final T object, final long fieldOffset) {
        return UNSAFE.getShortVolatile(object, fieldOffset);
    }

    public static <T> char getCharVolatile(final T object, final long fieldOffset) {
        return UNSAFE.getCharVolatile(object, fieldOffset);
    }

    public static <T> int getIntVolatile(final T object, final long fieldOffset) {
        return UNSAFE.getIntVolatile(object, fieldOffset);
    }

    public static <T> float getFloatVolatile(final T object, final long fieldOffset) {
        return UNSAFE.getFloatVolatile(object, fieldOffset);
    }

    public static <T> long getLongVolatile(final T object, final long fieldOffset) {
        return UNSAFE.getLongVolatile(object, fieldOffset);
    }

    public static <T> double getDoubleVolatile(final T object, final long fieldOffset) {
        return UNSAFE.getDoubleVolatile(object, fieldOffset);
    }

    public static <T, U> U getObjectVolatile(final T object, final long fieldOffset) {
        return (U) UNSAFE.getObjectVolatile(object, fieldOffset);
    }

    public static <T> boolean compareAndSwapInt(final T object, final long fieldOffset, final int expected, final int value) {
        return UNSAFE.compareAndSwapInt(object, fieldOffset, expected, value);
    }

    public static <T> boolean compareAndSwapLong(final T object, final long fieldOffset, final long expected, final long value) {
        return UNSAFE.compareAndSwapLong(object, fieldOffset, expected, value);
    }

    public static <T, U, V> boolean compareAndSwapObject(final T object, final long fieldOffset, final U expected, final V value) {
        return UNSAFE.compareAndSwapObject(object, fieldOffset, expected, value);
    }

    public static <T> boolean compareAndSwapFloat(final T object, final long fieldOffset, final float expected, final float value) {
        return compareAndSwapInt(object, fieldOffset, Float.floatToRawIntBits(expected), Float.floatToRawIntBits(value));
    }

    public static <T> boolean compareAndSwapDouble(final T object, final long fieldOffset, final double expected, final double value) {
        return compareAndSwapLong(object, fieldOffset, Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value));
    }

    public static <T> boolean CAS(final T object, final long fieldOffset, final int expected, final int value) {
        return compareAndSwapInt(object, fieldOffset, expected, value);
    }

    public static <T> boolean CAS(final T object, final long fieldOffset, final float expected, final float value) {
        return compareAndSwapFloat(object, fieldOffset, expected, value);
    }

    public static <T> boolean CAS(final T object, final long fieldOffset, final long expected, final long value) {
        return compareAndSwapLong(object, fieldOffset, expected, value);
    }

    public static <T> boolean CAS(final T object, final long fieldOffset, final double expected, final double value) {
        return compareAndSwapDouble(object, fieldOffset, expected, value);
    }

    public static <T, U, V> boolean CAS(final T object, final long fieldOffset, final U expected, final V value) {
        return compareAndSwapObject(object, fieldOffset, expected, value);
    }

    public static boolean CAS(final int[] array, final long index, final int expected, final int value) {
        return compareAndSwapInt(array, index, expected, value);
    }

    public static boolean CAS(final float[] array, final long index, final float expected, final float value) {
        return compareAndSwapFloat(array, index, expected, value);
    }

    public static boolean CAS(final long[] array, final long index, final long expected, final long value) {
        return compareAndSwapLong(array, index, expected, value);
    }

    public static boolean CAS(final double[] array, final long index, final double expected, final double value) {
        return compareAndSwapDouble(array, index, expected, value);
    }

    public static <T, U extends T, V extends T> boolean CAS(final T[] array, final long index, final U expected, final V value) {
        return compareAndSwapObject(array, index, expected, value);
    }

    public static <T> int getAndSetInt(final T object, final long fieldOffset, final int value) {
        return UNSAFE.getAndSetInt(object, fieldOffset, value);
    }

    public static <T> long getAndSetLong(final T object, final long fieldOffset, final long value) {
        return UNSAFE.getAndSetLong(object, fieldOffset, value);
    }

    public static <S, T, U extends S> T getAndSetObject(final T object, final long fieldOffset, final U value) {
        return (T) UNSAFE.getAndSetObject(object, fieldOffset, value);
    }

    public static <T> float getAndSetFloat(final T object, final long fieldOffset, final float value) {
        return Float.intBitsToFloat(UNSAFE.getAndSetInt(object, fieldOffset, Float.floatToRawIntBits(value)));
    }

    public static <T> double getAndSetDouble(final T object, final long fieldOffset, final double value) {
        return Double.longBitsToDouble(UNSAFE.getAndSetLong(object, fieldOffset, Double.doubleToRawLongBits(value)));
    }

    public static <T> int getAndSet(final T object, final long fieldOffset, final int value) {
        return getAndSetInt(object, fieldOffset, value);
    }

    public static <T> long getAndSet(final T object, final long fieldOffset, final long value) {
        return getAndSetLong(object, fieldOffset, value);
    }

    public static <S, T, U extends S> T getAndSet(final S object, final long fieldOffset, final U value) {
        return (T) UNSAFE.getAndSetObject(object, fieldOffset, value);
    }

    public static <T> float getAndSet(final T object, final long fieldOffset, final float value) {
        return getAndSetFloat(object, fieldOffset, value);
    }

    public static <T> double getAndSet(final T object, final long fieldOffset, final double value) {
        return getAndSetDouble(object, fieldOffset, value);
    }

    public static <T> int getAndAddInt(final T object, final long fieldOffset, final int value) {
        return UNSAFE.getAndAddInt(object, fieldOffset, value);
    }

    public static <T> long getAndAddLong(final T object, final long fieldOffset, final long value) {
        return UNSAFE.getAndAddLong(object, fieldOffset, value);
    }

    public static <T> int getAndAdd(final T object, final long fieldOffset, final int value) {
        return UNSAFE.getAndAddInt(object, fieldOffset, value);
    }

    public static <T> long getAndAdd(final T object, final long fieldOffset, final long value) {
        return UNSAFE.getAndAddLong(object, fieldOffset, value);
    }

    public static int getAndSet(final int[] array, final long index, final int value) {
        return getAndSetInt(array, index, value);
    }

    public static long getAndSet(final long[] array, final long index, final long value) {
        return getAndSetLong(array, index, value);
    }

    public static <S, T extends S, U extends S> T getAndSet(final S[] array, final long index, final U value) {
        return (T) UNSAFE.getAndSetObject(array, index, value);
    }

    public static float getAndSet(final float[] array, final long index, final float value) {
        return getAndSetFloat(array, index, value);
    }

    public static double getAndSet(final double[] array, final long index, final double value) {
        return getAndSetDouble(array, index, value);
    }

    public static int getAndAdd(final int[] array, final long index, final int value) {
        return getAndAddInt(array, index, value);
    }

    public static long getAndAdd(final long[] array, final long index, final long value) {
        return getAndAddLong(array, index, value);
    }
}
