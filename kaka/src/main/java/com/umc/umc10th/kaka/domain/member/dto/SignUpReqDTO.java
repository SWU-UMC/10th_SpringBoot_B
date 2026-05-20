package com.umc.umc10th.kaka.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class SignUpReqDTO {

    public record SignUpReqBody(
            @NotNull(message = "약관 동의 ID는 필수입니다.")
            AgreeReq agree,

            @NotBlank(message = "이름은 필수입니다.")
            String name,

            @NotBlank(message = "성별은 필수입니다.")
            @Pattern(regexp = "MALE|FEMALE", message = "성별은 MALE 또는 FEMALE이어야 합니다.")
            String gender,

            @NotBlank(message = "생년월일은 필수입니다.")
            @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "생년월일은 YYYY-MM-DD 형식이어야 합니다.")
            @Schema(example = "2002-02-02", description = "생년월일 (YYYY-MM-DD)")
            String birth,

            @NotBlank(message = "지역은 필수입니다.")
            @Pattern(
                    regexp = "SEOUL|INCHEON|BUSAN|DAEGU|DAEJEON|GWANGJU|ULSAN|GANGNAM|SEOCHO|MAPO|HONGDAE|JONGNO|YONGSAN|SEONGDONG|SONGPA|YEONGDEUNGPO",
                    message = "유효하지 않은 지역입니다."
            )
            String address,

            String detailAddress,

            @NotNull(message = "선호 음식은 필수입니다.")
            @Schema(example = "[\"KOREAN\", \"CHINESE\"]", description = "선호 음식 Enum 리스트")
            List<String> foodList,

            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            String password

//            @NotBlank(message = "전화번호는 필수입니다.")
//            String phoneNumber // 워크북 회원가입 api는 없어서 임시 주석처리함.

    ) {
    }

        public record AgreeReq(
                @Schema(example = "true") boolean age,
                @Schema(example = "true") boolean service,
                @Schema(example = "true") boolean privacy,
                @Schema(example = "true") boolean location,
                @Schema(example = "true") boolean marketing
        ) {}


}
