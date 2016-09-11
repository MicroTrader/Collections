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

package com.sakrio.collections.objectlayout.arrays.templates;

import com.sakrio.collections.objectlayout.BaseSupplier;
import com.sakrio.collections.objectlayout.arrays.ArraySupplier;
import com.sakrio.utils.UnsafeAccess;
import org.ObjectLayout.*;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Created by sirinath on 31/08/2016.
 */
public abstract class AbstractGenericArrayCartridge<S> implements ArrayCartridge<S> {
    protected static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    @Intrinsic
    private final S data;

    private final long length;

    protected <U extends BaseSupplier<S>> AbstractGenericArrayCartridge(final U instanceSupplier) {
        data = instanceSupplier.apply("data", this);

        long theLength = -1;

        for (; ; ) {
            try {
                theLength = ((ArraySupplier) instanceSupplier).getLength();
                break;
            } catch (Throwable t) {
            }

            try {
                theLength = ((Collection) data).size();
                break;
            } catch (Throwable t) {
            }

            try {
                theLength = ((StructuredArray) data).getLength();
                break;
            } catch (Throwable t) {
            }

            try {
                theLength = ((PrimitiveByteArray) data).getLength();
                break;
            } catch (Throwable t) {
            }

            try {
                theLength = ((PrimitiveCharArray) data).getLength();
                break;
            } catch (Throwable t) {
            }

            try {
                theLength = ((PrimitiveShortArray) data).getLength();
                break;
            } catch (Throwable t) {
            }

            try {
                theLength = ((PrimitiveIntArray) data).getLength();
                break;
            } catch (Throwable t) {
            }

            try {
                theLength = ((PrimitiveLongArray) data).getLength();
                break;
            } catch (Throwable t) {
            }

            try {
                theLength = ((PrimitiveFloatArray) data).getLength();
                break;
            } catch (Throwable t) {
            }

            try {
                theLength = ((PrimitiveDoubleArray) data).getLength();
                break;
            } catch (Throwable t) {
            }

            Class cls = data.getClass();

            try {
                Method m = cls.getMethod("getLength");
                theLength = (long) m.invoke(data);
                break;
            } catch (Throwable t) {
            }

            try {
                Method m = cls.getMethod("length");
                theLength = (long) m.invoke(data);
                break;
            } catch (Throwable t) {
            }

            try {
                Field f = cls.getField("length");
                theLength = f.getLong(data);
                break;
            } catch (Throwable t) {
            }

            try {
                Method m = cls.getMethod("getSize");
                theLength = (long) m.invoke(data);
                break;
            } catch (Throwable t) {
            }

            try {
                Method m = cls.getMethod("size");
                theLength = (long) m.invoke(data);
                break;
            } catch (Throwable t) {
            }

            try {
                Field f = cls.getField("size");
                theLength = f.getLong(data);
                break;
            } catch (Throwable t) {
            }

            try {
                Method m = cls.getMethod("getCount");
                theLength = (long) m.invoke(data);
                break;
            } catch (Throwable t) {
            }

            try {
                Method m = cls.getMethod("count");
                theLength = (long) m.invoke(data);
                break;
            } catch (Throwable t) {
            }

            try {
                Field f = cls.getField("count");
                theLength = f.getLong(data);
                break;
            } catch (Throwable t) {
            }

            throw new IllegalStateException("Array is not the expected types nor does not have expected fields or methods");
        }

        this.length = theLength;
    }

    public final long getLength() {
        return length;
    }

    public final S getUnderlyingArray() {
        return data;
    }
}
