package edu.hut.aiassistant.controller;

import edu.hut.aiassistant.custom.service.TagServiceCustom;
import edu.hut.aiassistant.req.TagReq;
import edu.hut.aiassistant.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TagController
 * @Author 王海鑫
 * @Date 2024/11/11 下午4:50
 * @Description TODO
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagServiceCustom tagServiceCustom;

    @PostMapping("/addTag")
    public R addTag(@RequestBody TagReq tagReq){
        return tagServiceCustom.addTag(tagReq);
    }

    @GetMapping("/queryTagList")
    public R queryTagList(){
        return tagServiceCustom.queryTagList();
    }
}
