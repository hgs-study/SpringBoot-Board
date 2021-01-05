package com.example.board.board_test.web;

import com.example.board.board_test.config.auth.LoginUser;
import com.example.board.board_test.config.auth.dto.SessionUser;
import com.example.board.board_test.service.posts.PostsService;
import com.example.board.board_test.web.dto.PostsResponseDto;
import com.example.board.board_test.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class indexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){

        model.addAttribute("posts",postsService.findAllDese());

//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); ->@LoginUser SessionUser user로 대체 d

        if(user != null){
            model.addAttribute("userName",user.getName());
            model.addAttribute("email",user.getEmail());
            model.addAttribute("picture",user.getPicture());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}
