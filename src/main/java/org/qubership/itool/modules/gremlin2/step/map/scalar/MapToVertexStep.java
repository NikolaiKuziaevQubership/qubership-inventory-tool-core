/*
 * Copyright 2024-2025 NetCracker Technology Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.qubership.itool.modules.gremlin2.step.map.scalar;

import org.qubership.itool.modules.gremlin2.Traversal;
import org.qubership.itool.modules.gremlin2.Traverser;
import io.vertx.core.json.JsonObject;

import java.util.Map;

public class MapToVertexStep<S, E extends JsonObject> extends ScalarMapStep<S, E> {

    public MapToVertexStep(Traversal.Admin traversal) {
        super(traversal);
    }

    @Override
    protected E map(Traverser.Admin<S> traverser) {
        JsonObject result = null;
        Object value = traverser.get();
        String id = null;

        if (value instanceof String) {
            id = (String)value;

        } else if (value instanceof Map) {
            id = (String)((Map)value).get("id");

        } else if (value instanceof JsonObject) {
            JsonObject json = (JsonObject) value;
            id = json.getString("id");
        }

        if (id != null) {
            result = this.traversal.getGraph().getVertex((String) value);
        }

        return (E)result;
    }
}
