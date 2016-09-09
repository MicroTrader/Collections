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

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

import static sun.misc.Unsafe.*;

/**
 * Created by sirin_000 on 03/10/2015.
 */
public class UnsafeAccess {
    public static final Unsafe UNSAFE;

    public static final long ARRAY_BOOLEAN_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_BOOLEAN_INDEX_SCALE) - 1L;
    public static final long ARRAY_BYTE_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_BYTE_INDEX_SCALE) - 1L;
    public static final long ARRAY_SHORT_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_SHORT_INDEX_SCALE) - 1L;
    public static final long ARRAY_CHAR_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_CHAR_INDEX_SCALE) - 1L;
    public static final long ARRAY_INT_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_INT_INDEX_SCALE) - 1L;
    public static final long ARRAY_LONG_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_LONG_INDEX_SCALE) - 1L;
    public static final long ARRAY_FLOAT_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_FLOAT_INDEX_SCALE) - 1L;
    public static final long ARRAY_DOUBLE_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_DOUBLE_INDEX_SCALE) - 1L;
    public static final long ARRAY_OBJECT_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_OBJECT_INDEX_SCALE) - 1L;

    static {
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

        UNSAFE = unsafe;
    }

    public static long getFieldOffset(final Class<?> cls, final String field) {
        try {
            return UNSAFE.objectFieldOffset(cls.getField(field));
        } catch (NoSuchFieldException e) {
            UncheckedExceptions.rethrow(e);
        }

        return 0L;
    }

    public static long getIndexOffset(final boolean[] array, final long index) {
        return ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT;
    }

    public static long getIndexOffset(final byte[] array, final long index) {
        return ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT;
    }

    public static long getIndexOffset(final short[] array, final long index) {
        return ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT;
    }

    public static long getIndexOffset(final char[] array, final long index) {
        return ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT;
    }

    public static long getIndexOffset(final int[] array, final long index) {
        return ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT;
    }

    public static long getIndexOffset(final long[] array, final long index) {
        return ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT;
    }

    public static long getIndexOffset(final float[] array, final long index) {
        return ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT;
    }

    public static long getIndexOffset(final double[] array, final long index) {
        return ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT;
    }

    public static <T> long getIndexOffset(final T[] array, final long index) {
        return ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT;
    }
}
