package com.flatstack.android.dagger.qualifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**
 * qualifies "pure" class instance with default/empty configurations
 * example: Gson without any type adapters
 */
@Documented @Qualifier @Retention(RetentionPolicy.RUNTIME)
public @interface Pure {
}
