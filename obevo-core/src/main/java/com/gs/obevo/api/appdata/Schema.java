/**
 * Copyright 2017 Goldman Sachs.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.gs.obevo.api.appdata;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents a logical schema within your deployment project.
 */
public class Schema {
    private final String name;
    private final ObjectTypeAndNamePredicateBuilder objectExclusionPredicateBuilder;
    private final boolean readOnly;

    public Schema(String name) {
        this(name, new ObjectTypeAndNamePredicateBuilder(ObjectTypeAndNamePredicateBuilder.FilterType.EXCLUDE), false);
    }

    public Schema(String name, ObjectTypeAndNamePredicateBuilder objectExclusionPredicateBuilder, boolean readOnly) {
        this.name = Validate.notNull(name);
        this.objectExclusionPredicateBuilder = Validate.notNull(objectExclusionPredicateBuilder);
        this.readOnly = readOnly;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Defines the objects that are excluded by default within this schema.
     */
    public ObjectTypeAndNamePredicateBuilder getObjectExclusionPredicateBuilder() {
        return objectExclusionPredicateBuilder;
    }

    /**
     * Defines whether the schema is read-only (i.e. that we won't do any deployments to it). The core deploy logic
     * itself does not use this value; it is exposed to facilitate integration by other tools.
     * The main use case is if your schema depends on another schema's objects to be deployed beforehand (while not
     * editing the other schema itself), and you need to declare this dependency for a reason such as requesting read
     * access to this other schema.(Some DBMS's like Sybase allow schema/database-level permissions)
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("objectExclusionPredicateBuilder", objectExclusionPredicateBuilder)
                .append("readOnly", readOnly)
                .toString();
    }
}