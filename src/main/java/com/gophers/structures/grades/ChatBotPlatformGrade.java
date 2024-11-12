package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.services.handlers.GradeConfigLoader;
import com.gophers.utilities.Constants;

public class ChatBotPlatformGrade extends GradeTemplate {
	private static final int totalMarks = 20;

	public ChatBotPlatformGrade(Result result) {
		super(result, totalMarks);
	}

	@Override
	protected void allocateWeightings() {
		super.testMarks = GradeConfigLoader.getWeightings(Constants.CHATBOT_PLATFORM_GRADE);
	}

	@Override
	protected void allocateFeedback() {
		for (String key : super.testMarks.keySet()) {
			super.feedbackMap.put(key, true);
		}
	}
}
