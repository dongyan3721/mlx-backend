package com.mlx.genshinimpactcookbook.service;

import com.mlx.genshinimpactcookbook.domain.Comments;
import com.mlx.genshinimpactcookbook.domain.CommentsQueryEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICommentsService {
    public int addNewCommentToComments(Comments comments);

    public List<Comments> selectComments(CommentsQueryEntity commentsQueryEntity);
}
