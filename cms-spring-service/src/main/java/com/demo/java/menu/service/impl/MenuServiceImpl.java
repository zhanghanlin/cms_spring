package com.demo.java.menu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.common.dict.Status;
import com.demo.java.menu.dao.MenuDao;
import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.service.MenuService;
import com.demo.java.utils.shiro.UserUtils;
import com.demo.java.utils.string.StringUtils;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuDao menuDao;

    @Override
    public List<Menu> findByParentId(Long parentId) {
        return menuDao.findByParentId(parentId);
    }

    @Override
    public int maxLevel() {
        return menuDao.maxLevel();
    }

    @Override
    public int add(Menu menu) {
        if (StringUtils.isBlank(menu.getLink())) {
            menu.setLink("###");
        }
        menu.setCreatedAt(new Date());
        menu.setCreatedBy(UserUtils.getUserName());
        String pcode = menu.getParentCode();
        String maxCode = menuDao.findMaxCodeByParentId(menu.getParentId());
        menu.setCode(nextCode(pcode, maxCode));
        menu.setStatus(Status.NORMAL);
        return menuDao.insert(menu);
    }

    @Override
    public List<String> findMenuNameByCode(String code) {
        List<String> param = new ArrayList<String>();
        List<String> list = new ArrayList<String>();
        list.add("CMS");
        for (int i = 1; i <= (code.length() / 3); i++) {
            param.add(code.substring(0, i * 3));
        }
        List<Map<String, Object>> objects = menuDao.findTreeNameByCode(param);
        if ((objects != null) && !objects.isEmpty()) {
            for (Map<String, Object> map : objects) {
                list.add(map.get("name").toString());
            }
        }
        return list;
    }

    static String nextCode(String pcode, String maxCode) {
        if (StringUtils.isBlank(maxCode)) {
            return pcode + "001";
        }
        String code = maxCode.substring(maxCode.length() - 3);
        Integer intCode = Integer.valueOf(code) + 1;
        code = pcode + StringUtils.leftPad(intCode.toString(), 3, "0");
        return code;
    }

    @Override
    public int updateStatus(Long id, int status) {
        Menu menu = new Menu();
        menu.setId(id);
        menu.setStatus(status);
        menu.setChangedAt(new Date());
        menu.setChangedBy(UserUtils.getUserName());
        return menuDao.update(menu);
    }

    @Override
    public int update(Menu menu) {
        menu.setChangedAt(new Date());
        menu.setChangedBy(UserUtils.getUserName());
        return menuDao.update(menu);
    }

    @Override
    public Menu get(Long id) {
        return menuDao.get(id);
    }

    @Override
    public List<Menu> list(int status) {
        return menuDao.list(status);
    }

    @Override
    public List<Menu> findByIds(Long[] menuIds) {
        return menuDao.findByIds(menuIds);
    }

    @Override
    public List<Menu> findByRoleId(Long roleId) {
        return menuDao.findByRoleId(roleId);
    }

    @Override
    public List<Menu> findByUserId(Long userId) {
        return menuDao.findByUserId(userId);
    }
}