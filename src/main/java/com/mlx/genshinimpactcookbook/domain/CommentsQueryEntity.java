package com.mlx.genshinimpactcookbook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsQueryEntity {
    private String RoleName;
    private int pageNum;
    private int startNum;
    private int endNum;
}
