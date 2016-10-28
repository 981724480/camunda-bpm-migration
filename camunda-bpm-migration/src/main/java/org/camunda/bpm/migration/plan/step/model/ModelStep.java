package org.camunda.bpm.migration.plan.step.model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.migration.plan.step.Step;
import org.camunda.bpm.migration.plan.step.StepExecutionContext;

import java.util.Collections;

/**
 * Executes a Camunda built-in migration plan.
 */
@RequiredArgsConstructor
public class ModelStep implements Step {

	@NonNull
	private MigrationPlanFactory migrationPlanFactory;

	@Override
	public void perform(StepExecutionContext context) {
		context.getProcessEngine().getRuntimeService()
				.newMigration(migrationPlanFactory.apply(context.getSourceProcessDefinitionId(), context.getTargetProcessDefinitionId()))
				.processInstanceIds(Collections.singletonList(context.getProcessInstanceId()))
				.execute();
	}
}
