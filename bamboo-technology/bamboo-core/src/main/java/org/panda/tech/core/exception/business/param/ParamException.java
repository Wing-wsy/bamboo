package org.panda.tech.core.exception.business.param;

import org.panda.tech.core.exception.ExceptionEnum;
import org.panda.tech.core.exception.business.BusinessException;

/**
 * 参数异常
 */
public class ParamException extends BusinessException {

    private static final long serialVersionUID = 1487156738294354716L;

    public ParamException(Integer code, String message) {
        super(code, message);
    }

    public ParamException(String message) {
        super(ExceptionEnum.PARAMETERS.getCode(), message);
    }

    public ParamException() {
        super(ExceptionEnum.PARAMETERS.getCode(), ExceptionEnum.PARAMETERS.getMessage());
    }
}
