/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2018-2020 The Feast Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package feast.core.job;

import feast.core.log.Action;
import feast.core.model.Job;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/** Task to terminate given {@link Job} by using {@link JobManager} */
@Getter
@Setter
@Builder(setterPrefix = "set")
public class TerminateJobTask implements JobTask {
  private Job job;
  private JobManager jobManager;

  @Override
  public Job call() {
    JobTask.logAudit(
        Action.ABORT,
        job,
        "Aborting job %s for runner %s",
        job.getId(),
        jobManager.getRunnerType().toString());
    return jobManager.abortJob(job);
  }
}
