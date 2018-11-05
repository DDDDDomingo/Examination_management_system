package studio.beita.hdxg.beitasystem.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog {
    /**
     * 描述
     */
    String description() default "";
}
