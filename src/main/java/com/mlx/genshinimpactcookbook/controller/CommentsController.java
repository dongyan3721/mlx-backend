package com.mlx.genshinimpactcookbook.controller;

import com.mlx.genshinimpactcookbook.domain.Comments;
import com.mlx.genshinimpactcookbook.domain.CommentsQueryEntity;
import com.mlx.genshinimpactcookbook.domain.common.HttpMessage;
import com.mlx.genshinimpactcookbook.domain.common.HttpQueryListResult;
import com.mlx.genshinimpactcookbook.domain.common.HttpStatus;
import com.mlx.genshinimpactcookbook.service.ICommentsService;
import com.mlx.genshinimpactcookbook.utils.IpRegionConvertUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private ICommentsService commentsService;

    @PostMapping("/get-comments")
    public HttpMessage getRoleComments(@RequestBody CommentsQueryEntity commentsQueryEntity){
        List<Comments> comments = commentsService.selectComments(commentsQueryEntity);
        return new HttpQueryListResult(HttpStatus.SUCCESS, "success", comments, comments.size());
    }

    @PostMapping("/add-comment")
    public HttpMessage addRoleComment(@RequestBody Comments comments, HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        comments.setAddress(IpRegionConvertUtil.ipConvertToAddress(remoteAddr));
        commentsService.addNewCommentToComments(comments);
        return new HttpMessage(HttpStatus.SUCCESS, "success");
    }
}
