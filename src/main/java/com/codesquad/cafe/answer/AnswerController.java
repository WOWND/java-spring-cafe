package com.codesquad.cafe.answer;

import com.codesquad.cafe.answer.dto.AnswerDetail;
import com.codesquad.cafe.user.dto.LoginUser;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("")
    public List<AnswerDetail> getAnswers(@SessionAttribute(name = "loginUser", required = false) LoginUser loginUser,
                                         @PathVariable Long questionId) {
        return answerService.getAnswers(questionId, loginUser.getId());
    }

    @PostMapping("")
    public AnswerDetail createAnswer(@SessionAttribute(name = "loginUser", required = false) LoginUser loginUser,
                                     @PathVariable Long questionId, @ModelAttribute Answer answer) {
        return answerService.save(answer, loginUser.getId(), questionId);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@SessionAttribute(name = "loginUser", required = false) LoginUser loginUser,
                                             @PathVariable Long answerId) {
        answerService.delete(loginUser.getId(), answerId);
        return ResponseEntity.ok().build();
    }
}
