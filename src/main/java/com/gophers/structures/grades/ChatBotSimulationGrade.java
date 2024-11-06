package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.services.helpers.GradeConfigLoader;

public class ChatBotSimulationGrade extends GradeTemplate {
	private static final int totalMarks = 12;

	public ChatBotSimulationGrade(Result result) {
		super(result, totalMarks);
	}

	@Override
	protected void allocateWeightings() {
		super.testMarks = GradeConfigLoader.getWeightings("ChatBotSimulationGrade");
	}

	@Override
	protected void allocateFeedback() {
		super.testFeedback = GradeConfigLoader.getFeedback("ChatBotSimulationGrade");
	}

}
