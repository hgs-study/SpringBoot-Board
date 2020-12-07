package com.example.board.board_test.config.auth.dto;

import com.example.board.board_test.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class SessionUser {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}
