package com.demo.java.web.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.demo.java.common.dict.Status;
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
     * 进入到新增菜单/子菜单.<br/>
     * id == 0 新增一级菜单 .<br/>
     * id > 0 新增子菜单 .<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("toAdd/{id}")
    public ModelAndView toAdd(@PathVariable Long id, HttpServletRequest request) {
        randomUUID(request);
        ModelAndView model = new ModelAndView("menu/input");
        if (id.longValue() > 0L) {
            Menu menu = menuService.get(id);
            model.addObject("parentCode", menu.getCode());
            model.addObject("parentId", menu.getId());
            List<String> list = menuService.findMenuNameByCode(menu.getCode());
            model.addObject("menuNames", list);
        }
        model.addObject("action", "/menu/add");
        model.addObject("submit", "新增");
        return model;
    }

    /**
     * 进入到列表页面.<br/>
     * 
     * @author zhanghanlin
     * @param UUID
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("toList")
    public ModelAndView toList(String UUID, HttpServletRequest request) {
        return new ModelAndView("menu/list");
    }

    /**
     * 
     * 查看详情页面 .<br/>
     * 
     * @author zhanghanlin
     * @param id
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("detail/{id}")
    public ModelAndView get(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("menu/input");
        Menu menu = menuService.get(id);
        model.addObject("menu", menu);
        List<String> list = menuService.findMenuNameByCode(menu.getParentCode());
        model.addObject("menuNames", list);
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
    @RequestMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, HttpServletRequest request) {
        randomUUID(request);
        ModelAndView model = new ModelAndView("menu/input");
        Menu menu = menuService.get(id);
        model.addObject("menu", menu);
        model.addObject("parentCode", menu.getParentCode());
        List<String> list = menuService.findMenuNameByCode(menu.getParentCode());
        model.addObject("menuNames", list);
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
    @RequestMapping("all")
    @ResponseBody
    public Map<Object, List<Menu>> allTree(HttpServletRequest request) {
        Map<Object, List<Menu>> map = new HashMap<Object, List<Menu>>();
        List<Menu> list = menuService.list(Status.ALL);
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
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("tree")
    @ResponseBody
    public Map<Object, List<Menu>> tree(HttpServletRequest request) {
        Map<Object, List<Menu>> map = new HashMap<Object, List<Menu>>();
        List<Menu> list = menuService.list(Status.NORMAL);
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
     * 根据父类Code查询子菜单列表.<br/>
     * 
     * @author zhanghanlin
     * @param pcode
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("p/{parentId}")
    @ResponseBody
    public List<Menu> getMenuByParent(@PathVariable Long parentId, HttpServletRequest request) {
        return menuService.findByParentId(parentId);
    }

    /**
     * 获取菜单最深级别.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("maxLevel")
    @ResponseBody
    public ResponseContent<Integer> maxLevel(HttpServletRequest request) {
        return new ResponseContent<Integer>(MenuEnum.SUCCESS, menuService.maxLevel());
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
    @RequestMapping(value = "update/status", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<Menu> updateStatus(@RequestParam Long id, @RequestParam int status, HttpServletRequest request) {
        int res = menuService.updateStatus(id, status);
        if (res > 0) {
            return new ResponseContent<Menu>(MenuEnum.SUCCESS, menuService.get(id));
        }
        return new ResponseContent<Menu>(MenuEnum.ERROR);
    }
}