package com.mlx.genshinimpactcookbook.controller;

import com.mlx.genshinimpactcookbook.domain.Comments;
import com.mlx.genshinimpactcookbook.domain.CommentsQueryEntity;
import com.mlx.genshinimpactcookbook.domain.common.HttpMessage;
import com.mlx.genshinimpactcookbook.domain.common.HttpQueryListResult;
import com.mlx.genshinimpactcookbook.domain.common.HttpStatus;
import com.mlx.genshinimpactcookbook.service.ICommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private ICommentsService commentsService;

    // TODO jwt recognize
    @PostMapping("/get-comments")
    public HttpMessage getRoleComments(CommentsQueryEntity commentsQueryEntity){
        List<Comments> comments = commentsService.selectComments(commentsQueryEntity);
        return new HttpQueryListResult(HttpStatus.SUCCESS, "success", comments, comments.size());
    }

    @PostMapping("/add-comment")
    // TODO jwt recognize
    public HttpMessage addRoleComment(Comments comments){
        commentsService.addNewCommentToComments(comments);
        return new HttpMessage(HttpStatus.SUCCESS, "success");
    }
}
