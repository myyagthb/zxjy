package edu.hut.aiassistant.controller;

import edu.hut.aiassistant.custom.service.CategoryServiceCustom;
import edu.hut.aiassistant.req.CategoryReq;
import edu.hut.aiassistant.resp.R;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CategoryController
 * @Author 王海鑫
 * @Date 2024/11/11 下午7:07
 * @Description TODO
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceCustom  categoryServiceCustom;

    @PostMapping("/addCategory")
    public R addCategory(@Valid @RequestBody CategoryReq categoryReq) {
        return categoryServiceCustom.addCategory(categoryReq);
    }

    @GetMapping("/queryCategoryList")
    public R queryCategoryList() {
        return categoryServiceCustom.queryCategoryList();
    }
}
