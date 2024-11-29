package com.metacoding.autoblog.board;

import com.metacoding.autoblog.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.http.HttpRequest;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final HttpSession session;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }
}
