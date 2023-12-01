package com.mlx.genshinimpactcookbook.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpMessage {
    protected int code;
    protected String msg;
}
