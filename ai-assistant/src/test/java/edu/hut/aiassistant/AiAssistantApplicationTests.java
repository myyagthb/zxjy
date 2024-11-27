package edu.hut.aiassistant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hut.aiassistant.custom.service.CourseServiceCustom;
import edu.hut.aiassistant.custom.service.TagServiceCustom;
import edu.hut.aiassistant.custom.service.UserServiceCustom;
import edu.hut.aiassistant.generator.domain.Course;
import edu.hut.aiassistant.generator.domain.User;
import edu.hut.aiassistant.generator.mapper.CourseMapper;
import edu.hut.aiassistant.generator.service.UserService;
import edu.hut.aiassistant.req.SearchParamsReq;
import edu.hut.aiassistant.req.TagReq;
import edu.hut.aiassistant.req.UserInfoReq;
import edu.hut.aiassistant.req.UserReq;
import edu.hut.aiassistant.resp.R;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class AiAssistantApplicationTests {

    @Test
    void contextLoads() {

    }


    @Autowired
    private UserService userService;

//    @Test
//    void addUser() {
//
//        User user = new User();
//        user.setUserId(10001L);
//        user.setPhone("123");
//        user.setPassword("123");
//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());
//        userService.save(user);
//    }

    @Autowired
    private UserServiceCustom userServiceCustom;


    @Test
    void testRegister(){
        UserReq userReq = new UserReq();
        userReq.setMobile("14727619000");
        userReq.setPassword("123456");

        R register = userServiceCustom.register(userReq);
        System.out.println(register);
    }


    @Test
    void testLogin(){
        UserReq userReq = new UserReq();
        userReq.setMobile("14727619000");
        userReq.setPassword("123456");

        R register = userServiceCustom.login(userReq);
        System.out.println(register);
    }

    @Test
    void testUpdateUserInfo(){
        UserInfoReq newUser = new UserInfoReq();
        newUser.setUserId(1854788583587618818L);
        newUser.setEducateStage("");
        newUser.setNickname("");
        R r = userServiceCustom.saveOrUpdateUserInfo(newUser);
        System.out.println(r);
    }


    @Autowired
    private TagServiceCustom tagServiceCustom;

    @Test
    void testAddTag(){
        TagReq tagReq = new TagReq();
        tagReq.setTagName("计算机");
        R r = tagServiceCustom.addTag(tagReq);
        System.out.println(r);
    }

    @Test
    void testQueryTagList(){
        R r = tagServiceCustom.queryTagList();
        System.out.println(r);
    }

    @Autowired
    private CourseMapper courseMapper;

    @Test
    void testAddCourse(){
        Course course = new Course();
        course.setCourseCategory("11");
        course.setCourseName("物理");
        courseMapper.insert(course);
    }


    //测试课程搜索
    @Autowired
    private CourseServiceCustom courseServiceCustom;

    /**
     * 改用户有记录的情况
     */
    @Test
    void testCourseHasRecord(){
        SearchParamsReq searchParamsReq = new SearchParamsReq();
        searchParamsReq.setSearchText(null);
        searchParamsReq.setSearchText(" 433  ");
        searchParamsReq.setCurrentPage(1);
        searchParamsReq.setPageSize(5);
        searchParamsReq.setUserId(1860578347017449473L);

        IPage<Course> courseIPage = courseServiceCustom.searchCourseBySearchText(searchParamsReq);
        System.out.println(courseIPage);
    }

    /**
     * 改用户有无记录的情况
     */
    @Test
    void testCourseNotHasRecord(){
        SearchParamsReq searchParamsReq = new SearchParamsReq();
        searchParamsReq.setSearchText(null);
        searchParamsReq.setCurrentPage(1);
        searchParamsReq.setPageSize(5);
        searchParamsReq.setUserId(1860578347017449474L);

        IPage<Course> courseIPage = courseServiceCustom.searchCourseBySearchText(searchParamsReq);
        System.out.println(courseIPage);
    }

}
