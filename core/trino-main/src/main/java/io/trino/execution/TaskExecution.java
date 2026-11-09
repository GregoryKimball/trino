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

import io.trino.operator.TaskContext;
import io.trino.sql.planner.plan.PlanNodeId;

import java.util.List;
import java.util.Set;

/**
 * Runs one task's plan fragment on a worker. An implementation reports task
 * progress and completion through the task state machine and task context it
 * was created with, and writes results to the task's output buffer.
 */
public interface TaskExecution
{
    TaskContext getTaskContext();

    void addSplitAssignments(List<SplitAssignment> splitAssignments);

    Set<PlanNodeId> getNoMoreSplits();

    void start();
}
