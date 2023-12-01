package com.mlx.genshinimpactcookbook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    private String comId;
    private String roleName;
    private String content;
    private String adder;
    private String address;
}
