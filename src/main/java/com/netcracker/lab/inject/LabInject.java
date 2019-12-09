package com.netcracker.lab.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Интерфейс - аннотоция для полей, которые можно инъектировать в класс.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LabInject {

}
