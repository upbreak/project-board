package com.fastcampus.projectboard.domain.Projection;

import com.fastcampus.projectboard.domain.UserAccount;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "withoutPassword", types = UserAccount.class)
public interface UserAccountProjection {
    Long getUserId();
    String getEmail();
    String getNickname();
    String getMemo();
    LocalDateTime getCreateAt();
    String getCreateBy();
    LocalDateTime getModifiedAt();
    String getModifiedBy();
}
