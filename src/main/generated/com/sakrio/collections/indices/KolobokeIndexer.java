

/*
 * _______________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and
 *     Project Contributors
 *
 * ${PROJECT_NAME}, is a collection of works and / or artifacts submitted
 * and / or contributed by multiple authors ("Project Contributors"),
 * collectively creating a larger work and / or artifact (the / this
 * "Project"). This project is licensed under the terms of either:
 *  - the Apache License, Version 2.0 (ASF 2.0), or
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

package com.sakrio.collections.indices;

import com.koloboke.collect.hash.HashConfig;
import com.koloboke.collect.impl.LongArrays;
import com.koloboke.collect.impl.Maths;
import com.koloboke.collect.impl.hash.HashConfigWrapper;
import com.koloboke.collect.impl.hash.LHash;
import com.koloboke.collect.impl.hash.LHashCapacities;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.annotation.Generated;
import java.util.ConcurrentModificationException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Generated(value = "com.koloboke.compile.processor.KolobokeCollectionProcessor")
@SuppressFBWarnings(value = {"IA_AMBIGUOUS_INVOCATION_OF_INHERITED_OR_OUTER_METHOD"})
@SuppressWarnings(value = {"all", "unsafe", "deprecation", "overloads", "rawtypes"})
final class KolobokeIndexer extends Indexer {
    static final HashConfigWrapper DEFAULT_CONFIG_WRAPPER = new HashConfigWrapper(HashConfig.getDefault());
    long freeValue;
    long[] table;
    int size;
    private HashConfigWrapper configWrapper;
    private int maxSize;
    private int modCount = 0;

    KolobokeIndexer(int expectedSize) {
        this.init(DEFAULT_CONFIG_WRAPPER, expectedSize);
    }

    KolobokeIndexer(HashConfig hashConfig, int expectedSize) {
        this.init(new HashConfigWrapper(hashConfig), expectedSize);
    }

    static void verifyConfig(HashConfig config) {
        if ((config.getGrowthFactor()) != 2.0) {
            throw new IllegalArgumentException(((((((config + " passed, HashConfig for a hashtable\n") + "implementation with linear probing must have growthFactor of 2.0.\n") + "A Koloboke Compile-generated hashtable implementation could have\n") + "a different growth factor, if the implemented type is annotated with\n") + "@com.koloboke.compile.hash.algo.openaddressing.QuadraticProbing or\n") + "@com.koloboke.compile.hash.algo.openaddressing.DoubleHashing"));
        }
    }

    public final boolean isEmpty() {
        return (size()) == 0;
    }

    boolean doubleSizedArrays() {
        return true;
    }

    final void init(HashConfigWrapper configWrapper, int size, long freeValue) {
        KolobokeIndexer.this.freeValue = freeValue;
        init(configWrapper, size);
    }

    public int capacity() {
        return (table.length) >> 1;
    }

    public final int size() {
        return size;
    }

    int insert(long key, long value) {
        long free;
        if (key == (free = freeValue)) {
            free = changeFree();
        }
        long[] tab = table;
        int capacityMask;
        int index;
        long cur;
        keyAbsent:
        if ((cur = tab[(index = (LHash.ParallelKVLongKeyMixing.mix(key)) & (capacityMask = (tab.length) - 2))]) != free) {
            if (cur == key) {
                return index;
            } else {
                while (true) {
                    if ((cur = tab[(index = (index - 2) & capacityMask)]) == free) {
                        break keyAbsent;
                    } else if (cur == key) {
                        return index;
                    }
                }
            }
        }
        incrementModCount();
        tab[index] = key;
        tab[(index + 1)] = value;
        postInsertHook();
        return -1;
    }

    public final int modCount() {
        return modCount;
    }

    final void incrementModCount() {
        (modCount)++;
    }

    public long defaultValue() {
        return 0L;
    }

    int index(long key) {
        long free;
        if (key != (free = freeValue)) {
            long[] tab = table;
            int capacityMask;
            int index;
            long cur;
            if ((cur = tab[(index = (LHash.ParallelKVLongKeyMixing.mix(key)) & (capacityMask = (tab.length) - 2))]) == key) {
                return index;
            } else {
                if (cur == free) {
                    return -1;
                } else {
                    while (true) {
                        if ((cur = tab[(index = (index - 2) & capacityMask)]) == key) {
                            return index;
                        } else if (cur == free) {
                            return -1;
                        }
                    }
                }
            }
        } else {
            return -1;
        }
    }

    final void init(HashConfigWrapper configWrapper, int size) {
        KolobokeIndexer.verifyConfig(configWrapper.config());
        KolobokeIndexer.this.configWrapper = configWrapper;
        KolobokeIndexer.this.size = 0;
        internalInit(targetCapacity(size));
    }

    private void internalInit(int capacity) {
        assert Maths.isPowerOf2(capacity);
        maxSize = maxSize(capacity);
        allocateArrays(capacity);
    }

    private int maxSize(int capacity) {
        return !(isMaxCapacity(capacity)) ? configWrapper.maxSize(capacity) : capacity - 1;
    }

    @Override
    public long get(long key) {
        int index = index(key);
        if (index >= 0) {
            return table[(index + 1)];
        } else {
            return defaultValue();
        }
    }

    private long findNewFreeOrRemoved() {
        long free = KolobokeIndexer.this.freeValue;
        Random random = ThreadLocalRandom.current();
        long newFree;
        {
            do {
                newFree = ((long) (random.nextLong()));
            } while ((newFree == free) || ((index(newFree)) >= 0));
        }
        return newFree;
    }

    long changeFree() {
        int mc = modCount();
        long newFree = findNewFreeOrRemoved();
        incrementModCount();
        mc++;
        LongArrays.replaceAllKeys(table, freeValue, newFree);
        KolobokeIndexer.this.freeValue = newFree;
        if (mc != (modCount()))
            throw new ConcurrentModificationException();

        return newFree;
    }

    final void initForRehash(int newCapacity) {
        (modCount)++;
        internalInit(newCapacity);
    }

    void allocateArrays(int capacity) {
        table = new long[capacity * 2];
        if ((freeValue) != 0)
            LongArrays.fillKeys(table, freeValue);

    }

    final void postInsertHook() {
        if ((++(size)) > (maxSize)) {
            int capacity = capacity();
            if (!(isMaxCapacity(capacity))) {
                rehash((capacity << 1));
            }
        }
    }

    private int targetCapacity(int size) {
        return LHashCapacities.capacity(configWrapper, size, doubleSizedArrays());
    }

    private boolean isMaxCapacity(int capacity) {
        return LHashCapacities.isMaxCapacity(capacity, doubleSizedArrays());
    }

    @SuppressFBWarnings(value = "EC_UNRELATED_TYPES_USING_POINTER_EQUALITY")
    @Override
    public String toString() {
        if (KolobokeIndexer.this.isEmpty())
            return "{}";

        StringBuilder sb = new StringBuilder();
        int elementCount = 0;
        int mc = modCount();
        long free = freeValue;
        long[] tab = table;
        for (int i = (tab.length) - 2; i >= 0; i -= 2) {
            long key;
            if ((key = tab[i]) != free) {
                sb.append(' ');
                sb.append(key);
                sb.append('=');
                sb.append(tab[(i + 1)]);
                sb.append(',');
                if ((++elementCount) == 8) {
                    int expectedLength = (sb.length()) * ((size()) / 8);
                    sb.ensureCapacity((expectedLength + (expectedLength / 2)));
                }
            }
        }
        if (mc != (modCount()))
            throw new ConcurrentModificationException();

        sb.setCharAt(0, '{');
        sb.setCharAt(((sb.length()) - 1), '}');
        return sb.toString();
    }

    void rehash(int newCapacity) {
        int mc = modCount();
        long free = freeValue;
        long[] tab = table;
        initForRehash(newCapacity);
        mc++;
        long[] newTab = table;
        int capacityMask = (newTab.length) - 2;
        for (int i = (tab.length) - 2; i >= 0; i -= 2) {
            long key;
            if ((key = tab[i]) != free) {
                int index;
                if ((newTab[(index = (LHash.ParallelKVLongKeyMixing.mix(key)) & capacityMask)]) != free) {
                    while (true) {
                        if ((newTab[(index = (index - 2) & capacityMask)]) == free) {
                            break;
                        }
                    }
                }
                newTab[index] = key;
                newTab[(index + 1)] = tab[(i + 1)];
            }
        }
        if (mc != (modCount()))
            throw new ConcurrentModificationException();

    }

    @Override
    public void justPut(long key, long value) {
        int index = insert(key, value);
        if (index < 0) {
            return;
        } else {
            table[(index + 1)] = value;
            return;
        }
    }

    static class Support {
    }
}