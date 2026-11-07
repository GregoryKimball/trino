/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.execution;

import io.opentelemetry.api.trace.Span;
import io.trino.Session;
import io.trino.execution.buffer.OutputBuffer;
import io.trino.memory.QueryContext;
import io.trino.spi.connector.ConnectorTableCredentials;
import io.trino.sql.planner.PlanFragment;
import io.trino.sql.planner.plan.PlanNodeId;

import java.util.Map;

public interface TaskExecutionFactory
{
    TaskExecution create(
            Session session,
            Span taskSpan,
            QueryContext queryContext,
            TaskStateMachine taskStateMachine,
            OutputBuffer outputBuffer,
            PlanFragment fragment,
            Map<PlanNodeId, ConnectorTableCredentials> tableCredentials,
            Runnable notifyStatusChanged);

    /**
     * Bound optionally in Guice by a server extension (see
     * {@code Server#getAdditionalModules}) to replace or wrap the default
     * {@link SqlTaskExecutionFactory}, for example to create some tasks
     * itself and delegate the rest to the default factory.
     */
    interface Decorator
    {
        TaskExecutionFactory decorate(TaskExecutionFactory defaultFactory);
    }
}
