package com.mlx.genshinimpactcookbook.service.impls;

import com.mlx.genshinimpactcookbook.domain.Comments;
import com.mlx.genshinimpactcookbook.domain.CommentsQueryEntity;
import com.mlx.genshinimpactcookbook.mapper.CommentsMapper;
import com.mlx.genshinimpactcookbook.service.ICommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService implements ICommentsService {

    @Autowired
    CommentsMapper commentsMapper;

    @Override
    public int addNewCommentToComments(Comments comments) {
        return commentsMapper.addNewCommentToComments(comments);
    }

    @Override
    public List<Comments> selectComments(CommentsQueryEntity commentsQueryEntity) {
        return commentsMapper.selectComments(commentsQueryEntity);
    }
}
