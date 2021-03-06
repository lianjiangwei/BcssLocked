package com.pbc.controller;

import com.pbc.domainentity.qentity.goodsorder.AddGoodsOrder;
import com.pbc.service.GoodsOrderService;
import com.pbc.utils.Tools.BaseController;
import com.pbc.utils.exceptions.CustomException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by Alex on 2016/10/9.
 */
@Controller
@RequestMapping("goodsorder")
public class GoodsOrderController extends BaseController {
    private static Logger log = LogManager.getLogger(GoodsOrderController.class);

    @Autowired
    private GoodsOrderService goodsOrderService;

    /**
     * 接口地址示例：http://localhost:8080/BcssLocked/goodsorder/getAll
     * 获取所有订单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAll", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String getAll() {
        return toJSONString(goodsOrderService.getAll());
    }

    /**
     * 接口地址示例：http://localhost:8080/BcssLocked/goodsorder/get
     * 根据id查询订单信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String get(@PathVariable("id") int id) {
        log.debug("根据id查询订单信息，订单id为：" + toJSONString(id));
        return toJSONString(goodsOrderService.get(id));
    }

    /**
     * 接口地址示例：http://localhost:8080/BcssLocked/goodsorder/add
     * 创建订单
     *
     * @param o
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String add(@Valid @RequestBody AddGoodsOrder o, BindingResult result) throws Exception {
//        if (o.getUsername().isEmpty()) {
//            throw new CustomException("用户名不能为空!");//controller 层使用CustomException这个异常类；
//        }
        if (result.hasErrors()) {//如果没有通过,跳转提示
            Map<String, String> map = getErrors(result);
            log.error(map);
            throw new CustomException(map.toString());//controller 层使用CustomException这个异常类；
//            return ERROR;
        } else {
            //继续业务逻辑
            log.debug("添加订单，订单参数为：" + toJSONString(o));
            return goodsOrderService.add(o) == 1 ? SUCCESS : ERROR;
        }
    }

    /**
     * 接口地址示例：http://localhost:8080/BcssLocked/goodsorder/del
     * 删除订单信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "del/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String del(@PathVariable("id") int id) {
        log.debug("删除商品信息：" + toJSONString(id));
        return goodsOrderService.del(id) == 1 ? SUCCESS : ERROR;
    }

    /**
     * 接口地址示例：http://localhost:8080/BcssLocked/goodsorder/inst
     * 创建订单
     *
     * @param o
     * @return
     */
    @RequestMapping(value = "inst", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String inst(@Valid @RequestBody AddGoodsOrder o, BindingResult result) {
        if (result.hasErrors()) {//如果没有通过,跳转提示
            Map<String, String> map = getErrors(result);
            log.error(map);
            return ERROR;
        } else {
            //继续业务逻辑
            log.debug("添加订单，订单参数为：" + toJSONString(o));
            return goodsOrderService.inst(o) == 1 ? SUCCESS : ERROR;
        }
    }
}
