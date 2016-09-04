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

package com.sakrio.collections;

import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;
import org.ObjectLayout.Intrinsic;
import sun.misc.Contended;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;

/**
 * Created by sirinath on 31/08/2016.
 */
public abstract class AbstractCircularTimeSeries<S, T> {
    private static final Unsafe UNSAFE;
    private static long markerOffset = getFieldOffset(AbstractCircularTimeSeries.class, "marker");

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

    @Intrinsic
    private final S data;

    private final long length;
    private final boolean isPowerOf2;
    private final long mask;

    @Contended
    private long marker = 0;

    protected <U extends BaseSupplier<S>> AbstractCircularTimeSeries(final U instanceSupplier) {
        data = instanceSupplier.apply("data", this);

        final Class<?>[] clazz = {data.getClass(), instanceSupplier.getClass()};
        final MethodAccess[] methodAccessArray = {MethodAccess.get(clazz[0]), MethodAccess.get(clazz[1])};
        final FieldAccess[] fieldAccessArray = {FieldAccess.get(clazz[0]), FieldAccess.get(clazz[1])};

        long theLength = -1;

        final String[] names = {"length", "size", "count", "items"};

        outerLoop:
        for (String name : names) {
            for (int i = 0; i < 2; i++) {
                final MethodAccess methodAccess = methodAccessArray[i];
                final FieldAccess fieldAccess = fieldAccessArray[i];

                try {
                    theLength = (long) methodAccess.invoke(data, "get" + name.substring(0, 1).toUpperCase() + name.substring(1));
                    break outerLoop;
                } catch (Throwable t) {
                }

                try {
                    theLength = (long) methodAccess.invoke(data, name);
                    break outerLoop;
                } catch (Throwable t) {
                }

                try {
                    theLength = (long) fieldAccess.get(data, name);
                    break outerLoop;
                } catch (Throwable t) {
                }
            }
        }

        if (theLength == -1)
            throw new IllegalStateException("Cannot deduce the array length! The data field or instanceSupplier parameter should contain accessible getters and / or fields for: " + Arrays.toString(names));

        this.length = theLength;
        this.isPowerOf2 = (length & (length - 1)) == 0;
        this.mask = isPowerOf2 ? (1 << (Long.SIZE - Long.numberOfLeadingZeros(length - 1))) : (length - 1);
    }

    private static long getFieldOffset(final Class<?> cls, final String field) {
        try {
            return UNSAFE.objectFieldOffset(cls.getField(field));
        } catch (Throwable t) {
            throw new RuntimeException("Error in accessing field: " + field + " in: " + cls, t);
        }
    }

    private long roll(final long index) {
        return isPowerOf2 ? index & mask : index > mask ? index - mask : index < 0 ? index + mask : index;
    }

    public final long getLength() {
        return length;
    }

    public final S getData() {
        return data;
    }

    protected abstract T getItAt(final long index);

    protected abstract void setItAt(final long index, final T value);

    public final T last(final long index) {
        long theMarker = marker;
        T theValue = getItAt(roll(theMarker - index));

        while (theMarker != (theMarker = UNSAFE.getLongVolatile(this, markerOffset))) {
            theValue = getItAt(roll(theMarker - index));
        }

        return theValue;
    }

    public void updateNext(final T value) {
        long theMarker = marker;
        long next = roll(theMarker + 1);
        while (!UNSAFE.compareAndSwapLong(this, markerOffset, theMarker, next)) {
            theMarker = UNSAFE.getLongVolatile(this, markerOffset);
            next = roll(theMarker + 1);
        }

        setItAt(next, value);
    }
}
