package com.tofba.blog.web.controller.admin;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tofba.blog.model.domain.Category;
import com.tofba.blog.model.dto.JsonResult;
import com.tofba.blog.model.enums.ResultCodeEnum;
import com.tofba.blog.service.CategoryService;
import com.tofba.blog.utils.LocaleMessageUtil;

@Slf4j
@Controller
@RequestMapping(value = "/admin/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private LocaleMessageUtil localeMessageUtil;
    
    /**
     * 查询所有分类并渲染category页面
     *
     * @return 模板路径admin/admin_category
     */
    @GetMapping
    public String categories() {
        return "admin/admin_category";
    }
    
    /**
     * 新增/修改分类目录
     *
     * @param category category对象
     * @return 重定向到/admin/category
     */
    @PostMapping(value = "/save")
    public String saveCategory(@ModelAttribute Category category) {
        try {
            categoryService.save(category);
        } catch (Exception e) {
            log.error("Modify category failed: {}", e.getMessage());
        }
        return "redirect:/admin/category";
    }
    
    /**
     * 验证分类目录路径是否已经存在
     *
     * @param cateUrl 分类路径
     * @return JsonResult
     */
    @GetMapping(value = "/checkUrl")
    @ResponseBody
    public JsonResult checkCateUrlExists(@RequestParam("cateUrl") String cateUrl) {
        Category category = categoryService.findByCateUrl(cateUrl);
        if (null != category) {
            return new JsonResult(ResultCodeEnum.FAIL.getCode(), localeMessageUtil.getMessage("code.admin.common.url-is-exists"));
        }
        return new JsonResult(ResultCodeEnum.SUCCESS.getCode(), "");
    }
    
    /**
     * 处理删除分类目录的请求
     *
     * @param cateId cateId
     * @return 重定向到/admin/category
     */
    @GetMapping(value = "/remove")
    public String removeCategory(@RequestParam("cateId") Long cateId) {
        try {
            categoryService.remove(cateId);
        } catch (Exception e) {
            log.error("Delete category failed: {}", e.getMessage());
        }
        return "redirect:/admin/category";
    }
    
    /**
     * 跳转到修改页面
     *
     * @param cateId cateId
     * @param model model
     * @return 模板路径admin/admin_category
     */
    @GetMapping(value = "/edit")
    public String toEditCategory(Model model, @RequestParam("cateId") Long cateId) {
        Optional<Category> category = categoryService.findByCateId(cateId);
        model.addAttribute("updateCategory", category.get());
        return "admin/admin_category";
    }
}
