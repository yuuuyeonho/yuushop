package com.grammers.shop.member.presentation.dto;

import com.grammers.shop.member.application.dto.MemberCommand;

public record MemberRequest(
        String email,
        String name,
        String password,
        String phone,
        String saltKey,
        String flag
) {
    public MemberCommand toCommand(){
        return new MemberCommand(email, name, password, phone, saltKey, flag);
    }
}
