package studio.beita.hdxg.beitasystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.beita.hdxg.beitasystem.annotation.ControllerLog;
import studio.beita.hdxg.beitasystem.service.WebPortalsService;

/**
 * @author zr
 * @program: beitasystem
 * @Title: WebPortalsController
 * @package: studio.beita.hdxg.beitasystem.controller
 * @description: 门户网站模块控制器
 **/
@Api(value = "WebPortalsController", description = "WebPortalsController")
@RestController
public class WebPortalsController {

    @Autowired
    private WebPortalsService webPortalsService;

    @ApiOperation(value = "管理员增加新闻", notes = "admin insert examNews")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "etypeId", value = "新闻类别ID", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "content", value = "新闻内容", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "time", value = "新闻发布时间", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/admin/examNews/add")
    @ControllerLog(description = "管理员增加新闻")
    public ResponseEntity<?> insertExamNewsByAdmin(@RequestParam("etypeId")Integer etypeId, @RequestParam("title")String title, @RequestParam("content")String content, @RequestParam("time")String time) {
        if(webPortalsService.insertExamNewsByAdmin(etypeId,title,content,time,true,0)){
            return ResponseEntity.ok("增加新闻成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！增加新闻失败");
        }
    }

    @ApiOperation(value = "管理员增加新闻资源", notes = "admin insert examNews")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "新闻ID", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "address", value = "资源链接地址", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "savePath", value = "资源存储地址", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "createTime", value = "资源创建时间", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/admin/examNews/resource/add")
    @ControllerLog(description = "管理员增加新闻资源")
    public ResponseEntity<?> insertResourceByAdmin(@RequestParam("newsId")Integer newsId, @RequestParam("address")String address,
                                                   @RequestParam("savePath")String savePath, @RequestParam("createTime")String createTime) {
        if(webPortalsService.insertResourceByAdmin(newsId, address, savePath, createTime)){
            return ResponseEntity.ok("增加新闻资源成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！增加新闻资源失败");
        }
    }

    @ApiOperation(value = "管理员增加新闻类别", notes = "admin insert examNewsType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "考试新闻类别", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/admin/examNewsType/add")
    @ControllerLog(description = "管理员增加新闻类别")
    public ResponseEntity<?> insertExamNewsTypeByAdmin(@RequestParam("name")String name){
        if(webPortalsService.insertExamNewsTypeByAdmin(name)){
            return ResponseEntity.ok("增加新闻类别成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！增加新闻类别失败");
        }
    }

    @ApiOperation(value = "管理员更改新闻", notes = "admin updata examNews")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "新闻ID", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "etypeId", value = "新闻类别ID", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "content", value = "新闻内容", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "time", value = "新闻发布时间", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "isNew", value = "是否为最新新闻", dataType = "boolean", paramType = "query", required = true),
            @ApiImplicitParam(name = "visits", value = "考试新闻访问次数", dataType = "String", paramType = "query", required = true)
    })
    @PutMapping("/admin/examNews/updata")
    @ControllerLog(description = "管理员更改新闻")
    public ResponseEntity<?> changeExamNewsByAdmin(@RequestParam("newsId")Integer newsId,@RequestParam("etypeId")Integer etypeId, @RequestParam("title")String title,@RequestParam("content")String content, @RequestParam("time")String time,
                                                   @RequestParam("isNew")boolean isNew,  @RequestParam("visits")Integer visits) {
        if(webPortalsService.changeExamNewsByAdmin(newsId, etypeId,title, content, time, isNew, visits)){
            return ResponseEntity.ok("更改新闻成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙    ！更改新闻失败");
        }
    }

    @ApiOperation(value = "管理员更改新闻资源", notes = "admin updata examNews")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "新闻ID", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "address", value = "资源链接地址", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "savePath", value = "资源存储地址", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "createTime", value = "资源创建时间", dataType = "String", paramType = "query", required = true)
    })
    @PutMapping("/admin/examNews/resource/updata")
    @ControllerLog(description = "管理员更改新闻资源")
    public ResponseEntity<?> changeResourceByAdmin(@RequestParam("resourceId")Integer resourceId,@RequestParam("newsId")Integer newsId, @RequestParam("address")String address,
                                                   @RequestParam("savePath")String savePath, @RequestParam("createTime")String createTime) {
        if(webPortalsService.changeResourceByAdmin(resourceId,newsId, address, savePath, createTime)){
            return ResponseEntity.ok("更改新闻资源成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！更改新闻资源失败");
        }
    }

    @ApiOperation(value = "管理员更改新闻类别", notes = "admin updata examNewsType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "etypeId", value = "新闻类别ID", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "name", value = "考试新闻类别", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/admin/examNewsType/updata")
    @ControllerLog(description = "管理员更改新闻类别")
    public ResponseEntity<?> changeExamNewsTypeByAdmin(@RequestParam("etypeId")Integer etypeId,@RequestParam("name")String name){
        if(webPortalsService.changeExamNewsTypeByAdmin(etypeId,name)){
            return ResponseEntity.ok("更改新闻类别成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！更改新闻类别失败");
        }
    }

    @ApiOperation(value = "管理员删除新闻", notes = "admin delete examNews")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "新闻ID", dataType = "Integer", paramType = "query", required = true)
    })
    @DeleteMapping("/admin/examNews/delete")
    @ControllerLog(description = "管理员删除新闻")
    public ResponseEntity<?> deleteExamNewsByAdmin(@RequestParam("newsId")Integer newsId){
        if(webPortalsService.deleteExamNewsByAdmin(newsId)){
            return ResponseEntity.ok("删除新闻成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！删除新闻失败");
        }
    }

    @ApiOperation(value = "管理员删除新闻资源", notes = "admin delete resource")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "资源ID", dataType = "Integer", paramType = "query", required = true)
    })
    @DeleteMapping("/admin/examNews/resource/delete")
    @ControllerLog(description = "管理员删除新闻资源")
    public ResponseEntity<?> deleteResourceByAdmin(@RequestParam("newsId")Integer resourceId){
        if(webPortalsService.deleteResourceByAdmin(resourceId)){
            return ResponseEntity.ok("删除新闻资源成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！删除新闻资源失败");
        }
    }

    @ApiOperation(value = "管理员删除新闻类别", notes = "admin delete resource")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "etypeId", value = "新闻类别ID", dataType = "Integer", paramType = "query", required = true)
    })
    @DeleteMapping("/admin/examNewsType/delete")
    @ControllerLog(description = "管理员删除新闻类别")
    public ResponseEntity<?> deleteExamNewsTypeByAdmin(@RequestParam("etypeId")Integer etypeId){
        if(webPortalsService.deleteExamNewsTypeByAdmin(etypeId)){
            return ResponseEntity.ok("删除新闻类别成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！删除新闻类别失败");
        }
    }

    @ApiOperation(value = "游客获取所有新闻", notes = "user get allExamNews")
    @GetMapping("/user/examNews/get")
    @ControllerLog(description = "游客获取所有新闻")
    public ResponseEntity<?> getAllExamNews() {
        return ResponseEntity
                .ok(webPortalsService.getAllExamNews());
    }

    @ApiOperation(value = "游客根据新闻类别id查看新闻", notes = "user get examNews")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "etypeId", value = "新闻类别ID", dataType = "Integer", paramType = "query", required = true)
    })
    @GetMapping("/user/examNewsType/get")
    @ControllerLog(description = "游客根据新闻类别id查看新闻")
    public ResponseEntity<?> getExamNewsByExamTypeId(@RequestParam("etypeId")Integer etypeId){
        return ResponseEntity
                .ok(webPortalsService.getExamNewsByExamTypeId(etypeId));
    }

    @ApiOperation(value = "游客根据新闻id查看新闻资源", notes = "user get resource")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "新闻ID", dataType = "Integer", paramType = "query", required = true)
    })
    @GetMapping("/user/resource/get")
    @ControllerLog(description = "游客根据新闻id查看新闻资源")
    public ResponseEntity<?> getResourceByExamTypeId(@RequestParam("newsId")Integer newsId){
        return ResponseEntity
                .ok(webPortalsService.getResourceByNewsId(newsId));
    }

    @ApiOperation(value = "游客点击新闻增加阅读量，下载新闻资源", notes = "user updata visits")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "新闻ID", dataType = "Integer", paramType = "query", required = true)
    })
    @PostMapping("/user/examNewsVisits/updata")
    @ControllerLog(description = "游客点击新闻增加阅读量，下载新闻资源")
    public ResponseEntity<?> changeExamNewsVisits(@RequestParam("newsId")Integer newsId){
        return ResponseEntity
                .ok(webPortalsService.changeExamNewsVisits(newsId));
    }

    @ApiOperation(value = "更改是否为最新新闻状态", notes = "admin updata changeIsNewByAdmin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "新闻ID", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "isNew", value = "考试新闻是否为新", dataType = "boolean", paramType = "query", required = true)
    })
    @PostMapping("/user/admin/isNew/updata")
    @ControllerLog(description = "更改是否为最新新闻状态")
    public ResponseEntity<?> changeIsNewByAdmin(@RequestParam("newsId")Integer newsId, @RequestParam("isNew")boolean isNew){
        if(webPortalsService.changeIsNewByAdmin(newsId,isNew)){
            return ResponseEntity.ok("更改状态成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！更改状态失败");
        }
    }

    @ApiOperation(value = "模糊查询新闻标题", notes = "user search news")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "搜索", dataType = "String", paramType = "query", required = true),
    })
    @GetMapping("/user/examNewsTitle/get")
    @ControllerLog(description = "模糊查询新闻标题")
    public ResponseEntity<?> getExamNewsByTitel(@RequestParam("title")String title){
        return ResponseEntity
                .ok(webPortalsService.getExamNewsByTitel(title));
    }
}
