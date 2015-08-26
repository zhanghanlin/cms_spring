package com.demo.java.web.role.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.java.common.dict.Status;
import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.service.MenuService;
import com.demo.java.role.entity.Role;
import com.demo.java.role.entity.RoleMenu;
import com.demo.java.role.service.RoleMenuService;
import com.demo.java.role.service.RoleService;
import com.demo.java.web.common.controller.AbstractController;
import com.demo.java.web.common.response.ResponseContent;
import com.demo.java.web.common.vo.PageVo;
import com.demo.java.web.role.response.RoleEnum;

@Controller
@RequestMapping("role")
public class RoleController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

    @Resource
    RoleService roleService;

    @Resource
    MenuService menuService;

    @Resource
    RoleMenuService roleMenuService;

    /**
     * 跳转到角色列表页面.<br/>
     * 
     * @author zhanghanlin
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("toList")
    public ModelAndView toList() {
        return new ModelAndView("role/list");
    }

    /**
     * 跳转到添加角色页面.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("role/input");
        randomUUID(request);
        model.addObject("action", "/role/add");
        model.addObject("submit", "新增");
        return model;
    }

    /**
     * 跳转到编辑页面.<br/>
     * 
     * @author zhanghanlin
     * @param id
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("role/input");
        randomUUID(request);
        Role role = roleService.get(id);
        model.addObject("role", role);
        model.addObject("action", "/role/update");
        model.addObject("submit", "编辑");
        return model;
    }

    /**
     * 角色详情页.<br/>
     * 
     * @author zhanghanlin
     * @param id
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("detail/{id}")
    public ModelAndView get(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("role/input");
        randomUUID(request);
        Role role = roleService.get(id);
        model.addObject("role", role);
        return model;
    }

    /**
     * zTree导航树.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("ztree")
    @ResponseBody
    public JSONArray zTree(@RequestParam Long roleId, HttpServletRequest request) {
        JSONArray array = new JSONArray();
        List<Menu> list = menuService.list(Status.NORMAL);
        List<RoleMenu> rmList = roleMenuService.findByRoleId(roleId);
        for (Menu m : list) {
            JSONObject obj = new JSONObject();
            obj.put("id", m.getId());
            obj.put("pId", m.getParentId());
            obj.put("name", m.getName());
            if (m.getParentId().longValue() == 0L) {
                obj.put("open", true);
            }
            for (RoleMenu rm : rmList) {
                if (rm.getMenuId().longValue() == m.getId()) {
                    obj.put("checked", true);
                    break;
                }
            }
            array.add(obj);
        }
        return array;
    }

    /**
     * 角色列表数据.<br/>
     * 
     * @author zhanghanlin
     * @param curPage
     * @param pageSize
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("list")
    @ResponseBody
    public PageVo<Role> list(int curPage, int pageSize, HttpServletRequest request) {
        List<Role> result = roleService.pageList(curPage, pageSize);
        int totalResults = roleService.findPageListTotalCount();
        return new PageVo<Role>(curPage, pageSize, totalResults, result);
    }

    /**
     * 添加角色信息.<br/>
     * 
     * @author zhanghanlin
     * @param role
     * @param UUID
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<Role> add(Role role, String UUID, HttpServletRequest request) {
        if (!checkUUID(UUID, request)) {
            return new ResponseContent<Role>(RoleEnum.ERROR);
        }
        int res = roleService.add(role);
        if (res > 0) {
            return new ResponseContent<Role>(RoleEnum.SUCCESS);
        }
        return new ResponseContent<Role>(RoleEnum.ERROR);
    }

    /**
     * 根据角色信息.<br/>
     * 
     * @author zhanghanlin
     * @param role
     * @param UUID
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<Role> update(Role role, String UUID, HttpServletRequest request) {
        if (!checkUUID(UUID, request)) {
            return new ResponseContent<Role>(RoleEnum.ERROR);
        }
        int res = roleService.update(role);
        if (res > 0) {
            return new ResponseContent<Role>(RoleEnum.SUCCESS);
        }
        return new ResponseContent<Role>(RoleEnum.ERROR);
    }

    /**
     * 分配权限到角色.<br/>
     * 
     * @author zhanghanlin
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "menu2role", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<Role> updateMenu2Role(Long id, Long[] menuIds) {
        Role role = roleService.get(id);
        if (role == null) {
            return new ResponseContent<Role>(RoleEnum.ERROR);
        }
        List<Menu> menuList = menuService.findByIds(menuIds);
        if ((menuList == null) || menuList.isEmpty() || (menuList.size() != menuIds.length)) {
            return new ResponseContent<Role>(RoleEnum.ERROR);
        }
        int res = roleMenuService.updateMenu2Role(role.getId(), menuList);
        if (res > 0) {
            return new ResponseContent<Role>(RoleEnum.SUCCESS);
        }
        return new ResponseContent<Role>(RoleEnum.ERROR);
    }
}