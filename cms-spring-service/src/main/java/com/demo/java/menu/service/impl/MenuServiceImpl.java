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
import com.demo.java.menu.utils.MenuNode;
import com.demo.java.user.entity.User;
import com.demo.java.utils.string.StringUtils;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuDao menuDao;

    @Override
    public MenuNode menuTree(int status) {
        List<Menu> list = new ArrayList<Menu>();
        if (Status.ALL == status) {
            list = menuDao.list();
        } else {
            list = menuDao.list(status);
        }
        MenuNode tree = new MenuNode();
        for (Menu m : list) {
            MenuNode t = new MenuNode(m);
            tree.addChildNode(t);
        }
        return tree;
    }

    @Override
    public List<Menu> getMenuByParentCode(String code) {
        return menuDao.getByParentCode(code);
    }

    @Override
    public int maxLevel() {
        return menuDao.maxLevel();
    }

    @Override
    public int add(Menu menu, User u) {
        if (StringUtils.isBlank(menu.getLink())) {
            menu.setLink("###");
        }
        menu.setCreatedAt(new Date());
        menu.setCreatedBy(u.getId().toString());
        String pcode = menu.getParentCode();
        String maxCode = menuDao.getMaxCodeByParentCode(pcode);
        menu.setCode(nextCode(pcode, maxCode));
        menu.setStatus(Status.NORMAL);
        menu.setWeight(1);
        menu.setVersion(1);
        return menuDao.save(menu);
    }

    @Override
    public List<String> getTreesNameByCode(String code) {
        List<String> param = new ArrayList<String>();
        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= (code.length() / 3); i++) {
            param.add(code.substring(0, i * 3));
        }
        List<Map<String, Object>> objects = menuDao.getTreesNameByCode(param);
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
    public int updateStatus(Long id, int status, User u) {
        Menu menu = new Menu();
        menu.setId(id);
        menu.setStatus(status);
        menu.setChangedAt(new Date());
        menu.setChangedBy(u.getUserName());
        return menuDao.update(menu);
    }

    @Override
    public int update(Menu menu, User u) {
        menu.setChangedAt(new Date());
        menu.setChangedBy(u.getUserName());
        return menuDao.update(menu);
    }

    @Override
    public Menu get(Long id) {
        return menuDao.get(id);
    }
}