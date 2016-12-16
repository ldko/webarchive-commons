/*
 * Copyright 2016 IIPC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.netpreserve.commons.cdx.json;

import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

import org.netpreserve.commons.uri.Uri;
import org.netpreserve.commons.uri.UriBuilder;
import org.netpreserve.commons.uri.UriConfigs;

/**
 *
 */
public final class UriValue implements Value<Uri> {

    private Uri value;

    private String unparsed;

    private UriValue(final String value) {
        this.unparsed = value;
    }

    private UriValue(final Uri value) {
        this.value = value;
    }

    public static UriValue valueOf(char[] src, int start, int end) {
        return new UriValue(String.copyValueOf(src, start, end - start));
    }

    public static UriValue valueOf(Uri value) {
        return new UriValue(value);
    }

    public static UriValue valueOf(String value) {
        return new UriValue(value);
    }

    @Override
    public Uri getValue() {
        if (value == null) {
            value = UriConfigs.LAX_URI.buildUri(unparsed);
        }
        return value;
    }

    @Override
    public String toString() {
        if (unparsed == null) {
            unparsed = value.toString();
        }
        return unparsed;
    }

    @Override
    public void toJson(Writer out) throws IOException {
        if (unparsed == null) {
            unparsed = value.toString();
        }
        out.write('\"');
        out.write(unparsed);
        out.write('\"');
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(getValue());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UriValue other = (UriValue) obj;
        if (!Objects.equals(this.getValue(), other.getValue())) {
            return false;
        }
        return true;
    }

}
