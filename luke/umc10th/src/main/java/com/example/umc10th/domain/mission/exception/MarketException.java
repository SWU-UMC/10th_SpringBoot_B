package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;

public class MarketException extends MissionException {
    public MarketException(BaseErrorCode code) { super(code);}
}
