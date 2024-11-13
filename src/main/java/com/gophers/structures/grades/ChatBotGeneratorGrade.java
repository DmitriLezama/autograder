package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.services.handlers.GradeConfigLoader;
import com.gophers.utilities.Constants;

public class ChatBotGeneratorGrade extends GradeTemplate {
	private static final int totalMarks = Constants.CHATBOT_GENERATOR_GRADE_TOTAL_MARKS;

	public ChatBotGeneratorGrade(Result result) {
		super(result, totalMarks);
	}

	@Override
	protected void allocateWeightings() {
		super.testMarks = GradeConfigLoader.getWeightings(Constants.CHATBOT_GENERATOR_GRADE);
	}

	@Override
	protected void allocateFeedback() {
		super.feedbackMap.replaceAll((k, v) -> true);
	}
}
