package com.demo.java.web.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.java.common.utils.UserUtils;
import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.service.MenuService;
import com.demo.java.web.common.controller.AbstractController;
import com.demo.java.web.common.response.ResponseContent;
import com.demo.java.web.menu.response.MenuEnum;

@Controller
@RequestMapping("menu")
public class MenuController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Resource
    MenuService menuService;

    /**
     * 进入到列表页面.<br/>
     * 
     * @author zhanghanlin
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("system:menu")
    @RequestMapping
    public ModelAndView main() {
        return new ModelAndView("menu/list");
    }

    /**
     * 进入到新增菜单/子菜单.<br/>
     * id == 0 新增一级菜单 .<br/>
     * id > 0 新增子菜单 .<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("system:menu")
    @RequestMapping("toAdd/{id}")
    public ModelAndView toAdd(@PathVariable Long id, HttpServletRequest request) {
        randomUUID(request);
        ModelAndView model = new ModelAndView("menu/input");
        if (id.longValue() > 0L) {
            Menu menu = menuService.get(id);
            model.addObject("parentId", menu.getId());
        }
        model.addObject("action", "/menu/add");
        model.addObject("submit", "新增");
        return model;
    }

    /**
     * 
     * 查看详情页面 .<br/>
     * 
     * @author zhanghanlin
     * @param id
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("system:menu")
    @RequestMapping("detail/{id}")
    public ModelAndView get(@PathVariable Long id) {
        ModelAndView model = new ModelAndView("menu/input");
        Menu menu = menuService.get(id);
        model.addObject("menu", menu);
        model.addObject("parentId", menu.getParentId());
        return model;
    }

    /**
     * 编辑详细信息页面.<br/>
     * 
     * @author zhanghanlin
     * @param id
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("system:menu")
    @RequestMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, HttpServletRequest request) {
        randomUUID(request);
        ModelAndView model = new ModelAndView("menu/input");
        Menu menu = menuService.get(id);
        model.addObject("menu", menu);
        model.addObject("parentId", menu.getParentId());
        model.addObject("action", "/menu/update");
        model.addObject("submit", "更新");
        return model;
    }

    /**
     * 获取所有菜单信息.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("system:menu")
    @RequestMapping("all")
    @ResponseBody
    public Map<Object, List<Menu>> all() {
        Map<Object, List<Menu>> map = new HashMap<Object, List<Menu>>();
        List<Menu> list = menuService.findAll();
        for (Menu m : list) {
            List<Menu> subList = map.get(m.getParentId().toString());
            if (subList == null) {
                subList = new ArrayList<Menu>();
            }
            subList.add(m);
            map.put(m.getParentId().toString(), subList);
        }
        return map;
    }

    /**
     * 左导航树.<br/>
     * 
     * @author zhanghanlin
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("tree")
    @ResponseBody
    public Map<Object, List<Menu>> tree() {
        Map<Object, List<Menu>> map = new HashMap<Object, List<Menu>>();
        List<Menu> list = menuService.findByUserMenu(UserUtils.getUserId());
        for (Menu m : list) {
            List<Menu> subList = map.get(m.getParentId().toString());
            if (subList == null) {
                subList = new ArrayList<Menu>();
            }
            subList.add(m);
            map.put(m.getParentId().toString(), subList);
        }
        return map;
    }

    /**
     * 新增操作.<br/>
     * 
     * @author zhanghanlin
     * @param menu
     * @param UUID
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("system:menu")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<Menu> add(Menu menu, String UUID, HttpServletRequest request) {
        if (!checkUUID(UUID, request)) {
            return new ResponseContent<Menu>(MenuEnum.ERROR);
        }
        int res = menuService.add(menu);
        if (res > 0) {
            return new ResponseContent<Menu>(MenuEnum.SUCCESS);
        }
        return new ResponseContent<Menu>(MenuEnum.ERROR);
    }

    /**
     * 更新操作.<br/>
     * 
     * @author zhanghanlin
     * @param menu
     * @param UUID
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("system:menu")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<Menu> update(Menu menu, String UUID, HttpServletRequest request) {
        if (!checkUUID(UUID, request)) {
            return new ResponseContent<Menu>(MenuEnum.ERROR);
        }
        int res = menuService.update(menu);
        if (res > 0) {
            return new ResponseContent<Menu>(MenuEnum.SUCCESS);
        }
        return new ResponseContent<Menu>(MenuEnum.ERROR);
    }

    /**
     * 更新菜单状态.<br/>
     * 
     * @author zhanghanlin
     * @param id
     * @param status
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("system:menu")
    @RequestMapping(value = "update/status", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<Menu> updateStatus(@RequestParam Long id, @RequestParam int status) {
        int res = menuService.updateStatus(id, status);
        if (res > 0) {
            return new ResponseContent<Menu>(MenuEnum.SUCCESS, menuService.get(id));
        }
        return new ResponseContent<Menu>(MenuEnum.ERROR);
    }
}