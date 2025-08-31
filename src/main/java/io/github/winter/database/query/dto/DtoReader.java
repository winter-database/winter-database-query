package io.github.winter.database.query.dto;

import io.github.winter.boot.sql.Preconditions;
import io.github.winter.database.template.Template;

/**
 * Data Transfer Object
 *
 * @author changebooks@qq.com
 */
public class DtoReader {
    /**
     * the {@link Template} instance
     */
    private final Template template;

    public DtoReader(Template template) {
        Preconditions.requireNonNull(template, "template must not be null");

        this.template = template;
    }

}
