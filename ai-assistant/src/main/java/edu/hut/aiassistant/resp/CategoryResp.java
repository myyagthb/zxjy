package edu.hut.aiassistant.resp;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @ClassName CategoryReq
 * @Author 王海鑫
 * @Date 2024/11/11 下午6:58
 * @Description TODO
 */
@Data
public class CategoryResp {
    private Integer categoryId;
    @NotBlank(message = "【种类名称】不能为空")
    private String categoryName;;
    private Integer parentId;
}
