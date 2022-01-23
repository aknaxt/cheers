package com.lupulus.cheers.repository.profile;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.lupulus.cheers.util.EnvironmentUtil;

public class ConditionMockRepository implements Condition {

	@Override
	public boolean matches(ConditionContext arg0, AnnotatedTypeMetadata arg1) {
		return EnvironmentUtil.isMockRepositoryActive(arg0.getEnvironment().getActiveProfiles());
	}
}
