package com.todorename.android.dagger.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**
 * to qualify {@link android.content.Context} as Application Context
 */
@Qualifier @Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}
