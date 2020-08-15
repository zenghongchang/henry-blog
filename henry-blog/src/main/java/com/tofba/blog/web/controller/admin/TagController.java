package com.tofba.blog.web.controller.admin;

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

import com.tofba.blog.model.domain.Tag;
import com.tofba.blog.model.dto.JsonResult;
import com.tofba.blog.model.enums.ResultCodeEnum;
import com.tofba.blog.service.TagService;
import com.tofba.blog.utils.LocaleMessageUtil;

@Slf4j
@Controller
@RequestMapping(value = "/admin/tag")
public class TagController {
    
    @Autowired
    private TagService tagService;
    
    @Autowired
    private LocaleMessageUtil localeMessageUtil;
    
    /**
     * 渲染标签管理页面
     *
     * @return 模板路径admin/admin_tag
     */
    @GetMapping
    public String tags() {
        return "admin/admin_tag";
    }
    
    /**
     * 新增/修改标签
     *
     * @param tag tag
     * @return 重定向到/admin/tag
     */
    @PostMapping(value = "/save")
    public String saveTag(@ModelAttribute Tag tag) {
        try {
            tagService.save(tag);
        } catch (Exception e) {
            log.error("Add/modify tag failed: {}", e.getMessage());
        }
        return "redirect:/admin/tag";
    }
    
    /**
     * 验证是否存在该路径
     *
     * @param tagUrl 标签路径名
     * @return true：不存在，false：已存在
     */
    @GetMapping(value = "/checkUrl")
    @ResponseBody
    public JsonResult checkTagUrlExists(@RequestParam("tagUrl") String tagUrl) {
        Tag tag = tagService.findByTagUrl(tagUrl);
        if (null != tag) {
            return new JsonResult(ResultCodeEnum.FAIL.getCode(), localeMessageUtil.getMessage("code.admin.common.url-is-exists"));
        }
        return new JsonResult(ResultCodeEnum.SUCCESS.getCode(), "");
    }
    
    /**
     * 处理删除标签的请求
     *
     * @param tagId 标签编号
     * @return 重定向到/admin/tag
     */
    @GetMapping(value = "/remove")
    public String removeTag(@RequestParam("tagId") Long tagId) {
        try {
            tagService.remove(tagId);
        } catch (Exception e) {
            log.error("Failed to delete tag: {}", e.getMessage());
        }
        return "redirect:/admin/tag";
    }
    
    /**
     * 跳转到修改标签页面
     *
     * @param model model
     * @param tagId 标签编号
     * @return 模板路径admin/admin_tag
     */
    @GetMapping(value = "/edit")
    public String toEditTag(Model model, @RequestParam("tagId") Long tagId) {
        Tag tag = tagService.findByTagId(tagId).get();
        model.addAttribute("updateTag", tag);
        return "admin/admin_tag";
    }
}
