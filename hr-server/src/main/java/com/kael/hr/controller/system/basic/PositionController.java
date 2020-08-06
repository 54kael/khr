package com.kael.hr.controller.system.basic;

import com.kael.hr.entity.Position;
import com.kael.hr.exception.HrException;
import com.kael.hr.responst.Result;
import com.kael.hr.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author kael
 * @Description 职位管理
 * @Date 2020/8/6 0006
 */
@Slf4j
@Api(tags = "职位管理")
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    PositionService positionServiceImpl;

    @ApiOperation("获取所有职位")
    @GetMapping
    public Result getAllPosition() {
        List<Position> positionList = positionServiceImpl.findAllPositions();
        return Result.ok().data("positions",positionList);
    }

    @ApiOperation("添加、更新职位")
    @PostMapping
    public Result putPosition(@RequestBody @Validated Position position) {
        Integer posId = position.getId();
        if (posId==null) {
            log.info("职位id为空，添加职位");
            positionServiceImpl.savePosition(position);
        } else {
            log.info("更新职位id为{}的信息",posId);
            if (position.getCreateDate()!=null) {
                log.warn("参数时间不为空");
            }
            positionServiceImpl.updatePos(position);
        }
        return Result.ok();
    }

    @ApiOperation("删除职位")
    @DeleteMapping("/{id}")
    public Result deletePosition(@PathVariable("id")Integer id) {
        positionServiceImpl.deletePosById(id);
        return Result.ok();
    }
}
