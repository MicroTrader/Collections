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

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.function.LongConsumer;

class MemoryFieldAddress {
    protected static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    private long address;

    public final long getAddress() {
        return address;
    }

    protected final void setAddress(final long address) {
        this.address = address;
    }
}

class MemoryAddressPad1 extends MemoryFieldAddress {
    long p1_1, p1_2, p1_3, p1_4, p1_5, p1_6, p1_7, p1_8, p1_9, p1_10, p1_11, p1_12, p1_13, p1_14, p1_15, p1_16, p1_17;
}

class MemoryFieldBytes extends MemoryAddressPad1 {
    private long bytes;

    public final long getBytes() {
        return bytes;
    }

    protected final void setBytes(final long bytes) {
        this.bytes = bytes;
    }
}

class MemoryAddressPad2 extends MemoryFieldBytes {
    long p2_1, p2_2, p2_3, p2_4, p2_5, p2_6, p2_7, p2_8, p2_9, p2_10, p2_11, p2_12, p2_13, p2_14, p2_15, p2_16, p2_17;
}

class MemoryFieldArray extends MemoryAddressPad2 {
    private Object array;

    public final Object getArray() {
        return array;
    }

    protected final void setArray(final Object array) {
        this.array = array;
    }

    public final boolean hasArray() {
        return array != null;
    }
}

class MemoryAddressPad3 extends MemoryFieldArray {
    long p3_1, p3_2, p3_3, p3_4, p3_5, p3_6, p3_7, p3_8, p3_9, p3_10, p3_11, p3_12, p3_13, p3_14, p3_15, p3_16, p3_17;
}

/**
 * Created by sirinath on 13/09/2016.
 */
public class Memory extends MemoryAddressPad3 {
    private final LongConsumer reallocate;

    private CleaningProcess cleaner;

    public Memory(final long bytes) {
        this.setBytes(bytes);
        this.setAddress(UNSAFE.allocateMemory(bytes));
        reallocate = (final long newLengthInBytes) -> {
            cleaner.invalidate();
            final long address = UNSAFE.reallocateMemory(this.getAddress(), newLengthInBytes);
            this.setAddress(address);
            this.cleaner = new CleaningProcess(this.getAddress());
            Cleaner.create(this, this.cleaner);
        };
        this.cleaner = new CleaningProcess(this.getAddress());
        Cleaner.create(this, this.cleaner);
    }

    private Memory(final Object array, final long address, final long bytes) {
        this.setArray(array);
        this.setAddress(address);
        this.setBytes(bytes);
        reallocate = (final long newLengthInBytes) -> {
            final Object currentArray = this.getArray();
            final Object newArray = Array.newInstance(currentArray.getClass(), (int) newLengthInBytes);
            this.setArray(newArray);
            System.arraycopy(currentArray, 0, newArray, 0, Array.getLength(currentArray));
        };
    }

    public Memory(final boolean[] array) {
        this(array, Unsafe.ARRAY_BOOLEAN_BASE_OFFSET, array.length * Unsafe.ARRAY_BOOLEAN_INDEX_SCALE);
    }

    public Memory(final char[] array) {
        this(array, Unsafe.ARRAY_CHAR_BASE_OFFSET, array.length * Unsafe.ARRAY_CHAR_INDEX_SCALE);
    }

    public Memory(final byte[] array) {
        this(array, Unsafe.ARRAY_BYTE_BASE_OFFSET, array.length * Unsafe.ARRAY_BYTE_INDEX_SCALE);
    }

    public Memory(final short[] array) {
        this(array, Unsafe.ARRAY_SHORT_BASE_OFFSET, array.length * Unsafe.ARRAY_SHORT_INDEX_SCALE);
    }

    public Memory(final int[] array) {
        this(array, Unsafe.ARRAY_INT_BASE_OFFSET, array.length * Unsafe.ARRAY_INT_INDEX_SCALE);
    }

    public Memory(final long[] array) {
        this(array, Unsafe.ARRAY_LONG_BASE_OFFSET, array.length * Unsafe.ARRAY_LONG_INDEX_SCALE);
    }

    public Memory(final float[] array) {
        this(array, Unsafe.ARRAY_FLOAT_BASE_OFFSET, array.length * Unsafe.ARRAY_FLOAT_INDEX_SCALE);
    }

    public Memory(final double[] array) {
        this(array, Unsafe.ARRAY_DOUBLE_BASE_OFFSET, array.length * Unsafe.ARRAY_DOUBLE_INDEX_SCALE);
    }

    public <T> Memory(final T[] array) {
        this(array, Unsafe.ARRAY_OBJECT_BASE_OFFSET, array.length * Unsafe.ARRAY_OBJECT_INDEX_SCALE);
    }

    public static long address(final ByteBuffer buffer) {
        if (buffer.isDirect())
            return ((sun.nio.ch.DirectBuffer) buffer).address();
        else
            return Unsafe.ARRAY_BYTE_BASE_OFFSET;
    }

    public synchronized void reallocate(final long newLengthInBytes) {
        reallocate.accept(newLengthInBytes);
    }

    private static class CleaningProcess implements Runnable {
        private final long address;

        private boolean valid = true;

        CleaningProcess(final long address) {
            this.address = address;
        }

        @Override
        public synchronized void run() {
            if (valid)
                UNSAFE.freeMemory(this.address);
        }

        public void invalidate() {
            this.valid = false;
        }
    }
}
