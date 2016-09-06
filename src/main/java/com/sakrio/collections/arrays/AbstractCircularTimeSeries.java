/*
 * _______________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and
 *     Project Contributors
 *
 * ${PROJECT_NAME}, is a collection of works and / or artifacts submitted
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

package com.sakrio.collections.arrays;

import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;
import com.sakrio.utils.UnsafeAccess;
import org.ObjectLayout.Intrinsic;
import sun.misc.Contended;
import sun.misc.Unsafe;

import java.util.Arrays;

/**
 * Created by sirinath on 31/08/2016.
 */
public abstract class AbstractCircularTimeSeries<S, T> {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;
    private static long markerOffset = UnsafeAccess.getFieldOffset(AbstractCircularTimeSeries.class, "marker");

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
