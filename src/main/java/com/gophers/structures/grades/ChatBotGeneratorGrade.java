package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.services.handlers.GradeConfigLoader;
import com.gophers.utilities.Constants;

public class ChatBotGeneratorGrade extends GradeTemplate {
	private static final int totalMarks = 7;

	public ChatBotGeneratorGrade(Result result) {
		super(result, totalMarks);
	}

	@Override
	protected void allocateWeightings() {
		super.testMarks = GradeConfigLoader.getWeightings(Constants.CHATBOT_GENERATOR_GRADE);
	}

	@Override
	protected void allocateFeedback() {
		for (String key : super.testMarks.keySet()) {
			super.feedbackMap.put(key, true);
		}
	}
}
