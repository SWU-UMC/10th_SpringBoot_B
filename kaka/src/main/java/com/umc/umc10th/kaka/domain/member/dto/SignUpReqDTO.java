package com.umc.umc10th.kaka.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SignUpReqDTO {

    public record SignUpReqBody(
            @NotBlank(message = "이름은 필수입니다.")
            String name,

            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            String password,

            @NotBlank(message = "전화번호는 필수입니다.")
            String phoneNumber,

            @NotBlank(message = "성별은 필수입니다.")
            @Pattern(regexp = "MALE|FEMALE", message = "성별은 MALE 또는 FEMALE이어야 합니다.")
            String gender,

            @NotBlank(message = "생년월일은 필수입니다.")
            @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "생년월일은 YYYY-MM-DD 형식이어야 합니다.")
            String birth,

            @NotBlank(message = "지역은 필수입니다.")
            @Pattern(
                    regexp = "SEOUL|INCHEON|BUSAN|DAEGU|DAEJEON|GWANGJU|ULSAN|GANGNAM|SEOCHO|MAPO|HONGDAE|JONGNO|YONGSAN|SEONGDONG|SONGPA|YEONGDEUNGPO",
                    message = "유효하지 않은 지역입니다."
            )
            String address,

            @NotNull(message = "약관 동의 ID는 필수입니다.")
            Integer agreedId
    ) {
    }
}
