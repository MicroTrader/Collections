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

import org.ObjectLayout.*;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;

/**
 * Created by sirinath on 01/09/2016.
 */
public class IntrinsicHelpers {
    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();

    public static <S extends StructuredArray<T>, T> StructuredArrayModel<S, T> structuredArrayModel(final long length) {
        return new StructuredArrayModel<S, T>(length);
    }

    public static <S extends StructuredArray<T>, T> StructuredArrayModel<S, T> structuredArrayModel(final Class<S> arrayClass, final Class<T> elementClass, final long length) {
        return new StructuredArrayModel<S, T>(arrayClass, elementClass, length);
    }

    public static <S extends StructuredArray<T>, T extends StructuredArray<U>, U> StructuredArrayModel<S, T> structuredArrayModel(final Class<S> arrayClass, final StructuredArrayModel<T, U> subArrayModel, final long length) {
        return new StructuredArrayModel<S, T>(arrayClass, subArrayModel, length);
    }

    public static <S extends StructuredArray<T>, T> StructuredArrayModel<S, T> structuredArrayModel(final Class<S> arrayClass, final PrimitiveArrayModel<?> subArrayModel, final long length) {
        return new StructuredArrayModel<S, T>(arrayClass, subArrayModel, length);
    }

    public static <T> CtorAndArgs<T> ctorAndArgs(final Class<T> instanceClass, final Class[] constructorArgTypes, final Object... args) {
        return new CtorAndArgs<T>(instanceClass, constructorArgTypes, args);
    }

    public static <T> CtorAndArgs<T> ctorAndArgs(final Constructor<T> constructor, final Object... args) {
        return new CtorAndArgs<T>(constructor, args);
    }

    public static <T> CtorAndArgs<T> ctorAndArgs(final Class<T> instanceClass) {
        return new CtorAndArgs<T>(instanceClass);
    }

    public static <S extends StructuredArray<T>, T> StructuredArrayBuilder<S, T> structuredArrayBuilder(final long length) {
        return new StructuredArrayBuilder<S, T>(structuredArrayModel(length)).resolve();
    }

    public static <S extends StructuredArray<T>, T> StructuredArrayBuilder<S, T> structuredArrayBuilder(final Class<S> arrayClass, final Class<T> elementClass, final long length) {
        return new StructuredArrayBuilder<S, T>(structuredArrayModel(arrayClass, elementClass, length)).resolve();
    }

    public static <S extends StructuredArray<T>, T> StructuredArrayBuilder<S, T> structuredArrayBuilder(final CtorAndArgs<S> arrayCtorAndArgs, final CtorAndArgs<T> elementCtorAndArgs, final long length) {
        return new StructuredArrayBuilder<S, T>(structuredArrayModel(length)).arrayCtorAndArgs(arrayCtorAndArgs).elementCtorAndArgs(elementCtorAndArgs).resolve();
    }

    public static <S extends StructuredArray<T>, T extends StructuredArray<U>, U> StructuredArrayBuilder<S, T> structuredArrayBuilder(final CtorAndArgs<S> arrayCtorAndArgs, final StructuredArrayModel<T, U> subArrayModel, final long length) {
        return new StructuredArrayBuilder<S, T>(structuredArrayModel(arrayCtorAndArgs.getConstructor().getDeclaringClass(), subArrayModel, length)).arrayCtorAndArgs(arrayCtorAndArgs).resolve();
    }

    public static <S extends StructuredArray<T>, T, U> StructuredArrayBuilder<S, T> structuredArrayBuilder(final CtorAndArgs<S> arrayCtorAndArgs, final PrimitiveArrayModel<?> subArrayModel, final long length) {
        return new StructuredArrayBuilder<S, T>(structuredArrayModel(arrayCtorAndArgs.getConstructor().getDeclaringClass(), subArrayModel, length)).arrayCtorAndArgs(arrayCtorAndArgs).resolve();
    }

    public static <S extends StructuredArray<T>, T> StructuredArrayBuilder<S, T> structuredArrayBuilder(final CtorAndArgs<S> arrayCtorAndArgs, final CtorAndArgsProvider<T> elementCtorAndArgsProvider, final long length) {
        return new StructuredArrayBuilder<S, T>(structuredArrayModel(length)).arrayCtorAndArgs(arrayCtorAndArgs).elementCtorAndArgsProvider(elementCtorAndArgsProvider).resolve();
    }

    public static <S extends StructuredArray<T>, T> StructuredArrayBuilder<S, T> structuredArrayBuilder(final CtorAndArgs<S> arrayCtorAndArgs, final CtorAndArgsProvider<T> elementCtorAndArgsProvider, final Object contextCookie, final long length) {
        return new StructuredArrayBuilder<S, T>(structuredArrayModel(length)).arrayCtorAndArgs(arrayCtorAndArgs).elementCtorAndArgsProvider(elementCtorAndArgsProvider).contextCookie(contextCookie).resolve();
    }

    public static <S> PrimitiveArrayBuilder<?> primitiveArrayBuilder(final CtorAndArgs<S> arrayCtorAndArgs, final long length) {
        try {
            return new PrimitiveArrayBuilder(arrayCtorAndArgs.getConstructor().getDeclaringClass(), length).arrayCtorAndArgs(arrayCtorAndArgs).resolve();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Malformed PrimitiveArrayBuilder", e);
        }
    }

    public static <S> PrimitiveArrayBuilder<?> primitiveArrayBuilder(final Class<S> arrayClass, final long length) {
        try {
            return new PrimitiveArrayBuilder(arrayClass, length).resolve();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Malformed PrimitiveArrayBuilder", e);
        }
    }

    public static <T> T constructWithin(final String fieldName, final Object containingObject) {
        return IntrinsicObjects.constructWithin(lookup, fieldName, containingObject);
    }

    public static <T> T constructWithin(final String fieldName, final Object containingObject, final CtorAndArgs<T> ctorAndArgs) {
        return IntrinsicObjects.constructWithin(lookup, fieldName, containingObject, ctorAndArgs);
    }

    public static <S extends StructuredArray<T>, T> S constructWithin(final String fieldName, final Object containingObject, final StructuredArrayBuilder<S, T> arrayBuilder) {
        return IntrinsicObjects.constructWithin(lookup, fieldName, containingObject, arrayBuilder);
    }

    public static <T> T constructWithin(final String fieldName, final Object containingObject, final PrimitiveArrayBuilder arrayBuilder) {
        return IntrinsicObjects.constructWithin(lookup, fieldName, containingObject, arrayBuilder);
    }
}
