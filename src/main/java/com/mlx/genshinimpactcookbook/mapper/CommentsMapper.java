package com.mlx.genshinimpactcookbook.mapper;

import com.mlx.genshinimpactcookbook.domain.Comments;
import com.mlx.genshinimpactcookbook.domain.CommentsQueryEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentsMapper {
    @Insert("insert into comments (com_id, role_name, content, adder, address) VALUES (uuid(), #{roleName}, #{content}, #{adder}, #{address})")
    public int addNewCommentToComments(Comments comments);

    @Select("select com_id, role_name, content, adder, address from comments where role_name = #{roleName} limit #{startNum}, #{endNum}")
    public List<Comments> selectComments(CommentsQueryEntity commentsQueryEntity);
}
