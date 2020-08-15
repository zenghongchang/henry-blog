package com.tofba.blog.web.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tofba.blog.model.domain.Attachment;
import com.tofba.blog.model.domain.Gallery;
import com.tofba.blog.model.dto.JsonResult;
import com.tofba.blog.model.enums.ResponseStatusEnum;
import com.tofba.blog.service.AttachmentService;
import com.tofba.blog.service.GalleryService;

/**
 * <pre>
 *     图库API
 * </pre>
 *
 * @author : RYAN0UP
 * @date : 2018/6/6
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/galleries")
public class ApiGalleryController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private AttachmentService attachmentService;

    /**
     * 获取所有图片
     *
     * <p>
     *     result json:
     *     <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": [
     *         {
     *             "galleryId": ,
     *             "galleryName": "",
     *             "galleryDesc": "",
     *             "galleryDate": "",
     *             "galleryLocation": "",
     *             "galleryThumbnailUrl": "",
     *             "galleryUrl": ""
     *         }
     *     ]
     * }
     *     </pre>
     * </p>
     *
     * @return JsonResult
     */
    @GetMapping
    public JsonResult galleries(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "18") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "attachId");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Attachment> attachments = attachmentService.findAll(pageable);
        System.err.println(attachments.toString());
        return new JsonResult(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg(),attachments);

    }

    /**
     * 获取单张图片的信息
     *
     * <p>
     *     result json:
     *     <pre>
     * {
     *     "code": 200,
     *     "msg": "OK",
     *     "result": [
     *         {
     *             "galleryId": ,
     *             "galleryName": "",
     *             "galleryDesc": "",
     *             "galleryDate": "",
     *             "galleryLocation": "",
     *             "galleryThumbnailUrl": "",
     *             "galleryUrl": ""
     *         }
     *     ]
     * }
     *     </pre>
     * </p>
     *
     * @param id id
     * @return JsonResult
     */
    @GetMapping(value = "/{id}")
    public JsonResult galleries(@PathVariable("id") Long id) {
        Optional<Gallery> gallery = galleryService.findByGalleryId(id);
        if (gallery.isPresent()) {
            return new JsonResult(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg(), gallery.get());
        } else {
            return new JsonResult(ResponseStatusEnum.NOTFOUND.getCode(), ResponseStatusEnum.NOTFOUND.getMsg());
        }
    }




}
