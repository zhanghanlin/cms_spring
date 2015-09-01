package com.demo.java.web.user.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.java.common.dict.Status;
import com.demo.java.role.entity.Role;
import com.demo.java.role.service.RoleService;
import com.demo.java.user.entity.User;
import com.demo.java.user.entity.UserRole;
import com.demo.java.user.entity.UserVo;
import com.demo.java.user.service.UserRoleService;
import com.demo.java.user.service.UserService;
import com.demo.java.web.common.controller.AbstractController;
import com.demo.java.web.common.response.ResponseContent;
import com.demo.java.web.common.vo.PageVo;
import com.demo.java.web.role.response.RoleEnum;
import com.demo.java.web.user.response.UserEnum;

@ControllerAdvice
@RequestMapping("user")
public class UserController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserService userService;

    @Resource
    RoleService roleService;

    @Resource
    UserRoleService userRoleService;

    /**
     * 跳转到列表页.<br/>
     * 
     * @author zhanghanlin
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("system:user")
    @RequestMapping
    public ModelAndView main() {
        return new ModelAndView("user/list");
    }

    /**
     * 用户数据列表.<br/>
     * 
     * @author zhanghanlin
     * @param curPage
     * @param pageSize
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("system:user")
    @RequestMapping("list")
    @ResponseBody
    public PageVo<UserVo> list(int curPage, int pageSize) {
        List<UserVo> result = userService.findListByPage(curPage, pageSize);
        int totalResults = userService.getToalCount();
        return new PageVo<UserVo>(curPage, pageSize, totalResults, result);
    }

    /**
     * 用户详情页.<br/>
     * 
     * @author zhanghanlin
     * @param id
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("system:user")
    @RequestMapping("detail/{id}")
    public ModelAndView get(@PathVariable Long id, HttpServletRequest request) {
        randomUUID(request);
        ModelAndView model = new ModelAndView("user/input");
        User user = userService.get(id);
        model.addObject("user", user);
        return model;
    }

    /**
     * 用户角色列表.<br/>
     * 
     * @author zhanghanlin
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("system:user")
    @RequestMapping("roles")
    @ResponseBody
    public JSONArray roles(@RequestParam Long userId) {
        JSONArray array = new JSONArray();
        List<Role> list = roleService.findList(Status.NORMAL);
        List<UserRole> urList = userRoleService.findByUserId(userId);
        for (Role role : list) {
            JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(role));
            for (UserRole ur : urList) {
                if (ur.getRoleId().longValue() == role.getId().longValue()) {
                    obj.put("check", true);
                    break;
                }
            }
            array.add(obj);
        }
        return array;
    }

    /**
     * 分配角色到用户.<br/>
     * 
     * @author zhanghanlin
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("system:user")
    @RequestMapping(value = "role2user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<User> updateMenu2Role(Long id, Long[] roleIds) {
        User user = userService.get(id);
        if (user == null) {
            return new ResponseContent<User>(UserEnum.ERROR);
        }
        List<Role> roleList = roleService.findByIds(roleIds);
        if ((roleList == null) || roleList.isEmpty() || (roleList.size() != roleIds.length)) {
            return new ResponseContent<User>(RoleEnum.ERROR);
        }
        int res = userRoleService.updateRole2User(user.getId(), roleList);
        if (res > 0) {
            return new ResponseContent<User>(UserEnum.SUCCESS);
        }
        return new ResponseContent<User>(UserEnum.ERROR);
    }
}